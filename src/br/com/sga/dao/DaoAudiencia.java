package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Audiencia;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoAudiencia;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoAudiencia implements IDaoAudiencia {

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public DaoAudiencia() {
	}

	@Override
	public void salvar(Audiencia entidade) throws DaoException {
		
		try {
			
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Audiencia.INSERT_ALL);
			
//			data_audiencia,tipo,vara,orgao,status,processo_id
			statement.setDate(1, new Date(entidade.getData_audiencia().getTime()));
			statement.setString(2, entidade.getTipo());
			statement.setString(3, entidade.getVara());
			statement.setString(4, entidade.getOrgao());
			statement.setString(5, entidade.getStatus());
			statement.setInt(12, entidade.getProcesso().getId());
			
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR AUDIENCIA - CONTATE O ADM");
		}

	}

	@Override
	public void editar(Audiencia entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public Audiencia buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Audiencia> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Audiencia> buscarPorIdProcesso(int id) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Audiencia.SELECT_PROCESSO_ID);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();

			List<Audiencia> audiencias = new ArrayList<>();
			Audiencia audiencia;
			
			while(resultSet.next()) {

				audiencia = new Audiencia();
				
//				data_audiencia,tipo,vara,orgao,status,processo_id
				audiencia.setId(resultSet.getInt("id"));
				audiencia.setData_audiencia(resultSet.getDate("data_audiencia"));
				audiencia.setOrgao(resultSet.getString("orgao"));
				audiencia.setStatus(resultSet.getString("status"));
				audiencia.setTipo(resultSet.getString("tipo"));
				audiencia.setVara(resultSet.getString("vara"));
				
				audiencias.add(audiencia);
			}

			this.conexao.close();
			return audiencias;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR AUDIENCIA - CONTATE O ADM");
		}

	}

}
