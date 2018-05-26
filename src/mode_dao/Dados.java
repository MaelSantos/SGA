package mode_dao;

import java.util.ArrayList;

import model_vo.Usuario;

public class Dados {

	private static Dados dados;
	private ArrayList<Usuario> usuarios;
	
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
			System.out.println("Salvo");
			return true;
		}
		
		return false;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
}
