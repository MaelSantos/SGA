package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import app.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model_dao.Dados;
import model_vo.Tela;
import model_vo.Usuario;

public class ControlePerfil implements Initializable{

    @FXML
    private Button editarPerfilButton;

    @FXML
    private Label lblNome;
    
    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == editarPerfilButton) 
    		App.notificarOuvintes(Tela.editar_perfil, Dados.getInstance().getUsuarioLogado());
    }	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		App.addOuvinte(new Ouvinte() {
			
			@Override
			public void atualizar(Tela tela, Usuario usuario) {
				
				lblNome.setText(usuario.getNome()+" "+usuario.getSobrenome());
				
			}
		});
	}
}