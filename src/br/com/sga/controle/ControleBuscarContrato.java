package br.com.sga.controle;

import br.com.sga.entidade.tabelaView.Contrato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane paneBusca;

    @FXML
    private AnchorPane paneDetalhes;

    @FXML
    void actionButton(ActionEvent event) {
    	
    }
    @FXML
    void initialize() {
    
    	dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
    	areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    	objetivoColumn.setCellValueFactory( new PropertyValueFactory<>("objetivo"));
    	
    	
    }
}
