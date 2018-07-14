package br.com.sga.controle;

import java.util.List;

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

public class ControleBuscarContrato {

    @FXML
    private TextField buscarField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<br.com.sga.entidade.tabelaView.Contrato> contratosTableView;

    @FXML
    private TableColumn<br.com.sga.entidade.tabelaView.Contrato, String> dataColumn;

    @FXML
    private TableColumn<br.com.sga.entidade.tabelaView.Contrato, String> areaColumn;

    @FXML
    private TableColumn<br.com.sga.entidade.tabelaView.Contrato, String> objetivoColumn;

    @FXML
    private AnchorPane paneBusca;

    @FXML
    private AnchorPane paneDetalhes;

    private IFachada fachada;	
    
    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == buscarButton) {
    		String busca = buscarField.getText().trim();
    		if(busca.length() >0)
				try {
					List<br.com.sga.entidade.Contrato> contratos =fachada.buscarContratoPorCliente(busca);
					for(br.com.sga.entidade.Contrato e : contratos) {
						contratosTableView.getItems().add(new br.com.sga.entidade.tabelaView.Contrato(e.getData_contrato().toString(),
								e.getArea().toString(),e.getObjeto()));
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			else
    			Alerta.getInstance().showMensagem("Alerta","","Campo de pesquisa vazio, entre com dado de cliente");
    	}
    }
    @FXML
    void initialize() {
    	fachada = Fachada.getInstance();
    	dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
    	areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    	objetivoColumn.setCellValueFactory( new PropertyValueFactory<>("objeto"));
    	
    	
    }
}
