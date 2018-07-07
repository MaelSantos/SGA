package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sga.entidade.enums.Tabela;
import java.util.ArrayList;

import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoCommun implements IDaoCommun{
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	
	@Override
	public int getCurrentValorTabela(Tabela tabela) throws DaoException {
		  try {
	            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
	            this.statement = connection.prepareStatement("select id from " + tabela.toString() + " order by id desc limit 1");

	            resultSet = this.statement.executeQuery();
	            int id;
	            if (resultSet.next()) {
	                id = resultSet.getInt(1);
	            } else {
	                throw new DaoException("N√£o h√° registro nessa tabela");
	            }
	            this.connection.close();
	            return id;

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            throw new DaoException("PROBLEMA AO CONSULTAR " + tabela.toString() + " - Contate o ADM");
	        }
	}

	@Override
	public void salvarVinculoFuncionario(Integer notificacao_id, Integer funcionario_id)  throws DaoException {
		try {
			connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			statement  = connection.prepareStatement(SQLUtil.VinculoFuncionario.INSERT_ALL);
			statement.setInt(1,notificacao_id);
			statement.setInt(2,funcionario_id);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
	        throw new DaoException("PROBLEMA AO SALVAR VINCULO FUNCIONARIO/NOTIFICA«√O CONTATE ADM");
		}
	}

    @Override
    public void salvarEndereco(Endereco endereco) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Endereco.INSERT_ALL);
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getNumero());
            statement.setString(3, endereco.getBairro());
            statement.setString(4, endereco.getCidade());
            statement.setString(5, endereco.getEstado());
            statement.execute();
            this.connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR ENDERECO - Contate o ADM");
        }
    }

    @Override
    public void salvarContato(Telefone telefone, int id) throws DaoException {

        try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Telefone.INSERT_ALL);
            statement.setInt(1, telefone.getNumero());
            statement.setInt(2, telefone.getPrefixo());
            statement.setInt(3, id);
            statement.execute();
            this.connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR ENDERECO - Contate o ADM");
        }

    }

    @Override
    public List<Telefone> getContatos(Integer id) throws DaoException {

        try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Telefone.SELECT_TELEFONE_CLIENTE);
            this.statement.setInt(1, id);

            resultSet = this.statement.executeQuery();
            List<Telefone> contatos = new ArrayList<>();
            Telefone telefone;
            while (resultSet.next()) {
//            	id ,cliente_id, numero, prefixo
                telefone = new Telefone();
                telefone.setId(resultSet.getInt(1));
                telefone.setPrefixo(resultSet.getInt(2));
                telefone.setNumero(resultSet.getInt(3));
                contatos.add(telefone);
            }
            this.connection.close();

            return contatos;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO CONSULTAR CONTATOS - Contate o ADM");
        }

    }
}
