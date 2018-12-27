package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.MaskFieldUtil;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoFinanceiro;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class ControleCadastroReceitaDespesa extends Controle{

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnAdd;

	@FXML
	private ComboBox<TipoFinanceiro> cbxTipo;

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
	
	@FXML
	private Label lblTipo;

	private Funcionario funcionario;
	private Financeiro financeiro;
	private Receita receita;
	private Despesa despesa;
	private IFachada fachada;
	private IDaoCommun daoCommun;

	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Financeiro) {
			financeiro = (Financeiro) object;

			btnAdd.setText("Adicionar");
			cbxTipo.setVisible(true);
			lblTipo.setVisible(true);
			limparCampos();
		}
		else if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}
		else if(tela == Tela.CADASTRO_RECEITA_DESPESA)
		{
			if (object instanceof Receita) {
				receita = (Receita) object;
				despesa = null;
				btnAdd.setText("Salvar");
				cbxTipo.setVisible(false);
				lblTipo.setVisible(false);
				mudarCampos();
				
			}
			else if (object instanceof Despesa) {
				despesa = (Despesa) object;
				receita = null;
				btnAdd.setText("Salvar");
				cbxTipo.setVisible(false);
				lblTipo.setVisible(false);
				mudarCampos();
			}
			
		}
	}

	@Override
	public void init() {

		cbxTipo.getItems().addAll(TipoFinanceiro.values());
		cbxTipoPagamentoGasto.getItems().addAll(TipoPagamento.values());		
		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();
		
		MaskFieldUtil.numericField(tfdValor);
		
		cbxTipo.setButtonCell(new ListCell<TipoFinanceiro>() {
			@Override
			protected void updateItem(TipoFinanceiro item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo");
				} else {
					setText(item.toString());
				}
			}
		});
		
		cbxTipoPagamentoGasto.setButtonCell(new ListCell<TipoPagamento>() {
			@Override
			protected void updateItem(TipoPagamento item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo Pagamento");
				} else {
					setText(item.toString());
				}
			}
		});
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == cbxTipo)
		{
			if(cbxTipo.getValue() == TipoFinanceiro.RECEITA)
			{
				tfdEntradaRetirada.setPromptText("Data De Entrada");
				cbxTipoPagamentoGasto.setPromptText("Tipo De Pagamento");
			}
			if(cbxTipo.getValue() == TipoFinanceiro.DESPESA)
			{
				
				tfdEntradaRetirada.setPromptText("Data De Retirada");
				cbxTipoPagamentoGasto.setPromptText("Tipo De Gasto");
			}
		}
		if(obj == btnAdd)
		{
			Log log = null;
			if(cbxTipo.getValue() == TipoFinanceiro.RECEITA)
			{
				try {
				if(receita == null)
				{
					receita = new Receita();
					despesa = null;
					mudarDados();					
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(receita.getData_entrada());
					daoCommun.salvarReceita(receita,  fachada.buscarFinanceiroPorAno(Calendar.getInstance().get(Calendar.YEAR)).getId());
					Alerta.getInstance().showMensagem(AlertType.INFORMATION,"Salvo!", "", "Adicionado Com Sucesso");
					
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova Receita: "+receita, StatusLog.CONCLUIDO);
					
					limparCampos();
				}
				else if(receita.getId() != null)
				{
					despesa = null;
					mudarDados();
					daoCommun.editarReceita(receita);
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Receita: "+receita, StatusLog.CONCLUIDO);
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido!", "Receita Atualizada", "");
				}
				
				} catch (BusinessException |DaoException e) {
					e.printStackTrace();
					if(receita.getId() == null)
					{
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova Receita: Erro", StatusLog.ERRO);
						Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Salvar Receita", e.getMessage());
						
					}
					else
					{
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Receita: Erro", StatusLog.ERRO);
						Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Editar Receita", e.getMessage());
					}
					
				}
			}
			if(cbxTipo.getValue() == TipoFinanceiro.DESPESA)
			{

				try {
					
					if(despesa == null)
					{
						despesa = new Despesa();
						receita = null;
						mudarDados();					
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(despesa.getData_retirada());
						daoCommun.salvarDespesa(despesa, fachada.buscarFinanceiroPorAno(Calendar.getInstance().get(Calendar.YEAR)).getId());
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova Despesa: "+despesa, StatusLog.CONCLUIDO);
						Alerta.getInstance().showMensagem(AlertType.INFORMATION,"Salvo!", "Adicionado Com Sucesso", "");
						limparCampos();
					}
					else if(despesa.getId() != null)
					{
						mudarDados();
						receita = null;
						daoCommun.editarDespesa(despesa);
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Despesa: "+despesa, StatusLog.CONCLUIDO);
						Alerta.getInstance().showMensagem(AlertType.INFORMATION,"Concluido", "Despesa Atualizada", "");
					}
				} catch (BusinessException | DaoException e) {
					e.printStackTrace();
					if(despesa.getId() == null)
					{
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova Despesa: Erro", StatusLog.ERRO);
						Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Salvar Despesa", e.getMessage());
						
					}
					else
					{
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Despesa: Erro", StatusLog.ERRO);
						Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Editar Despesa", e.getMessage());
					}
					
				}				
			}
			
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		if(obj == btnVoltar)
			App.notificarOuvintes(Tela.FINANCEIRO, financeiro);
	}

	private void limparCampos()
	{
		cbxTipo.getSelectionModel().clearSelection();
		cbxTipoPagamentoGasto.getSelectionModel().clearSelection();
		tfdCentroCusto.setText("");
		tfdDescricao.setText("");
		tfdEntradaRetirada.getEditor().setText("");
		tfdValor.setText("");
		tfdVencimento.getEditor().setText("");
	}
	
	private void mudarCampos()
	{
		if(despesa != null)
		{
			cbxTipo.setValue(TipoFinanceiro.DESPESA);
			cbxTipoPagamentoGasto.setValue(despesa.getTipo_gasto());
			tfdCentroCusto.setText(despesa.getCentro_custo());
			tfdDescricao.setText(despesa.getDescricao());
			tfdValor.setText(despesa.getValor()+"");
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			tfdEntradaRetirada.getEditor().setText(df.format(despesa.getData_retirada()));
			tfdVencimento.getEditor().setText(df.format(despesa.getVencimento()));
		}
		else if(receita != null)
		{
			cbxTipo.setValue(TipoFinanceiro.RECEITA);
			cbxTipoPagamentoGasto.setValue(receita.getTipo_pagamento());
			tfdCentroCusto.setText(receita.getCentro_custo());
			tfdDescricao.setText(receita.getDescricao());
			tfdValor.setText(receita.getValor()+"");
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			tfdEntradaRetirada.getEditor().setText(df.format(receita.getData_entrada()));
			tfdVencimento.getEditor().setText(df.format(receita.getVencimento()));
		}
	}
	
	private void mudarDados()
	{
		if(despesa != null)
		{
			try {
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date data = df.parse(tfdEntradaRetirada.getEditor().getText().trim());
				despesa.setData_retirada(data);
				
				Date vencimento = df.parse(tfdVencimento.getEditor().getText().trim());
				despesa.setVencimento(vencimento);
			} catch (ParseException e) {
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Erro!", "Formato da Data Errada!!!", "");
				e.printStackTrace();
			}
			despesa.setCentro_custo(tfdCentroCusto.getText().trim());
			despesa.setDescricao(tfdDescricao.getText().trim());
			despesa.setStatus(true);
			despesa.setTipo_gasto(cbxTipoPagamentoGasto.getValue());
			despesa.setValor(Float.parseFloat(tfdValor.getText().trim()));

		}
		else if(receita != null)
		{
			try {
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date data = df.parse(tfdEntradaRetirada.getEditor().getText().trim());
				receita.setData_entrada(data);
				
				Date vencimento = df.parse(tfdVencimento.getEditor().getText().trim());
				receita.setVencimento(vencimento);
			} catch (ParseException e) {
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Erro!", "Formato da Data Errada!!!", "");
				e.printStackTrace();
			}
			receita.setCentro_custo(tfdCentroCusto.getText().trim());
			receita.setDescricao(tfdDescricao.getText().trim());
			receita.setStatus(true);
			receita.setTipo_pagamento(cbxTipoPagamentoGasto.getValue());
			receita.setValor(Float.parseFloat(tfdValor.getText().trim()));			
		}
	}
	
}