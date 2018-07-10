package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.business.Validar;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleEditarPerfil implements Initializable{

    @FXML
    private TextField nomeField;

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
    private TextField numero_oabField;

    @FXML
    private Button atualizarLoginButton;

    @FXML
    private Button atualizarPerfilButton;

    @FXML
    private Button atualizarSenhaButton;

    @FXML
    private Button voltarButton;

    private Funcionario funcionario;
    
    private Fachada fachada;

    @FXML
    private void actionButton(ActionEvent event) {
    	if(event.getSource() == voltarButton) {
    		App.notificarOuvintes(Tela.perfil, funcionario);
    	}
    	else
    	{ 
	    	String feedback = null;
	    	Boolean sucesso = false;
    		// copia para dados não sejam editados mesmo errados
	    	Funcionario copiaFuncionario = new Funcionario(funcionario.getId(),funcionario.getNome(),funcionario.getEmail(),funcionario.getLogin(),funcionario.getSenha(),funcionario.getNumero_oab());
	    	
    		if(event.getSource() == atualizarPerfilButton) 
    		{
    			sucesso =atualizarPerfil(copiaFuncionario);
    			feedback = "Dados do perfil";
    			if(sucesso) {
	    			nomeField.setText("");
	    			emailField.setText("");
	    			numero_oabField.setText("");
	    		}
    		}
	    	else if(event.getSource() == atualizarLoginButton)
	    	{
	    		sucesso =atualizarLogin(copiaFuncionario);
	    		feedback = "Login";
	    		if(sucesso) 
	    			loginField.setText("");
	    	}
	    	else if(event.getSource() == atualizarSenhaButton) 
	    	{
	    		sucesso =atualizarSenha(copiaFuncionario);
	    		feedback = "Senha";
	    		if(sucesso) {
	    			senhaAtualField.setText("");
	    			confirmarSenhaField.setText("");
	    			novaSenhaField.setText("");
	    		}
	    	}
	    	try {
				fachada.salvarUsuario(copiaFuncionario);
				if(sucesso) {
					funcionario = copiaFuncionario;
					App.notificarOuvintes(Tela.editar_perfil,funcionario);
					new Alert(AlertType.INFORMATION,feedback+" atualizado com sucesso",ButtonType.OK).show();
				}
			} catch (BusinessException e) {
				new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
			}
    	}
    }
    
    @Override
  	public void initialize(URL location, ResourceBundle resources) {
  		fachada = Fachada.getInstance();
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
    
    private Boolean atualizarSenha(Funcionario usuario) {
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
    			return false ;
    		}
    		if(senhaAtual.equals(usuario.getSenha())) 
        		if(novaSenha.equals(confirmarSenha)) 
        		{
        			usuario.setSenha(novaSenha);
    				return true;
        		}
        		else
        			Alerta.getInstance().showMensagem("Erro","","nova senha e sua confirmação não coincidem");
        	else
        		Alerta.getInstance().showMensagem("Erro","","Senha atual informada é diferente da original");
    		return false;
    	}
    	Alerta.getInstance().showMensagem("Erro","","Nada foi alterado, entradas de texto estão vazias");
    	return false;
    }
    
    private Boolean atualizarLogin(Funcionario usuario) {
    	String login = loginField.getText().trim();
    	if(login.length() >0) 
    	{
			usuario.setLogin(login);
			return true;
    	}
		else 
			Alerta.getInstance().showMensagem("Erro","","Nada foi alterado, entrada de texto esta vazia");
    	return false;
    }
	
    private Boolean atualizarPerfil(Funcionario usuario) {
    	
    	String nome,email,numero_oab;
		nome = nomeField.getText().trim();
		email = emailField.getText().trim();
		numero_oab = numero_oabField.getText().trim();
		if(email.length() >0) {
			String validacao = Validar.getInstance().validarEmail(email);
			if(validacao != null) {
				Alerta.getInstance().showMensagem("","",validacao);
				return false;
			}
		}
		if(nome.length()>0 || email.length()>0 || numero_oab.length()>0) {
			if(nome.length()>0) 
				usuario.setNome(nome);
			if(numero_oab.length()>0) 
				usuario.setNumero_oab(numero_oab);
			if (email.length() >0) 
				usuario.setEmail(email);
			return true;
		}
		else {
			Alerta.getInstance().showMensagem("Erro","","Nada foi alterado, entradas de texto estão vazias");
			return false;
		}
	
    }
}
