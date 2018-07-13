package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;


public class ControleLogin implements Initializable{

	@FXML
	private TextField tfdLogin;

	@FXML
	private Button btnEntrar;

	@FXML
	private Button btnSair;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private ProgressIndicator progresso;
	Service<Void> servico;
	
	private IFachada fachada;

	@FXML
	private void actionButton(ActionEvent e)
	{		
		if(e.getSource() == btnEntrar)
		{			
			try {
				Funcionario funcionario = fachada.buscarPorLogin(tfdLogin.getText(), tfdSenha.getText());
				App.changeStage(Tela.menu);
				App.notificarOuvintes(Tela.menu,funcionario);
			}catch (BusinessException e1) {
				Alerta.getInstance().showMensagem("Dados Incorretos", "Login/Email Ou Senha Incorretos",e1.getMessage());
			}
		}

		if(e.getSource() == btnSair)
		{
			System.exit(0);
		}	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = Fachada.getInstance();		
	}

}
