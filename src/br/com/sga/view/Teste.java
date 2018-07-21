package br.com.sga.view;

import java.awt.Panel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Teste extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		VBox box = new VBox();
		Scene scene = new Scene(box,500,500);
		primaryStage.setScene(scene);
//		primaryStage.show();
//		primaryStage.centerOnScreen();
		
		Dialogo d = Dialogo.getInstance();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
