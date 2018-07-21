package br.com.sga.app;

import java.awt.SystemTray;
import java.util.ArrayList;

import org.controlsfx.control.Notifications;

import br.com.sga.interfaces.Ouvinte;
import br.com.sga.view.Dialogo;
import br.com.sga.entidade.enums.Tela;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application{
	
	static Stage stage;
	
	static Scene loginScene;
	static Scene menuScene;
	
	static Pane login, cadastro, menu, informacoes, editarPerfil, perfil, pesquisa, configuracoes,
	clientes, cadastroCliente, cadastroContrato, processo, cadastrarProcesso, detalhesProcesso,
	buscarContrato, cadastrarAudiencia, financeiro,cadastroConsulta, cadastroReceitaDespesa, agenda;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		stage.centerOnScreen();
		Parent parent = FXMLLoader.load(getClass().getResource("../view/Carregar.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		stage.centerOnScreen();
		stage.setTitle("SGA - Sistema De Gerenciamento Advocativo");
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(("Icon.png"))));
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
		case cadastro_contrato:
			return cadastroContrato;
		case processos:
			return processo;
		case cadastro_processo:
			return cadastrarProcesso;
		case detalhes_processo:
			return detalhesProcesso;
		case buscar_contrato:
			return buscarContrato;
		case cadastro_audiencia:
			return cadastrarAudiencia;
		case financeiro:
			return financeiro;
		case cadastro_consulta:
			return cadastroConsulta;
		case Cadastro_Receita_Despesa:
			return cadastroReceitaDespesa;
		case agenda:
			return agenda;
		default:
			System.err.println("Valor Não Correspondente");
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
