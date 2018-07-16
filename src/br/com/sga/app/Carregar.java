package br.com.sga.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.entidade.enums.Tela;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public class Carregar implements Initializable{

	@FXML
	private ProgressBar pgbCarregar;

	@FXML
	private Label lblInformacao;

	private Service<Object> servico;
	private String texto = "...";
	double porcentagem = 0.0;

	public Pane carregarArquivo(String caminho) throws IOException
	{
		Pane pane = FXMLLoader.load(getClass().getResource(caminho));
		this.texto = caminho;
		return pane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		servico = new Service<Object>() {
			@Override
			protected Task<Object> createTask() {
				return new Task<Object>() {
					@Override
					protected Void call() throws Exception {

						System.out.println("carregando...");
						updateMessage("...");

						App.login = carregarArquivo("../view/Login.fxml");
						updateMessage(texto);
						porcentagem = 0.0625;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.cadastro = carregarArquivo("../view/Cadastro.fxml");
						updateMessage(texto);
						porcentagem = 0.125;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.menu = carregarArquivo("../view/Menu.fxml");
						updateMessage(texto);
						porcentagem = 0.1875;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.informacoes = carregarArquivo("../view/Informacoes.fxml");
						updateMessage(texto);
						porcentagem = 0.25;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.perfil = carregarArquivo("../view/Perfil.fxml");
						updateMessage(texto);
						porcentagem = 0.3125;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.editarPerfil = carregarArquivo("../view/EditarPerfil.fxml");
						updateMessage(texto);
						porcentagem = 0.375;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.configuracoes = carregarArquivo("../view/Configuracoes.fxml");
						updateMessage(texto);
						porcentagem = 0.4375;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.clientes = carregarArquivo("../view/Clientes.fxml");
						updateMessage(texto);
						porcentagem = 0.5;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.cadastroCliente = carregarArquivo("../view/CadastroCliente.fxml");
						updateMessage(texto);
						porcentagem = 0.5625;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.contatos = carregarArquivo("../view/Contatos.fxml");
						updateMessage(texto);
						porcentagem = 0.625;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.cadastroContrato = carregarArquivo("../view/CadastroContrato.fxml");
						updateMessage(texto);
						porcentagem = 0.6875;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.processo = carregarArquivo("../view/Processo.fxml");
						updateMessage(texto);
						porcentagem = 0.75;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.cadastrarProcesso = carregarArquivo("../view/CadastroProcesso.fxml");
						updateMessage(texto);
						porcentagem = 0.8125;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.buscarContrato = carregarArquivo("../view/BuscarContrato.fxml");
						updateMessage(texto);
						porcentagem = 0.875;
						updateProgress(porcentagem, 1);
						System.out.println(texto);

						App.detalhesProcesso = carregarArquivo("../view/DetalhesProcesso.fxml");
						updateMessage(texto);
						porcentagem = 0.9375;
						updateProgress(porcentagem, 1);
						System.out.println(texto);
						
						App.cadastrarAudiencia = carregarArquivo("../view/CadastroAudiencia.fxml");
						updateMessage(texto);
						porcentagem = 1;
						updateProgress(porcentagem, 1);
						System.out.println(texto);
						
						App.loginScene = new Scene(App.login);
						App.menuScene = new Scene(App.menu);

						return null;
					}
					@Override
					protected void succeeded() {
						super.succeeded();
						App.changeStage(Tela.login);
					}
				};


			}
		};

		//fazendo o bind (ligando) nas proprety
		lblInformacao.textProperty().bind(servico.messageProperty());
		pgbCarregar.progressProperty().bind(servico.progressProperty());
		//precisa inicializar o Service
		servico.start();

		//		new LoaderThread().start();


	}

	//	public class LoaderThread extends Thread {
	//
	//		@Override
	//		public void run() {
	//
	////			while(porcentagem <= 1)
	////			{
	//				System.out.println("no while");
	//fazendo o bind (ligando) nas proprety
	//    lblInformacao.textProperty().bind(servico.messageProperty());
	//    pgbCarregar.progressProperty().bind(servico.progressProperty());
	//    //precisa inicializar o Service
	//    servico.start();
	//    
	////			}
	//				servico.cancel();
	//			System.out.println("fora while");
	//			
	//		}
	//	}

}
