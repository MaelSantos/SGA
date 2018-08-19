package br.com.sga.controle;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoDocumento;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControleDocumentos extends Controle {

	@FXML
	private ComboBox<TipoDocumento> cbxTipo;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnGerar;

	@FXML
	private DatePicker tfdDe;

	@FXML
	private DatePicker tfdAte;

	@FXML
	private ProgressIndicator pgiDados;

	private String arquivo;
	private IFachada fachada;
	private IDaoCommun daoCommun;
	private List<? extends Object> list;

	private double porcentagem = 0;
	private Service service;

	private Funcionario funcionario;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;

		}

	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();

		cbxTipo.getItems().setAll(TipoDocumento.values());

		service = new Service() {

			@Override
			protected Task createTask() {

				return new Task() {

					public void update()
					{
						updateMessage("Gerando Arquivo...");
						porcentagem += 16.666666667;
						updateProgress(porcentagem, 100);
					}

					@Override
					protected Object call() throws Exception {
						updateTitle("Preparando Arquivo...");

						//gerando o jasper design
						InputStream inputStream = getClass().getClassLoader().getResourceAsStream(arquivo);
						update();

						JasperDesign desenho = JRXmlLoader.load(inputStream);
						update();

						//compila o relatório
						JasperReport relatorio = JasperCompileManager.compileReport(desenho);
						update();

						/* Convert List to JRBeanCollectionDataSource */
						JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
						update();

						/* Map to hold Jasper report Parameters */
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put("ItemDataSource", itemsJRBean);
						update();

						/* Using compiled version(.jasper) of Jasper report to generate PDF */
						JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parameters, itemsJRBean);
						update();

						JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
						jasperViewer.setZoomRatio(0.75F);
						jasperViewer.setLocationRelativeTo(null);
						jasperViewer.show();		
						//		JasperViewer.viewReport(jasperPrint);

						return null;
					}

					@Override
					protected void succeeded() {
						super.succeeded();
						porcentagem = 0;
					}
				};
			}
		};

		pgiDados.progressProperty().bind(service.progressProperty());
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnGerar)
		{
			Log log = null;
			try {
				if(list != null && arquivo != null && !(list.isEmpty()))
				{
					service.restart();
//					gerarDocumento(list, arquivo);					
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Documento: ", StatusLog.COLCLUIDO);
				}
				else
				{
					Alerta.getInstance().showMensagem("Erro!", "Erro Ao Gerar Documento!!!", "Verifique Se Todos Os Dados Estão Corretos");
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Documento: Sem Resultados", StatusLog.SEM_RESULTADOS);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Documento: ", StatusLog.ERRO);
				Alerta.getInstance().showMensagem("Erro!", "Erro Ao Gerar Documento!!!", "Verifique Se Todos Os Dados Estão Corretos");
			}

			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}

		}
		if(obj == btnBuscar)
		{
			Log log;
			try {

				list = carregarLista(cbxTipo.getValue());
				if(!list.isEmpty())
				{
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido!","Dados Carregados Com Sucesso!!!", "Pronto Para Gerar Arquivo");
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar: "+cbxTipo.getValue(), StatusLog.COLCLUIDO);
				}
				else
				{
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Não Encontrado!","Dados Não Encontrados!!!", "Tente Novamente Procurando Por Outros Dados!!!");
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar: "+cbxTipo.getValue()+" - Sem Resultados", StatusLog.SEM_RESULTADOS);
				}
			} catch (Exception e) {
				Alerta.getInstance().showMensagem("Erro!","Erro ao Carregar Arquivos!!!", e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar: "+cbxTipo.getValue()+" - Erro", StatusLog.ERRO);
			}

			try {
				fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}

		}
		if(obj == cbxTipo)
		{
			if(cbxTipo.getValue() == TipoDocumento.DESPESA)
				arquivo = "Despesas.jrxml";
			if(cbxTipo.getValue() == TipoDocumento.RECEITA)
				arquivo = "Receitas.jrxml";
		}

	}

	public List<? extends Object> carregarLista(TipoDocumento documento) throws ValidacaoException
	{
		try {
			switch (documento) {
			case DESPESA:
				return daoCommun.getDespesaPorIntervalo(Date.from(tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
						Date.from(tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			case RECEITA:
				return daoCommun.getReceitaPorIntervalo(Date.from(tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
						Date.from(tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			default:
			}			
		}		
		catch (NullPointerException e) {
			throw new ValidacaoException("Dados Incompletos!!! - Informe Todos os Dados Necessarios!!!");
		}catch (DaoException e) {
			throw new ValidacaoException(e.getMessage());
		}

		throw new ValidacaoException("Dados Não Encontrados!!! - Tente Procurar Informando Outro Dado");					
	}

	@SuppressWarnings("deprecation")
	public void gerarDocumento(List<? extends Object> list, String layout) throws JRException, FileNotFoundException {

		//gerando o jasper design
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(layout);

		System.out.println(inputStream);
		JasperDesign desenho = JRXmlLoader.load(inputStream);

		//compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		/* Convert List to JRBeanCollectionDataSource */
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);

		/* Map to hold Jasper report Parameters */
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ItemDataSource", itemsJRBean);

		/* Using compiled version(.jasper) of Jasper report to generate PDF */
		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parameters, itemsJRBean);

		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
		jasperViewer.setZoomRatio(0.75F);
		jasperViewer.setLocationRelativeTo(null);
		jasperViewer.show();		
		//		JasperViewer.viewReport(jasperPrint);

	}

}
