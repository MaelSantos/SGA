package br.com.sga.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.tabelaView.Parte;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.view.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;

public class ControleCadastroContrato {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField valorTotalField;

    @FXML
    private TextField quantidadeParcelaField;

    @FXML
    private TextField nomeClienteField;

    @FXML
    private TextField nomeConsultaField;

    @FXML
    private ComboBox<String> tipoPagamamentoBox;

    @FXML
    private TextArea dadosBancoArea;

    @FXML
    private ComboBox<String> areaBox;

    @FXML
    private TextField objetoField;

    @FXML
    private DatePicker dataContratoPicker;

    @FXML
    private RadioButton dataAtualRadio;

    @FXML
    private TableView<Parte> parteTableView;

    @FXML
    private TableColumn<Parte, Boolean> selecionadoParteTableColumn;

    @FXML
    private TableColumn<Parte, String> nomeParteTableColumn;

    @FXML
    private TableColumn<Parte, String> tipoParteTableColumn;

    @FXML
    private TableColumn<Parte, String> tipoParticipacaoParteTableColumn;

    @FXML
    private Button addParteButton;

    @FXML
    private Button removerParteSelecionadaButton;

    @FXML
    private TextField nomeParteField;

    @FXML
    private ComboBox<String> tipoParteBox;

    @FXML
    private ComboBox<String> tipoParticipcaoBox;

    @FXML
    private Button gerarDocumentoButton;

    @FXML
    private Button salvarContratoButton;
    
    @FXML
    private Button buscarConsultaButton;

    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == buscarConsultaButton) {
    		buscarConsultas();
    	}
    	else if(addParteButton == event.getSource()) {
    		String alerta = addParte();
    		if(alerta != null)
    			Alerta.getInstance().showMensagem("Alerta","", alerta);
    	}else if(removerParteSelecionadaButton == event.getSource()) {
    		System.out.println("indo remover");
    		Alerta.getInstance().showMensagem("Alerta","",removerParte());
    	}else if(gerarDocumentoButton == event.getSource()) {
    		
    	}else if(salvarContratoButton == event.getSource()) {
    		
    	}
    }
    
    private String addParte(){
    	String nome = nomeParteField.getText().trim();
    	String tipo_parte = tipoParteBox.getSelectionModel().getSelectedItem();
    	String tipo_participacao = tipoParticipcaoBox.getSelectionModel().getSelectedItem();
    	
    	if(nome.length() <1 || tipo_parte == null || tipo_participacao == null)
    		return "Não foi possivel adicionar parte: Há campos vazios";
    	parteTableView.getItems().add(new Parte(nome,tipo_parte,tipo_participacao));
    	return null;
    }
    
    private String removerParte() {
    	parteTableView.refresh();
    	ObservableList<Parte> listaParaRemover = FXCollections.observableArrayList();
    	for(Parte p : parteTableView.getItems()) {
    		System.out.println(p.getSelecionado());
    		System.out.println(p.getNome());
    		if(p.getSelecionado())
    			listaParaRemover.add(p);
    	}
    	parteTableView.getItems().removeAll(listaParaRemover);
    	return listaParaRemover.size()+" Partes removidas";
    		
    }
    
    private String salvarProcesso() {
    	// buscar cliente unido a suas consultas
    	// filtrar as consultas
    	return null;
    }
    private void buscarConsultas() {
		String dadoBusca = nomeClienteField.getText().trim();
		if(dadoBusca.length() >0)
			try {
				List<Consulta> consultas = Fachada.getInstance().buscarConsultaPorCliente(dadoBusca);
				ArrayList<String> feedBack = new ArrayList<>();
				for(Consulta e : consultas)
					feedBack.add(e.getData_consulta().toString() +" "+ e.getArea());
				System.out.println(feedBack);
				TextFields.bindAutoCompletion(nomeConsultaField,feedBack);
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Alerta","",e.getMessage());
			}
		else {
			
		}
    }
    
    @FXML
    void initialize() {
    	for(TipoPagamento tipo : TipoPagamento.values())
    		tipoPagamamentoBox.getItems().add(tipo.toString());
    	for(TipoParte tipo : TipoParte.values())
    		tipoParteBox.getItems().add(tipo.toString());
    	for(TipoParticipacao tipo : TipoParticipacao.values())
    		tipoParticipcaoBox.getItems().add(tipo.toString());
    	for(Area tipo : Area.values())
    		areaBox.getItems().add(tipo.toString());
    	
    	selecionadoParteTableColumn.setCellValueFactory(new PropertyValueFactory<>("selecionado"));
        nomeParteTableColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoParteTableColumn.setCellValueFactory( new PropertyValueFactory<>("tipo_parte"));
        tipoParticipacaoParteTableColumn.setCellValueFactory( new PropertyValueFactory<>("tipo_participacao"));
        
        selecionadoParteTableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selecionadoParteTableColumn));
        nomeParteTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tipoParteTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(tipoParteBox.getItems()));
        tipoParticipacaoParteTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(tipoParticipcaoBox.getItems()));
        
        parteTableView.setItems(FXCollections.observableArrayList());
    }
}
