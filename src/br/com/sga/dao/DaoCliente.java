package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCliente;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoCliente implements IDaoCliente {

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	@Override
	public void salvar(Cliente entidade) throws DaoException {
		
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Cliente.INSERT_ALL);

            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getSenha());
            statement.setString(3, entidade.getLogin());
            statement.setString(4, entidade.getNumero_oab());
            statement.execute();
            this.conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - CONTATE O ADM");
        }

		

	}

	@Override
	public void editar(Cliente entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public Cliente buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Cliente buscarPorCodigo(String codigo) throws DaoException {
		try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Cliente.SELECT_CPF_CNPJ);
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
            throw new DaoException("PROBLEMA AO SALVAR USUARIO - CONTATE O ADM");
        }
		
		return null;
	}

	@Override
	public List<Cliente> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

}
