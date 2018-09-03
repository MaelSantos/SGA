package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.StatusAudiencia;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoAudiencia;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleCadastroAudiencia extends Controle {

	@FXML
	private Label lblProcesso;
	
	@FXML
	private ComboBox<TipoAudiencia> cbxTipo;
	
	@FXML
	private ComboBox<StatusAudiencia> cbxStatus;

	@FXML
	private TextField tfdVara;

	@FXML
	private TextField tfdOrgao;

	@FXML
	private DatePicker tfdData;

	@FXML
	private Button btnCadastrar;

	@FXML
    private Button btnVoltar;
	
	private IFachada fachada;
	private IDaoCommun daoCommun;
	private Audiencia audiencia;
	private Notificacao notificacao;
	private Funcionario funcionario;
	private Processo processo;	
	
	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Processo) {
			processo = (Processo) object;
			lblProcesso.setText("Processo: "+processo.toString());
			
		}
		if (object instanceof Funcionario) {
			this.funcionario = (Funcionario) object;
			
		}

	}

	@Override
	public void init() {
		
		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();
		
		cbxStatus.getItems().addAll(StatusAudiencia.values());
		cbxTipo.getItems().addAll(TipoAudiencia.values());

		cbxStatus.setButtonCell(new ListCell<StatusAudiencia>() {
	        @Override
	        protected void updateItem(StatusAudiencia item, boolean empty) {
	            super.updateItem(item, empty) ;
	            if (empty || item == null) {
	                setText("Status");
	            } else {
	                setText(item.toString());
	            }
	        }
	    });
		
		cbxTipo.setButtonCell(new ListCell<TipoAudiencia>() {
	        @Override
	        protected void updateItem(TipoAudiencia item, boolean empty) {
	            super.updateItem(item, empty) ;
	            if (empty || item == null) {
	                setText("Tipo de AudiÍncia");
	            } else {
	                setText(item.toString());
	            }
	        }
	    });
		
	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == btnCadastrar)
		{
			Log log;
			try {
				Audiencia audiencia = criarAudiencia();
				if(processo.getId() != null)
				{
					daoCommun.salvarAudiencia(audiencia, audiencia.getProcesso().getId());
					
					notificacao = new Notificacao(TipoNotificacao.AUDIENCIA, Prioridade.BAIXA,
							audiencia.getProcesso().getDescricao(), Andamento.PENDENTE, audiencia.getData_audiencia());
					
					fachada.salvarEditarNotificacao(notificacao);
					App.notificarOuvintes(Tela.CADASTRO_AUDIENCIA, notificacao);
					App.notificarOuvintes(Tela.CADASTRO_AUDIENCIA, audiencia);
				}
				
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Salvo", "", "Audiencia Cadastrada Com Sucesso");

				limparCampos();
				
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova AudiÍncia: "+audiencia.getTipo(), StatusLog.CONCLUIDO);
				
			} catch (ParseException | DaoException | BusinessException e) {
				e.printStackTrace();
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova AudiÍncia: Erro", StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Salvar Audiencia", e.getMessage());
			}
			
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		if(obj == btnVoltar)
		{
			if(processo.getId() != null)
				App.notificarOuvintes(Tela.DETALHES_PROCESSO);
			else
				App.notificarOuvintes(Tela.CADASTRO_PROCESSO);
		}
	}

	private void limparCampos() {
		
		tfdData.getEditor().setText("");
		tfdOrgao.setText("");
		tfdVara.setText("");

		cbxStatus.getSelectionModel().clearSelection();
		cbxTipo.getSelectionModel().clearSelection();
		
	}

	private Audiencia criarAudiencia() throws ParseException {
		
		audiencia = new Audiencia();
		audiencia.setProcesso(processo);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date data = df.parse(tfdData.getEditor().getText());
		audiencia.setData_audiencia(data);

		audiencia.setStatus(cbxStatus.getValue());
		audiencia.setOrgao(tfdOrgao.getText().trim());
		audiencia.setTipo(cbxTipo.getValue());
		audiencia.setVara(tfdVara.getText().trim());
		
		if(processo.getAudiencias() != null)
			processo.getAudiencias().add(audiencia);
		else
		{
			processo.setAudiencias(new ArrayList<>());
			processo.getAudiencias().add(audiencia);
		}
		
		return audiencia;
	}

}
