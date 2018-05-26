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
import model_vo.Tela;

public class ControleLogin implements Initializable{
	
	@FXML
    private TextField lblLogin;

    @FXML
    private Button btnEntrar;

    @FXML
    private PasswordField lblSenha;
	
	@FXML
	private void actionButton(ActionEvent e)
	{		
		if(e.getSource() == btnEntrar)
		{
			App.changeStage(Tela.cadastro);		
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Stub de método gerado automaticamente
		
	}

}
