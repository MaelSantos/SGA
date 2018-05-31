package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import app.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import model_vo.Tela;
import model_vo.Usuario;

public class ControleMenu implements Initializable{

	@FXML
	private MenuItem btnPerfil;

	@FXML
	private MenuItem btnConfiguracoes;

	@FXML
	private MenuItem btnSair;

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
	private Button btnAlertas;

	@FXML
	private Button btnInformacoes;

	@FXML
	private Pane pane;

	@FXML
	private Button btnContatos;
	
	@FXML
    private MenuButton mnbNome;
	
	@FXML
	public void actionButton(ActionEvent e)
	{
		if(e.getSource() == btnArea)
			atualizarTela(App.changePane(Tela.perfil));
		if(e.getSource() == btnAgenda)
			atualizarTela(App.changePane(Tela.agenda));
		if(e.getSource() == btnClientes)
			atualizarTela(App.changePane(Tela.clientes));
		if(e.getSource() == btnContatos)
			atualizarTela(App.changePane(Tela.contatos));
		if(e.getSource() == btnProcessos)
			atualizarTela(App.changePane(Tela.processos));
		if(e.getSource() == btnDocumentos)
			atualizarTela(App.changePane(Tela.documentos));
		if(e.getSource() == btnFinanceiro)
			atualizarTela(App.changePane(Tela.financeiro));
		if(e.getSource() == btnEstatisticas)
			atualizarTela(App.changePane(Tela.estatiticas));
		if(e.getSource() == btnHistorico)
			atualizarTela(App.changePane(Tela.historico));
		if(e.getSource() == btnAlertas)
			atualizarTela(App.changePane(Tela.alertas));
		if(e.getSource() == btnInformacoes)
			atualizarTela(App.changePane(Tela.informacoes));
		if(e.getSource() == btnPerfil)
			atualizarTela(App.changePane(Tela.perfil));
		if(e.getSource() == btnConfiguracoes)
			atualizarTela(App.changePane(Tela.configuracoes));
		if(e.getSource() == btnSair)
			App.changeStage(Tela.login);//muda a tela
		
	}
	
	public void atualizarTela(Pane arg1)
	{
		pane.getChildren().setAll(arg1);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(new Ouvinte() {
			@Override
			public void atualizar(Tela tela, Usuario usuario) {
				if(tela == Tela.perfil) {
					atualizarTela(App.changePane(Tela.perfil));
				}else if(tela == Tela.editar_perfil) {
					atualizarTela(App.changePane(Tela.editar_perfil));
				}
//				if(!(mnbNome.getText().equalsIgnoreCase(usuario.getNome())))
					mnbNome.setText(usuario.getNome());
			}
		});
		
	}

}
