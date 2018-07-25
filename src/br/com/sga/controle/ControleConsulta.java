package br.com.sga.controle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.enums.Area;
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

public class ControleConsulta {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField buscarField;

    @FXML
    private Button buscarButton;
    
    @FXML
    private Button informacoesButton;
    
    @FXML
    private Button editarButton;

    @FXML
    private TableView<Consulta> contratosTableView;

    @FXML
    private TableColumn<Consulta,Date> dataColumn;

    @FXML
    private TableColumn<Consulta,Float> valorColumn;

    @FXML
    private TableColumn<Consulta,Area> areaColumn;

    @FXML
    private Button cadastrarConsultaButton;
    
    private IFachada fachada;
    

    @FXML
    void actionButton(ActionEvent event) {
    	if(buscarButton == event.getSource()) 
    	{
    		contratosTableView.getItems().clear();
    		try {
				contratosTableView.getItems().addAll(fachada.buscarConsultaPorCliente((buscarField.getText().trim())));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
    	}
    	else if(cadastrarConsultaButton == event.getSource()) 
    		App.notificarOuvintes(Tela.cadastro_consulta);
    	else if(informacoesButton == event.getSource()) {
    		Consulta consulta = contratosTableView.getSelectionModel().getSelectedItem();
    		if(consulta  != null) {
    			App.notificarOuvintes(Tela.Detalhes_consulta,consulta); // informando que vou para tela de detalhes de consulta e mando uma consulta selecionada
    		}else {
    			Alerta.getInstance().showMensagem("Alerta","","Não há nenhuma consulta selecionada ,\ncom isso não é possivel ver detalhes de consulta");
    		}
    	}
    }

    @FXML
    void initialize() {
    	fachada  = Fachada.getInstance();
    	
    	dataColumn.setCellValueFactory(new PropertyValueFactory<>("data_consulta"));
    	valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor_honorario"));
    	areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    }
}
