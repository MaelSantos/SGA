package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Funcionario;
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
		//DATA_CONSULTA = ?, VALOR_HONORARIO = ?, AREA  = ?, DESCRICAO = ?, INDICACAO ? WHERE ID = ?
		try {
			
		
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Consulta.UPDATE_ALL);
			this.statement.setDate(1, new Date(entidade.getData_consulta().getTime()));
			this.statement.setFloat(2,entidade.getValor_honorario());
			this.statement.setString(3,entidade.getArea().toString());
			this.statement.setString(4,entidade.getDescricao());
			this.statement.setString(5,entidade.getIndicacao());
			this.statement.setInt(6,entidade.getId());
			this.statement.executeUpdate();
            this.connection.close();
            
            for(Testemunha testemunha : entidade.getTestemunhas())
    			daoCommun.editarTestemunha(testemunha);
            
		}catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO EDITAR CONSULTA - CONTATE O ADM");
        } catch (Exception ex) {
			ex.printStackTrace();
		}
		
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
		        Cliente cliente = null;
		        Funcionario funcionario = null;
		        if(resultSet.next()) {
		        	consulta = new Consulta();
		        	consulta.setId(id);
		        	consulta.setArea(Area.getArea(resultSet.getString("area")));
		        	consulta.setDescricao(resultSet.getString("descricao"));
		        	consulta.setData_consulta(resultSet.getDate("data_consulta"));
		        	consulta.setValor_honorario(resultSet.getFloat("valor_honorario"));
		        	consulta.setIndicacao(resultSet.getString("indicacao"));
		        	consulta.setTestemunhas(testemunhas);
		        	
		        	cliente = new Cliente();
		        	cliente.setId(resultSet.getInt("cliente_id"));
		        	
		        	funcionario = new Funcionario();
		        	funcionario.setId(resultSet.getInt("funcionario_id"));
		        	
		        	consulta.setCliente(cliente);
		        	consulta.setFuncionario(funcionario);
		        }
		        if(consulta == null){
		        	throw new DaoException("N�o existe consultas com esse id");
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
	public List<ConsultaAdapter> buscaPorClienteAdapter(String[] busca) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Consulta.BUSCA_POR_BUSCA);
            
			for(int i =1 ; i <=4; i++) // preparando o statemente referente a dados de cliente
				statement.setString(i,"%"+busca[0]+"%");
			
			if(busca.length == 1) {
				for(int i =5 ; i <=6 ; i++) // caso tenha sido passado apenas dados de cliente ent�o deve-se ignorar os filtros
	        		statement.setString(i,"%_%");
			}else { // caso contrario cada um deve ser considerado
				int aux = 5;
				for(int i = 1 ; i < busca.length ; i++) 
					statement.setString(aux++,(busca[i].length() >0)? busca[i] :"%_%"); // se a string n�o for vazia a adiciono a busca, se n�o desconsidero a mesma
			}
			System.out.println(statement.toString());
			resultSet = statement.executeQuery();
			
           
            List<ConsultaAdapter> lista = new ArrayList<>();
            while(resultSet.next()) {
            	lista.add(new ConsultaAdapter(resultSet.getInt("id"),Area.getArea(resultSet.getString("area")),
            			resultSet.getDate("data_consulta"),resultSet.getFloat("valor_honorario"),(resultSet.getString("nome"))));
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
