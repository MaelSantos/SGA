package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
	
		Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Cadastro.fxml"));

		Scene scene = new Scene(pane, 700, 600);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
