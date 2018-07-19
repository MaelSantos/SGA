package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleFinanceiro extends Controle {

	@FXML
	private Label lblAno;

	@FXML
	private TextField tfdAno;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<Receita> tblReceitas;

	@FXML
	private TableColumn<Receita, Float> colValor1;

	@FXML
	private TableColumn<Receita, String> colTipo1;

	@FXML
	private TableColumn<Receita, String> colDescricao1;

	@FXML
	private TableColumn<Receita, Date> colVencimento1;

	@FXML
	private TableColumn<Receita, Boolean> colStatus1;

	@FXML
	private TableView<Despesa> tblDespesas;

	@FXML
	private TableColumn<Despesa, Float> colValor2;

	@FXML
	private TableColumn<Despesa, String> colCentroCusto;

	@FXML
	private TableColumn<Despesa, String> colDescricao2;

	@FXML
	private TableColumn<Despesa, Date> colVencimento2;

	@FXML
	private TableColumn<Despesa, Boolean> colStatus2;

	@FXML
	private Button btnAddReceita;

	@FXML
	private Button btnAddDespesa;

	@FXML
	private Label lblReceitas;

	@FXML
	private Label lblDespesas;

	
	private IFachada fachada;
	private Financeiro financeiro;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Financeiro) {
			Financeiro financeiro = (Financeiro) object;
			
			if (financeiro.getId() == this.financeiro.getId()) {
				
				lblDespesas.setText("Total De Despesas: "+financeiro.getTotal_despesas());
				lblReceitas.setText("Total De Receitas: "+financeiro.getTotal_lucro());
				
				if(financeiro.getDespesas() != null)
					tblDespesas.getItems().setAll(financeiro.getDespesas());
				if(financeiro.getReceitas() != null)
					tblReceitas.getItems().setAll(financeiro.getReceitas());
				
			}
		}
	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();

		colCentroCusto.setCellValueFactory(
				new PropertyValueFactory<>("centro_custo"));
		colDescricao1.setCellValueFactory(
				new PropertyValueFactory<>("descricao"));
		colDescricao2.setCellValueFactory(
				new PropertyValueFactory<>("descricao"));
		colStatus1.setCellValueFactory(
				new PropertyValueFactory<>("status"));
		colStatus2.setCellValueFactory(
				new PropertyValueFactory<>("status"));
		colTipo1.setCellValueFactory(
				new PropertyValueFactory<>("tipo_pagamento"));
		colValor1.setCellValueFactory(
				new PropertyValueFactory<>("valor"));
		colValor2.setCellValueFactory(
				new PropertyValueFactory<>("valor"));
		colVencimento1.setCellValueFactory(
				new PropertyValueFactory<>("vencimento"));
		colVencimento2.setCellValueFactory(
				new PropertyValueFactory<>("vencimento"));

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnBuscar)
		{
			try {

				financeiro = fachada.buscarFinanceiroPorAno(Integer.parseInt(tfdAno.getText().trim()));
				if(financeiro.getDespesas() != null)
					tblDespesas.getItems().setAll(financeiro.getDespesas());
				if(financeiro.getReceitas() != null)
					tblReceitas.getItems().setAll(financeiro.getReceitas());
				lblAno.setText("Ano: "+financeiro.getAno_coberto());
				lblDespesas.setText("Total De Despesas: "+financeiro.getTotal_despesas());
				lblReceitas.setText("Total De Receitas: "+financeiro.getTotal_lucro());

			}catch (BusinessException | NumberFormatException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Erro!", "Erro Ao Buscar Dados Financeiros!!!", e.getMessage());
			}
		}
		if(obj == btnAddReceita)
			App.notificarOuvintes(Tela.Cadastro_Receita_Despesa, financeiro);

	}

}
