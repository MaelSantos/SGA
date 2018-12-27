package br.com.sga.controle;

import java.awt.Desktop;
import java.net.URI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ControleInformacoes {

    @FXML
    private Hyperlink emailM;

    @FXML
    private Hyperlink emailW;

    @FXML
    void openLink(ActionEvent event) {
    	Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    	if(event.getSource() == emailW) {
		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
		        try {
		            desktop.browse(new URI("https://wanderson100v.github.io/"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	    }
	    else if(event.getSource() == emailM) {
		    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
		        try {
		            desktop.browse(new URI("https://MaelSantos.github.io/"));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	    }
    }

    @FXML
    void initialize() { }
}
