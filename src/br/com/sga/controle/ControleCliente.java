package br.com.sga.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.interfaces.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class ControleCliente implements Initializable, Ouvinte{

	 @FXML
	 private Button btnAdd;

	 @FXML
	 private TextField tfdBusca;

	 @FXML
	 private ComboBox<TipoCliente> cbxTipo;

	 @FXML
	 private TableView<Cliente> tblClientes;
	 
	 @FXML
	 private TableColumn<Cliente, String> colNome;

	 @FXML
	 private TableColumn<Cliente, String> colCpfCnpj;

	 @FXML
	 private TableColumn<Cliente, String> colRg;

	 @FXML
	 private TableColumn<Cliente, ArrayList<Telefone>> colTelefone;

	 @FXML
	 private TableColumn<Cliente, String> colEmail;

	 @FXML
	 private TableColumn<Cliente, TipoCliente> colTipo;
	 
    @FXML
    void actionButton(ActionEvent event) {

    	Object obj = event.getSource();
    	
    	if(obj == btnAdd)
    		App.notificarOuvintes(Tela.cadastro_cliente);
    	if(obj == cbxTipo)
//    		for(Cliente c : tblClientes.getItems())
//    			if(!(cbxTipo.getValue() == c.getTipoCliente()))
//    				tblClientes.getItems()getClass().get
//    				System.out.println(c);
    }
    
    @FXML
    void actionKey(KeyEvent event) {

    	
    	
    }

        
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		cbxTipo.getItems().addAll(TipoCliente.values());
		
		colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
		colCpfCnpj.setCellValueFactory(
                new PropertyValueFactory<>("cpf_cnpj"));
		colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
		colRg.setCellValueFactory(
                new PropertyValueFactory<>("rg"));
		colTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefones"));
		colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
		
		try {
			tblClientes.getItems().addAll(Fachada.getInstance().buscarClientePorCodigo("07551074384"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		TextFields.bindAutoCompletion(tfdBusca,tblClientes.getItems());
		
	}

	@Override
	public void atualizar(Tela tela, Funcionario usuario) {
		
		
	}
    
}
	

