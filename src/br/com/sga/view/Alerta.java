package br.com.sga.view;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Alerta extends Alert {

	private static Alerta alert;
	
	private Alerta(AlertType alertType, String titulo, String header, String content){
		super(alertType);
		setTitle(titulo);
		setHeaderText(header);
		setContentText(content);
		
		initModality(Modality.APPLICATION_MODAL);
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
		setAlertType(AlertType.NONE);
		setTitle(titulo);
		setHeaderText(header);
		setContentText(content);
		this.show();
	}
	
}
