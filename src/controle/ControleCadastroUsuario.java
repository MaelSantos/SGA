package controle;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import mode_dao.Dados;
import model_vo.Usuario;

public class ControleCadastroUsuario implements Initializable {

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdSobrenome;

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
    private void actionButton(ActionEvent event) {
		System.out.println("click");
		//verifico se tem algum campo vazio
		if(! (tfdNome.getText().trim().equals("") &&
				tfdSobrenome.getText().trim().equals("") &&
				tfdSenha.getText().trim().equals("") &&
				tfdConfirmar.getText().trim().equals("")) )
		{
//			verifico se o campo senha e confirmar são iguais 
			if(tfdSenha.getText().equals(
					tfdConfirmar.getText()))
			{
				Dados.getInstance().addUsuario(new Usuario(
						tfdNome.getText().trim(), //nome
						tfdSobrenome.getText().trim(), //sobrenome
						tfdEmail.getText().trim(), //email 
						tfdLogin.getText().trim(), //login 
						tfdSenha.getText().trim())); //senha
			}
			else
			{
				//mensagem de erro
				System.out.println("Senhas Não Batem");
			}
			
		}
		else
		{
			System.out.println("Campos Vazios");
		}
		
		
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Stub de método gerado automaticamente

	}

	
	
}
