package br.com.sga.controle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleCadastroContrato {

    @FXML
    private TextField valorTotalField;

    @FXML
    private ComboBox<Integer> quantidadeParcelasBox;

    @FXML
    private TextField nomeClienteField;

    @FXML
    private Label dadosConsultaLabel;

    @FXML
    private ComboBox<String> tipoPagamamentoBox;

    @FXML
    private TextArea dadosBancoArea;

    @FXML
    private TextField objetoField;

    @FXML
    private DatePicker dataContratoPicker;


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
    
    @FXML
	private Button voltarButton;
    
    private Consulta consulta;
    private IFachada fachada;
    private Funcionario funcionario;

    @FXML
    void actionButton(ActionEvent event) {
    	// condição para tirar da tela quando não for necessário o campo para add informações do banco
    	if(voltarButton == event.getSource() ) {
    		App.notificarOuvintes(Tela.buscar_contrato);
    	}
    	else if(event.getSource() == tipoPagamamentoBox) {
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
    	{
    		
			try {
				String busca[] = {nomeClienteField.getText().trim()};
				List<ConsultaAdapter> consultas = Fachada.getInstance().buscarConsultaPorClienteAdapter(busca);
				ConsultaAdapter consultaBasica = Dialogo.getInstance().selecionar(consultas);
				consulta = new Consulta();
				consulta.setId(consultaBasica.getId());
				consulta.setArea(consultaBasica.getArea());
				consulta.setData_consulta(consultaBasica.getData());
				consulta.setValor_honorario(consultaBasica.getValor_honorario());
				dadosConsultaLabel.setText(consultaBasica.toString());
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Erro","",e.getMessage());
			}
			
    	}
    	else if(addParteButton == event.getSource())
    		if(nomeParteField.getText().trim().length() >1 && tipoParteBox.getSelectionModel().getSelectedItem() != null
    			&& tipoParticipcaoBox.getSelectionModel().getSelectedItem() != null)
    		{
    			String nome = nomeParteField.getText().trim();
	        	TipoParte tipo_parte = tipoParteBox.getSelectionModel().getSelectedItem();
	        	TipoParticipacao tipo_participacao = tipoParticipcaoBox.getSelectionModel().getSelectedItem();
	        	parteTableView.getItems().add(new Parte(tipo_parte, tipo_participacao, nome));
    		}else
    			Alerta.getInstance().showMensagem("Alerta","","Não foi possivel adicionar parte:\nHá campos obrigatorios vazios");
    		
    	else if(gerarDocumentoButton == event.getSource()) {}
    	else if(salvarContratoButton == event.getSource())
    		salvarContrato();
    	
    }

    private void salvarContrato() {
    	Log log = null;
    	try {
	    	Financeiro financeiro = fachada.buscarFinanceiroPorAno( Calendar.getInstance().get(Calendar.YEAR));
	    	Float valor_total = Float.parseFloat(valorTotalField.getText().trim());
		    Float juros = Float.parseFloat(jurosField.getText());
		    Float multa = Float.parseFloat(multaField.getText());
		    Integer quantidade_parcelas = quantidadeParcelasBox.getSelectionModel().getSelectedItem();
	    	Integer dia_pagamento = diaPagamentoBox.getSelectionModel().getSelectedItem();
	    	TipoPagamento tipo_pagamento = TipoPagamento.getTipoPagamento(tipoPagamamentoBox.getSelectionModel().getSelectedItem());
	    	String objeto = objetoField.getText().trim();
	    	
	    	// pegando dados do banco caso seja diferente de a vista
	    	String dados_banco = "";
	    	if(tipo_pagamento != TipoPagamento.A_VISTA)
	    		dados_banco = dadosBancoArea.getText().trim();
	    	else
	    		quantidade_parcelas = 1;
	    	
	    	
	    	if(dadosConsultaLabel.getText().length() >0 && consulta != null) {
				if(dataContratoPicker.getValue() != null) {
					if(objeto.length() >0  && tipo_pagamento != null && dia_pagamento != null 
							&& quantidade_parcelas != null) {
							
							// pegando data da tela
							LocalDate ld = dataContratoPicker.getValue();
				        	Calendar c =  Calendar.getInstance();
				        	c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				        	Date data_contrato  = c.getTime();
						
							//gerando as parecelas
							List<Parcela> parcelas = new ArrayList<>();
					    	for(int i =0 ; i < quantidade_parcelas; i ++)
					    		parcelas.add(new Parcela((Float)(valor_total/quantidade_parcelas),juros, multa,"CONTRATO",Andamento.PENDENTE,dia_pagamento));
					    	
					    	Contrato contrato = new Contrato(objeto, valor_total, tipo_pagamento, data_contrato, consulta.getArea(), 
					    			dados_banco, parteTableView.getItems(),parcelas, consulta,financeiro);
					    	
					    	fachada.salvarEditarContrato(contrato);
					    
					    	for(Parcela parcela : contrato.getParcelas())
					    		fachada.salvarEditarNotificacao
					    		(new Notificacao(TipoNotificacao.FINANCEIRO,Prioridade.MEDIA,"Parcela de contrato ",
					    				Andamento.PENDENTE,parcela.getVencimento(),fachada.buscarUsuarioPorBusca("%_%")));
					    	
					    	
							Alerta.getInstance().showMensagem("Confirmação","","Contrato salvo com sucesso");
							log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Novo Contrato: "+contrato.getArea(), StatusLog.COLCLUIDO);
							limparCampos();
					}else
						Alerta.getInstance().showMensagem("Alerta","","Há campos obrigatorios vazios ou não selecionados");
				}else
					Alerta.getInstance().showMensagem("Alerta","","Nenhuma data selecionada : possivelmente a data escolhida é invalida");
			}else
				Alerta.getInstance().showMensagem("Alerta","","Nenhuma consulta com o cliente foi selecionada");
    	
    	}catch (NumberFormatException e) {
    		Alerta.getInstance().showMensagem("Alerta","","Entrada invalida para campos numericos ");
    	}catch (BusinessException e) {
			e.printStackTrace();
			log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Novo Contrato: Erro", StatusLog.ERRO);
			Alerta.getInstance().showMensagem("Erro","",e.getMessage());
		}
    	
    	try {
    		if(log != null)
    			fachada.salvarEditarLog(log);
		} catch (BusinessException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
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
        dataContratoPicker.setValue(LocalDate.now());
        dadosBancoArea.setVisible(false);
    }
    
    private void limparCampos() {
		
    	dadosBancoArea.setText("");
    	dataContratoPicker.getEditor().setText("");
    	jurosField.setText("");
		multaField.setText("");
		nomeClienteField.setText("");
		nomeParteField.setText("");
		objetoField.setText("");
		valorTotalField.setText("");
		dataContratoPicker.setValue(LocalDate.now());
		consulta = null;
		dadosConsultaLabel.setText("");
		
		
	}

}
