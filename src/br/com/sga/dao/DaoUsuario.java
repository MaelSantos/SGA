package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoUsuario;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoUsuario implements IDaoUsuario{

	private static DaoUsuario dados;
	private ArrayList<Funcionario> usuarios;
	private Funcionario usuarioLogado;
	
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
	
	public boolean addUsuario(Funcionario usuario)
	{
		for(Funcionario u : usuarios)
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
		
		for(Funcionario u : usuarios)
		{
			if(u.getLogin().equalsIgnoreCase(login) && u.getSenha().equals(senha))
			{
				usuarioLogado = u;
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Funcionario> getUsuarios() {
		return usuarios;
	}

	public Funcionario getUsuarioLogado() {
		return usuarioLogado;
	}

	@Override
	public void salvar(Funcionario usuario) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Curso.INSERT_ALL);

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getEmail());
            statement.execute();
            this.conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - Contate o ADM");
        }
		
	}

	@Override
	public void editar(Funcionario usuario) {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Funcionario buscarPorId(int id) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Funcionario buscarPorCodigo(String codigo) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Funcionario> buscarPorBusca(String busca) {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	
}
