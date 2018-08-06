package br.com.sga.controle;

import org.controlsfx.control.textfield.TextFields;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControleMenu extends Controle{

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
	public void actionButton(ActionEvent e)
	{
		if(e.getSource() == btnArea) 
			atualizarTela(App.changePane(Tela.perfil));
		else if(e.getSource() == btnAgenda)
			atualizarTela(App.changePane(Tela.agenda));
		else if(e.getSource() == btnClientes)
			atualizarTela(App.changePane(Tela.clientes));
		else if(e.getSource() == btnProcessos)
			atualizarTela(App.changePane(Tela.processos));
		else if(e.getSource() == btnDocumentos)
			atualizarTela(App.changePane(Tela.documentos));
		else if(e.getSource() == btnFinanceiro)
			atualizarTela(App.changePane(Tela.financeiro));
		else if(e.getSource() == btnEstatisticas)
			atualizarTela(App.changePane(Tela.Estatistica));
		else if(e.getSource() == btnHistorico)
			atualizarTela(App.changePane(Tela.historico));
		else if(e.getSource() == btnHome)
			atualizarTela(App.changePane(Tela.home));
		else if(e.getSource() == btnInformacoes)
			atualizarTela(App.changePane(Tela.informacoes));
		else if(e.getSource() == btnPerfil)
			atualizarTela(App.changePane(Tela.perfil));
		else if(e.getSource() == btnConfiguracoes)
			atualizarTela(App.changePane(Tela.configuracoes));
		else if(e.getSource() == contratoButton) 
    		atualizarTela(App.changePane(Tela.buscar_contrato));
		else if(e.getSource() == consultaButton) 
    		atualizarTela(App.changePane(Tela.Consulta));
		else if(e.getSource() == btnSair) {
			funcionario = null;
			App.changeStage(Tela.login);
			
		}
		
	}
	
	@FXML
    void actionKey(KeyEvent event) {

		System.out.println("key");
		KeyCode codigo = event.getCode();
		
		switch (codigo) {
		case ENTER:
			if(tfdPesquisar.getText() != null || !(tfdPesquisar.getText().trim().equals("")))
				atualizarTela(App.changePane(Tela.getTela(tfdPesquisar.getText())));
			break;
		default:
			break;
		}
		
    }

	
	@SuppressWarnings("static-access")
	public void atualizarTela(Pane paneNovo)
	{	
		pane.setBottomAnchor(paneNovo, 0.0);
		pane.setTopAnchor(paneNovo, 0.0);
		pane.setLeftAnchor(paneNovo, 0.0);
		pane.setRightAnchor(paneNovo, 0.0);
		pane.getChildren().setAll(paneNovo);
	}
	
	@Override
	public void init() {
		TextFields.bindAutoCompletion(tfdPesquisar,Tela.values());
		
	}
	
	@Override
	public void atualizar(Tela tela, Object object) {
		
		if(object != null)
		{
			if (object instanceof Funcionario) {
				if(this.funcionario == null) {
					/*Timeline oneMinuteTimeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
						Notifications.create().title("Olá!").text("teste").action().position(Pos.BOTTOM_RIGHT).showInformation();
						}));
						oneMinuteTimeline.setCycleCount(Timeline.INDEFINITE); // Executar indefinidamente.
						oneMinuteTimeline.play();*/
				}
				this.funcionario = (Funcionario) object;
				String nome_completo = funcionario.getNome();
				mnbNome.setText((nome_completo.contains(" ")) ? nome_completo.substring(0,nome_completo.indexOf(" ")): nome_completo);
			}
		}
		atualizarTela(App.changePane(tela));
	}

}
