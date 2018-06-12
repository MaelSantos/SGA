package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import app.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model_vo.Tela;

public class ControleCliente implements Initializable{

    @FXML
    private Button btnAdd;

    @FXML
    void actionButton(ActionEvent event) {

    	if(event.getSource() == btnAdd)
    	{
    		App.notificarOuvintes(Tela.cadastro_cliente);
    	}
    	
    }
        
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
    
}
	

