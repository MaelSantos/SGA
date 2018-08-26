package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleCadastroUsuario extends Controle{


    @FXML
    private TextField cepField;

    @FXML
    private TextField ruaField;

    @FXML
    private TextField numField;

    @FXML
    private TextField bairroField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField paisField;

    @FXML
    private ComboBox<Estado> estadoBox;

    @FXML
    private TextField compField;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdNumeroOab;

	@FXML
	private Button btnCriar;

	@FXML
	private TextField tfdEmail;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private PasswordField tfdConfirmar;

	@FXML
	private TextField tfdLogin;
	
	@FXML
    private Button btnCancelar;
	
	private IFachada fachada;
	
	private Funcionario funcionario;
	
	@FXML
    public void actionButton(ActionEvent event) {
		
		if(event.getSource() == btnCriar)
		{
			Log log = null;
			try {
				
				if(tfdSenha.getText().equals(tfdConfirmar.getText()) && estadoBox.getValue()!= null)
				{
					Endereco endereco = new Endereco(
							ruaField.getText(),
							numField.getText(), 
							bairroField.getText(), 
							cidadeField.getText(),
							estadoBox.getValue().toString(), 
							paisField.getText(),
							compField.getText(),
							cepField.getText()
							);
					Funcionario funcionario = new Funcionario(
							tfdNome.getText().trim(), //nome
							tfdEmail.getText().trim(), //email 
							tfdLogin.getText().trim(), //login 
							tfdSenha.getText().trim(), //senha
							tfdNumeroOab.getText().trim(),//numero OAB
							endereco
							); 
					fachada.salvarEditarUsuario(funcionario);
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Salvo", "Salvando...", "Salvo Com Sucesso!");
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, this.funcionario.getNome(), "Novo Usuario: "+funcionario.getNome(), StatusLog.CONCLUIDO);
					limparCampos();
				}
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem("Erro!!!", "Erro ao Salvar!!!", e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Novo Usuario: Erro", StatusLog.ERRO);
			}
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		if(event.getSource() == btnCancelar)
		{
			App.notificarOuvintes(Tela.CONFIGURACOES,funcionario);
		}
    }
	
	@Override
	public void atualizar(Tela tela, Object usuario) {
		if (usuario instanceof Funcionario) {
			
			funcionario = (Funcionario) usuario;
			
		}
	}
	
	private void limparCampos() {
		
		tfdConfirmar.setText("");
		tfdEmail.setText("");
		tfdLogin.setText("");
		tfdNome.setText("");
		tfdNumeroOab.setText("");
		tfdSenha.setText("");
		
		
		ruaField.setText("");
		paisField.setText("");
		numField.setText("");
		compField.setText("");
		cidadeField.setText("");
		cepField.setText("");
		bairroField.setText("");
		estadoBox.getSelectionModel().clearSelection();
		
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		estadoBox.getItems().addAll(Estado.values());
		
		estadoBox.setButtonCell(new ListCell<Estado>() {
	        @Override
	        protected void updateItem(Estado item, boolean empty) {
	            super.updateItem(item, empty) ;
	            if (empty || item == null) {
	                setText("Estado");
	            } else {
	                setText(item.toString());
	            }
	        }
	    });
		
	}

}
