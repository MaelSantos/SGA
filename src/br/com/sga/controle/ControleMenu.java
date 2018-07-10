package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import br.com.sga.app.App;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControleMenu implements Initializable, Ouvinte{

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
    private Button btnContatos;

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
    private Button btnAlertas;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button btnInformacoes;
    
    @FXML
    private Button cadastrarContratoButton;

    @FXML
    private Button editarContratoButton;

    @FXML
    private Button buscarContratoButton;

    @FXML
    private Button fecharContratoButton;

	
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
		else if(e.getSource() == btnContatos)
			atualizarTela(App.changePane(Tela.contatos));
		else if(e.getSource() == btnProcessos)
			atualizarTela(App.changePane(Tela.processos));
		else if(e.getSource() == btnDocumentos)
			atualizarTela(App.changePane(Tela.documentos));
		else if(e.getSource() == btnFinanceiro)
			atualizarTela(App.changePane(Tela.financeiro));
		else if(e.getSource() == btnEstatisticas)
			atualizarTela(App.changePane(Tela.estatiticas));
		else if(e.getSource() == btnHistorico)
			atualizarTela(App.changePane(Tela.historico));
		else if(e.getSource() == btnAlertas)
			atualizarTela(App.changePane(Tela.alertas));
		else if(e.getSource() == btnInformacoes)
			atualizarTela(App.changePane(Tela.informacoes));
		else if(e.getSource() == btnPerfil)
			atualizarTela(App.changePane(Tela.perfil));
		else if(e.getSource() == btnConfiguracoes)
			atualizarTela(App.changePane(Tela.configuracoes));
		else if(e.getSource() == btnSair)
			App.changeStage(Tela.login);//muda a tela
	}
	

    @FXML
    void contratoHandeler(ActionEvent event) {
    	if(event.getSource() == cadastrarContratoButton)
			atualizarTela(App.changePane(Tela.cadastro_contrato));
    	else if(event.getSource() == editarContratoButton) {}
    	else if(event.getSource() == buscarContratoButton) {}
    	else if(event.getSource() == fecharContratoButton) {}
    }
	
	@FXML
    void actionKey(KeyEvent event) {

		KeyCode codigo = event.getCode();
		
		switch (codigo) {
		case ENTER:
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);
		TextFields.bindAutoCompletion(tfdPesquisar,Tela.values());
		
	}
	@Override
	public void atualizar(Tela tela, Funcionario usuario) {
		funcionario = usuario;
//				if(tela == Tela.perfil) {
//					atualizarTela(App.changePane(Tela.perfil));
//				}
//				if(tela == Tela.cadastro) {
//					atualizarTela(App.changePane(Tela.cadastro));
//				}
//				if(tela == Tela.configuracoes) {
//					atualizarTela(App.changePane(Tela.configuracoes));
//				}else if(tela == Tela.editar_perfil) {
//					atualizarTela(App.changePane(Tela.editar_perfil));
//				}
		
		atualizarTela(App.changePane(tela));
		mnbNome.setText(funcionario.getNome());
	}

}
