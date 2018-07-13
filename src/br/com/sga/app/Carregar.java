package br.com.sga.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

		new LoaderThread().start();
	}

	public void preCarregar() throws Exception
	{	
		System.out.println("carregando...");
		App.login = carregarArquivo("../view/Login.fxml");
		porcentagem = 0.083;
		App.cadastro = carregarArquivo("../view/Cadastro.fxml");
		porcentagem = 0.166;
		App.menu = carregarArquivo("../view/Menu.fxml");
		porcentagem = 0.249;
		App.informacoes = carregarArquivo("../view/Informacoes.fxml");
		porcentagem = 0.332;
		App.perfil = carregarArquivo("../view/Perfil.fxml");
		porcentagem = 0.415;
		App.editarPerfil = carregarArquivo("../view/EditarPerfil.fxml");
		porcentagem = 0.498;
		App.configuracoes = carregarArquivo("../view/Configuracoes.fxml");
		porcentagem = 0.581;
		App.clientes = carregarArquivo("../view/Clientes.fxml");
		porcentagem = 0.664;
		App.cadastroCliente = carregarArquivo("../view/CadastroCliente.fxml");
		porcentagem = 0.747;
		App.contatos = carregarArquivo("../view/Contatos.fxml");
		porcentagem = 0.83;
		App.cadastroContrato = carregarArquivo("../view/CadastroContrato.fxml");
		porcentagem = 0.913;
		App.processo = carregarArquivo("../view/Processo.fxml");
		porcentagem = 1;
		App.cadastrarProcesso = carregarArquivo("../view/Cadastro Processo.fxml");

		App.loginScene = new Scene(App.login);
		App.menuScene = new Scene(App.menu);
		
	}

	public class LoaderThread extends Thread {

		@Override
		public void run() {

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					while(porcentagem < 1)
					{
						try {
							Thread.sleep(50);
							System.out.println(porcentagem);
							System.out.println(texto);
							lblInformacao.setText(texto);
							pgbCarregar.setProgress(porcentagem*10);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
			});

			try {
				preCarregar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
