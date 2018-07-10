package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoUsuario;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoUsuario implements IDaoUsuario{

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private DaoCommun daoCommun = new DaoCommun();
	

	@Override
	public void salvar(Funcionario entidade) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			Integer endereco_id = null;
			if(entidade.getEndereco()!= null) {
				daoCommun.salvarEndereco(entidade.getEndereco());
				endereco_id = daoCommun.getCurrentValorTabela(Tabela.ENDERECO);
				this.statement = conexao.prepareStatement(SQLUtil.Funcionario.INSERT_ALL);
			}else {
				this.statement = conexao.prepareStatement(SQLUtil.Funcionario.INSERT_SEM_ENDERECO);
			}

            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getSenha());
            statement.setString(3, entidade.getLogin());
            statement.setString(4, entidade.getNumero_oab());
            statement.setString(5,entidade.getEmail());
            if(endereco_id != null)
            	statement.setInt(6,endereco_id);
            statement.execute();
            this.conexao.close();

		}catch (PSQLException ex) {
			throw new DaoException("EMAIL, LOGIN OU NUMERO OAB JÁ ESTÁ CADASTRATO");
		}catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - CONTATE O ADM");
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
            if(resultSet.next()) {
            	funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"), resultSet.getString("numero_oab"));
            }else {
            	throw new DaoException("Não existe usuarios com esses dados");
            }
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return funcionario;	
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - CONTATE O ADM");
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
	public void editar(Funcionario entidade) throws DaoException {
		//"UPDATE FUNCIONARIO SET NOME = ?, LOGIN = ? , SENHA = ?, NUMERO_OAB = ?, EMAIL = ? where id = ?";
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Funcionario.UPDATE_ALL);
			
            statement.setString(1,entidade.getNome());
            statement.setString(2,entidade.getLogin());
            statement.setString(3,entidade.getSenha());
            statement.setString(4,entidade.getNumero_oab());
            statement.setString(5,entidade.getEmail());
            statement.setInt(6,entidade.getId());
            statement.executeUpdate();
            this.conexao.close();
            
		}catch (PSQLException ex) {
			throw new DaoException("EMAIL, LOGIN OU NUMERO OAB JÁ ESTÁ CADASTRATO");
		}catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO EDITAR USUARIO - CONTATE O ADM");
        }
		
	}

	@Override
	public Funcionario buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Funcionario buscarPorCodigo(String codigo) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Funcionario> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

/*

	@Override
	public Funcionario buscarPorNome(String nome) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT_NOME);
            statement.setString(1,nome);
            resultSet = statement.executeQuery();
            
            Funcionario f = null;
            while(resultSet.next()) {
            	f = new Funcionario(resultSet.getInt("id"));
            }
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return f;	

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - Contate o ADM");
        }
	}
	*/

	
}
