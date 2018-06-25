package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private static Funcionario usuarioLogado;
	
	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public DaoUsuario() {
		
		usuarios = new ArrayList<>();
		
	}

	

	public static Funcionario getUsuarioLogado() {
		return usuarioLogado;
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

	@Override
	public void salvar(Funcionario usuario) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Funcionario.INSERT_ALL);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getNumero_oab());
            statement.execute();
            this.conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - Contate o ADM");
        }
		
	}

	@Override
	public Funcionario buscarPorLogin(String login, String senha) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT_LOGIN_SENHA);
            statement.setString(1,login);
            statement.setString(2,senha);
            resultSet = statement.executeQuery();
            
            Funcionario funcionario = null;
            while(resultSet.next()) {
            	usuarioLogado = funcionario;
            	funcionario = new Funcionario(resultSet.getString("nome"), resultSet.getString("login"), resultSet.getString("senha"));
            }
          
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return funcionario;	

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - Contate o ADM");
        }
	}
	public static void main(String[] args) {
		/*try {
			new DaoUsuario().salvar(new Funcionario("Wanderson","Wanderson100v@gmail.com","wanderson100v","1234","123123-31"));
		} catch (DaoException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}*/
		
		
		try {
//			System.out.println(new DaoUsuario().buscarPorLogin("wanderson100v","1234"));
			System.out.println(new DaoUsuario().buscarPorLogin("mael_santos7","0708"));
		} catch (DaoException e) {;
			e.printStackTrace();
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
