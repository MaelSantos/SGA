package br.com.sga.controle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.business.BusinessUtil;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.MaskFieldUtil;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleCadastroContrato extends Controle{

	@FXML
	private TextField valorTotalField;

	@FXML
	private ComboBox<Integer> quantidadeParcelasBox;

	@FXML
	private TextField nomeClienteField;

	@FXML
	private Label dadosConsultaLabel;

	@FXML
	private ComboBox<TipoPagamento> tipoPagamamentoBox;

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
	private Button addParteButton;

	@FXML
	private TextField nomeParteField;

	@FXML
	private ComboBox<TipoParte> tipoParteBox;


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
	public void actionButton(ActionEvent event) {
		// condi��o para tirar da tela quando n�o for necess�rio o campo para add
		// informa��es do banco
		if (voltarButton == event.getSource()) {
			App.notificarOuvintes(Tela.BUSCAR_CONTRATO);
		} else if (event.getSource() == tipoPagamamentoBox) {
			if (!tipoPagamamentoBox.getSelectionModel().getSelectedItem().equals(TipoPagamento.A_VISTA))
				quantidadeParcelasBox.setVisible(true);
			else
				quantidadeParcelasBox.setVisible(false);
			if(tipoPagamamentoBox.getValue().equals(TipoPagamento.DEPOSITO_EM_CONTA))
				dadosBancoArea.setVisible(true);
			else
			{
				dadosBancoArea.setText("");
				dadosBancoArea.setVisible(false);
			}
		} else if (event.getSource() == buscarConsultaButton) {
			parteTableView.getItems().clear();
			try {
				String busca[] = { nomeClienteField.getText().trim() };

				if (!nomeClienteField.getText().trim().isEmpty()) {
					List<ConsultaAdapter> consultas = Fachada.getInstance().buscarConsultaPorClienteAdapter(busca);
					ConsultaAdapter consultaBasica = Dialogo.getInstance().selecionar(consultas);
					consulta = new Consulta();
					consulta.setId(consultaBasica.getId());
					consulta.setArea(consultaBasica.getArea());
					consulta.setData_consulta(consultaBasica.getData());
					consulta.setValor_honorario(consultaBasica.getValor_honorario());
					parteTableView.getItems().add(new Parte(TipoParte.ATIVO,consultaBasica.getNome_cliente()));
					dadosConsultaLabel.setText(consultaBasica.toString());
				} else
					Alerta.getInstance().showMensagem(AlertType.WARNING, "A��o Nescessaria!","Informe um dado para pesquisa!!!", "");
				
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro", "", e.getMessage());
			}

		} else if (addParteButton == event.getSource())
			if (nomeParteField.getText().trim().length() > 1
					&& tipoParteBox.getSelectionModel().getSelectedItem() != null) {
				String nome = nomeParteField.getText().trim();
				TipoParte tipo_parte = tipoParteBox.getSelectionModel().getSelectedItem();
				parteTableView.getItems().add(new Parte(tipo_parte,nome));
			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
						"N�o foi possivel adicionar parte:\nH� campos obrigatorios vazios");

		else if (gerarDocumentoButton == event.getSource()) {
		} else if (salvarContratoButton == event.getSource())
			salvarContrato();

	}

	private void salvarContrato() {
		Log log = null;
		try {
			Financeiro financeiro = fachada.buscarFinanceiroPorAno(Calendar.getInstance().get(Calendar.YEAR));
			Float valor_total = Float.parseFloat(valorTotalField.getText().trim());
			Float taxa_juros = Float.parseFloat(jurosField.getText());
			Float taxa_multa = Float.parseFloat(multaField.getText());
			Integer quantidade_parcelas = quantidadeParcelasBox.getSelectionModel().getSelectedItem();
			Integer dia_pagamento = diaPagamentoBox.getSelectionModel().getSelectedItem();
			TipoPagamento tipo_pagamento = tipoPagamamentoBox.getSelectionModel().getSelectedItem();
			String objeto = objetoField.getText().trim();

			// pegando dados do banco caso seja diferente de a vista
			String dados_banco = "";
			if (tipo_pagamento == TipoPagamento.DEPOSITO_EM_CONTA)
				dados_banco = dadosBancoArea.getText().trim();
			else
				quantidade_parcelas = 1;

			if (dadosConsultaLabel.getText().length() > 0 && consulta != null) {
				if (dataContratoPicker.getValue() != null) {
					if (objeto.length() > 0 && tipo_pagamento != null && dia_pagamento != null
							&& quantidade_parcelas != null) {

						Date data_contrato = BusinessUtil.toDate(dataContratoPicker);

						// gerando as parecelas
						List<Parcela> parcelas = new ArrayList<>();
						for (int i = 0; i < quantidade_parcelas; i++)
							parcelas.add(new Parcela((Float) (valor_total / quantidade_parcelas), "CONTRATO",
									dia_pagamento));
						
						Contrato contrato = new Contrato(objeto, valor_total, tipo_pagamento, data_contrato,
								consulta.getArea(), dados_banco, parteTableView.getItems(), parcelas, taxa_multa,
								taxa_juros, consulta, financeiro);

						fachada.salvarEditarContrato(contrato);

						for (Parcela parcela : contrato.getParcelas())
							fachada.salvarEditarNotificacao(new Notificacao(TipoNotificacao.FINANCEIRO,
									Prioridade.MEDIA, "Parcela de contrato ", Andamento.PENDENTE,
									parcela.getVencimento(), fachada.buscarUsuarioPorBusca("%_%")));

						Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Confirma��o", "",
								"Contrato salvo com sucesso");
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
								"Novo Contrato: " + contrato.getArea(), StatusLog.CONCLUIDO);
						limparCampos();
					} else
						Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
								"H� campos obrigatorios vazios ou n�o selecionados");
				} else
					Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
							"Nenhuma data selecionada : possivelmente a data escolhida � invalida");
			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
						"Nenhuma consulta com o cliente foi selecionada");

		} catch (NumberFormatException e) {
			Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
					"Entrada invalida para campos numericos ");
		} catch (BusinessException e) {
			e.printStackTrace();
			log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
					"Novo Contrato: Erro", StatusLog.ERRO);
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro", "", e.getMessage());
		}
		 catch (NullPointerException e) {
				e.printStackTrace();
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
						"Novo Contrato: Erro", StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro", "", e.getMessage());
			}

		try {
			if (log != null)
				fachada.salvarEditarLog(log);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

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
		
		diaPagamentoBox.getSelectionModel().clearSelection();
		quantidadeParcelasBox.getSelectionModel().clearSelection();
		tipoPagamamentoBox.getSelectionModel().clearSelection();
		tipoParteBox.getSelectionModel().clearSelection();

	}

	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
			
		}
		
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();

		tipoPagamamentoBox.getItems().addAll(TipoPagamento.values());
		tipoParteBox.getItems().addAll(TipoParte.values());

		for (int i = 1; i <= 28; i++)
			diaPagamentoBox.getItems().add(i);
		for (int i = 1; i <= 12; i++)
			quantidadeParcelasBox.getItems().add(i);

		nomeParteTableColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tipoParteTableColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));

		parteTableView.setItems(FXCollections.observableArrayList());
		dataContratoPicker.setValue(LocalDate.now());

		MaskFieldUtil.numericField(valorTotalField);
		MaskFieldUtil.numericField(jurosField);
		MaskFieldUtil.numericField(multaField);

		diaPagamentoBox.setButtonCell(new ListCell<Integer>() {
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Dia Para Pagamento");
				} else {
					setText(item.toString());
				}
			}
		});
		
		quantidadeParcelasBox.setButtonCell(new ListCell<Integer>() {
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Quantidade de Parcelas");
				} else {
					setText(item.toString());
				}
			}
		});
		
		tipoPagamamentoBox.setButtonCell(new ListCell<TipoPagamento>() {
			@Override
			protected void updateItem(TipoPagamento item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Pagamento");
				} else {
					setText(item.toString());
				}
			}
		});
		
		tipoParteBox.setButtonCell(new ListCell<TipoParte>() {
			@Override
			protected void updateItem(TipoParte item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo da Parte");
				} else {
					setText(item.toString());
				}
			}
		});
		
	}

}
