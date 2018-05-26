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
	static Scene cadastroScene;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		Pane login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Pane cadastro = FXMLLoader.load(getClass().getResource("../view/Cadastro.fxml"));
		
		cadastroScene = new Scene(cadastro, 700, 600);
		loginScene = new Scene(login);		
		
		stage.setScene(loginScene);
		stage.show();
		
		Dados.getInstance().addUsuario(new Usuario("Mael", "Santos", "maelsantos777@gmail.com", "Mael_Santos7", "0708"));
		
	}
	
	public static void changeStage(Tela tela)
	{
		switch (tela) {
		case cadastro:
			stage.setScene(cadastroScene);
			break;

		default:
			break;
		}
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
