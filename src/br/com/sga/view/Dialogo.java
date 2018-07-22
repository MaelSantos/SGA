package br.com.sga.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public Cliente selecaoCliente(List<Cliente> clientes) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Selecione um cliente");
		dialog.setHeaderText("Lista com todos os clientes para a busca");
		dialog.setResizable(true);
		
		TableView<Cliente> clienteTable = new TableView<>();
	    TableColumn<Cliente, String> nomeColumn = new TableColumn<>("Nome");
	    TableColumn<Cliente, String> cpfCnpjColumn = new TableColumn<>("CPF / CPNJ");
	    TableColumn<Cliente, String> emailColumn = new TableColumn<>("Email");
	    
	    clienteTable.getColumns().addAll(nomeColumn,cpfCnpjColumn,emailColumn);
	    clienteTable.setPrefSize(600,300);	    
	   
	    nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	nomeColumn.setPrefWidth(200);
	    
    	cpfCnpjColumn.setCellValueFactory(new PropertyValueFactory<>("cpf_cnpj"));
    	cpfCnpjColumn.setPrefWidth(200);
	   
    	emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    	emailColumn.setPrefWidth(200);
    	
    	clienteTable.getItems().addAll(clientes);
		
    	dialog.getDialogPane().setContent(clienteTable);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent())
			return clienteTable.getSelectionModel().getSelectedItem();
		return null;
	}
	public Funcionario selecaoFuncionario(List<Funcionario> funcionarios) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Selecione um cliente");
		dialog.setHeaderText("Lista com todos os clientes para a busca");
		dialog.setResizable(true);
		
		TableView<Funcionario> funcionarioTable = new TableView<>();
	    TableColumn<Funcionario, String> nomeColumn = new TableColumn<>("Nome");
	    TableColumn<Funcionario, String> numeroOABColumn = new TableColumn<>("Número na OAB");
	    TableColumn<Funcionario, String> emailColumn = new TableColumn<>("Email");
	    
	    funcionarioTable.getColumns().addAll(nomeColumn,numeroOABColumn,emailColumn);
	   
	    funcionarioTable.setPrefSize(600,300);	    
	   
	    nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	nomeColumn.setPrefWidth(200);
	    
    	numeroOABColumn.setCellValueFactory(new PropertyValueFactory<>("numero_oab"));
    	numeroOABColumn.setPrefWidth(200);
	   
    	emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    	emailColumn.setPrefWidth(200);
    	
    	funcionarioTable.getItems().addAll(funcionarios);
		
    	dialog.getDialogPane().setContent(funcionarioTable);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent())
			return funcionarioTable.getSelectionModel().getSelectedItem();
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
	
	public Notificacao DetalhesData(LocalDate date)
	{
		System.out.println("Data do panel é: " + date);
		Dialog<Notificacao> dialog = new Dialog<>();
		dialog.setTitle(date.toString());
		
		VBox v = new VBox();
		v.setPrefSize(500, 300);
		
		ListView<Notificacao> view = new ListView<Notificacao>();
		try {
			view.getItems().setAll(FXCollections.observableArrayList(
					Fachada.getInstance().buscarNotificacaoPorData(Date.valueOf(date))));
			v.getChildren().addAll(new Label(date.toString()), view);
		} catch (BusinessException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		
		dialog.getDialogPane().setContent(v);
		
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		Optional<Notificacao> optional = dialog.showAndWait();
		
		if(optional.isPresent())
			return view.getSelectionModel().getSelectedItem();			
		return null;
	}
	
}
