package br.com.sga.controle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.dao.DaoXml;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoXml;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class ControleLogin implements Initializable {

	@FXML
	private TextField tfdLogin;

	@FXML
	private Button btnEntrar;

	@FXML
	private Button btnConfigurar;
	
	@FXML
	private Button btnSair;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private ImageView imgLogo;

	private IFachada fachada;
	private Dialogo dialogo;
	private IDaoXml daoXml;
	private Funcionario funcionario;
	private String usuario;

	@FXML
	private void actionButton(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if (obj == btnEntrar) {
			Log log;
			try {
				funcionario = fachada.buscarPorLogin(tfdLogin.getText(), tfdSenha.getText());
				usuario = funcionario.getNome();
				App.changeStage(Tela.MENU);
				App.notificarOuvintes(Tela.MENU, funcionario);

				log = new Log(new Date(System.currentTimeMillis()), EventoLog.LOGIN, funcionario.getNome(),
						"Login: Sistema", StatusLog.CONCLUIDO);

			} catch (Exception e1) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Dados Incorretos", "Login/Email Ou Senha Incorretos",
						e1.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.LOGIN, tfdLogin.getText().trim(),
						"Login: Erro ", StatusLog.ERRO);
			}
			tfdLogin.setText("");
			tfdSenha.setText("");

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		else if(obj == btnConfigurar)
		{
			String ip = dialogo.dialogoDeEntradaText("Configurar IP", "IP atual: "+daoXml.getIp(), "Escolha Um Novo IP");
			
			if(!ip.trim().isEmpty())
			{
				
				System.out.println("Retorno: "+ip);
				daoXml.SalvarEditarIP(ip);
				
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "IP Alterado Para: "+ip, "");
			}
			else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Nada Alterado", "Nada Foi Modificado: IP Atual: "+daoXml.getIp(), "Informe Algum Dado!!!");
		}
		else if (obj == btnSair) {
			try {
				fachada.salvarEditarLog(new Log(new Date(System.currentTimeMillis()), EventoLog.ENCERRAR,
						usuario, "Encerrando Sistema: ", StatusLog.CONCLUIDO));
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}

			System.exit(0);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();
		daoXml = DaoXml.getInstance();
		
		usuario = "";
	}

}
