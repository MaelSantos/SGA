package br.com.sga.app;

import java.util.ArrayList;

import br.com.sga.dao.Dados;
import br.com.sga.entidade.Ouvinte;
import br.com.sga.entidade.Tela;
import br.com.sga.entidade.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
	
	static Stage stage;
	
	static Scene loginScene;
	static Scene menuScene;
	
	static Pane login, cadastro, menu, informacoes, editarPerfil, perfil, pesquisa, configuracoes,
	clientes, cadastroCliente;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		cadastro = FXMLLoader.load(getClass().getResource("../view/Cadastro.fxml"));
		menu = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
		informacoes = FXMLLoader.load(getClass().getResource("../view/Informacoes.fxml"));
		perfil = FXMLLoader.load(getClass().getResource("../view/Perfil.fxml"));
		editarPerfil = FXMLLoader.load(getClass().getResource("../view/EditarPerfil.fxml"));
		configuracoes = FXMLLoader.load(getClass().getResource("../view/Configuracoes.fxml"));
		clientes = FXMLLoader.load(getClass().getResource("../view/Clientes.fxml"));
		cadastroCliente = FXMLLoader.load(getClass().getResource("../view/CadastroCliente.fxml"));
		
		loginScene = new Scene(login);
		menuScene = new Scene(menu);
		
		stage.setScene(loginScene);
		stage.setTitle("SGA - Sistema De Gerenciamento Advocativo");
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(("SGA.png"))));
		stage.centerOnScreen();
		stage.show();
		
		Dados.getInstance().addUsuario(new Usuario("Mael", "Santos", "maelsantos777@gmail.com", "Mael_Santos7", "0708"));
		Dados.getInstance().addUsuario(new Usuario("Wanderson", "Pereira", "exemple@gmail.com", "wanderson100v", "1234"));
		
	}
	
	public static void changeStage(Tela tela)
	{
//		double w = stage.getWidth();
//		double h = stage.getHeight();
		switch (tela) {
		case login:
			stage.setScene(loginScene);
//			stage.setWidth(w);
//			stage.setHeight(h);
			stage.centerOnScreen();
			break;
		case menu:
			stage.setScene(menuScene);
//			stage.setWidth(w);
//			stage.setHeight(h);
			stage.centerOnScreen();
			break;
		default:
			break;
		}
	}
	
	public static Pane changePane(Tela tela)
	{
		switch (tela) {
		case informacoes:
			return informacoes;
		case cadastro:
			return cadastro;
		case perfil:
			return perfil;
		case editar_perfil:
			return editarPerfil;
		case configuracoes:
			return configuracoes;
		case clientes:
			return clientes;
		case cadastro_cliente:
			return cadastroCliente;
		default:
			System.err.println("Valor Não Correspondente");
			break;
		}
		
		return new Pane();
	}
	
	private static ArrayList<Ouvinte> ouvintes = new ArrayList<>();

	public static void notificarOuvintes(Tela tela, Usuario usuario) {
		for(Ouvinte ouvinte : ouvintes)
			ouvinte.atualizar(tela, usuario);
	}
	public static void notificarOuvintes(Tela tela) {
		notificarOuvintes(tela, Dados.getInstance().getUsuarioLogado());
	}
	
	public static void addOuvinte(Ouvinte ouvinte) {
		ouvintes.add(ouvinte);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
