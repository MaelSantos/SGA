package br.com.sga.view;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.sga.app.App;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Dialogo {

	private static Dialogo instance;

	private Dialogo() {
	}

	public static Dialogo getInstance() {
		if (instance == null)
			instance = new Dialogo();
		return instance;
	}

	public String dialogoDeEntradaSenha(String titulo, String cabecario, String msg) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(titulo);
		dialog.setHeaderText(cabecario);
		VBox v = new VBox();
		PasswordField senha = new PasswordField();
		senha.setPrefWidth(180);
		v.getChildren().addAll(new Label(msg), senha);

		dialog.getDialogPane().setContent(v);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		Optional<String> result = dialog.showAndWait();
		String entrada = "";
		if (result.isPresent())
			entrada = senha.getText();
		return entrada;

	}

	public CadastroNotificacao cadastroNotificacaoDialog(LocalDate date) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Cadastro de tarefa");
		dialog.setHeaderText("Cadastre uma nova tarefa para voce ou todos os funcionários");
		CadastroNotificacao cadastroNotificacao = new CadastroNotificacao(date);
		dialog.getDialogPane().setContent(cadastroNotificacao);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
			return cadastroNotificacao;
		return null;
	}

	public String dialogoDeEntradaText(String titulo, String cabecario, String msg) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(titulo);
		dialog.setHeaderText(cabecario);
		dialog.setContentText(msg);
		Optional<String> result = dialog.showAndWait();
		String entrada = "";
		if (result.isPresent())
			entrada = result.get();
		return entrada;

	}

	public Notificacao DetalhesData(LocalDate date) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle(date.toString());

		VBox v = new VBox();
		v.setPrefSize(500, 300);

		ListView<Notificacao> view = new ListView<Notificacao>();
		try {
			view.getItems().setAll(FXCollections
					.observableArrayList(Fachada.getInstance().buscarNotificacaoPorData(Date.valueOf(date))));
			v.getChildren().addAll(new Label(date.toString()), view);
		} catch (BusinessException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}

		dialog.getDialogPane().setContent(v);

		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		ButtonType btpEditar = new ButtonType("Editar", ButtonData.YES);
//		ButtonType btpCadastrar = new ButtonType("Cadastrar", ButtonData.RIGHT);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, btpEditar, ButtonType.CANCEL);

		Optional<ButtonType> optional = dialog.showAndWait();

		if (optional.isPresent() && optional.get().getButtonData() == ButtonData.YES) {

			if (view.getSelectionModel().getSelectedItem() != null)
				App.notificarOuvintes(Tela.DETALHES_NOTIFICACAO, view.getSelectionModel().getSelectedItem());
			else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!",
						"Selecione Uma Notificação Antes de Proseguir", "");
		}
//		else if (optional.isPresent() && optional.get().getButtonData() == ButtonData.RIGHT) {
//			
//			CadastroNotificacao cadastroNotificacao = cadastroNotificacaoDialog(date);
//			
//			Integer hora = cadastroNotificacao.getHoraBox().getSelectionModel().getSelectedItem();
//			Prioridade prioridade= cadastroNotificacao.getPrioridadeBox().getSelectionModel().getSelectedItem();
//			LocalDate ld = cadastroNotificacao.getDataPicker().getValue();
//
//			if(hora != null && ld != null && prioridade != null) {
//				Calendar c =  Calendar.getInstance();
//				c.setTime(new java.util.Date());
//				c.set(Calendar.YEAR,ld.getYear());
//				c.set(Calendar.MONTH,ld.getMonthValue()-1);
//				c.set(Calendar.DAY_OF_MONTH,ld.getDayOfMonth());
//				c.set(Calendar.HOUR_OF_DAY,hora);
//				c.set(Calendar.SECOND,0);
//				c.set(Calendar.MILLISECOND,0);
//				c.set(Calendar.MINUTE,0);
//				java.util.Date aviso_data = c.getTime();
//				List<Funcionario> funcionarios = new ArrayList<>();
//				if(cadastroNotificacao.getApenasMinRadio().isSelected()) {
////					funcionarios.add(funcionario);
//				}else {
//					try {
//						funcionarios = Fachada.getInstance().buscarUsuarioPorBusca("%_%");
//					} catch (BusinessException e) {
//						e.printStackTrace();
//					}
//				}
//				Log log;
//				try {
//					Fachada.getInstance().salvarEditarNotificacao(new Notificacao(TipoNotificacao.TAREFA, prioridade, cadastroNotificacao.getDescricaoArea().getText(), Andamento.PENDENTE, 
//							aviso_data, funcionarios));
//					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Confirmação","","Nova notificação cadastrada com sucesso");
////					calendario.AtualizarMes();
//					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, "", "Nova Tarefa: "+aviso_data, StatusLog.CONCLUIDO);
//				} catch (BusinessException e) {
//					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, "", "Nova Tarefa:  Erro", StatusLog.ERRO);
//					e.printStackTrace();
//					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!","Erro Ao Cadastrar Tarefa!!!",e.getMessage());
//				}
//				
//				try {
//					if(log != null)
//						Fachada.getInstance().salvarEditarLog(log);
//				} catch (BusinessException e) {
//					e.printStackTrace();
//				}					
//				
//			}
//
//			
//			return null;
//			
//		}
		else if (optional.isPresent() && optional.get().getButtonData() == ButtonData.OK_DONE) {
			
			return view.getSelectionModel().getSelectedItem();
		}

		return null;
	}

	public <T> T selecionar(List<T> list) {

		Dialog<T> dialog = new Dialog<>();
		dialog.setTitle("Selecione: ");
		if (!list.isEmpty())
			dialog.setHeaderText("Lista com todos os(a) "
					+ list.get(0).getClass().getSimpleName().replace("Adapter", "").toUpperCase()
					+ " : \nSeleciona um(a) para mais detalhes");
		else
			dialog.setHeaderText("Lista com todos os(a) " + " : \nSeleciona um(a) para mais detalhes");
		dialog.setResizable(true);

		TableView<T> table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setPrefSize(600, 300);

		int contador = 0;

		if (!list.isEmpty())
			for (Field f : list.get(0).getClass().getDeclaredFields()) {
				if (!f.getName().equals("id")) {
					TableColumn<T, ?> column = new TableColumn<>(f.getName().replaceAll("_", " ").toUpperCase());
					column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
					table.getColumns().add(column);
				}

				if (contador >= 6)
					break;
				else
					contador++;

			}

		table.getItems().addAll(list);

		dialog.getDialogPane().setContent(table);
		ButtonType loginButtonType = new ButtonType("Confirmar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		Optional<T> result = dialog.showAndWait();
		if (result.isPresent())
			return table.getSelectionModel().getSelectedItem();
		return null;
	}

}
