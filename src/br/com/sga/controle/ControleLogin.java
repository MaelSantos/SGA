package br.com.sga.controle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
	
	private IFachada fachada;
	private Funcionario funcionario;

	@FXML
	private void actionButton(ActionEvent e)
	{		
		if(e.getSource() == btnEntrar)
		{			
			Log log;
			try {
				funcionario = fachada.buscarPorLogin(tfdLogin.getText(), tfdSenha.getText());
				App.changeStage(Tela.menu);
				App.notificarOuvintes(Tela.menu,funcionario);
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.LOGIN, "Sistema", "Login: "+funcionario.getNome(), StatusLog.COLCLUIDO);
			}catch (Exception e1) {
				Alerta.getInstance().showMensagem("Dados Incorretos", "Login/Email Ou Senha Incorretos",e1.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.LOGIN, "Sistema", "Login: Erro "+tfdLogin.getText().trim(), StatusLog.ERRO);
			}
			tfdLogin.setText("");
			tfdSenha.setText("");
			
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e1) {
				// TODO Bloco catch gerado automaticamente
				e1.printStackTrace();
			}
		}

		if(e.getSource() == btnSair)
		{
			try {
				fachada.salvarEditarLog(new Log(new Date(System.currentTimeMillis()), EventoLog.ENCERRAR, funcionario.getNome(), "Encerrando Sistema: ", StatusLog.COLCLUIDO));
			} catch (BusinessException e1) {
				// TODO Bloco catch gerado automaticamente
				e1.printStackTrace();
			}
			
			System.exit(0);
		}	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = Fachada.getInstance();		
	}

}
