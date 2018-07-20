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
					
					public void updateData()
					{
						updateMessage(texto);
						porcentagem += 5.52631578947;
						updateProgress(porcentagem, 100);
						System.out.println(texto);
					}
					
					@Override
					protected Void call() throws Exception {
						// peso por tela - Double sumPerView =  1.0/ Tela.values().length;
						
						App.login = carregarArquivo("../view/Login.fxml");
						updateData();

						App.cadastro = carregarArquivo("../view/CadastroUsuario.fxml");
						updateData();
						
						App.menu = carregarArquivo("../view/Menu.fxml");
						updateData();

						App.informacoes = carregarArquivo("../view/Informacoes.fxml");
						updateData();
						
						App.perfil = carregarArquivo("../view/Perfil.fxml");
						updateData();
						
						App.editarPerfil = carregarArquivo("../view/EditarPerfil.fxml");
						updateData();
						
						App.configuracoes = carregarArquivo("../view/Configuracoes.fxml");
						updateData();
						
						App.clientes = carregarArquivo("../view/Clientes.fxml");
						updateData();
						
						App.cadastroCliente = carregarArquivo("../view/CadastroCliente.fxml");
						updateData();
						
						App.cadastroContrato = carregarArquivo("../view/CadastroContrato.fxml");
						updateData();
						
						App.processo = carregarArquivo("../view/Processo.fxml");
						updateData();
						
						App.cadastrarProcesso = carregarArquivo("../view/CadastroProcesso.fxml");
						updateData();
						
						App.buscarContrato = carregarArquivo("../view/BuscarContrato.fxml");
						updateData();
						
						App.detalhesProcesso = carregarArquivo("../view/DetalhesProcesso.fxml");
						updateData();
						
						App.cadastrarAudiencia = carregarArquivo("../view/CadastroAudiencia.fxml");
						updateData();
						
						App.financeiro = carregarArquivo("../view/Financeiro.fxml");
						updateData();
						
						App.cadastroReceitaDespesa = carregarArquivo("../view/CadastroReceitaDespesa.fxml");
						updateData();
						
						App.cadastroConsulta = carregarArquivo("../view/CadastroConsulta.fxml");
						updateData();
						
						App.agenda = carregarArquivo("../view/Agenda.fxml");
						updateData();
						
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

	}
	
}
