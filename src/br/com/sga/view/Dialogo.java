package br.com.sga.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.sga.entidade.Notificacao;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	
	public CadastroNotificacao cadastroNotificacaoDialog() {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Cadastro de tarefa");
		dialog.setHeaderText("Cadastre uma nova tarefa para voce ou todos os funcionários");
		CadastroNotificacao cadastroNotificacao  = new CadastroNotificacao();
		dialog.getDialogPane().setContent(cadastroNotificacao);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent())
			return cadastroNotificacao;
		return null;
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
	
	public Notificacao DetalhesData(LocalDate date, List<Notificacao> list)
	{
		System.out.println("Data do panel é: " + date);
		Dialog<Notificacao> dialog = new Dialog<>();
		dialog.setTitle(date.toString());
		
		VBox v = new VBox();
		v.setPrefSize(500, 300);
		
		ListView<Notificacao> view = new ListView<>(FXCollections.observableArrayList(list));
		
		v.getChildren().addAll(new Label(date.toString()), view);
		dialog.getDialogPane().setContent(v);
		
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		Optional<Notificacao> optional = dialog.showAndWait();
		
		if(optional.isPresent())
			return view.getSelectionModel().getSelectedItem();			
		return null;
	}
	
}
