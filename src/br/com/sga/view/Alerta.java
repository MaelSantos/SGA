package br.com.sga.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerta extends Alert {

	static Alerta alert;
	
	private Alerta(AlertType alertType, String titulo, String header, String content){
		super(alertType);
		setTitle(titulo);
		setHeaderText(header);
		setContentText(content);
	}

	public static Alerta getInstance()
	{
		if(alert == null)
			alert = new Alerta(AlertType.ERROR, "Erro!!!", "Erro!!!", "Erro!!!");
		return alert;
	}
	
	public void showMensagem(AlertType alertType, String titulo, String header, String content)
	{
		setAlertType(alertType);
		setTitle(titulo);
		setHeaderText(header);
		setContentText(content);
		this.show();
	}
	public void showMensagem(String titulo, String header, String content)
	{
		setTitle(titulo);
		setHeaderText(header);
		setContentText(content);
		this.show();
	}
	
}
