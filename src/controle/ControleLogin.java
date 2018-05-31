package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model_dao.Dados;
import model_vo.Tela;
import view.Alerta;


public class ControleLogin implements Initializable{
	
	@FXML
    private TextField tfdLogin;

    @FXML
    private Button btnEntrar;

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
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Stub de método gerado automaticamente
		
	}

}
