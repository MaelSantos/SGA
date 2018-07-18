package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.enums.TipoTelefone;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleCadastroConsulta extends Controle{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField honorarioField;

    @FXML
    private TextField dadoClienteField;

    @FXML
    private ComboBox<Area> areaBox;

    @FXML
    private TextArea descricaoArea;

    @FXML
    private DatePicker dataConsultaPicker;

    @FXML
    private TableView<Testemunha> testemunhaTableView;

    @FXML
    private TableColumn<Testemunha,String> nomeTestemunhaColumn;

    @FXML
    private TableColumn<Testemunha,Telefone> telefoneTestemunhaColumn;

    @FXML
    private TableColumn<Testemunha,Endereco> enderecoTestemunhaColumn;

    @FXML
    private Button gerarDocumentoButton;

    @FXML
    private Button salvarConsulta;

    @FXML
    private Button buscarClienteButton;

    @FXML
    private TextField dadoFuncionarioField;

    @FXML
    private Button buscarFuncionarioButton;

    @FXML
    private RadioButton funcionarioLogadoRadio;

    @FXML
    private RadioButton outroFuncionarioRadio;

    @FXML
    private TextField nomeIndicacaoField;

    @FXML
    private Button addTestemunhaField;

    @FXML
    private TextField ruaField;

    @FXML
    private TextField numeroField;

    @FXML
    private TextField bairroField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField paisField;

    @FXML
    private ComboBox<Estado> estadoBox;

    @FXML
    private TextField cepField;

    @FXML
    private TextField complementoField;

    @FXML
    private TextField nomeTestemunhaField;

    @FXML
    private TextField telefonePreField;

    @FXML
    private TextField telefoneNumeroField;

    @FXML
    private ComboBox<TipoTelefone> tipoTelefoneBox;

    @FXML
    private Button cadastrarClienteButton;

    private IFachada fachada ;
    
    public void actionButton(ActionEvent event) {
    	System.out.println("evento");
    	if(event.getSource() == outroFuncionarioRadio) {
    		buscarFuncionarioButton.setVisible(true);
    		dadoFuncionarioField.setVisible(true);
    	}else if(event.getSource() == funcionarioLogadoRadio) {
    		buscarFuncionarioButton.setVisible(false);
    		dadoFuncionarioField.setVisible(false);
    		dadoFuncionarioField.setText("");
    	}
    }

	@Override
	public void atualizar(Tela tela, Object object) {
		
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
    	
    	
    	areaBox.getItems().addAll(Area.values());
		estadoBox.getItems().addAll(Estado.values());
		tipoTelefoneBox.getItems().addAll(TipoTelefone.values());
		
        nomeTestemunhaColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telefoneTestemunhaColumn.setCellValueFactory( new PropertyValueFactory<>("telefone")); // utiliazra do to string de ambos
        enderecoTestemunhaColumn.setCellValueFactory( new PropertyValueFactory<>("endereco"));
        
        ToggleGroup tg = new ToggleGroup();
		funcionarioLogadoRadio.setToggleGroup(tg);
		funcionarioLogadoRadio.setSelected(true);
		outroFuncionarioRadio.setToggleGroup(tg);
	}
}
