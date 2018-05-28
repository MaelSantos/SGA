package model_dao;

import java.util.ArrayList;

import model_vo.Usuario;

public class Dados {

	private static Dados dados;
	private ArrayList<Usuario> usuarios;
	private Usuario usuarioLogado;
	
	private Dados() {
		
		usuarios = new ArrayList<>();
		usuarioLogado = new Usuario("Vazio", "Vazio", "Vazio", "Vazio", "Vazio");
	}

	public static Dados getInstance()
	{
		if(dados == null)
			dados = new Dados();
		return dados;
	}
	
	public boolean addUsuario(Usuario usuario)
	{
		boolean add = true;
		for(Usuario u : usuarios)
		{
			if(usuario.equals(u))
			{
				add = false;
			}
		}
		if(add)
		{
			usuarios.add(usuario);
			return true;
		}
		
		return false;
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
