package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Usuario;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoUsuario;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoUsuario implements IDaoUsuario{

	private static DaoUsuario dados;
	private ArrayList<Usuario> usuarios;
	private Usuario usuarioLogado;
	
	private Connection conexao;
	private PreparedStatement statement;
	
	public DaoUsuario() {
		
		usuarios = new ArrayList<>();
		
	}

	public static DaoUsuario getInstance()
	{
		if(dados == null)
			dados = new DaoUsuario();
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

	@Override
	public void salvar(Usuario usuario) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Curso.INSERT_ALL);

            statement.setString(1, usuario.getNome());
//            statement.setString(2, usuario.getCodigo());
//            statement.setInt(3, usuario.getCargaHoraria());
            statement.execute();
            this.conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR CURSO - Contate o ADM");
        }
		
	}

	@Override
	public void editar(Usuario usuario) {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Usuario buscarPorId(int id) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Usuario buscarPorCodigo(String codigo) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Usuario> buscarPorBusca(String busca) {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	
}
