package br.com.sga.controle;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.Parte;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ControleCadastroContrato {


    @FXML
    private TextField valorTotalField;

    @FXML
    private ComboBox<Integer> quantidadeParcelasBox;

    @FXML
    private TextField nomeClienteField;

    @FXML
    private ComboBox<String> consultaBox;

    @FXML
    private ComboBox<String> tipoPagamamentoBox;

    @FXML
    private TextArea dadosBancoArea;

    @FXML
    private TextField objetoField;

    @FXML
    private DatePicker dataContratoPicker;

    @FXML
    private RadioButton dataAtualRadio;

    @FXML
    private TableView<Parte> parteTableView;

    @FXML
    private TableColumn<Parte, String> nomeParteTableColumn;

    @FXML
    private TableColumn<Parte, TipoParte> tipoParteTableColumn;

    @FXML
    private TableColumn<Parte, TipoParticipacao> tipoParticipacaoParteTableColumn;

    @FXML
    private Button addParteButton;

    @FXML
    private TextField nomeParteField;

    @FXML
    private ComboBox<TipoParte> tipoParteBox;

    @FXML
    private ComboBox<TipoParticipacao> tipoParticipcaoBox;

    @FXML
    private Button gerarDocumentoButton;

    @FXML
    private Button salvarContratoButton;
    
    @FXML
    private Button buscarConsultaButton;

    @FXML
    private ComboBox<Integer> diaPagamentoBox;

    @FXML
    private TextField jurosField;

    @FXML
    private TextField multaField;
    
    List<Consulta> consultas; 
    IFachada fachada ;

    @FXML
    void actionButton(ActionEvent event) {
    	// condição para tirar da tela quando não for necessário o campo para add informações do banco
    	if(event.getSource() == tipoPagamamentoBox) {
    		if(!tipoPagamamentoBox.getSelectionModel().getSelectedItem().equals(TipoPagamento.A_VISTA.toString())) {
    		     dadosBancoArea.setVisible(true);
    		     quantidadeParcelasBox.setVisible(true);
    		}else {
    			 dadosBancoArea.setText("");
    		     dadosBancoArea.setVisible(false);
    		     quantidadeParcelasBox.setVisible(false);
    		}
    	}
    	else if(event.getSource() == buscarConsultaButton)
    		buscarConsultas();
    	else if(addParteButton == event.getSource())
    		addParte();
    	else if(gerarDocumentoButton == event.getSource()) {}
    	else if(salvarContratoButton == event.getSource())
    		salvarContrato();
    	
    }
    
    private void addParte(){
    	String nome = nomeParteField.getText().trim();
    	TipoParte tipo_parte = tipoParteBox.getSelectionModel().getSelectedItem();
    	TipoParticipacao tipo_participacao = tipoParticipcaoBox.getSelectionModel().getSelectedItem();
    	if(nome.length() <1 || tipo_parte == null || tipo_participacao == null)
    		Alerta.getInstance().showMensagem("Alerta","","Não foi possivel adicionar parte: Há campos vazios");
    	parteTableView.getItems().add(new Parte(tipo_parte, tipo_participacao, nome));
    }
    
    private void salvarContrato() {
    	Financeiro financeiro = null;
    	try {
			financeiro = fachada.buscarFinanceiroPorAno( Calendar.getInstance().get(Calendar.YEAR));
		} catch (BusinessException e2) {
			e2.printStackTrace();
			Alerta.getInstance().showMensagem("Alerta","",e2.getMessage());
			return;
		}
    	// pegando valores da tela
    	String objeto = objetoField.getText().trim();
    	String dados_consulta = consultaBox.getSelectionModel().getSelectedItem();
    	Float valor_total = null;
    	Float juros = null;
    	Float multa = null;
    	Integer quantidade_parcelas = null;
    	
    	try {
	    	valor_total = Float.parseFloat(valorTotalField.getText().trim());
	    	juros = Float.parseFloat(jurosField.getText());
	    	multa = Float.parseFloat(multaField.getText());
	    	quantidade_parcelas = quantidadeParcelasBox.getSelectionModel().getSelectedItem();
	    	
    	}catch (NumberFormatException e) {
    		Alerta.getInstance().showMensagem("Alerta","","Entrada invalida para campos numericos ");
    		return;
    	}
    	Integer dia_pagamento = diaPagamentoBox.getSelectionModel().getSelectedItem();
    	TipoPagamento tipo_pagamento = TipoPagamento.getTipoPagamento(tipoPagamamentoBox.getSelectionModel().getSelectedItem());
    	
    	// pegandoa data caso esteja selecionado a data atual a data do date picker é desconsiderada
    	Date data_contrato = null;
    	if(dataAtualRadio.isSelected())
    		data_contrato = Calendar.getInstance().getTime();
    	else if (dataContratoPicker.getValue() != null){
    		LocalDate ld = dataContratoPicker.getValue();
        	Calendar c =  Calendar.getInstance();
        	c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        	data_contrato = c.getTime();
    	}
    	// pegando dados do banco caso seja diferente de a vista
    	String dados_banco = "";
    	if(tipo_pagamento != TipoPagamento.A_VISTA)
    		dados_banco = dadosBancoArea.getText().trim();
    	else
    		quantidade_parcelas = 1;
    	
    	// pegando lista de partes
    	List<Parte> partes = new ArrayList<>();
    	partes.addAll(parteTableView.getItems());
    	
    	// pegando consulta selecionada
    	Consulta consulta = null;
    	for(Consulta e :consultas) {
    		if(dados_consulta.contains(e.getData_consulta().toString())) {
    			consulta = e;
    		}
    	}
		// pegando id do contrato referente ao ano corrente
    	if(dados_consulta != null) {
			if(data_contrato != null) {
				if(objeto.length()>0  || tipo_pagamento != null || dia_pagamento != null || quantidade_parcelas != null)
					try {
						//gerando as parecelas
				    	List<Parcela> parcelas = new ArrayList<>();
				    	for(int i =0 ; i < quantidade_parcelas; i ++)
				    		parcelas.add(new Parcela((Float)(valor_total/quantidade_parcelas),juros, multa,"CONTRATO",Andamento.PENDENTE,dia_pagamento));
				    	
						fachada.salvarEditarContrato(new Contrato(objeto, valor_total, tipo_pagamento, data_contrato, consulta.getArea(), dados_banco, partes,parcelas, consulta,financeiro));
						Alerta.getInstance().showMensagem("Confirmação","","Contrato salvo com sucesso");
					} catch (BusinessException e1) {
						e1.printStackTrace();
						Alerta.getInstance().showMensagem("Alerta","",e1.getMessage());
					}
				else
					Alerta.getInstance().showMensagem("Alerta","","Há campos vazios ou não selecionados");
			}else
				Alerta.getInstance().showMensagem("Alerta","","Nenhuma data selecionada : selecione a data atual ou especifica");
		}else
			Alerta.getInstance().showMensagem("Alerta","","Nenhuma consulta com o cliente foi selecionada");
    }
    
    private void buscarConsultas() {
		String dadoBusca = nomeClienteField.getText().trim();
		if(dadoBusca.length() >0)
			try {
				consultaBox.getItems().clear();
				consultas = Fachada.getInstance().buscarConsultaPorCliente(dadoBusca);
				ArrayList<String> feedBack = new ArrayList<>();
				for(Consulta e : consultas)
					feedBack.add(e.toString());
				System.out.println(feedBack);
				consultaBox.getItems().addAll(feedBack);
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Alerta","",e.getMessage());
			}
		else {
			
		}
    }
    
    @FXML
    void initialize() {
    	fachada = Fachada.getInstance();
    	for(TipoPagamento tipo : TipoPagamento.values())
    		tipoPagamamentoBox.getItems().add(tipo.toString());
    	
    	tipoParteBox.getItems().addAll(TipoParte.values());
		tipoParticipcaoBox.getItems().addAll(TipoParticipacao.values());
    	
		for(int i = 1 ; i <=28 ; i ++)
    		diaPagamentoBox.getItems().add(i);
    	for(int i = 1 ; i <=12 ; i ++)
    		quantidadeParcelasBox.getItems().add(i);
    	
        nomeParteTableColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoParteTableColumn.setCellValueFactory( new PropertyValueFactory<>("tipo_parte"));
        tipoParticipacaoParteTableColumn.setCellValueFactory( new PropertyValueFactory<>("tipo_participacao"));
        
       // nomeParteTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
       // tipoParteTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(tipoParteBox.getItems()));
       // tipoParticipacaoParteTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(tipoParticipcaoBox.getItems()));
        
        parteTableView.setItems(FXCollections.observableArrayList());
        
        dadosBancoArea.setVisible(false);
    }
}
