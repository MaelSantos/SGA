package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.interfaces.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public abstract class Controle implements Initializable, Ouvinte {
	
	public abstract void init();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);
		init();
	}
	
	 @FXML
	 public abstract void actionButton(ActionEvent event);

	
}
