package br.com.sga.view;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Dialogo {
	
	private static Dialogo instance;
	
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
//	public Cliente selecaoCliente(List<Cliente> clientes) {
//		Dialog<String> dialog = new Dialog<>();
//		dialog.setTitle("Selecione um cliente");
//		dialog.setHeaderText("Lista com todos os clientes para a busca");
//		dialog.setResizable(true);
//		
//		TableView<Cliente> clienteTable = new TableView<>();
//	    TableColumn<Cliente, String> nomeColumn = new TableColumn<>("Nome");
//	    TableColumn<Cliente, String> cpfCnpjColumn = new TableColumn<>("CPF / CPNJ");
//	    TableColumn<Cliente, String> emailColumn = new TableColumn<>("Email");
//	    
//	    clienteTable.getColumns().addAll(nomeColumn,cpfCnpjColumn,emailColumn);
//	    clienteTable.setPrefSize(600,300);	    
//	   
//	    nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
//    	nomeColumn.setPrefWidth(200);
//	    
//    	cpfCnpjColumn.setCellValueFactory(new PropertyValueFactory<>("cpf_cnpj"));
//    	cpfCnpjColumn.setPrefWidth(200);
//	   
//    	emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
//    	emailColumn.setPrefWidth(200);
//    	
//    	clienteTable.getItems().addAll(clientes);
//		
//    	dialog.getDialogPane().setContent(clienteTable);
//		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//		
//		Optional<String> result = dialog.showAndWait();
//		if(result.isPresent())
//			return clienteTable.getSelectionModel().getSelectedItem();
//		return null;
//	}
	
//	public Funcionario selecaoFuncionario(List<Funcionario> funcionarios) {
//		Dialog<String> dialog = new Dialog<>();
//		dialog.setTitle("Selecione um cliente");
//		dialog.setHeaderText("Lista com todos os clientes para a busca");
//		dialog.setResizable(true);
//		
//		TableView<Funcionario> funcionarioTable = new TableView<>();
//	    TableColumn<Funcionario, String> nomeColumn = new TableColumn<>("Nome");
//	    TableColumn<Funcionario, String> numeroOABColumn = new TableColumn<>("Número na OAB");
//	    TableColumn<Funcionario, String> emailColumn = new TableColumn<>("Email");
//	    
//	    funcionarioTable.getColumns().addAll(nomeColumn,numeroOABColumn,emailColumn);
//	   
//	    funcionarioTable.setPrefSize(600,300);	    
//	   
//	    nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
//    	nomeColumn.setPrefWidth(200);
//	    
//    	numeroOABColumn.setCellValueFactory(new PropertyValueFactory<>("numero_oab"));
//    	numeroOABColumn.setPrefWidth(200);
//	   
//    	emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
//    	emailColumn.setPrefWidth(200);
//    	
//    	funcionarioTable.getItems().addAll(funcionarios);
//		
//    	dialog.getDialogPane().setContent(funcionarioTable);
//		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//		
//		Optional<String> result = dialog.showAndWait();
//		if(result.isPresent())
//			return funcionarioTable.getSelectionModel().getSelectedItem();
//		return null;
//	}
	
//	public ConsultaAdapter selecaoConsulta(List<ConsultaAdapter> consultas) {
//		Dialog<String> dialog = new Dialog<>();
//		dialog.setTitle("Selecione uma Consulta");
//		dialog.setHeaderText("Lista com todos as consultas: \nSeleciona uma consulta ");
//		dialog.setResizable(true);
//		
//		TableView<ConsultaAdapter> consultaTable = new TableView<>();
//	    TableColumn<ConsultaAdapter, Date> dataColumn = new TableColumn<>("Data");
//	    TableColumn<ConsultaAdapter, String> nomeColumn = new TableColumn<>("Nome Cliente");
//	    TableColumn<ConsultaAdapter, Area> areaColumn = new TableColumn<>("Area");
//	    TableColumn<ConsultaAdapter, Float> valorColumn = new TableColumn<>("Valor honorario");
//	    
//	    consultaTable.getColumns().addAll(dataColumn,nomeColumn,areaColumn,valorColumn);
//	    consultaTable.setPrefSize(800
//	    		,300);	    
//	   
//	    dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
//	    dataColumn.setPrefWidth(120);
//	    
//	    nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
//	    nomeColumn.setPrefWidth(280);
//	    
//	    valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor_honorario"));
//    	valorColumn.setPrefWidth(200);
//	   
//    	areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
//    	areaColumn.setPrefWidth(200);
//    	
//    	consultaTable.getItems().addAll(consultas);
//		
//    	dialog.getDialogPane().setContent(consultaTable);
//		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//		
//		Optional<String> result = dialog.showAndWait();
//		if(result.isPresent())
//			return consultaTable.getSelectionModel().getSelectedItem();
//		return null;
//	}
	
//	public Testemunha selecionarTestemunha(List<Testemunha> testemunhas) {
//		Dialog<String> dialog = new Dialog<>();
//		dialog.setTitle("Selecione uma testemunha");
//		dialog.setHeaderText("Lista com todos as consultas: \nSeleciona uma testemunha para mais detalhes ");
//		dialog.setResizable(true);
//		
//
//		TableView<Testemunha> testemunhaTable = new TableView<>();
//	    TableColumn<Testemunha,String> nomeTestemunhaColumn = new TableColumn<>("Nome da Testemunha");
//	    TableColumn<Testemunha,Telefone> telefoneTestemunhaColumn = new TableColumn<>("Telefone");
//	    TableColumn<Testemunha,Endereco> enderecoTestemunhaColumn = new TableColumn<>("Endereco");
//	    testemunhaTable.getColumns().addAll(nomeTestemunhaColumn,telefoneTestemunhaColumn,enderecoTestemunhaColumn);
//	    testemunhaTable.setPrefSize(600,300);	    
//	   
//	    nomeTestemunhaColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
//	    nomeTestemunhaColumn.setPrefWidth(200);
//	    
//    	telefoneTestemunhaColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
//    	telefoneTestemunhaColumn.setPrefWidth(200);
//	   
//    	enderecoTestemunhaColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));
//    	enderecoTestemunhaColumn.setPrefWidth(200);
//    	
//    	testemunhaTable.getItems().addAll(testemunhas);
//		
//    	dialog.getDialogPane().setContent(testemunhaTable);
//		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//		
//		Optional<String> result = dialog.showAndWait();
//		if(result.isPresent())
//			return testemunhaTable.getSelectionModel().getSelectedItem();
//		return null;
//	}

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

//	public <T> T selecao(List<T> list,String titulo,String descricao)
//	{
//		Dialog<T> dialog = new Dialog<>();
//		dialog.setTitle(titulo);
//		
//		ListView<T> view = new ListView<T>();
//		ListCell<T> cell = new ListCell<T>();
//		
////		view.setCellFactory(param -> cell);
//		
//		ObservableList<T> nomes = FXCollections
//		        .observableArrayList ();
//		
//		nomes.addAll(list);
//		
//		view.setCellFactory(ComboBoxListCell.forListView(nomes));
//		
//		VBox v = new VBox();
//		v.setPrefSize(500, 300);
//		
//		view.getItems().setAll(list);
//		v.getChildren().addAll(new Label(descricao), view);
//		
//		dialog.getDialogPane().setContent(v);
//		
//		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
//		
//		Optional<T> optional = dialog.showAndWait();
//		
//		if(optional.isPresent())
//			return view.getSelectionModel().getSelectedItem();			
//		return null;
//	}

	public <T> T selecionar(List<T> list) {
				
		Dialog<T> dialog = new Dialog<>();
		dialog.setTitle("Selecione: ");
		if(!list.isEmpty())
			dialog.setHeaderText("Lista com todos os(a) "+list.get(0).getClass().getSimpleName().replace("Adapter", "").toUpperCase()+" : \nSeleciona um(a) para mais detalhes");
		else
			dialog.setHeaderText("Lista com todos os(a) "+" : \nSeleciona um(a) para mais detalhes");
		dialog.setResizable(true);
		
		TableView<T> table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    table.setPrefSize(600,300);	    
   	
	    int contador = 0;
	    
	    if(!list.isEmpty())
    	for(Field f : list.get(0).getClass().getDeclaredFields())
    	{
    		if(!f.getName().equals("id"))
    		{
    			TableColumn<T, ?> column = new TableColumn<>(f.getName().replaceAll("_", " ").toUpperCase());
    			column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
    			table.getColumns().add(column);    			
    		}
    		
    		if(contador >= 6)
    			break;
    		else
    			contador++;
    		
    	}
    	
    	table.getItems().addAll(list);
	
    	dialog.getDialogPane().setContent(table);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		
		Optional<T> result = dialog.showAndWait();
		if(result.isPresent())
			return table.getSelectionModel().getSelectedItem();
		return null;
	}
		
}
