package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleConsulta  extends Controle{

 @FXML
    private TextField buscarField;

    @FXML
    private Button buscarButton;

    @FXML
    private Button informacoesButton;

    @FXML
    private TextField totalField;


    @FXML
    private ComboBox<String> tipoPessoaBox;

    @FXML
    private ComboBox<String> areaBox;

    @FXML
    private TableView<ConsultaAdapter> contratosTableView;

    @FXML
    private TableColumn<ConsultaAdapter,Date> dataColumn;

    @FXML
    private TableColumn<ConsultaAdapter,String> nomeColumn;

    @FXML
    private TableColumn<ConsultaAdapter,Float> valorColumn;

    @FXML
    private TableColumn<ConsultaAdapter,Area> areaColumn;

    @FXML
    private Button cadastrarConsultaButton;
    
    private IFachada fachada;
    
    public void actionButton(ActionEvent event) {
    	if(buscarButton == event.getSource()) 
    	{
    		contratosTableView.getItems().clear();
    		try {
				String[] busca = new String[3];
				
				busca[0] = buscarField.getText().trim();
				
				if(tipoPessoaBox.getSelectionModel().getSelectedItem() != null 
						&& !tipoPessoaBox.getSelectionModel().getSelectedItem().equals("TODOS"))
					busca[1] = tipoPessoaBox.getSelectionModel().getSelectedItem();
				else
					busca[1] = "";
		
				if(areaBox.getSelectionModel().getSelectedItem() != null 
						&& !areaBox.getSelectionModel().getSelectedItem().equals("TODAS")) 
					busca[2] = areaBox.getSelectionModel().getSelectedItem();
				else
					busca[2] = "";
				for(String s: busca)
					System.out.println(s);
    			contratosTableView.getItems().addAll(fachada.buscarConsultaPorClienteAdapter(busca));
			} catch (BusinessException e) {
				e.printStackTrace();
			}
    	}
    	else if(cadastrarConsultaButton == event.getSource()) 
    		App.notificarOuvintes(Tela.cadastro_consulta);
    	else if(informacoesButton == event.getSource()) {
    		ConsultaAdapter consulta = contratosTableView.getSelectionModel().getSelectedItem();
    		if(consulta  != null) {
    			App.notificarOuvintes(Tela.Detalhes_consulta,consulta); // informando que vou para tela de detalhes de consulta e mando uma consulta selecionada
    		}else {
    			Alerta.getInstance().showMensagem("Alerta","","Não há nenhuma consulta selecionada ,\ncom isso não é possivel ver detalhes de consulta");
    		}
    	}
    }

	@Override
	public void atualizar(Tela tela, Object object) {
		
	}

	@Override
	public void init() {
		fachada  = Fachada.getInstance();
    	dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
    	valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor_honorario"));
    	areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    	nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
    	
    	areaBox.getItems().add("TODAS");
    	for(Area e : Area.values())
    		areaBox.getItems().add(e.toString());
    	
    	tipoPessoaBox.getItems().add("TODOS");
    	for(TipoCliente e : TipoCliente.values())
    		tipoPessoaBox.getItems().add(e.toString());    	
		
	}
}
