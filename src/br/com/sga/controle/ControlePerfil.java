package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
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
    		App.notificarOuvintes(Tela.editar_perfil, DaoUsuario.getUsuarioLogado());
    }	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		App.addOuvinte(new Ouvinte() {
			
			@Override
			public void atualizar(Tela tela, Funcionario usuario) {
				
				lblNome.setText(usuario.getNome());
				
			}
		});
	}
}