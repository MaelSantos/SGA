package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model_vo.Tela;

public class ControlePerfil implements Initializable{

    @FXML
    private Button editarPerfilButton;

    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == editarPerfilButton) 
    		App.notificarOuvintes(Tela.editar_perfil);
    		
    }	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}