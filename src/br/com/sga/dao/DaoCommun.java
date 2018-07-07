package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sga.entidade.enums.Tabela;
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


}
