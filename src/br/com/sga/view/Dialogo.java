package br.com.sga.view;

import java.util.Optional;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class Dialogo {
	public static Dialogo instance;
	
	private Dialogo() {}
	
	public static Dialogo getInstance() {
		if(instance == null)
			instance = new Dialogo();
		return instance;
	}
	
	public String dialogoDeEntradaSenha(String titulo,String cabecario,String msg) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(titulo);
		dialog.setHeaderText(cabecario);
		VBox v = new VBox();
		PasswordField senha = new PasswordField();
		senha.setPrefWidth(180);
		v.getChildren().addAll(new Label(msg),senha);
		
		dialog.getDialogPane().setContent(v);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		Optional<String> result = dialog.showAndWait();
		String entrada = "";
		if (result.isPresent())
		   entrada = senha.getText();
		return entrada;
		
	}
	public String dialogoDeEntradaText(String titulo,String cabecario,String msg) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(titulo);
		dialog.setHeaderText(cabecario);
		dialog.setContentText(msg);
		Optional<String> result = dialog.showAndWait();
		String entrada = "";
		if (result.isPresent())
		   entrada = result.get();
		return entrada;
		
	}
	
}
