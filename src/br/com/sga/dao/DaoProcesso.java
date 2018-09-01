package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoProcesso;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.interfaces.IDaoProcesso;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoProcesso implements IDaoProcesso {

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private IDaoCommun daoCommun;

	public DaoProcesso() {
		daoCommun = DaoCommun.getInstance();
	}

	@Override
	public void salvar(Processo entidade) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.INSERT_ALL);
//			numero,tipo_processo,fase,descricao,decisao,comarca,orgao_julgador,classe_judicial,data_atuacao,status, cliente_id
			statement.setString(1, entidade.getNumero());
			statement.setString(2, entidade.getTipo_processo().toString());
			statement.setString(3, entidade.getFase());
			statement.setString(4, entidade.getDescricao());
			statement.setString(5, entidade.getDecisao());
			statement.setString(6, entidade.getComarca());
			statement.setString(7, entidade.getOrgao_julgador());
			statement.setString(8, entidade.getClasse_judicial());
			statement.setDate(9, new Date(entidade.getData_atuacao().getTime()));
			statement.setBoolean(10, entidade.isStatus());
			statement.setInt(11, entidade.getCliente().getId());
			statement.execute();

			int id_processo = daoCommun.getCurrentValorTabela(Tabela.PROCESSO);
			
			for (Parte parte : entidade.getPartes())
				daoCommun.salvarParte(parte, id_processo, Tabela.PROCESSO);

			conexao.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR PROCESSO - CONTATE O ADM");
		}
	}

	@Override
	public void editar(Processo entidade) throws DaoException {

	}

	@Override
	public Processo buscarPorId(int id) throws DaoException {

		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.SELECT_ID);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();

			Processo processo = null;
			Cliente cliente = null;

			if (resultSet.next()) {

				processo = new Processo();

				processo.setId(resultSet.getInt("id"));
				processo.setNumero(resultSet.getString("numero"));
				processo.setData_atuacao(resultSet.getDate("data_atuacao"));
				processo.setComarca(resultSet.getString("comarca"));
				processo.setDecisao(resultSet.getString("decisao"));
				processo.setClasse_judicial(resultSet.getString("classe_judicial"));
				processo.setDescricao(resultSet.getString("descricao"));
				processo.setFase(resultSet.getString("fase"));
				processo.setOrgao_julgador(resultSet.getString("orgao_julgador"));
				processo.setTipo_processo(TipoProcesso.getTipo(resultSet.getString("tipo_processo")));

				cliente = new Cliente();
				cliente.setId(resultSet.getInt("cliente_id"));
				
				processo.setCliente(cliente);
				
				processo.setPartes(daoCommun.getPartes(processo.getId(), Tabela.PROCESSO));
				
				processo.setAudiencias(daoCommun.buscarAudienciaPorIdProcesso(processo.getId()));
			}

			this.conexao.close();
			return processo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("ERRO AO BUSCAR PROCESSO POR ID - CONTATE O ADM");
		}

	}

	@Override
	public List<Processo> buscarPorBusca(String busca) throws DaoException {
		List<Processo> processos = new ArrayList<>();
		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.BUSCA_POR_BUSCA);
			this.statement.setString(1, busca);
			this.statement.setString(2, busca);
			this.statement.setString(3, busca);
			this.statement.setString(4, busca);
			this.statement.setString(5, busca);
			this.statement.setString(6, busca);
			this.statement.setString(7, busca);

			resultSet = this.statement.executeQuery();

			Processo processo;
			while (resultSet.next()) {

				processo = new Processo();

				// p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase
				processo.setId(resultSet.getInt("id"));
				processo.setNumero(resultSet.getString("numero"));
				processo.setData_atuacao(resultSet.getDate("data_atuacao"));
				processo.setComarca(resultSet.getString("comarca"));
				processo.setDecisao(resultSet.getString("decisao"));

				// int contrato_id = resultSet.getInt("contrato_id");
				// processo.setPartes(daoCommun.getPartes(contrato_id));

				processos.add(processo);
			}

			this.conexao.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("ERRO AO BUSCAR PROCESSOS" + busca.toUpperCase() + " - CONTATE O ADM");
		}
	}

	@Override
	public List<ProcessoAdapter> buscarAllAdapter(String tipo) throws DaoException {

		List<ProcessoAdapter> processos = new ArrayList<>();
		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.SELECT_TIPO);
			this.statement.setString(1, tipo);

			resultSet = this.statement.executeQuery();

			ProcessoAdapter processo;
			while (resultSet.next()) {

				processo = new ProcessoAdapter();

				// p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase
				processo.setId(resultSet.getInt(1));
				processo.setNumero(resultSet.getString("numero"));
				processo.setData_atuacao(resultSet.getDate("data_atuacao"));
				processo.setComarca(resultSet.getString("comarca"));
				processo.setDecisao(resultSet.getString("decisao"));

				processo.setPartes(daoCommun.getPartes(processo.getId(), Tabela.PROCESSO).toString());

				processos.add(processo);
			}

			this.conexao.close();
			return processos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("ERRO AO BUSCAR PROCESSOS DO TIPO " + tipo.toUpperCase() + " - CONTATE O ADM");
		}

	}

	@Override
	public List<ProcessoAdapter> buscaPorClienteAdapter(int id_cliente) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.SELECT_ADAPTER_ID_CLIENTE);

			statement.setInt(1, id_cliente);

			resultSet = statement.executeQuery();

			List<ProcessoAdapter> processos = new ArrayList<>();

			ProcessoAdapter processo;
			while (resultSet.next()) {

				processo = new ProcessoAdapter();

				// p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase
				processo.setId(resultSet.getInt(1));
				processo.setNumero(resultSet.getString("numero"));
				processo.setData_atuacao(resultSet.getDate("data_atuacao"));
				processo.setComarca(resultSet.getString("comarca"));
				processo.setDecisao(resultSet.getString("decisao"));

				int contrato_id = resultSet.getInt("contrato_id");

				processo.setPartes(daoCommun.getPartes(processo.getId(), Tabela.PROCESSO).toString());

				System.out.println(contrato_id);
				System.out.println(processo.getPartes());

				processos.add(processo);
			}

			this.conexao.close();
			this.statement.close();
			this.resultSet.close();

			return processos;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR PROCESSOS DO USUARIO - CONTATE O ADM");
		}
	}

	@Override
	public List<Processo> buscarPorIdContrato(int contrato_id) throws DaoException {
		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.SELECT_ID_CONTRATO);
			this.statement.setInt(1, contrato_id);

			resultSet = this.statement.executeQuery();

			Processo processo = null;
			Cliente cliente = null;
			List<Processo> processos = new ArrayList<>();

			while (resultSet.next()) {

				processo = new Processo();
				processo.setId(resultSet.getInt(1));
				processo.setNumero(resultSet.getString("numero"));
				processo.setData_atuacao(resultSet.getDate("data_atuacao"));
				processo.setComarca(resultSet.getString("comarca"));
				processo.setDecisao(resultSet.getString("decisao"));
				processo.setClasse_judicial(resultSet.getString("classe_judicial"));
				processo.setDescricao(resultSet.getString("descricao"));
				processo.setFase(resultSet.getString("fase"));
				processo.setOrgao_julgador(resultSet.getString("orgao_julgador"));
				processo.setTipo_processo(TipoProcesso.getTipo(resultSet.getString("tipo_processo")));

				cliente = new Cliente();
				cliente.setId(resultSet.getInt("cliente_id"));

				processo.setCliente(cliente);

				processos.add(processo);
				return processos;

			}
			if (processos.isEmpty())
				throw new DaoException("NENHUM PROCESSO CADASTRADO PARA ESSE CONTRATO!!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR PROCESSOS - CONTATE O ADM");
		}
		return null;

	}

	@Override
	public List<ProcessoAdapter> buscarPorBusca(String[] busca) throws DaoException {
		List<ProcessoAdapter> processos = new ArrayList<>();
		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.BUSCA_POR_BUSCA);
			this.statement.setString(1, "%" + busca[0] + "%");
			this.statement.setString(2, "%" + busca[0] + "%");
			this.statement.setString(3, "%" + busca[0] + "%");
			this.statement.setString(4, "%" + busca[0] + "%");
			this.statement.setString(5, "%" + busca[0] + "%");
			this.statement.setString(6, "%" + busca[0] + "%");
			this.statement.setString(7, "%" + busca[1] + "%");

			resultSet = this.statement.executeQuery();

			ProcessoAdapter processo;
			while (resultSet.next()) {

				processo = new ProcessoAdapter();

				// p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase
				processo.setId(resultSet.getInt("id"));
				processo.setNumero(resultSet.getString("numero"));
				processo.setData_atuacao(resultSet.getDate("data_atuacao"));
				processo.setComarca(resultSet.getString("comarca"));
				processo.setDecisao(resultSet.getString("decisao"));

				processo.setPartes(daoCommun.getPartes(processo.getId(), Tabela.PROCESSO).toString());

				processos.add(processo);
			}

			this.conexao.close();
			return processos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("ERRO AO BUSCAR PROCESSOS" + busca[0].toUpperCase() + " - CONTATE O ADM");
		}
	}
}
