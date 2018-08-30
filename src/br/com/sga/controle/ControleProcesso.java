package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoProcesso;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ControleProcesso extends Controle {

	@FXML
	private TableView<ProcessoAdapter> tbl1Vara;

	@FXML
	private TableColumn<ProcessoAdapter, Integer> colProcesso1;

	@FXML
	private TableColumn<ProcessoAdapter, String> colComarca1;

	@FXML
	private TableColumn<ProcessoAdapter, String> colPartes1;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAndamento1;

	@FXML
	private TableColumn<ProcessoAdapter, Date> colData1;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAcoes1;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAcoes2;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAcoes3;

	@FXML
	private TableView<ProcessoAdapter> tbl2Vara;

	@FXML
	private TableColumn<ProcessoAdapter, Integer> colProcesso2;

	@FXML
	private TableColumn<ProcessoAdapter, String> colComarca2;

	@FXML
	private TableColumn<ProcessoAdapter, String> colPartes2;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAndamento2;

	@FXML
	private TableColumn<ProcessoAdapter, Date> colData2;

	@FXML
	private TableView<ProcessoAdapter> tbl3Vara;

	@FXML
	private TableColumn<ProcessoAdapter, Integer> colProcesso3;

	@FXML
	private TableColumn<ProcessoAdapter, String> colComarca3;

	@FXML
	private TableColumn<ProcessoAdapter, String> colPartes3;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAndamento3;

	@FXML
	private TableColumn<ProcessoAdapter, Date> colData3;

	@FXML
	private Button btnCadastrar;

	private IFachada fachada;
	private Funcionario funcionario;

	@FXML
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnCadastrar)
			App.notificarOuvintes(Tela.CADASTRO_PROCESSO);
	}

	public void init() {

		fachada = Fachada.getInstance();

		colAndamento1.setCellValueFactory(new PropertyValueFactory<>("decisao"));
		colAndamento2.setCellValueFactory(new PropertyValueFactory<>("decisao"));
		colAndamento3.setCellValueFactory(new PropertyValueFactory<>("decisao"));

		colComarca1.setCellValueFactory(new PropertyValueFactory<>("comarca"));
		colComarca2.setCellValueFactory(new PropertyValueFactory<>("comarca"));
		colComarca3.setCellValueFactory(new PropertyValueFactory<>("comarca"));

		colData1.setCellValueFactory(new PropertyValueFactory<>("data_atuacao"));
		colData2.setCellValueFactory(new PropertyValueFactory<>("data_atuacao"));
		colData3.setCellValueFactory(new PropertyValueFactory<>("data_atuacao"));

		colPartes1.setCellValueFactory(new PropertyValueFactory<>("partes"));
		colPartes2.setCellValueFactory(new PropertyValueFactory<>("partes"));
		colPartes3.setCellValueFactory(new PropertyValueFactory<>("partes"));

		colProcesso1.setCellValueFactory(new PropertyValueFactory<>("numero"));
		colProcesso2.setCellValueFactory(new PropertyValueFactory<>("numero"));
		colProcesso3.setCellValueFactory(new PropertyValueFactory<>("numero"));

		colAcoes1.setCellValueFactory(new PropertyValueFactory<>("numero"));
		colAcoes2.setCellValueFactory(new PropertyValueFactory<>("numero"));
		colAcoes3.setCellValueFactory(new PropertyValueFactory<>("numero"));

		colAcoes1.setCellFactory(
				new Callback<TableColumn<ProcessoAdapter, String>, TableCell<ProcessoAdapter, String>>() {
					@Override
					public TableCell<ProcessoAdapter, String> call(TableColumn<ProcessoAdapter, String> column) {
						return new TableCell<ProcessoAdapter, String>() {
							Button b = new Button("Detalhes");

							@Override
							protected void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (item == null || empty) {
									setText(null);
									setStyle("");
								} else {
									Button b = new Button("Detalhes");
									b.setOnAction((a) -> {
										App.notificarOuvintes(Tela.DETALHES_PROCESSO,
												tbl1Vara.getSelectionModel().getSelectedItem());
									});
									setGraphic(b);
								}
							}
						};

					}
				});

		colAcoes2.setCellFactory(
				new Callback<TableColumn<ProcessoAdapter, String>, TableCell<ProcessoAdapter, String>>() {
					@Override
					public TableCell<ProcessoAdapter, String> call(TableColumn<ProcessoAdapter, String> column) {
						return new TableCell<ProcessoAdapter, String>() {
							Button b = new Button("Detalhes");

							@Override
							protected void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (item == null || empty) {
									setText(null);
									setStyle("");
								} else {
									Button b = new Button("Detalhes");
									b.setOnAction((a) -> {
										App.notificarOuvintes(Tela.DETALHES_PROCESSO,
												tbl2Vara.getSelectionModel().getSelectedItem());
									});
									setGraphic(b);
								}
							}
						};

					}
				});

		// tbl1Vara.setRowFactory(tv -> );

		colAcoes3.setCellFactory(
				new Callback<TableColumn<ProcessoAdapter, String>, TableCell<ProcessoAdapter, String>>() {
					@Override
					public TableCell<ProcessoAdapter, String> call(TableColumn<ProcessoAdapter, String> column) {
						return new TableCell<ProcessoAdapter, String>() {
							Button b = new Button("Detalhes");

							@Override
							protected void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (item == null || empty) {
									setText(null);
									setStyle("");
								} else {
									Button b = new Button("Detalhes");
									b.setOnAction((a) -> {
										App.notificarOuvintes(Tela.DETALHES_PROCESSO,
												tbl3Vara.getSelectionModel().getSelectedItem());
									});
									setGraphic(b);
								}
							}
						};

					}
				});

		Log log;
		try {

			tbl1Vara.getItems().addAll(fachada.buscaAllProcessoAdapter(TipoProcesso.VARA_1.toString()));
			tbl2Vara.getItems().addAll(fachada.buscaAllProcessoAdapter(TipoProcesso.VARA_2.toString()));
			tbl3Vara.getItems().addAll(fachada.buscaAllProcessoAdapter(TipoProcesso.VARA_CRIMINAL.toString()));

			if (tbl1Vara.getItems().isEmpty() && tbl2Vara.getItems().isEmpty() && tbl3Vara.getItems().isEmpty())
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, "Sistema",
						"Buscar Processos: Sem Resultados", StatusLog.SEM_RESULTADOS);
			else
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, "Sistema", "Buscar Processos: ",
						StatusLog.CONCLUIDO);

		} catch (Exception e) {
			e.printStackTrace();
			Alerta.getInstance().showMensagem("Erro!!!", "Erro Ao Carregar Processos", e.getMessage());
			log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, "Sistema", "Buscar Processos: ",
					StatusLog.ERRO);
		}

		try {
			if (log != null)
				fachada.salvarEditarLog(log);
		} catch (BusinessException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}

		tbl1Vara.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tbl1Vara.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES_PROCESSO, tbl1Vara.getSelectionModel().getSelectedItem());
		});
		tbl2Vara.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tbl2Vara.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES_PROCESSO, tbl2Vara.getSelectionModel().getSelectedItem());
		});
		tbl3Vara.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tbl3Vara.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES_PROCESSO, tbl3Vara.getSelectionModel().getSelectedItem());
		});

	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Processo) {
			Processo processo = (Processo) object;

			if (tela == Tela.CADASTRO_PROCESSO)
				if (processo.getTipo_processo() == TipoProcesso.VARA_1) {
					for (ProcessoAdapter a : tbl1Vara.getItems())
						if (!(a.getId() == processo.getId()))
							tbl1Vara.getItems().add(ProcessoAdapter.ToAdapter(processo));
				}
			if (processo.getTipo_processo() == TipoProcesso.VARA_2) {
				for (ProcessoAdapter a : tbl2Vara.getItems())
					if (!(a.getId() == processo.getId()))
						tbl2Vara.getItems().add(ProcessoAdapter.ToAdapter(processo));
			}
			if (processo.getTipo_processo() == TipoProcesso.VARA_CRIMINAL) {
				for (ProcessoAdapter a : tbl3Vara.getItems())
					if (!(a.getId() == processo.getId()))
						tbl3Vara.getItems().add(ProcessoAdapter.ToAdapter(processo));
			}
		}

		if (object instanceof Funcionario) {
			if (object != null)
				funcionario = (Funcionario) object;
		}

	}

}
