package br.com.sga.controle;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.MaskFieldUtil;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoPagamento;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleDetalhesContrato extends Controle {

	@FXML
	private TextField dataField;

	@FXML
	private TextField areaField;

	@FXML
	private TextField tipoPagaField;

	@FXML
	private TextField valorTotalField;

	@FXML
	private Button selectConButton;

	@FXML
	private Button salvarButton;

	@FXML
	private TextField objetofField;

	@FXML
	private TextArea bancoField;

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
	private TextField tipoParteField;

	@FXML
	private Button selectParteButton;

	@FXML
	private TextField tipoParticiField;

	@FXML
	private Button voltarButton;

	private Funcionario funcionario;
	private Cliente cliente;
	private Contrato contrato;
	private IFachada fachada;
	private Parcela parcela;
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
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
						"Buscar Contrato: " + cliente.getCpf_cnpj(), StatusLog.CONCLUIDO);
				contrato = new Contrato();
				// ContratoAdapter adapter = dialogo.selecao(contratos,"Selecione
				// Contrato","Selecione um contrato para mais detalhes");
				ContratoAdapter adapter = dialogo.selecionar(contratos);
				contrato.setId(adapter.getId());
				atualizarDadosContrato();
			} catch (BusinessException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
						"Buscar Consulta: Erro", StatusLog.ERRO);
				e.printStackTrace();
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				// TODO Bloco catch gerado automaticamente
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
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(),
							"Editar Parcela: " + parcela.getEstado(), StatusLog.CONCLUIDO);
				} catch (BusinessException | DaoException e) {
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro", "", e.getMessage());
					e.printStackTrace();
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(),
							"Editar Parcela: Erro", StatusLog.ERRO);
				}

				try {
					fachada.salvarEditarLog(log);
				} catch (BusinessException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}
			}
		} else if (contrato != null) {
			if (selectParcelaButton == event.getSource()) {
				// parcela = dialogo.selecao(contrato.getParcelas(),"Selecione
				// Parcelas","Selecione uma parcela para mais detalhes");
				parcela = dialogo.selecionar(contrato.getParcelas());
				valorField.setText(parcela.getValor() + "");
				tipoParcelaField.setText(parcela.getTipo().toString());
				jurosField.setText(parcela.getJuros() + "");
				multaField.setText(parcela.getMulta() + "");
				andamentoBox.setValue(parcela.getEstado());

			} else if (selectParteButton == event.getSource()) {
				// Parte parte = dialogo.selecao(contrato.getPartes(),"Seleção de
				// parte","Selecione uma das partes para mais detalhes ");
				Parte parte = dialogo.selecionar(contrato.getPartes());
				nomeParteField.setText(parte.getNome());
				tipoParteField.setText(parte.getTipo_parte().toString());
				tipoParticiField.setText(parte.getTipo_participacao().toString());
			}
		} else
			Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!", "Ação invalida!!!", "Não há contratos selecionados");

	}

	private void atualizarDadosContrato() {
		try {
			contrato = fachada.buscarContratoPorId(contrato.getId());

			dataField.setText(contrato.getData_contrato().toString());
			areaField.setText(contrato.getArea().toString());
			valorTotalField.setText(contrato.getValor_total() + "");
			bancoField.setText(contrato.getDados_banco());
			tipoPagaField.setText(contrato.getTipo_pagamento().toString());
			objetofField.setText(contrato.getObjeto());

		} catch (BusinessException e) {
			e.printStackTrace();
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
		dataField.setText("");
		areaField.setText("");
		valorTotalField.setText("");
		bancoField.setText("");
		tipoPagaField.setText("");
		objetofField.setText("");

		valorField.setText("");
		tipoParcelaField.setText("");
		jurosField.setText("");
		multaField.setText("");
		andamentoBox.setValue(null);

		nomeParteField.setText("");
		tipoParteField.setText("");
		tipoParticiField.setText("");
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();
		andamentoBox.getItems().addAll(Andamento.CONCLUIDO, Andamento.PENDENTE);
		
		MaskFieldUtil.numericField(valorField);
		MaskFieldUtil.numericField(valorTotalField);
		MaskFieldUtil.numericField(multaField);
		MaskFieldUtil.numericField(jurosField);
	}
}
