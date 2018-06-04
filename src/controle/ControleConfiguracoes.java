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
import model_vo.Usuario;

public class ControleConfiguracoes implements Initializable,Ouvinte{

	@FXML
    private Button btnAddAdm;

    Usuario usuario;
    
    @FXML
    void actionButton(ActionEvent event) {

    	if(event.getSource() == btnAddAdm)
    	{
    		App.notificarOuvintes(Tela.cadastro, usuario);
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);
	}

	@Override
	public void atualizar(Tela tela, Usuario usuario) {
		
		this.usuario = usuario;
		
	}

}
