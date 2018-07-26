package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoConsulta;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoConsulta implements IDaoConsulta {
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	DaoCommun daoCommun ;
	
	public DaoConsulta(){
		daoCommun = DaoCommun.getInstance();
	}
	
	@Override
	public void salvar(Consulta entidade) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Consulta.INSERT_ALL);
	        
			statement.setFloat(1,entidade.getValor_honorario());
			statement.setString(2,entidade.getDescricao());
			statement.setString(3,entidade.getArea().toString());
			statement.setString(4,entidade.getIndicacao());
			statement.setDate(5,new Date(entidade.getData_consulta().getTime()));
			statement.setInt(6,entidade.getCliente().getId());
			statement.setInt(7,entidade.getFuncionario().getId());
			
			statement.execute();
			connection.close();
			
			int consulta_id = daoCommun.getCurrentValorTabela(Tabela.CONSULTA);
			for(Testemunha e: entidade.getTestemunhas())
				daoCommun.salvarTestemunha(e,consulta_id);
			
        } catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ERRO AO SALVAR CONSULTA - CONTATAR ADM");
		}
        
	}
	
	@Override
	public void editar(Consulta entidade) throws DaoException {

	}

	@Override
	public Consulta buscarPorId(int id) throws DaoException {
			try {
				List<Testemunha> testemunhas = daoCommun.getTestemunhas(id);
				
				this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
		        this.statement = connection.prepareStatement(SQLUtil.Consulta.SELECT_ID_CONSULTA);
		        statement.setInt(1,id);
		        resultSet = statement.executeQuery();
		        Consulta consulta = null;
		        if(resultSet.next()) {
		        	consulta = new Consulta();
		        	consulta.setId(id);
		        	consulta.setArea(Area.getArea(resultSet.getString("area")));
		        	consulta.setDescricao(resultSet.getString("descricao"));
		        	consulta.setData_consulta(resultSet.getDate("data_consulta"));
		        	consulta.setValor_honorario(resultSet.getFloat("valor_honorario"));
		        	consulta.setIndicacao(resultSet.getString("indicacao"));
		        	consulta.setTestemunhas(testemunhas);
		        }
		        if(consulta == null){
		        	throw new DaoException("Não existe consultas com ess id");
		        }
		        this.connection.close();
		        this.statement.close();
		        this.resultSet.close();
		        return consulta;
		} catch (SQLException ex) {
	        ex.printStackTrace();
	        throw new DaoException("PROBLEMA AO BUSCAR CONSULTAS DO USUARIO - CONTATE O ADM");
	    }
	}
	@Override
	public List<Consulta> buscarPorBusca(String busca) throws DaoException {
		return null;
	}

	@Override
	public List<ConsultaAdapter> buscaPorClienteAdapter(String busca) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Consulta.BUSCA_POR_CLIENTE);
            statement.setString(1,busca);
            statement.setString(2,busca);
            statement.setString(3,busca);
            statement.setString(4,busca);
            resultSet = statement.executeQuery();
           
            List<ConsultaAdapter> lista = new ArrayList<>();
            while(resultSet.next()) {
            	lista.add(new ConsultaAdapter(resultSet.getInt("id"),Area.getArea(resultSet.getString("area")),
            			resultSet.getDate("data_consulta"),resultSet.getFloat("valor_honorario")));
            }if(lista.isEmpty()){
            	throw new DaoException("Não existe consultas para esse usuario, com esses dados");
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            return lista;
		} catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR CONSULTAS DO USUARIO - CONTATE O ADM");
        }
	}

}
