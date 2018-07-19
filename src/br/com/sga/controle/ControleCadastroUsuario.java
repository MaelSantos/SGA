package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleCadastroUsuario implements Initializable, Ouvinte {

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
    private void actionButton(ActionEvent event) {
		
		if(event.getSource() == btnCriar)
		{
			System.out.println("Entrando");
			try {
				
				if(tfdSenha.getText().equals(tfdConfirmar.getText()))
				{
					Funcionario funcionario = new Funcionario(
							tfdNome.getText().trim(), //nome
							tfdEmail.getText().trim(), //email 
							tfdLogin.getText().trim(), //login 
							tfdSenha.getText().trim(), //senha
							tfdNumeroOab.getText().trim()); //numero OAB
						fachada.salvarEditarUsuario(funcionario);
						Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Salvo", "Salvando...", "Salvo Com Sucesso!");
						limparCampos();
				}
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem("Erro!!!", "Erro ao Salvar!!!", e.getMessage());
			}
		}
		if(event.getSource() == btnCancelar)
		{
			App.notificarOuvintes(Tela.configuracoes,funcionario);
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);
		fachada = Fachada.getInstance();
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
		
	}

}
