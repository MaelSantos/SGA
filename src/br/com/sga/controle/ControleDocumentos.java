package br.com.sga.controle;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoDocumento;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
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
	private ProgressIndicator pgiDados;

	@FXML
	private Button btnGerar;

	@FXML
	private DatePicker tfdDe;

	@FXML
	private DatePicker tfdAte;

	private String arquivo;
	private IFachada fachada;
	private IDaoCommun daoCommun;
	private List<? extends Object> list;

	@Override
	public void atualizar(Tela tela, Object object) {


	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();

		cbxTipo.getItems().setAll(TipoDocumento.values());

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnGerar)
		{
			try {
				if(list != null && arquivo != null)
					gerarDocumento(list, arquivo);
				else
					Alerta.getInstance().showMensagem("Erro!", "Erro Ao Gerar Documento!!!", "Verifique Se Todos Os Dados Estão Corretos");
			} catch (Exception e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Erro!", "Erro Ao Gerar Documento!!!", "Verifique Se Todos Os Dados Estão Corretos");
			}
		}
		if(obj == btnBuscar)
		{
			try {
				if(cbxTipo.getValue() == TipoDocumento.DESPESA)
				{
					list = daoCommun.getDespesaPorIntervalo(Date.from(tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
							Date.from(tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

					Alerta.getInstance().showMensagem("Concluido", "Dados Carregados Com Sucesso!!!","");
				}
				else if(cbxTipo.getValue() == TipoDocumento.RECEITA)
				{
					list = daoCommun.getReceitaPorIntervalo(Date.from(tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
							Date.from(tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

					Alerta.getInstance().showMensagem("Concluido", "Dados Carregados Com Sucesso!!!","");
				}
				else
					Alerta.getInstance().showMensagem(AlertType.INFORMATION,"Ação Nescessaria!!!", "Escolha Um Tipo De Documento","");

			} catch (Exception e) {
				Alerta.getInstance().showMensagem("Erro!","Erro ao Carregar Arquivos!!!", e.getMessage());
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
