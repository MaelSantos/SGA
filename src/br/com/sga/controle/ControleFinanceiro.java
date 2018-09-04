package br.com.sga.controle;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleFinanceiro extends Controle {

	@FXML
	private Label lblData;

	@FXML
	private Button btnBuscar;

	@FXML
	private DatePicker tfdDe;

	@FXML
	private DatePicker tfdAte;

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
	private Funcionario funcionario;

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
		
		if (object instanceof Funcionario) {
			if(object != null)
				funcionario = (Funcionario) object;
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

		
		colVencimento1.setCellFactory(coluna -> {
			
			return new TableCell<Receita, Date>() {
				protected void updateItem(Date item, boolean empty) {
					
					super.updateItem(item, empty);

					if (item == null || empty) {
					setText(null);
					} else {
					setText(new SimpleDateFormat("dd/MM/yyyy").format(item));
					}
					}
				};
		});
		colVencimento2.setCellFactory(coluna -> {
			
			return new TableCell<Despesa, Date>() {
				protected void updateItem(Date item, boolean empty) {
					
					super.updateItem(item, empty);

					if (item == null || empty) {
					setText(null);
					} else {
					setText(new SimpleDateFormat("dd/MM/yyyy").format(item));
					}
					}
				};
		});


		
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnBuscar)
		{
			Log log;
			try {

				financeiro = fachada.buscarFinanceiroPorIntervalo(
						Date.from(tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
						Date.from(tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				if(financeiro.getDespesas() != null)
					tblDespesas.getItems().setAll(financeiro.getDespesas());
				if(financeiro.getReceitas() != null)
					tblReceitas.getItems().setAll(financeiro.getReceitas());
				if(tblDespesas.getItems().isEmpty() && tblReceitas.getItems().isEmpty())
					lblData.setText("De: "+tfdDe.getEditor().getText().trim()+" - Até: "+tfdAte.getEditor().getText().trim()+" SEM RESULTADOS!!!");
				else
					lblData.setText("De: "+tfdDe.getEditor().getText().trim()+" - Até: "+tfdAte.getEditor().getText().trim());
				float total_despesas = 0, total_receita = 0;
				for(Receita r : financeiro.getReceitas())
					total_receita += r.getValor();
				for(Despesa d : financeiro.getDespesas())
					total_despesas += d.getValor();
				lblDespesas.setText("Total De Despesas: "+total_despesas);
				lblReceitas.setText("Total De Receitas: "+total_receita);

				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Cocluido", "Busca Concluida Com Sucesso","");
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar Financeiro: "+lblData.getText(), StatusLog.CONCLUIDO);
				
			}catch (BusinessException | NumberFormatException e) {
				e.printStackTrace();
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar Financeiro: Erro - "+lblData.getText(), StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Buscar Dados Financeiros!!!", e.getMessage());
			}
			catch (NullPointerException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Buscar Financeiro: Erro - "+lblData.getText(), StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Preencha Todos os Dados !!!", e.getMessage());
			}
			
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		if(obj == btnAddReceita)
			App.notificarOuvintes(Tela.CADASTRO_RECEITA_DESPESA, financeiro);

	}

	@FXML
	void mouseEntered(MouseEvent event) {
		((Button) (event.getSource())).setStyle("-fx-background-color : #386a78");
	}

	@FXML
	void mouseExited(MouseEvent event) {
		((Button) (event.getSource())).setStyle("-fx-background-color : #008B8B");
	}
	
}
