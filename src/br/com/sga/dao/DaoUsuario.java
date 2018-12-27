package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoUsuario;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoUsuario implements IDaoUsuario{

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private DaoCommun daoCommun;
	

	public DaoUsuario() {
		daoCommun = DaoCommun.getInstance();
	}
	
	@Override
	public void salvar(Funcionario entidade) throws DaoException {
		try {
			String sql = null;
			Integer endereco_id = null;
			if(entidade.getEndereco()!= null) {
				sql = SQLUtil.Funcionario.INSERT_ALL;
				daoCommun.salvarEndereco(entidade.getEndereco());
				endereco_id = daoCommun.getCurrentValorTabela(Tabela.ENDERECO);
			}else {
				sql = SQLUtil.Funcionario.INSERT_SEM_ENDERECO;
			}
			
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(sql);

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
			ex.printStackTrace();
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
            	funcionario.setEndereco(daoCommun.getEndereco(resultSet.getInt("endereco_id")));
            }else {
            	throw new DaoException("Não existe usuarios com esses dados");
            }
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return funcionario;	
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR USUARIO - CONTATE O ADM");
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
            
            daoCommun.EditarEndereco(entidade.getEndereco());
            
		}catch (PSQLException ex) {
			throw new DaoException("EMAIL, LOGIN OU NUMERO OAB JÁ ESTÁ CADASTRATO");
		}catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO EDITAR USUARIO - CONTATE O ADM");
        }
		
	}

	@Override
	public Funcionario buscarPorId(int id) throws DaoException {
		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT_ID);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();

			Funcionario funcionario = null;
			if (resultSet.next()) {

				funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("login"), resultSet.getString("senha"), resultSet.getString("numero_oab"));
            	funcionario.setEndereco(daoCommun.getEndereco(resultSet.getInt("endereco_id")));
            	
			}

			this.conexao.close();
			return funcionario;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("ERRO AO BUSCAR USUARIO POR ID - CONTATE O ADM");
		}

	}
	
	public FuncionarioAdapter buscarPorConsultaAdapter(Integer consulta_id) throws DaoException{
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT_ID_CONSULTA_ADAPTER);
            statement.setInt(1, consulta_id);
            resultSet = statement.executeQuery();
            FuncionarioAdapter funcionario = null;
            
            if(resultSet.next()) {
            	funcionario = new FuncionarioAdapter();
            	funcionario.setId(resultSet.getInt("id"));
            	funcionario.setNome(resultSet.getString("nome"));
            	funcionario.setEmail(resultSet.getString("email"));
            	funcionario.setNumero(resultSet.getString("numero_oab"));
            }else {
            	throw new DaoException("Não existe funcionario para essa consulta");
            }
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return funcionario;	
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR USUARIO - CONTATE O ADM");
        }
	}

	@Override
	public List<Funcionario> buscarPorBusca(String busca) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT_ALL_BUSCA_ALL);
            statement.setString(1,busca);
            statement.setString(2,busca);
            statement.setString(3,busca);
            statement.setString(4,busca);
            statement.setString(5,busca);
            resultSet = statement.executeQuery();
            
            List<Funcionario> lista = new ArrayList<>();
            while(resultSet.next()) {
            	lista.add(new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), 
            			resultSet.getString("login"), resultSet.getString("senha"), resultSet.getString("numero_oab"),
            			daoCommun.getEndereco(resultSet.getInt("endereco_id"))));
            	
            }if(lista.isEmpty()){
            	throw new DaoException("Não existe usuarios com esses dados");
            }
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return lista;
		} catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR USUÁRIOS - CONTATE O ADM");
        }
	}

	@Override
	public Funcionario buscarPorIdConsulta(int id_consulta) throws DaoException {
		
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT_CONSULTA_ID);
            statement.setInt(1, id_consulta);
            resultSet = statement.executeQuery();
            Funcionario funcionario = null;
            
            if(resultSet.next()) {
            	funcionario = new Funcionario(
            			resultSet.getInt("id"), 
            			resultSet.getString("nome"), 
            			resultSet.getString("email"), 
            			resultSet.getString("login"), 
            			resultSet.getString("senha"), 
            			resultSet.getString("numero_oab"),
            			daoCommun.getEndereco(resultSet.getInt("endereco_id")));
            }else {
            	throw new DaoException("Não existe funcionario para essa consulta");
            }
            this.conexao.close();
            this.statement.close();
            this.resultSet.close();
            return funcionario;	
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR USUARIO - CONTATE O ADM");
        }
	}

}
