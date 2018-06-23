package br.com.sga.business;

import br.com.sga.dao.Dados;
import br.com.sga.entidade.Usuario;

public class Verificar {

	public static String validarEmail(String email) {
		if(email.matches("\\w+@\\w+.com|.com.br"))
			return "Email informado invalido";
		for(Usuario usuario : Dados.getInstance().getUsuarios()) 
			if(usuario.getEmail().equalsIgnoreCase(email)) 
				return "Email informado já esta cadastrado";
		return null;
	}
	
	public static boolean validerLogin(String login) {
		for(Usuario usuario : Dados.getInstance().getUsuarios()) 
			if(usuario.getLogin().equalsIgnoreCase(login)) 
				return false;
		return true;
	}
	
	public static String validarSenha(String senha) {
		if(senha.length()<8)
			return "Senha curta, minimo 8 caracteres";
		return null;
	}
	public static void main(String[] args) {
	
	}

}
