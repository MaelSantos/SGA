package br.com.sga.controle;

import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleBuscarContrato {

    @FXML
    private TextField buscarField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<ContratoAdapter> contratosTableView;

    @FXML
    private TableColumn<ContratoAdapter, Date> dataColumn;

    @FXML
    private TableColumn<ContratoAdapter, Float> valorColumn;

    @FXML
    private TableColumn<ContratoAdapter, String> nomeColumn;
   
    @FXML
    private Button cadastrarContratoButton;
    

    @FXML
    private Button detalhesButton;


    private IFachada fachada;	
    
    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == buscarButton) {
    		String busca = buscarField.getText().trim();
    		if(busca.length() >0)
				try {
					contratosTableView.getItems().clear();
					List<ContratoAdapter> contratos =fachada.buscarContratoPorClienteAdapter(busca);
					contratosTableView.getItems().addAll(contratos);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			else
    			Alerta.getInstance().showMensagem("Alerta","","Campo de pesquisa vazio, entre com dado de cliente");
    	}else if(event.getSource() == cadastrarContratoButton) 
    		App.notificarOuvintes(Tela.cadastro_contrato);
    	else if(event.getSource() == detalhesButton) {
    		ContratoAdapter adapter = contratosTableView.getSelectionModel().getSelectedItem();
    		if(adapter != null) {
    			App.notificarOuvintes(Tela.Detalhes_contrato,adapter);
    		}else {
    			Alerta.getInstance().showMensagem("Alerta","","Não há nenhuma contrato selecionado ,\ncom isso não é possivel ver detalhes de contrato");
    		}
    	}
    		
    }
    @FXML
    void initialize() {
    	fachada = Fachada.getInstance();
    	dataColumn.setCellValueFactory(new PropertyValueFactory<>("data_contrato"));
    	valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
    	nomeColumn.setCellValueFactory( new PropertyValueFactory<>("nome_cliente"));
    }
}
