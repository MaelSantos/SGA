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
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.enums.TipoProcesso;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoProcesso;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoProcesso implements IDaoProcesso {

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private DaoCommun daoCommun;

	public DaoProcesso() {
		daoCommun = DaoCommun.getInstance();
	}

	@Override
	public void salvar(Processo entidade) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.INSERT_ALL);
			// numero,tipo_participacao,tipo_processo,fase,descricao,decisao,comarca,orgao_julgador,
			// classe_judicial,data_atuacao,status,contrato_id
			statement.setString(1, entidade.getNumero());
			statement.setString(2, entidade.getTipo_participacao().toString());
			statement.setString(3, entidade.getTipo_processo().toString());
			statement.setString(4, entidade.getFase());
			statement.setString(5, entidade.getDescricao());
			statement.setString(6, entidade.getDecisao());
			statement.setString(7, entidade.getComarca());
			statement.setString(8, entidade.getOrgao_julgador());
			statement.setString(9, entidade.getClasse_judicial());
			statement.setDate(10, new Date(entidade.getData_atuacao().getTime()));
			statement.setBoolean(11, entidade.isStatus());
			statement.setInt(12, entidade.getContrato().getId());
			statement.execute();
			// int processo_id = daoCommun.getCurrentValorTabela(Tabela.PROCESSO);
			// for(Audiencia e : entidade.getAudiencias())
			// daoCommun.salvarAudiencia(e,processo_id);

			conexao.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR PROCESSO - CONTATE O ADM");
		}
	}

	// public static void main(String[] args) {
	// // inserir processo
	// /* 1 - cadastrar audiencia já que foi definido que teria ao menos uma ao
	// cadastrar o processo (elas tb poderiam ser cadastradas depois)
	// * 2 - criar entidade processo (o mesmo já deve contrer um id de contrato
	// (irei usar um contrato já cadastrato para evitar uso de select inicialmente))
	// */
	//
	// List<Audiencia> audiencias = new ArrayList<>();
	// audiencias.add(new Audiencia("A conparecer","vara tal","orgao tal","audiencia
	// inicial",Calendar.getInstance().getTime()));
	// Contrato contrato = new Contrato();
	// contrato.setId(11); // id de contrato já cadastro em minha base
	//// Processo processo = new Processo(contrato,true,
	// Calendar.getInstance().getTime(),"123-123-1233Ab","classe tal","orgao
	// tal","comarca tal", "pendente","processo de fulano a cerca de","primeira
	// instancia","judicial",TipoParticipacao.EXECUTADO, audiencias);
	// try {
	// new DaoProcesso().salvar(processo);
	// } catch (DaoException e) {
	// e.printStackTrace();
	// }
	// }

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
			Contrato contrato = null;
			Consulta consulta = null;
			Cliente cliente = null;

			if (resultSet.next()) {

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
				processo.setTipo_participacao(TipoParticipacao.getValue(resultSet.getString("tipo_participacao")));
				processo.setTipo_processo(TipoProcesso.getTipo(resultSet.getString("tipo_processo")));

				contrato = new Contrato();
				contrato.setValor_total(resultSet.getFloat("valor_total"));
				contrato.setId(resultSet.getInt("contrato_id"));

				consulta = new Consulta();
				cliente = new Cliente();
				cliente.setNome(resultSet.getString("nome"));
				consulta.setCliente(cliente);

				contrato.setConsulta(consulta);

				contrato.setPartes(daoCommun.getPartes(contrato.getId()));

				processo.setContrato(contrato);

				// for(Audiencia a : daoCommun.a)
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
		return null;
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

				int contrato_id = resultSet.getInt("contrato_id");

				processo.setPartes(daoCommun.getPartes(contrato_id).toString());

				System.out.println(contrato_id);
				System.out.println(processo.getPartes());

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
	public List<ProcessoAdapter> buscaPorClienteAdapter(String[] busca) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.SELECT_ADAPTER_CLIENTE);

			statement.setInt(1, Integer.parseInt(busca[0]));

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

				processo.setPartes(daoCommun.getPartes(contrato_id).toString());

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

}
