package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoTelefone;
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

    public void actionButton(ActionEvent event) {

    }

	@Override
	public void atualizar(Tela tela, Object object) {
		
	}

	@Override
	public void init() {
			
	}
}
