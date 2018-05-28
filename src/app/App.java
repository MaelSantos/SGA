package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model_dao.Dados;
import model_vo.Tela;
import model_vo.Usuario;

public class App extends Application{
	
	static Stage stage;
	
	static Scene loginScene;
	public static Scene cadastroScene;
	static Scene menuScene;
	static Scene informacoesScene;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		Pane login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Pane cadastro = FXMLLoader.load(getClass().getResource("../view/Cadastro.fxml"));
		Pane menu = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
		Pane informacoes = FXMLLoader.load(getClass().getResource("../view/Informacoes.fxml"));
		
		cadastroScene = new Scene(cadastro, 700, 600);
		loginScene = new Scene(login,500,500);
		menuScene = new Scene(menu, 900, 620);
		informacoesScene = new Scene(informacoes, 700, 600);
		
		stage.setScene(loginScene);
		stage.setTitle("SGA - Sistema De Gerenciamento Advocativo");
		stage.centerOnScreen();
		stage.show();
		
		Dados.getInstance().addUsuario(new Usuario("Mael", "Santos", "maelsantos777@gmail.com", "Mael_Santos7", "0708"));
		Dados.getInstance().addUsuario(new Usuario("Wanderson", "Pereira", "exemple@gmail.com", "wanderson100v", "1234"));
		
	}
	
	public static void changeStage(Tela tela)
	{
		switch (tela) {
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
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
