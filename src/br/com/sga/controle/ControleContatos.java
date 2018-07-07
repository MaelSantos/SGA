package br.com.sga.controle;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Telefone;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

public class ControleContatos implements Initializable{

	 @FXML
	    private TreeTableView<Cliente> tblContatos;

	    @FXML
	    private TreeTableColumn<Cliente, String> columnNome;

	    @FXML
	    private TreeTableColumn<Cliente, String> columnTelefone;

	    @FXML
	    private TreeTableColumn<Cliente, String> columnOpcoes;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		List<Cliente> clientes = tblContatos.setItems(FXCollections.observableArrayList(clientes));
		
//		empBoss.setNome("Mael");
//		Cliente empSmith = new Cliente();
//		Cliente empMcNeil = new Cliente();
//		TreeItem<Cliente> itemRoot = new TreeItem<Cliente>(empBoss);
//		TreeItem<Cliente> itemSmith = new TreeItem<Cliente>(empSmith);
//		TreeItem<Cliente> itemMcNeil = new TreeItem<Cliente>(empMcNeil);
//		itemRoot.getChildren().addAll(itemSmith, itemMcNeil);
//		tblContatos.setRoot(itemRoot);

		List<Telefone> list = Arrays.asList(new Telefone(1,1,1,new Cliente()));
		Cliente cliente = new Cliente("Mael",list);
		TreeItem<Cliente> item = new TreeItem<Cliente>(cliente);
		
		columnNome.setCellValueFactory(new TreeItemPropertyValueFactory<Cliente, String>("Nome"));

	    columnTelefone.setCellValueFactory(new TreeItemPropertyValueFactory<Cliente, String>("Telefone"));

	    columnOpcoes.setCellValueFactory(new TreeItemPropertyValueFactory<Cliente, String>("Opções"));
		
	
        
        
	}


}
