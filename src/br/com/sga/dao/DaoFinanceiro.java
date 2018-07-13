package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoFinanceiro;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoFinanceiro implements IDaoFinanceiro {
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	DaoCommun daoCommun;
	
	public DaoFinanceiro() {
		daoCommun = new DaoCommun();
	}
	@Override
	public void salvar(Financeiro entidade) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Financeiro.INSERT_ALL);
			//total_lucro,total_despesa,ano_coberto
			statement.setFloat(1,entidade.getTotal_lucro());
			statement.setFloat(2,entidade.getTotal_despesas());
			statement.setString(3,entidade.getAno_conerto());
			statement.execute();
			entidade.setId(daoCommun.getCurrentValorTabela(Tabela.FINANCEIRO)); 
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR FINANCEIRO - CONTATE O ADM");
		}
	}
	
	public static void main(String[] args) {
		Financeiro financeiro = new Financeiro(0f, 0f,"2024"); // ANO UNICO !!!
		try {
			new DaoFinanceiro().salvar(financeiro);
			DaoCommun d = new DaoCommun();
			d.salvarDespesa(new Despesa(Calendar.getInstance().getTime(),false,"comida","Gasto com comida para ir em audiencia",
					100f,"Diversos",Calendar.getInstance().getTime()),financeiro.getId());
			d.salvarDespesa(new Despesa(Calendar.getInstance().getTime(),false,"veiculo","gasolina",
					1000f,"tansporte",Calendar.getInstance().getTime()),financeiro.getId());
			d.salvarReceita(new Receita(Calendar.getInstance().getTime(),false,"cliente","ganho como pagamento por consulta",
					150f,"receita de consulta",Calendar.getInstance().getTime()),financeiro.getId());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void editar(Financeiro entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public Financeiro buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Financeiro buscarPorCodigo(String codigo) throws DaoException {
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
            this.statement = connection.prepareStatement(SQLUtil.Financeiro.BUSCAR_ANO);
            statement.setString(1,String.valueOf(ano));
            resultSet = statement.executeQuery();
            Financeiro financeiro = null;
            if(resultSet.next()) {
            	financeiro = new Financeiro(resultSet.getInt("id"));
            }else {
            	throw new DaoException("ANO NÃO CADASTRADO A BASE");
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
