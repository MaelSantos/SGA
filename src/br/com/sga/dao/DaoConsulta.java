package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.entidade.enums.TipoTelefone;
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
	
//	public static void main(String[] args) {
//		//Salvar consulta
//		// * 1 - testemunhas - > dados da testemunha , endereço , telefone e nome
//		// * 2 - demais dados da consulta,
//		// * 3 - buscar cliente e funcionario correspondente (para fins de teste irei por já o id)
//		 
//		Endereco e1 = new Endereco( "Andrelino Jose ","412", "AAVV","Pesqueira", "TA", "Brasil","casa","50230-550");
//		Telefone tel1 = new Telefone(3213123,32,TipoTelefone.PESSOAL);
//		Testemunha t1 = new Testemunha(e1, tel1,"zèsin");
//		
//		Endereco e2 = new Endereco( "Jose P","232", "Coa","Pesqueira", "TA", "Brasil","Ca","50230-550");
//		Telefone tel2 = new Telefone(3213123,32,TipoTelefone.RESIDENCIAL);
//		Testemunha t2 = new Testemunha(e2, tel2,"Pedro pereira");
//		
//		List<Testemunha> testemunhas = new ArrayList<>();
//		testemunhas.add(t1);
//		testemunhas.add(t2);
//		
//		List<Telefone> telefonesCliente = new ArrayList<>();
//		telefonesCliente.add(tel2);
//		Cliente cliente = new Cliente("Jose2",Calendar.getInstance().getTime(),"12343",Sexo.MASCULINO,"123343","zé@Hmail2","sar","Babado",false,"Não",TipoCliente.FISICO,e2, telefonesCliente);
//		cliente.setEndereco(e2);
//		try {
//			DaoCliente.getInstance().salvar(cliente);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//		
//		Funcionario funcionario = new Funcionario();
//		funcionario.setId(2); // <---------------------------------------------------------
//		Consulta consulta = new Consulta(Area.TRABALHISTA,"Descrição do caso entrada por funcionario",Calendar.getInstance().getTime(),13000f,"Maria", cliente, funcionario, testemunhas);
//		try {
//			new DaoConsulta().salvar(consulta);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void editar(Consulta entidade) throws DaoException {

	}

	@Override
	public Consulta buscarPorId(int id) throws DaoException {
		return null;
	}

	@Override
	public List<Consulta> buscarPorBusca(String busca) throws DaoException {
		return null;
	}

	@Override
	public List<Consulta> buscaPorCliente(String busca) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Consulta.BUSCA_POR_CLIENTE);
            statement.setString(1,busca);
            statement.setString(2,busca);
            statement.setString(3,busca);
            statement.setString(4,busca);
            resultSet = statement.executeQuery();
           
            List<Consulta> lista = new ArrayList<>();
            while(resultSet.next()) {
            	lista.add(new Consulta(resultSet.getInt("id"),
            			Area.getArea(resultSet.getString("area")),
            			resultSet.getDate("data_consulta")));
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
