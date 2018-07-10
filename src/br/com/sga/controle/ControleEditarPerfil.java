package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.business.Validar;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControleEditarPerfil implements Initializable{

    @FXML
    private TextField nomeField;

    @FXML
    private TextField sobrenomeField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaAtualField;

    @FXML
    private PasswordField novaSenhaField;

    @FXML
    private PasswordField confirmarSenhaField;

    @FXML
    private TextField loginField;

    @FXML
    private Button atualizarLoginButton;

    @FXML
    private Button atualizarPerfilButton;

    @FXML
    private Button atualizarSenhaButton;

    @FXML
    private Button voltarButton;

    private Funcionario funcionario;

    @FXML
    private void actionButton(ActionEvent event) {
    	if(event.getSource() == voltarButton) {
    		App.notificarOuvintes(Tela.perfil, funcionario);
    	}
    	else
    	{ 
	    	if(event.getSource() == atualizarPerfilButton) 
	    		atualizarPerfil(funcionario);
	    	else if(event.getSource() == atualizarLoginButton)
	    	{
	    		atualizarLogin(funcionario);
	    	}
	    	else if(event.getSource() == atualizarSenhaButton) 
	    	{
	    		atualizarSenha(funcionario);
	    	}
    	}
    }
    
    @Override
  	public void initialize(URL location, ResourceBundle resources) {
  		App.addOuvinte(new Ouvinte() {
  			@Override
  			public void atualizar(Tela tela, Funcionario usuario) {
  				funcionario = usuario;
  				if(tela == Tela.editar_perfil) {
  					funcionario = usuario;
  					System.out.println(funcionario.getNome());
  					System.out.println(funcionario.getEmail());
  					System.out.println(funcionario.getLogin());
  					nomeField.setPromptText(funcionario.getNome());
  					emailField.setPromptText(funcionario.getEmail());
  					loginField.setPromptText(funcionario.getLogin());
  				}
  			}
  		});
  		
  	}
    
    private void atualizarSenha(Funcionario usuario) {
    	String senhaAtual,novaSenha,confirmarSenha;
    	senhaAtual = senhaAtualField.getText().trim();
    	novaSenha = novaSenhaField.getText().trim();
    	confirmarSenha = confirmarSenhaField.getText().trim();
    	
    	if(senhaAtual.length() >0 && novaSenha.length() >0 && confirmarSenha.length() >0 ) 
    	{
    		String validacao = Validar.getInstance().validarSenha(novaSenha);
    		if(validacao != null) 
    		{
    			Alerta.getInstance().showMensagem("Erro","",validacao);
    			return ;
    		}
    		if(senhaAtual.equals(usuario.getSenha())) 
        	{
        		if(novaSenha.equals(confirmarSenha)) 
        		{
        			usuario.setSenha(novaSenha);
        			Alerta.getInstance().showMensagem("Confirmação","","Senha alterada com sucesso");
        		}
        		else
        			Alerta.getInstance().showMensagem("Erro","","nova senha e sua confirmação não coincidem");
        	}
        	else
        		Alerta.getInstance().showMensagem("Erro","","Senha atual informada é diferente da original");
    		return;
    	}
    	Alerta.getInstance().showMensagem("Erro","","Nada foi alterado, entradas de texto estão vazias");
    }
    
    private void atualizarLogin(Funcionario usuario) {
    	String login = loginField.getText().trim();
    	if(login.length() >0) {
			/*if(Verificar.validerLogin(login)) 
			{
				usuario.setLogin(login);
				Alerta.getInstance().showMensagem("","","Login alterado com sucesso");
				return;
			}
			Alerta.getInstance().showMensagem("Confirmação","","Login já esta cadastrado , por favor informe outro");*/
    	}
    	else 
			Alerta.getInstance().showMensagem("Erro","","Nada foi alterado, entrada de texto esta vazia");
    }
	
    private void atualizarPerfil(Funcionario usuario) {
    	
    	String nome,email,sobrenome;
		nome = nomeField.getText().trim();
		email = emailField.getText().trim();
		sobrenome = sobrenomeField.getText().trim();
		
		if(email.length() >0) {
			String validacao = Validar.getInstance().validarEmail(email);
			if(validacao != null) {
				Alerta.getInstance().showMensagem("","",validacao);
				return;
			}
		if(nome.length()>0 || email.length()>0 || sobrenome.length() >0) {
			StringBuffer feedbak = new StringBuffer("Foi alterado ");
			int tamIni = feedbak.length();
			int tamAtual = tamIni;
			if(nome.length()>0) 
			{
				usuario.setNome(nome);
				feedbak.append(" nome");
				tamAtual = feedbak.length();
			}
			/*if(sobrenome.length() >0)  
			{
				usuario.setSobrenome(sobrenome);
				if(tamIni == tamAtual)
					feedbak.append(" Sobrenome");
				else {
					feedbak.append(", sobrenome");
					tamAtual =feedbak.length();
				}
			}*/
			if (email.length() >0) 
			{
				usuario.setEmail(email);
				if(tamIni == tamAtual)
					feedbak.append(" email");
				else 
					feedbak.append("e email");
			}
			Alerta.getInstance().showMensagem("Confirmação","Dados alterados: ",feedbak.toString());
		}
		else
			Alerta.getInstance().showMensagem("Erro","","Nada foi alterado, entradas de texto estão vazias");
		}
    }
}
