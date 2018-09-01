package br.com.sga.app;

import java.util.ArrayList;

import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.enums.Tela;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application{
	
	static Stage stage;
	
	static Scene loginScene;
	static Scene menuScene;
	
	static Pane login, home, cadastro, menu, informacoes, editarPerfil, perfil, pesquisa, configuracoes,
	clientes, cadastroCliente, cadastroContrato, processo, cadastrarProcesso, detalhesProcesso,
	buscarContrato, cadastrarAudiencia, financeiro,cadastroConsulta, cadastroReceitaDespesa, agenda,
	consulta,detalhesConsulta,detalhesContrato,documentos, historico,estatistica;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		stage.centerOnScreen();
		Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/sga/view/Carregar.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("SGA - Sistema de Gerenciamento Advocatício");
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(("Icon.png"))));
	}
	
	public static void changeStage(Tela tela)
	{
		switch (tela) {
		case LOGIN:
			stage.setScene(loginScene);
			stage.centerOnScreen();
			
			break;
		case MENU:
			stage.setScene(menuScene);
			stage.setMaximized(true);
			stage.centerOnScreen(); 
			break;
		default:
			break;
		}
	}
	
	public static Pane changePane(Tela tela)
	{
		switch (tela) {
		case HOME:
			return home;
		case INFORMACOES:
			return informacoes;
		case CADASTRO:
			return cadastro;
		case PERFIL:
			return perfil;
		case EDITAR_PERFIL:
			return editarPerfil;
		case CONFIGURACOES:
			return configuracoes;
		case CLIENTES:
			return clientes;
		case CADASTRO_CLIENTE:
			return cadastroCliente;
		case CADASTRO_CONTRATO:
			return cadastroContrato;
		case PROCESSOS:
			return processo;
		case CADASTRO_PROCESSO:
			return cadastrarProcesso;
		case DETALHES_PROCESSO:
			return detalhesProcesso;
		case BUSCAR_CONTRATO:
			return buscarContrato;
		case CADASTRO_AUDIENCIA:
			return cadastrarAudiencia;
		case FINANCEIRO:
			return financeiro;
		case CADASTRO_CONSULTA:
			return cadastroConsulta;
		case CADASTRO_RECEITA_DESPESA:
			return cadastroReceitaDespesa;
		case AGENDA:
			return agenda;
		case CONSULTA:
			return consulta;
		case DETALHES_CONSULTA:
			return detalhesConsulta;
		case DETALHES_CONTRATO:
			return detalhesContrato;
		case DOCUMENTOS:
			return documentos;
		case HISTORICO:
			return historico;
		case ESTATISTICA:
			return estatistica;
		default:
			break;
		}
		return new Pane();
	}
	
	private static ArrayList<Ouvinte> ouvintes = new ArrayList<>();

	public static void notificarOuvintes(Tela tela, Object object) {
		for(Ouvinte ouvinte : ouvintes)
			ouvinte.atualizar(tela, object);
	}
	
	public static void notificarOuvintes(Tela tela) {
			notificarOuvintes(tela, null);
	}
	
	public static void addOuvinte(Ouvinte ouvinte) {
		ouvintes.add(ouvinte);
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}
