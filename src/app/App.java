package app;

import java.util.ArrayList;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model_dao.Dados;
import model_vo.Tela;
import model_vo.Usuario;

public class App extends Application{
	
	static Stage stage;
	
	static Scene loginScene;
	static Scene cadastroScene;
	static Scene menuScene;
	static Scene informacoesScene;
	
	static Pane login, cadastro, menu, informacoes, editarPerfil, perfil, pesquisa;
	
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
		
		cadastroScene = new Scene(cadastro, 700, 600);
		loginScene = new Scene(login,500,500);
		menuScene = new Scene(menu, 900, 620);
		informacoesScene = new Scene(informacoes, 700, 600);
		
		stage.setScene(loginScene);
		stage.setTitle("SGA - Sistema De Gerenciamento Advocativo");
		//adiciona um icone 
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(("SGA.png"))));
		stage.centerOnScreen();
		stage.show();
		
		Dados.getInstance().addUsuario(new Usuario("Mael", "Santos", "maelsantos777@gmail.com", "Mael_Santos7", "0708"));
		Dados.getInstance().addUsuario(new Usuario("Wanderson", "Pereira", "exemple@gmail.com", "wanderson100v", "1234"));
		
	}
	
	public static void changeStage(Tela tela)
	{
		switch (tela) {
		case login:
			
			stage.setScene(loginScene);
			stage.centerOnScreen();
			break;
		case menu:
			stage.setScene(menuScene);
			stage.centerOnScreen();
			break;
		case cadastro:
			stage.setScene(cadastroScene);
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
		default:
			break;
		}
		
		return new Pane();
	}
	public static ArrayList<Ouvinte> ouvintes = new ArrayList<>();

	public static void notificarOuvintes(Tela tela) {
		for(Ouvinte ouvinte : ouvintes) 
			ouvinte.atualizar(tela);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void addOuvinte(Ouvinte ouvinte) {
		ouvintes.add(ouvinte);
	}
}
