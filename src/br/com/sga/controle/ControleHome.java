package br.com.sga.controle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.sga.app.App;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleHome extends Controle {

	@FXML
	private TableView<NotificacaoAdapter> tblSemana;

	@FXML
	private TableColumn<NotificacaoAdapter, String> colDescricao1;

	@FXML
	private TableColumn<NotificacaoAdapter, TipoNotificacao> colTipo1;

	@FXML
	private TableColumn<NotificacaoAdapter, Andamento> colEstado1;

	@FXML
	private TableColumn<NotificacaoAdapter, Date> colData1;

	@FXML
	private TableView<NotificacaoAdapter> tblAtrasados;

	@FXML
	private TableColumn<NotificacaoAdapter, String> colDescricao2;

	@FXML
	private TableColumn<NotificacaoAdapter, TipoNotificacao> colTipo2;

	@FXML
	private TableColumn<NotificacaoAdapter, Andamento> colEstado2;

	@FXML
	private TableColumn<NotificacaoAdapter, Date> colData2;

	@FXML
	private Button btnConcluir;

	@FXML
	private Button btnEditar;

	private IFachada fachada;

	@Override
	public void atualizar(Tela tela, Object object) {


	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();

		colData1.setCellValueFactory(
				new PropertyValueFactory<>("aviso_data"));
		colData2.setCellValueFactory(
				new PropertyValueFactory<>("aviso_data"));

		colDescricao1.setCellValueFactory(
				new PropertyValueFactory<>("descricao"));
		colDescricao2.setCellValueFactory(
				new PropertyValueFactory<>("descricao"));

		colEstado1.setCellValueFactory(
				new PropertyValueFactory<>("estado"));
		colEstado2.setCellValueFactory(
				new PropertyValueFactory<>("estado"));

		colTipo1.setCellValueFactory(
				new PropertyValueFactory<>("tipoNotificacao"));
		colTipo2.setCellValueFactory(
				new PropertyValueFactory<>("tipoNotificacao"));

		Log log;
		try {

			Date primeiro = resolvePrimeiroUltimo(new Date(), true);

			Date ultimo = resolvePrimeiroUltimo(new Date(), false);

			tblAtrasados.getItems().setAll(fachada.BuscarNotificacaoAdapterPorEstado(Andamento.VENCIDO.toString()));
			tblSemana.getItems().setAll(fachada.BuscarNotificacaoAdapterPorData(primeiro, ultimo));

			if(tblAtrasados.getItems().isEmpty() && tblSemana.getItems().isEmpty())
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, "Sistema", "Buscar Tarefas Da Semana e Atrasadas: Nada Encontrado", StatusLog.SEM_RESULTADOS);
			else
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, "Sistema", "Buscar Tarefas Da Semana e Atrasadas: ", StatusLog.CONCLUIDO);

		} catch (Exception e) {

			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Carregar Notificações!!!", e.getMessage());
			log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, "Sistema", "Buscar Tarefas Da Semana e Atrasadas: Erro ", StatusLog.ERRO);
			e.printStackTrace();
		}

		try {
			if(log != null)
				fachada.salvarEditarLog(log);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		colData1.setCellFactory(coluna -> {

			return new TableCell<NotificacaoAdapter, Date>() {
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

		colData2.setCellFactory(coluna -> {

			return new TableCell<NotificacaoAdapter, Date>() {
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

		if(obj == btnConcluir)
		{
			try {

				Notificacao notificacao = null;
				NotificacaoAdapter adapter = null;

				if(tblAtrasados.getSelectionModel().getSelectedItem() != null)
				{
					adapter = tblAtrasados.getSelectionModel().getSelectedItem();
					notificacao = fachada.buscarNotificacaoPorId(adapter.getId());

					tblAtrasados.getItems().remove(adapter);
				}
				else if (tblSemana.getSelectionModel().getSelectedItem() != null)
				{
					adapter = tblSemana.getSelectionModel().getSelectedItem();
					notificacao = fachada.buscarNotificacaoPorId(adapter.getId());

					Date primeiro = resolvePrimeiroUltimo(new Date(), true);

					Date ultimo = resolvePrimeiroUltimo(new Date(), false);

					System.out.println("Primeiro: "+primeiro+" Ultimo: "+ultimo);

					tblSemana.getItems().setAll(fachada.BuscarNotificacaoAdapterPorData(primeiro, ultimo));
					tblAtrasados.getItems().setAll(fachada.BuscarNotificacaoAdapterPorEstado(Andamento.VENCIDO.toString()));

				}

				if(notificacao != null)
				{
					notificacao.setEstado(Andamento.CONCLUIDO);
					adapter.setEstado(Andamento.CONCLUIDO);

					fachada.salvarEditarNotificacao(notificacao);
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Notificação Atualizada", "");	

				}
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Atualizar Notificação!!!", e.getMessage());
				e.printStackTrace();
			}
		}
		else if(obj == btnEditar)
		{
			if(tblAtrasados.getSelectionModel().getSelectedItem() != null)
				App.notificarOuvintes(Tela.DETALHES_NOTIFICACAO, tblAtrasados.getSelectionModel().getSelectedItem());
			else if(tblSemana.getSelectionModel().getSelectedItem() != null)
				App.notificarOuvintes(Tela.DETALHES_NOTIFICACAO, tblSemana.getSelectionModel().getSelectedItem());
			else 
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!", "Por Favor Selecione Uma Notificação!!!", "");
		}

	}

	@FXML
	void actionMouse(MouseEvent event) {

		Object obj = event.getSource();

		if(obj == tblAtrasados)
		{
			tblSemana.getSelectionModel().clearSelection();

			if (event.getClickCount() > 1)
				if (tblAtrasados.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES_NOTIFICACAO, tblAtrasados.getSelectionModel().getSelectedItem());
		}
		else if(obj == tblSemana)
		{
			tblAtrasados.getSelectionModel().clearSelection();

			if (event.getClickCount() > 1)
				if (tblSemana.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.DETALHES_NOTIFICACAO, tblSemana.getSelectionModel().getSelectedItem());
		}


	}

	public Date resolvePrimeiroUltimo(Date data, boolean isPrimeiro)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(data);

		if(isPrimeiro)
		{
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(Calendar.AM_PM, Calendar.AM);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		else
		{
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.set(Calendar.AM_PM, Calendar.PM);
			calendar.set(Calendar.HOUR, 11);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}

		return calendar.getTime();
	}

}
