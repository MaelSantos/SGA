package br.com.sga.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.enums.Tela;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

public class ControleContatos extends Controle {

	@FXML
	private TreeTableView<String> tblContatos;

	@FXML
	private TreeTableColumn<String, String> columnNome;

	@FXML
	private TreeTableColumn<String, String> columnTelefone;

	@FXML
	private TreeTableColumn<String, String> columnOpcoes;

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		List<Telefone> list = Arrays.asList(new Telefone(0,981169372,88,new Cliente()));
		Cliente cliente = new Cliente("Mael",list);
		
		TreeItem<String> itemNome = new TreeItem<String>(cliente.getNome());	
		
		List<TreeItem<String>> treeItems = new ArrayList<>();
		for(Telefone t : list)
			treeItems.add(new TreeItem<String>("("+t.getPrefixo()+") "+t.getNumero()));
		
		TreeItem<String> itemTelefones = new TreeItem<String>();
		itemTelefones.getChildren().setAll(treeItems);
		
		TreeItem<String> root = new TreeItem<String>();
		root.getChildren().addAll(itemNome,itemTelefones);
		
		
		columnNome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String, String> param) {
				return new SimpleStringProperty(param.getValue().getValue());
			}
		});
			
		columnTelefone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String,String>, ObservableValue<String>>() {			
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String, String> param) {
				return new SimpleStringProperty(param.getValue().getValue());
			}
		});
		
		
		tblContatos.setRoot(root);
		
	}

	@Override
	public void atualizar(Tela tela, Object usuario) {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public void actionButton(ActionEvent event) {
		// TODO Stub de método gerado automaticamente
		
	}


}
