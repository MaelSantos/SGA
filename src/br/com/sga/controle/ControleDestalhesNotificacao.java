package br.com.sga.controle;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleDestalhesNotificacao extends Controle{

    @FXML
    private TextField tfdDescricao;

    @FXML
    private DatePicker tfdData;

    @FXML
    private ComboBox<TipoNotificacao> cbxTipo;

    @FXML
    private ComboBox<Andamento> cbxEstado;

    @FXML
    private ComboBox<Prioridade> cbxPrioridade;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSalvar;

    private IFachada fachada;
    private Funcionario funcionario;
    private Notificacao notificacao;
    private boolean voltar;
    
    @FXML
    public void actionButton(ActionEvent event) {

    	Object obj = event.getSource();
    	
    	if(obj == btnSalvar)
    	{
    		Log log;
    		try {
    			
    			mudarNotificacao();
				fachada.salvarEditarNotificacao(notificacao);
				
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Notificação Atualizada", "");
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Notificação "+notificacao, StatusLog.CONCLUIDO);
				
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Atualizar Notificação!!!", e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Notificação "+notificacao, StatusLog.ERRO);
			}
    		
    		try {
    			if(log != null)
    				fachada.salvarEditarLog(log);
    		} catch (BusinessException e) {
    			e.printStackTrace();
    		}

    	}
    	
    	else if(obj == btnVoltar)
    	{
    		if(voltar)
    			App.notificarOuvintes(Tela.AGENDA, notificacao);
    		else
    			App.notificarOuvintes(Tela.HOME, notificacao);
    	}
    	
    }

    @Override
    public void atualizar(Tela tela, Object object) {


    	if (object instanceof Funcionario) {
    		funcionario = (Funcionario) object;
    	}
    	else if(tela == Tela.DETALHES_NOTIFICACAO)
    	{
    		if (object instanceof Notificacao) 
    		{
    			notificacao = (Notificacao) object;
    			voltar = true;
    			mudarCampos();
    		}
    		else if (object instanceof NotificacaoAdapter) {
    			NotificacaoAdapter adapter = (NotificacaoAdapter) object;
    			try {
    				notificacao = fachada.buscarNotificacaoPorId(adapter.getId());
    				voltar = false;
    				mudarCampos();
    			} catch (BusinessException e) {
    				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro com a Notificação!!!", e.getMessage());
    				e.printStackTrace();
    			}
    		}

    	}

	}

	@Override
	public void init() {
		
		fachada = Fachada.getInstance();
		
		cbxEstado.getItems().setAll(Andamento.values());
		cbxPrioridade.getItems().setAll(Prioridade.values());
		cbxTipo.getItems().setAll(TipoNotificacao.values());
		
		cbxEstado.setButtonCell(new ListCell<Andamento>() {
			@Override
			protected void updateItem(Andamento item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Estado");
				} else {
					setText(item.toString());
				}
			}
		});
		
		cbxPrioridade.setButtonCell(new ListCell<Prioridade>() {
			@Override
			protected void updateItem(Prioridade item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Prioridade");
				} else {
					setText(item.toString());
				}
			}
		});
		
		cbxTipo.setButtonCell(new ListCell<TipoNotificacao>() {
			@Override
			protected void updateItem(TipoNotificacao item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo da Notificação");
				} else {
					setText(item.toString());
				}
			}
		});
		
		
	}

	private void mudarCampos() {
		
		tfdDescricao.setText(notificacao.getDescricao());
		cbxEstado.setValue(notificacao.getEstado());
		cbxPrioridade.setValue(notificacao.getPrioridade());
		cbxTipo.setValue(notificacao.getTipoNotificacao());
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		tfdData.getEditor().setText(df.format(notificacao.getAviso_data()));
		
	}
	
	private void mudarNotificacao()
	{
		notificacao.setDescricao(tfdDescricao.getText().trim());
		notificacao.setEstado(cbxEstado.getValue());
		notificacao.setPrioridade(cbxPrioridade.getValue());
		notificacao.setTipoNotificacao(cbxTipo.getValue());
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(tfdData.getEditor().getText().trim());
			notificacao.setAviso_data(data);
			
		} catch (ParseException e) {
			Alerta.getInstance().showMensagem(AlertType.WARNING, "Erro!", "Formato da Data Esta Incorreto!!!", "");
			e.printStackTrace();
		}
	}
}
