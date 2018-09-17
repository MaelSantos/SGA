package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.MaskFieldUtil;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleDetalhesContrato extends Controle {

	@FXML
	private TextField valorTotalField;

	@FXML
	private Button selectConButton;

	@FXML
	private TextField objetofField;

	@FXML
	private Label lblBanco;

	@FXML
	private TextArea bancoField;

	@FXML
	private DatePicker dataField;

	@FXML
	private ComboBox<Area> areaField;

	@FXML
	private ComboBox<TipoPagamento> tipoPagaField;

	@FXML
	private TextField valorField;

	@FXML
	private TextField jurosField;

	@FXML
	private Button selectParcelaButton;

	@FXML
	private TextField multaField;

	@FXML
	private ComboBox<Andamento> andamentoBox;

	@FXML
	private TextField tipoParcelaField;

	@FXML
	private TextField nomeParteField;

	@FXML
	private Button selectParteButton;

	@FXML
	private ComboBox<TipoParte> tipoParteField;

	@FXML
	private ComboBox<TipoParticipacao> tipoParticiField;

	@FXML
	private Button voltarButton;

	@FXML
	private Button btnAtualizar;
	
	private Funcionario funcionario;
	private Cliente cliente;
	private Contrato contrato;
	private IFachada fachada;
	private Parcela parcela;
	private Parte parte;
	private Dialogo dialogo;

	@Override
	public void actionButton(ActionEvent event) {
		if (voltarButton == event.getSource()) {
			if (selectConButton.isVisible()) { // siginifica ter vindo da tela de cliente
				App.notificarOuvintes(Tela.CLIENTES);
			} else
				App.notificarOuvintes(Tela.BUSCAR_CONTRATO);
		} else if (selectConButton == event.getSource()) {
			Log log;
			try {
				List<ContratoAdapter> contratos = fachada.buscarContratoPorClienteAdapter(cliente.getCpf_cnpj());
				
				if(!contratos.isEmpty())
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar Contrato: " + cliente.getCpf_cnpj(), StatusLog.CONCLUIDO);
				else
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar Contrato: " + cliente.getCpf_cnpj(), StatusLog.SEM_RESULTADOS);
				
				contrato = new Contrato();
				// ContratoAdapter adapter = dialogo.selecao(contratos,"Selecione
				// Contrato","Selecione um contrato para mais detalhes");
				ContratoAdapter adapter = dialogo.selecionar(contratos);
				contrato.setId(adapter.getId());
				atualizarDadosContrato();
			} catch (BusinessException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar Consulta: Erro", StatusLog.ERRO);
				e.printStackTrace();
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		} else if (andamentoBox == event.getSource()) {
			if (andamentoBox.getSelectionModel().getSelectedItem() == Andamento.CONCLUIDO) {
				Log log;
				try {
					parcela.setEstado(Andamento.CONCLUIDO);
					fachada.editarParcela(parcela);

					DaoCommun.getInstance()
							.salvarReceita(new Receita(Calendar.getInstance().getTime(), "Parcela", "pagamento parcela",
									parcela.getValor(), false, TipoPagamento.A_VISTA, Calendar.getInstance().getTime()),
									fachada.buscarFinanceiroPorAno(Calendar.getInstance().get(Calendar.YEAR)).getId());
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Atualizado", "Estado da parcela atualizado",
							"CONCLUIDO");
					
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Parcela: " + parcela.getEstado(), StatusLog.CONCLUIDO);
					
				} catch (BusinessException | DaoException e) {
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro", "", e.getMessage());
					e.printStackTrace();
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Parcela: Erro", StatusLog.ERRO);
				}

				try {
					if(log != null)
						fachada.salvarEditarLog(log);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
			
		} 
		else if(event.getSource() == btnAtualizar)
		{
			Log log;
			try {
				atualizarDados();
				fachada.salvarEditarContrato(contrato);
				if(parcela != null)
					DaoCommun.getInstance().editarParcela(parcela);
				if(parte != null)
					DaoCommun.getInstance().editarParte(parte);

				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Cocluido", "Dados Atualizados", "");
				
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Contrato: " + contrato, StatusLog.CONCLUIDO);
				
			} catch (BusinessException | DaoException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Contrato: " + contrato, StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Atualizar Dados ", e.getMessage());
				e.printStackTrace();
			}
			
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}else if (contrato != null) {
			if (selectParcelaButton == event.getSource()) {
				// parcela = dialogo.selecao(contrato.getParcelas(),"Selecione
				// Parcelas","Selecione uma parcela para mais detalhes");
				parcela = dialogo.selecionar(contrato.getParcelas());
				valorField.setText(parcela.getValor() + "");
				tipoParcelaField.setText(parcela.getTipo());
				jurosField.setText(parcela.getJuros() + "");
				multaField.setText(parcela.getMulta() + "");
				andamentoBox.setValue(parcela.getEstado());

			} else if (selectParteButton == event.getSource()) {
				// Parte parte = dialogo.selecao(contrato.getPartes(),"Seleção de
				// parte","Selecione uma das partes para mais detalhes ");
				parte = dialogo.selecionar(contrato.getPartes());
				nomeParteField.setText(parte.getNome());
				tipoParteField.setValue(parte.getTipo_parte());
				tipoParticiField.setValue(parte.getTipo_participacao());
			}
		} else
			Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!", "Ação invalida!!!", "Não há contratos selecionados");
		
	}

	private void atualizarDadosContrato() {
		try {
			contrato = fachada.buscarContratoPorId(contrato.getId());

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			dataField.getEditor().setText(df.format(contrato.getData_contrato()));
			
			areaField.setValue(contrato.getArea());
			valorTotalField.setText(contrato.getValor_total() + "");
			bancoField.setText(contrato.getDados_banco());
			tipoPagaField.setValue(contrato.getTipo_pagamento());
			objetofField.setText(contrato.getObjeto());

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private void atualizarDados()
	{
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(dataField.getEditor().getText().trim());
			contrato.setData_contrato(data);
		} catch (ParseException e) {
			Alerta.getInstance().showMensagem(AlertType.WARNING, "Erro!", "Formato da Data Esta Incorreto!!!", "");
			e.printStackTrace();
		}
		
		contrato.setArea(areaField.getValue());
		contrato.setValor_total(Float.parseFloat(valorTotalField.getText().trim()));
		contrato.setTipo_pagamento(tipoPagaField.getValue()); 
		contrato.setObjeto(objetofField.getText().trim());
		
		if(contrato.getTipo_pagamento() == TipoPagamento.DEPOSITO_EM_CONTA)
			contrato.setDados_banco(bancoField.getText().trim());
		
		if(parcela != null)
		{
			parcela.setEstado(andamentoBox.getValue());
			parcela.setJuros(Float.parseFloat(jurosField.getText().trim()));
			parcela.setMulta(Float.parseFloat(multaField.getText().trim()));
			parcela.setTipo(tipoParcelaField.getText().trim());
			parcela.setValor(Float.parseFloat(valorField.getText().trim()));			
		}
		if(parte != null)
		{
			parte.setNome(nomeParteField.getText().trim());
			parte.setTipo_parte(tipoParteField.getValue());
			parte.setTipo_participacao(tipoParticiField.getValue());			
		}
		
	}
	
	@Override
	public void atualizar(Tela tela, Object object) {
		if (tela == Tela.DETALHES_CONTRATO) {
			limparCampos();
			if (object instanceof Cliente) {
				Cliente cliente = (Cliente) object;
				if (this.cliente == null || !this.cliente.equals(cliente))
					this.cliente = cliente;

				selectConButton.setVisible(true);
			} else if (object instanceof ContratoAdapter) {
				this.contrato = new Contrato();
				this.contrato.setId(((ContratoAdapter) (object)).getId());
				selectConButton.setVisible(false);
				atualizarDadosContrato();
			}else if (object instanceof Contrato) {
				this.contrato = (Contrato) object;
				selectConButton.setVisible(true);
				atualizarDadosContrato();
			}

		} else {
			contrato = null;
		}
		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;

		}
	}

	private void limparCampos() {
		
		dataField.getEditor().setText("");
		areaField.getSelectionModel().clearSelection();
		valorTotalField.setText("");
		bancoField.setText("");
		tipoPagaField.getSelectionModel().clearSelection();
		objetofField.setText("");

		valorField.setText("");
		tipoParcelaField.setText("");
		jurosField.setText("");
		multaField.setText("");
		andamentoBox.getSelectionModel().clearSelection();

		nomeParteField.setText("");
		tipoParteField.getSelectionModel().clearSelection();
		tipoParticiField.getSelectionModel().clearSelection();
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();
		andamentoBox.getItems().addAll(Andamento.CONCLUIDO, Andamento.PENDENTE);
		
		areaField.getItems().setAll(Area.values());
		tipoPagaField.getItems().setAll(TipoPagamento.values());
		tipoParteField.getItems().setAll(TipoParte.values());
		tipoParticiField.getItems().setAll(TipoParticipacao.values());
		
		andamentoBox.setButtonCell(new ListCell<Andamento>() {
			@Override
			protected void updateItem(Andamento item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Andamento");
				} else {
					setText(item.toString());
				}
			}
		});
		
		areaField.setButtonCell(new ListCell<Area>() {
			@Override
			protected void updateItem(Area item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Área");
				} else {
					setText(item.toString());
				}
			}
		});

		tipoPagaField.setButtonCell(new ListCell<TipoPagamento>() {
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
		tipoParteField.setButtonCell(new ListCell<TipoParte>() {
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
		
		tipoParticiField.setButtonCell(new ListCell<TipoParticipacao>() {
			@Override
			protected void updateItem(TipoParticipacao item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Participação");
				} else {
					setText(item.toString());
				}
			}
		});
		
		
		MaskFieldUtil.numericField(valorField);
		MaskFieldUtil.numericField(valorTotalField);
		MaskFieldUtil.numericField(multaField);
		MaskFieldUtil.numericField(jurosField);
	}
}
