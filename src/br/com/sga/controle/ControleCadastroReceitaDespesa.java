package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControleCadastroReceitaDespesa extends Controle{

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnAdd;

	@FXML
	private ComboBox<String> cbxTipo;

	@FXML
	private ComboBox<TipoPagamento> cbxTipoPagamentoGasto;

	@FXML
	private DatePicker tfdEntradaRetirada;

	@FXML
	private DatePicker tfdVencimento;

	@FXML
	private TextField tfdValor;

	@FXML
	private TextField tfdCentroCusto;
	
	@FXML
	private TextField tfdDescricao;

	private Financeiro financeiro;
	private Receita receita;
	private Despesa despesa;
	private int financeiro_id;
	private IFachada fachada;
	private IDaoCommun daoCommun;

	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Financeiro) {
			Financeiro financeiro = (Financeiro) object;
			
			this.financeiro = financeiro;
			financeiro_id = financeiro.getId();
			System.out.println(financeiro_id);
		}

	}

	@Override
	public void init() {

		cbxTipo.getItems().addAll("RECEITA","DESPESA");
		cbxTipoPagamentoGasto.getItems().addAll(TipoPagamento.values());		
		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == cbxTipo)
		{
			if(cbxTipo.getValue().equals("RECEITA"))
			{
				tfdEntradaRetirada.setPromptText("Data De Entrada");
				cbxTipoPagamentoGasto.setPromptText("Tipo De Pagamento");
			}
			if(cbxTipo.getValue().equals("DESPESA"))
			{
				
				tfdEntradaRetirada.setPromptText("Data De Retirada");
				cbxTipoPagamentoGasto.setPromptText("Tipo De Gasto");
			}
		}
		if(obj == btnAdd)
		{
			if(cbxTipo.getValue().equals("RECEITA"))
			{
				receita = new Receita();
				try {
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date data = df.parse(tfdEntradaRetirada.getEditor().getText().trim());
					receita.setData_entrada(data);
					
					Date vencimento = df.parse(tfdVencimento.getEditor().getText().trim());
					receita.setVencimento(vencimento);
				} catch (ParseException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}
				receita.setCentro_custo(tfdCentroCusto.getText().trim());
				receita.setDescricao(tfdDescricao.getText().trim());
				receita.setStatus(true);
				receita.setTipo_pagamento(cbxTipoPagamentoGasto.getValue());
				receita.setValor(Float.parseFloat(tfdValor.getText().trim()));
				
				try {
					financeiro.getReceitas().add(receita);
					fachada.salvarEditarFinanceiro(financeiro);
					daoCommun.salvarReceita(receita, financeiro.getId());
					Alerta.getInstance().showMensagem(AlertType.INFORMATION,"Salvo!", "", "Adicionado Com Sucesso");
				} catch (BusinessException | DaoException e) {
					e.printStackTrace();
					Alerta.getInstance().showMensagem("Erro!", "Erro Ao Salvar Receita", e.getMessage());
				}
				finally {
					limparCampos();
				}
				
			}
			if(cbxTipo.getValue().equals("DESPESA"))
			{
				despesa = new Despesa();
				try {
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date data = df.parse(tfdEntradaRetirada.getEditor().getText().trim());
					despesa.setData_retirada(data);
					
					Date vencimento = df.parse(tfdVencimento.getEditor().getText().trim());
					despesa.setVencimento(vencimento);
				} catch (ParseException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}
				despesa.setCentro_custo(tfdCentroCusto.getText().trim());
				despesa.setDescricao(tfdDescricao.getText().trim());
				despesa.setStatus(true);
				despesa.setTipo_gasto(cbxTipoPagamentoGasto.getValue());
				despesa.setValor(Float.parseFloat(tfdValor.getText().trim()));

				try {
					financeiro.getDespesas().add(despesa);
					fachada.salvarEditarFinanceiro(financeiro);
					daoCommun.salvarDespesa(despesa, financeiro.getId());
					Alerta.getInstance().showMensagem(AlertType.INFORMATION,"Salvo!", "", "Adicionado Com Sucesso");
				} catch (BusinessException | DaoException e) {
					e.printStackTrace();
					Alerta.getInstance().showMensagem("Erro!", "Erro Ao Salvar Despesa", e.getMessage());
				}
				finally {
					limparCampos();
				}
				
			}
		}
		if(obj == btnVoltar)
			App.notificarOuvintes(Tela.financeiro, financeiro);
	}

	private void limparCampos()
	{
		tfdCentroCusto.setText("");
		tfdDescricao.setText("");
		tfdEntradaRetirada.getEditor().setText("");
		tfdValor.setText("");
		tfdVencimento.getEditor().setText("");
	}
	
}
