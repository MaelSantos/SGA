package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoProcesso;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoProcesso implements IDaoProcesso {
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	DaoCommun daoCommun;
	
	public DaoProcesso() {
		daoCommun = new DaoCommun();
	}
	@Override
	public void salvar(Processo entidade) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Processo.INSERT_ALL);
			//numero,tipo_participacao,tipo_processo,fase,descricao,decisao,comarca,orgao_julgador,
			//classe_judicial,data_atuacao,status,contrato_id
			statement.setString(1,entidade.getNumero());
			statement.setString(2,entidade.getTipo_participacao().toString());
			statement.setString(3,entidade.getTipo_processo());
			statement.setString(4,entidade.getFase());
			statement.setString(5,entidade.getDescricao());
			statement.setString(6,entidade.getDecisao());
			statement.setString(7,entidade.getComarca());
			statement.setString(8,entidade.getOrgao_julgador());
			statement.setString(9,entidade.getClasse_judicial());
			statement.setDate(10,new Date(entidade.getData_atuacao().getTime()));
			statement.setBoolean(11,entidade.isStatus());
			statement.setInt(12,entidade.getContrato().getId());
			statement.execute();
			int  processo_id = daoCommun.getCurrentValorTabela(Tabela.PROCESSO);
			connection.close();
			for(Audiencia e : entidade.getAudiencias())
				daoCommun.salvarAudiencia(e,processo_id);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR PROCESSO - CONTATE O ADM");
		}
	}
	
	public static void main(String[] args) {
		// inserir processo
			 /* 1 - cadastrar audiencia já que foi definido que teria ao menos uma ao cadastrar o processo (elas tb poderiam ser cadastradas depois)
			  * 2 - criar entidade processo (o mesmo já deve contrer um id de contrato (irei usar um contrato já cadastrato para evitar uso de select inicialmente))
			  */
		
			List<Audiencia> audiencias = new ArrayList<>();
			audiencias.add(new Audiencia("A conparecer","vara tal","orgao tal","audiencia inicial",Calendar.getInstance().getTime()));
			Contrato contrato = new Contrato();
			contrato.setId(11); // id de contrato já cadastro em minha base
			Processo processo = new Processo(contrato,true, Calendar.getInstance().getTime(),"123-123-1233Ab","classe tal","orgao tal","comarca tal", "pendente","processo de fulano a cerca de","primeira instancia","judicial",TipoParticipacao.EXECUTADO, audiencias);
			try {
				new DaoProcesso().salvar(processo);
			} catch (DaoException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void editar(Processo entidade) throws DaoException {
		
	}

	@Override
	public Processo buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Processo buscarPorCodigo(String codigo) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Processo> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

}
