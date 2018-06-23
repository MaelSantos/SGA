package br.com.sga.dao;

import java.util.ArrayList;

import br.com.sga.entidade.Usuario;

public class Dados{

	private static Dados dados;
	private ArrayList<Usuario> usuarios;
	private Usuario usuarioLogado;
	
	private Dados() {
		
		usuarios = new ArrayList<>();
		
	}

	public static Dados getInstance()
	{
		if(dados == null)
			dados = new Dados();
		return dados;
	}
	
	public boolean addUsuario(Usuario usuario)
	{
		for(Usuario u : usuarios)
		{
			if(usuario.equals(u))
			{
				return false;// se entrar sigifica que existe um usuario igual ja registrado 
			}
		}
		
		usuarios.add(usuario);
		return true;
	}

	public boolean entrarSistema(String login, String senha) {
		
		for(Usuario u : usuarios)
		{
			if(u.getLogin().equalsIgnoreCase(login) && u.getSenha().equals(senha))
			{
				usuarioLogado = u;
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
}
