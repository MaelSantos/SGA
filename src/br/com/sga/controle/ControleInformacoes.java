package br.com.sga.controle;

import java.awt.Desktop;
import java.net.URI;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.interfaces.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ControleInformacoes {


    @FXML
    private Hyperlink emailM;

    @FXML
    private Hyperlink emailW;

    Funcionario funcionario;
    @FXML
    void openLink(ActionEvent event) {
	    if(event.getSource() == emailW) {
	    	Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
		        try {
		            desktop.browse(new URI("https://wanderson100v.github.io/"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	    }
    }

    @FXML
    void initialize() {
    	App.addOuvinte(new Ouvinte() {
			
			@Override
			public void atualizar(Tela tela, Funcionario usuario) {
				funcionario  = usuario;
			}
		});
    }
}
