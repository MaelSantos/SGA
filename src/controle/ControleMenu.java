package controle;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import model_dao.Dados;

public class ControleMenu implements Initializable {

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
	private AnchorPane pane;

	@FXML
	private Button btnContatos;
	
	@FXML
    private MenuButton mnbNome;
	
	@FXML
	public void actionButton(ActionEvent e)
	{
		if(e.getSource() == btnArea)
			System.out.println("Area");
		if(e.getSource() == btnAgenda)
			System.out.println("Agenda");
		if(e.getSource() == btnClientes)
			System.out.println("Cliente");
		if(e.getSource() == btnContatos)
			System.out.println("Contatos");
		if(e.getSource() == btnProcessos)
			System.out.println("Processos");
		if(e.getSource() == btnDocumentos)
			System.out.println("Documentos");
		if(e.getSource() == btnFinanceiro)
			System.out.println("Financeiro");
		if(e.getSource() == btnEstatisticas)
			System.out.println("Estatisticas");
		if(e.getSource() == btnHistorico)
			System.out.println("Historico");
		if(e.getSource() == btnAlertas)
			System.out.println("Alertas");
		if(e.getSource() == btnInformacoes)
			System.out.println("Informaçoes");
		if(e.getSource() == btnPerfil)
			System.out.println("Perfil");
		if(e.getSource() == btnConfiguracoes)
			System.out.println("Configuraçoes");
		if(e.getSource() == btnSair)
			System.exit(0);
		
	}
	
	public void atualizarUsuario()
	{
		mnbNome.setText(Dados.getInstance().getUsuarioLogado().getNome());		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		atualizarUsuario();
	}

}
