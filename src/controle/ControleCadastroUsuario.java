package controle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model_dao.Dados;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model_vo.Usuario;
import view.Alerta;

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

		//verifico se tem algum campo vazio
		if(! (tfdNome.getText().trim().equals("") ||
				tfdSobrenome.getText().trim().equals("") ||
				tfdSenha.getText().trim().equals("") ||
				tfdConfirmar.getText().trim().equals("") ||
				tfdLogin.getText().trim().equals("")) )
		{
//			verifico se o campo senha e confirmar são iguais 
			if(tfdSenha.getText().equals(
					tfdConfirmar.getText()) && tfdSenha.getText().length() >= 8 )
			{
				if(Dados.getInstance().addUsuario(new Usuario(
						tfdNome.getText().trim(), //nome
						tfdSobrenome.getText().trim(), //sobrenome
						tfdEmail.getText().trim(), //email 
						tfdLogin.getText().trim(), //login 
						tfdSenha.getText().trim()))) //senha
				{
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Erro!!", "Salvando...", "Salvo Com Sucesso!");
				}
				else
				{
					Alerta.getInstance().showMensagem("Erro!!!", "Erro ao Salvar!!!", "Login Já Existente");
				}
			}
			else
			{
				Alerta.getInstance().showMensagem("Erro!!!", "Senha Diferente Ou Muito Curta", "Confira Se As Senhas Informadas São Iguais");
			}
			
		}
		else
		{
			Alerta.getInstance().showMensagem("Erro!!!", "Campos Vazios", "Alguns Campos Se Encontram Vazios");
		}
		
		Alerta.getInstance().setAlertType(AlertType.ERROR);
		
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
