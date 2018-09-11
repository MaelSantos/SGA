package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.interfaces.IDaoFinanceiro;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoFinanceiro implements IDaoFinanceiro {
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	IDaoCommun daoCommun;

	public DaoFinanceiro() {
		daoCommun = DaoCommun.getInstance();
	}
	@Override
	public void salvar(Financeiro entidade) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Financeiro.INSERT_ALL);
			//total_lucro,total_despesa,ano_coberto
			statement.setFloat(1,entidade.getTotal_lucro());
			statement.setFloat(2,entidade.getTotal_despesas());
			statement.setString(3,entidade.getAno_coberto());
			statement.execute();
			entidade.setId(daoCommun.getCurrentValorTabela(Tabela.FINANCEIRO)); 
			connection.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR FINANCEIRO - CONTATE O ADM");
		}
		
	}

	@Override
	public void editar(Financeiro entidade) throws DaoException {
		//"UPDATE FINANCEIRO SET ano_coberto = ?, total_despesa = ? , total_lucro = ? where id = ?";
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Financeiro.UPDATE_ALL);

			for(Receita r : entidade.getReceitas())
				entidade.setTotal_lucro(entidade.getTotal_lucro() + r.getValor());
			for(Despesa d : entidade.getDespesas())
				entidade.setTotal_despesas(entidade.getTotal_despesas() + d.getValor());
			
			statement.setString(1,entidade.getAno_coberto());
			statement.setFloat(2,entidade.getTotal_despesas());
			statement.setFloat(3,entidade.getTotal_lucro());
			statement.setInt(4,entidade.getId());
			statement.executeUpdate();

			this.connection.close();

		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO ATUALIZAR DADOS FINANCEIROS - CONTATE O ADM");
		}
	}

	@Override
	public Financeiro buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}


	@Override
	public List<Financeiro> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	@Override
	public Financeiro buscarPorAno(Integer ano) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Financeiro.SELECT_ID_ANO);
			statement.setString(1,String.valueOf(ano));
			resultSet = statement.executeQuery();
			Financeiro financeiro = null;
			if(resultSet.next()) {
				financeiro = new Financeiro(resultSet.getInt("id"));
			}else {
				financeiro = new Financeiro(0, 0,  ano+""); 
				salvar(financeiro);
				
//				throw new DaoException("ANO NÃO CADASTRADO A BASE");				
			}
			this.connection.close();
			this.statement.close();
			this.resultSet.close();
			return financeiro;	
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR FINANCEIRO - CONTATE O ADM");
		}
	}
	@Override
	public Financeiro buscarPorIntervalo(Date de, Date ate) throws DaoException {
		
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Financeiro.SELECT_ANO);
			SimpleDateFormat ano = new SimpleDateFormat("yyyy");
			statement.setString(1, ano.format(ate));
			resultSet = statement.executeQuery();
			
			Financeiro financeiro = null;
			if(resultSet.next()) {
				financeiro = new Financeiro(resultSet.getInt("id"));
				financeiro.setAno_coberto(resultSet.getString("ano_coberto"));
				financeiro.setTotal_despesas(resultSet.getFloat("total_despesa"));
				financeiro.setTotal_lucro(resultSet.getFloat("total_lucro"));

				financeiro.setReceitas(daoCommun.getReceitaPorIntervalo(de, ate));
				financeiro.setDespesas(daoCommun.getDespesaPorIntervalo(de, ate));

			}
			this.connection.close();
			this.statement.close();
			this.resultSet.close();
			return financeiro;	
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR FINANCEIRO - CONTATE O ADM");
		}

	}

}
