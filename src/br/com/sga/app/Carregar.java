package br.com.sga.app;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.view.Alerta;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
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
		Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource(caminho));
		this.texto = caminho;
		return pane;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		servico = new Service<Object>() {			
			@Override
			protected Task<Object> createTask() {
				return new Task<Object>() {
					
					int contador = 0;
					
					public void updateData()
					{
						updateMessage(texto);
						porcentagem += 100/27; //porcentagem total dividido por quantidade de telas
						updateProgress(porcentagem, 100);
						System.out.println(contador++);
						System.out.println(texto);
					}
					
					@Override
					protected Void call() throws Exception {
						// peso por tela - Double sumPerView =  1.0/ Tela.values().length;
						try {
						
							App.login = carregarArquivo("br/com/sga/view/Login.fxml");
							updateData();
	
							App.cadastro = carregarArquivo("br/com/sga/view/CadastroUsuario.fxml");
							updateData();
							
							App.menu = carregarArquivo("br/com/sga/view/Menu.fxml");
							updateData();
	
							App.informacoes = carregarArquivo("br/com/sga/view/Informacoes.fxml");
							updateData();
							
							App.perfil = carregarArquivo("br/com/sga/view/Perfil.fxml");
							updateData();
							
							App.editarPerfil = carregarArquivo("br/com/sga/view/EditarPerfil.fxml");
							updateData();
							
							App.configuracoes = carregarArquivo("br/com/sga/view/Configuracoes.fxml");
							updateData();
							
							App.clientes = carregarArquivo("br/com/sga/view/Clientes.fxml");
							updateData();														
							
							App.cadastroCliente = carregarArquivo("br/com/sga/view/CadastroCliente.fxml");
							updateData();
							
							App.cadastroContrato = carregarArquivo("br/com/sga/view/CadastroContrato.fxml");
							updateData();
							
							App.processo = carregarArquivo("br/com/sga/view/Processo.fxml");
							updateData();
							
							App.cadastrarProcesso = carregarArquivo("br/com/sga/view/CadastroProcesso.fxml");
							updateData();
							
							App.cadastroParte = carregarArquivo("br/com/sga/view/CadastroParte.fxml");
							updateData();
							
							App.buscarContrato = carregarArquivo("br/com/sga/view/BuscarContrato.fxml");
							updateData();
							
							App.detalhesProcesso = carregarArquivo("br/com/sga/view/DetalhesProcesso.fxml");
							updateData();
							
							App.cadastrarAudiencia = carregarArquivo("br/com/sga/view/CadastroAudiencia.fxml");
							updateData();
							
							App.financeiro = carregarArquivo("br/com/sga/view/Financeiro.fxml");
							updateData();
							
							App.cadastroReceitaDespesa = carregarArquivo("br/com/sga/view/CadastroReceitaDespesa.fxml");
							updateData();
							
							App.cadastroConsulta = carregarArquivo("br/com/sga/view/CadastroConsulta.fxml");
							updateData();
							
							App.agenda = carregarArquivo("br/com/sga/view/Agenda.fxml");
							updateData();
							
							App.consulta = carregarArquivo("br/com/sga/view/Consulta.fxml");
							updateData();
							
							
							App.detalhesConsulta = carregarArquivo("br/com/sga/view/DetalhesConsulta.fxml");
							updateData();
							
							
							App.detalhesContrato = carregarArquivo("br/com/sga/view/DetalhesContrato.fxml");
							updateData();
							
							App.documentos = carregarArquivo("br/com/sga/view/Documentos.fxml");
							updateData();
							
							try {
								Fachada.getInstance().validarNotificacoes(Calendar.getInstance().getTime());
							}catch(Exception e ) {
								e.printStackTrace();
							}
							
							App.home = carregarArquivo("br/com/sga/view/Home.fxml");
							updateData();
							
							App.historico = carregarArquivo("br/com/sga/view/Historico.fxml");
							updateData();
							
							try {
							App.estatistica = carregarArquivo("br/com/sga/view/Estatistica.fxml");
							updateData();
							}catch (Exception e) {
								e.printStackTrace();
							}
							
							App.detalhesNotificacao = carregarArquivo("br/com/sga/view/DetalhesNotificacao.fxml");
							updateData();
							
							App.loginScene = new Scene(App.login);
							App.menuScene = new Scene(App.menu);
							
							return null;
						}catch (Exception e) {
							e.printStackTrace();
							Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!","","Erro ao carregar tela!!!, Contate o ADM");
							throw new Exception();
						}
					}
					@Override
					protected void succeeded() {
						super.succeeded();
						App.changeStage(Tela.LOGIN);
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
