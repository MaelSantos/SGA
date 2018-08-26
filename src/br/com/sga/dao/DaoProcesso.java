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
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.entidade.enums.TipoPagamento;
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

	@Override
	public List<Processo> buscarPorIdContrato(int contrato_id) throws DaoException {
		try {

			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Processo.SELECT_ID_CONTRATO);
			this.statement.setInt(1, contrato_id);

			resultSet = this.statement.executeQuery();

			Processo processo = null;
			Contrato contrato = null;
			Consulta consulta = null;
			Cliente cliente = null;
			Funcionario funcionario = null;
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
				processo.setTipo_participacao(TipoParticipacao.getValue(resultSet.getString("tipo_participacao")));
				processo.setTipo_processo(TipoProcesso.getTipo(resultSet.getString("tipo_processo")));

				contrato = new Contrato();
				List<Parte> partes = daoCommun.getPartes(contrato_id);
				List<Parcela> parcelas = daoCommun.getParcelas(contrato_id);
				contrato = new Contrato();
				contrato.setId(resultSet.getInt("contrato_id"));
				contrato.setData_contrato(resultSet.getDate("data_contrato"));
				contrato.setValor_total(resultSet.getFloat("valor_total"));
				contrato.setObjeto(resultSet.getString("objeto"));
				contrato.setTipo_pagamento(TipoPagamento.getTipoPagamento(resultSet.getString("tipo_pagamento")));
				contrato.setArea(Area.getArea(resultSet.getString("area")));
				contrato.setDados_banco(resultSet.getString("dados_banco"));
				contrato.setPartes(partes);
				contrato.setParcelas(parcelas);

				consulta = new Consulta();
				consulta.setId(resultSet.getInt("consulta_id"));
				consulta.setArea(Area.getArea(resultSet.getString("area")));
				consulta.setDescricao(resultSet.getString("descricao"));
				consulta.setData_consulta(resultSet.getDate("data_consulta"));
				consulta.setValor_honorario(resultSet.getFloat("valor_honorario"));
				consulta.setIndicacao(resultSet.getString("indicacao"));

				cliente = new Cliente();
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setNascimento(resultSet.getDate("data_nascimento"));
				cliente.setCpf_cnpj(resultSet.getString("cpf_cnpj"));
				cliente.setGenero(Sexo.getSexo(resultSet.getString("genero")));
				cliente.setRg(resultSet.getString("rg"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setEstado_civil(resultSet.getString("estado_civil"));
				cliente.setProfissao(resultSet.getString("profissao"));
				cliente.setFilhos(resultSet.getBoolean("filhos"));
				cliente.setResponsavel(resultSet.getString("responsavel"));
				cliente.setTipoCliente(TipoCliente.getTipo(resultSet.getString("tipo")));
				cliente.setEndereco(daoCommun.getEndereco(cliente.getId()));

				funcionario = new Funcionario(resultSet.getInt("funcionario_id"), resultSet.getString("nome"), resultSet.getString("email"), 
            			resultSet.getString("login"), resultSet.getString("senha"), resultSet.getString("numero_oab"));
				
				funcionario.setEndereco(daoCommun.getEndereco(funcionario.getId()));
				
				consulta.setCliente(cliente);
				consulta.setFuncionario(funcionario);
				contrato.setConsulta(consulta);
				processo.setContrato(contrato);

				processos.add(processo);
				return processos;
				
			}if(processos.isEmpty())
				throw new DaoException("NENHUM PROCESSO CADASTRADO PARA ESSE CONTRATO!!!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR PROCESSOS - CONTATE O ADM");
		}
		return null;

	}
}
