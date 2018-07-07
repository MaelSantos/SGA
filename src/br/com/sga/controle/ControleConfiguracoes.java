package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControleConfiguracoes implements Initializable,Ouvinte{

	@FXML
    private Button btnAddAdm;

    Funcionario usuario;
    
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
	public void atualizar(Tela tela, Funcionario usuario) {
		
		this.usuario = usuario;
		
	}

}
