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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ControleProcesso extends Controle {

	@FXML
	private Button btnCadastrar;

	@FXML
	private TextField tfdBusca;

	@FXML
	private Button btnBuscar;

	@FXML
	private ComboBox<TipoProcesso> cbxTipo;

	@FXML
	private TableView<ProcessoAdapter> tblProcesso;

	@FXML
	private TableColumn<ProcessoAdapter, String> colNumero;

	@FXML
	private TableColumn<ProcessoAdapter, String> colComarca;

	@FXML
	private TableColumn<ProcessoAdapter, String> colPartes;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAndamento;

	@FXML
	private TableColumn<ProcessoAdapter, String> colData;

	@FXML
	private TableColumn<ProcessoAdapter, String> colAcoes;

	private IFachada fachada;
	private Funcionario funcionario;

	@FXML
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnCadastrar)
			App.notificarOuvintes(Tela.CADASTRO_PROCESSO);
		if(obj == btnBuscar)
		{
			try {
			if(!tfdBusca.getText().trim().isEmpty())
			{
					tblProcesso.getItems().setAll(fachada.buscaAllProcessoAdapter(cbxTipo.getValue().toString()));
			}
			else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!", "Informe Um Dado Para Buscar!!!", "");
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Buscar Processo!!!", e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void init() {

		fachada = Fachada.getInstance();

		colAndamento.setCellValueFactory(new PropertyValueFactory<>("decisao"));

		colComarca.setCellValueFactory(new PropertyValueFactory<>("comarca"));

		colData.setCellValueFactory(new PropertyValueFactory<>("data_atuacao"));

		colPartes.setCellValueFactory(new PropertyValueFactory<>("partes"));

		colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

		colAcoes.setCellValueFactory(new PropertyValueFactory<>("numero"));

		colAcoes.setCellFactory(
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
												tblProcesso.getSelectionModel().getSelectedItem());
									});
									setGraphic(b);
								}
							}
						};

					}
				});
		
		tblProcesso.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblProcesso.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES_PROCESSO, tblProcesso.getSelectionModel().getSelectedItem());
		});

		cbxTipo.getItems().setAll(TipoProcesso.values());
		
	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			if (object != null)
				funcionario = (Funcionario) object;
		}

	}

}
