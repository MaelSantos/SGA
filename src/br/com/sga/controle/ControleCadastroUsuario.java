package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.entidade.Tela;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
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
    private Button btnCancelar;
	
	@FXML
    private void actionButton(ActionEvent event) {
		
		if(event.getSource() == btnCriar)
		{
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
					if(DaoUsuario.getInstance().addUsuario(new Funcionario(
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
		if(event.getSource() == btnCancelar)
		{
			App.notificarOuvintes(Tela.configuracoes, DaoUsuario.getInstance().getUsuarioLogado());
		}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);
	}

	@Override
	public void atualizar(Tela tela, Funcionario usuario) {
		
		
		
	}

}
