package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.dao.Dados;
import br.com.sga.entidade.Ouvinte;
import br.com.sga.entidade.Tela;
import br.com.sga.entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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