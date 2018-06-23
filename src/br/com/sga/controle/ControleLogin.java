package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.dao.Dados;
import br.com.sga.entidade.Tela;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ControleLogin implements Initializable{
	
	@FXML
    private TextField tfdLogin;

    @FXML
    private Button btnEntrar;
    
    @FXML
    private Button btnSair;

    @FXML
    private PasswordField tfdSenha;
	
	@FXML
	private void actionButton(ActionEvent e)
	{		
		if(e.getSource() == btnEntrar)
		{
			if(Dados.getInstance().entrarSistema(tfdLogin.getText(), tfdSenha.getText()))
			{
				App.changeStage(Tela.menu);
				App.notificarOuvintes(Tela.menu, Dados.getInstance().getUsuarioLogado());
			}
			else
			{
				Alerta.getInstance().showMensagem("Dados Incorretos", "Login/Email Ou Senha Incorretos", "Verifique Os Dados Informados E Tente Novamente");
			}
		}
		if(e.getSource() == btnSair)
		{
			System.exit(0);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Stub de método gerado automaticamente
		
	}

}
