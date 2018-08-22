package br.com.sga.controle;

import java.time.ZoneId;
import java.util.Date;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleHistorico extends Controle {

	@FXML
	private Label lblData;

	@FXML
	private Button btnBuscar;

	@FXML
	private DatePicker tfdDe;

	@FXML
	private DatePicker tfdAte;

	@FXML
	private TableView<Log> tblLogs;

	@FXML
	private TableColumn<Log, Date> colData;

	@FXML
	private TableColumn<Log, String> colRemetente;

	@FXML
	private TableColumn<Log, EventoLog> colEvento;

	@FXML
	private TableColumn<Log, String> colDestinatario;

	@FXML
	private TableColumn<Log, StatusLog> colStatus;

	@FXML
	private ComboBox<EventoLog> cbxEvento;

	@FXML
	private ComboBox<StatusLog> cbxStatus;

	@FXML
	private Button btnRemoverFiltros;

	private IFachada fachada;
	private Funcionario funcionario;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Log) {
			Log log = (Log) object;

			tblLogs.getItems().add(log);

		}

		if (object instanceof Funcionario) {
			Funcionario funcionario = (Funcionario) object;

			this.funcionario = funcionario;

		}

	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();

		colData.setCellValueFactory(new PropertyValueFactory<>("data"));
		colDestinatario.setCellValueFactory(new PropertyValueFactory<>("destinatario"));
		colEvento.setCellValueFactory(new PropertyValueFactory<>("evento"));
		colRemetente.setCellValueFactory(new PropertyValueFactory<>("remetente"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		cbxEvento.getItems().addAll(EventoLog.values());
		cbxStatus.getItems().addAll(StatusLog.values());

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			Log log;
			try {
				String status = "";
				String evento = "";
				if (cbxEvento.getSelectionModel().getSelectedItem() != null)
					evento = cbxEvento.getSelectionModel().getSelectedItem().name();

				if (cbxStatus.getSelectionModel().getSelectedItem() != null)
					status = cbxStatus.getSelectionModel().getSelectedItem().name();

				tblLogs.getItems().setAll(fachada.buscarLogPorData(
						Date.from(tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
						Date.from(tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), evento, status));

				System.out.println("De: " + tfdDe.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				System.out.println("Ate: " + tfdAte.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

				if (!(tblLogs.getItems().isEmpty()))
					lblData.setText("De: " + tfdDe.getEditor().getText().trim() + " - Até: "
							+ tfdAte.getEditor().getText().trim());
				else
					lblData.setText("De: " + tfdDe.getEditor().getText().trim() + " - Até: "
							+ tfdAte.getEditor().getText().trim() + " SEM RESULTADOS!!!");

				Alerta.getInstance().showMensagem("Cocluido", "Busca Concluida Com Sucesso", "");
				if (!(tblLogs.getItems().isEmpty()))
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
							"Buscar Historico: " + lblData.getText(), StatusLog.CONCLUIDO);
				else
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
							"Buscar Historico: " + lblData.getText(), StatusLog.SEM_RESULTADOS);
			} catch (Exception e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Erro!", "Erro Ao Buscar Historico!!!", e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
						"Buscar Historico: Erro - " + lblData.getText(), StatusLog.ERRO);
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		}

		if(obj == btnRemoverFiltros)
		{
			cbxEvento.getSelectionModel().select(null);
			cbxStatus.getSelectionModel().select(null);
			
			cbxEvento.getEditor().setText("Evento");
			cbxStatus.getEditor().setText("Status");
			
		}
		
	}

}
