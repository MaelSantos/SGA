package br.com.sga.controle;

import java.util.List;

import br.com.sga.app.App;
import br.com.sga.entidade.Contrato;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class ControleBuscarContrato {

    @FXML
    private TextField buscarField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<Contrato> contratosTableView;

    @FXML
    private TableColumn<Contrato, String> dataColumn;

    @FXML
    private TableColumn<Contrato, String> areaColumn;

    @FXML
    private TableColumn<Contrato, String> objetivoColumn;
   
    @FXML
    private TableColumn<?, ?>  acoesColumn;
    
    @FXML
    private Button cadastrarContratoButton;


    private IFachada fachada;	
    
    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == buscarButton) {
    		String busca = buscarField.getText().trim();
    		if(busca.length() >0)
				try {
					contratosTableView.getItems().clear();
					List<Contrato> contratos =fachada.buscarContratoPorCliente(busca);
					contratosTableView.getItems().addAll(contratos);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			else
    			Alerta.getInstance().showMensagem("Alerta","","Campo de pesquisa vazio, entre com dado de cliente");
    	}else if(event.getSource() == cadastrarContratoButton) {
    		App.notificarOuvintes(Tela.cadastro_contrato);
    	}
    }
    @FXML
    void initialize() {
    	fachada = Fachada.getInstance();
    	dataColumn.setCellValueFactory(new PropertyValueFactory<>("data_contrato"));
    	areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    	objetivoColumn.setCellValueFactory( new PropertyValueFactory<>("objeto"));
    }
}
