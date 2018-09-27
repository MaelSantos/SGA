package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Log;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoLog;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoLog implements IDaoLog{

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;

	@Override
	public void salvar(Log log) throws DaoException {
		
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Log.INSERT_ALL);
			
//			data, evento, remetente, destinatario, status
			statement.setDate(1, new java.sql.Date(log.getData().getTime()));
			statement.setString(2, log.getEvento().name());
			statement.setString(3, log.getRemetente());
			statement.setString(4, log.getDestinatario());
			statement.setString(5, log.getStatus().name());
			
			statement.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR LOG - CONTATE O ADM");
		}

		
	}

	@Override
	public void editar(Log entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Log buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Log> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Log> buscarPorData(Date de, Date ate, String evento, String status) throws DaoException {
		
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Log.SELECT_DATA_INTERVALO);
			this.statement.setDate(1, new java.sql.Date(de.getTime()));
			this.statement.setDate(2, new java.sql.Date(ate.getTime()));
			this.statement.setString(3, "%"+evento+"%");
			this.statement.setString(4, "%"+status+"%");
			
			resultSet = this.statement.executeQuery();
			
			List<Log> logs = new ArrayList<Log>();

			while(resultSet.next()) {
				
				Log log = new Log();
				log.setId(resultSet.getInt("id"));
				log.setData(resultSet.getDate("data"));
				log.setDestinatario(resultSet.getString("destinatario"));
				log.setEvento(EventoLog.getEvento(resultSet.getString("evento")));
				log.setRemetente(resultSet.getString("remetente"));
				log.setStatus(StatusLog.getStatus(resultSet.getString("status")));
	
				logs.add(log);
				
			}if(logs.isEmpty())
				new DaoException("NÃO HÁ LOGS SALVOS ENTRE ESSAS DATAS");

			this.conexao.close();			
			return logs;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}

	}

}
