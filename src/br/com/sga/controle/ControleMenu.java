package br.com.sga.controle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControleMenu extends Controle {

	@FXML
	private AnchorPane menu;

	@FXML
	private MenuButton mnbNome;

	@FXML
	private MenuItem btnPerfil;

	@FXML
	private MenuItem btnConfiguracoes;

	@FXML
	private MenuItem btnSair;

	@FXML
	private TextField tfdPesquisar;

	@FXML
	private Button btnArea;

	@FXML
	private Button btnAgenda;

	@FXML
	private Button btnClientes;

	@FXML
	private Button btnProcessos;

	@FXML
	private Button btnDocumentos;

	@FXML
	private Button btnFinanceiro;

	@FXML
	private Button btnEstatisticas;

	@FXML
	private Button btnHistorico;

	@FXML
	private Button btnHome;

	@FXML
	private AnchorPane pane;

	@FXML
	private Button btnInformacoes;

	@FXML
	private Button contratoButton;

	@FXML
	private Button consultaButton;

	private Funcionario funcionario;

	@FXML
	public void actionButton(ActionEvent e) {
		
		if (e.getSource() == btnAgenda)
			App.notificarOuvintes(Tela.AGENDA);
		else if (e.getSource() == btnClientes)
			App.notificarOuvintes(Tela.CLIENTES);
		else if (e.getSource() == btnProcessos)
			App.notificarOuvintes(Tela.PROCESSOS);
		else if (e.getSource() == btnDocumentos)
			App.notificarOuvintes(Tela.DOCUMENTOS);
		else if (e.getSource() == btnFinanceiro)
			App.notificarOuvintes(Tela.FINANCEIRO);
		else if (e.getSource() == btnEstatisticas)
			App.notificarOuvintes(Tela.ESTATISTICA);
		else if (e.getSource() == btnHistorico)
			App.notificarOuvintes(Tela.HISTORICO);
		else if (e.getSource() == btnHome)
			App.notificarOuvintes(Tela.HOME);
		else if (e.getSource() == btnInformacoes)
			App.notificarOuvintes(Tela.INFORMACOES);
		else if (e.getSource() == btnPerfil)
			App.notificarOuvintes(Tela.PERFIL);
		else if (e.getSource() == btnConfiguracoes)
			App.notificarOuvintes(Tela.CONFIGURACOES);
		else if (e.getSource() == contratoButton)
			App.notificarOuvintes(Tela.BUSCAR_CONTRATO);
		else if (e.getSource() == consultaButton)
			App.notificarOuvintes(Tela.CONSULTA);
		else if (e.getSource() == btnSair) {
			funcionario = null;
			App.changeStage(Tela.LOGIN);

		}

	}

	@FXML
	void actionKey(KeyEvent event) {

		System.out.println("key");
		KeyCode codigo = event.getCode();

		switch (codigo) {
		case ENTER:
			if (tfdPesquisar.getText() != null || !(tfdPesquisar.getText().trim().equals("")))
				atualizarTela(App.changePane(Tela.getTela(tfdPesquisar.getText())));
			break;
		default:
			break;
		}

	}

	@SuppressWarnings("static-access")
	public void atualizarTela(Pane paneNovo) {
		pane.setBottomAnchor(paneNovo, 0.0);
		pane.setTopAnchor(paneNovo, 0.0);
		pane.setLeftAnchor(paneNovo, 0.0);
		pane.setRightAnchor(paneNovo, 0.0);
		pane.getChildren().setAll(paneNovo);
	}

	@Override
	public void init() {
//		TextFields.bindAutoCompletion(tfdPesquisar, Tela.values());

	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object != null) {
			if (object instanceof Funcionario) {
				if (this.funcionario == null) {
					/*
					 * Timeline oneMinuteTimeline = new Timeline(new KeyFrame(Duration.seconds(5),
					 * event -> {
					 * Notifications.create().title("Ol�!").text("teste").action().position(Pos.
					 * BOTTOM_RIGHT).showInformation(); }));
					 * oneMinuteTimeline.setCycleCount(Timeline.INDEFINITE); // Executar
					 * indefinidamente. oneMinuteTimeline.play();
					 */

//					Notifications.create().title("Ol�!").text("Bem vindo " + ((Funcionario) object).getNome()).action()
//							.position(Pos.BOTTOM_RIGHT).showInformation();
				}
				this.funcionario = (Funcionario) object;
				String nome_completo = funcionario.getNome();
				mnbNome.setText((nome_completo.contains(" ")) ? nome_completo.substring(0, nome_completo.indexOf(" "))
						: nome_completo);
			}
		}
		atualizarTela(App.changePane(tela));
	}

}
