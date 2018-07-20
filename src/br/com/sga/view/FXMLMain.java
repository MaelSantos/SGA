package br.com.sga.view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderRepeat;
import javafx.stage.Stage;

import java.time.YearMonth;

import br.com.sga.controle.Controle;
import br.com.sga.controle.ControleAgenda;

public class FXMLMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
        primaryStage.setTitle("Full Calendar FXML Example");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
