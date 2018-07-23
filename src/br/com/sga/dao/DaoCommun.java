package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;

import java.util.ArrayList;

import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoCommun implements IDaoCommun{
	
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;

	private static DaoCommun instance;
	
	private DaoCommun() {
		
	}
	
	public static DaoCommun getInstance() {
		if(instance == null)
			instance = new DaoCommun();
		return instance;
	}
	
	@Override
	public int getCurrentValorTabela(Tabela tabela) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement("select id from " + tabela.toString() + " order by id desc limit 1");

			resultSet = this.statement.executeQuery();
			int id;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			} else {
				throw new DaoException("N√£o h√° registro nessa tabela");
			}
			this.connection.close();
			return id;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO CONSULTAR " + tabela.toString() + " - Contate o ADM");
		}
	}
	public void salvarTestemunha(Testemunha entidade,Integer consulta_id) throws DaoException {
		try {
			salvarEndereco(entidade.getEndereco());
			int endereco_id = getCurrentValorTabela(Tabela.ENDERECO);

			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Testemunha.INSERT_ALL);
			statement.setString(1,entidade.getNome());
			statement.setInt(2,endereco_id);
			statement.setInt(3, consulta_id);
			statement.execute();
			int testemunha_id = getCurrentValorTabela(Tabela.TESTEMUNHA);
			this.connection.close();
			salvarContato(entidade.getTelefone(),testemunha_id,Tabela.TESTEMUNHA);

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR TESTEMUNHA - CONTATE O ADM");
		}
	}
	@Override
	public void salvarVinculoFuncionario(Integer notificacao_id, Integer funcionario_id)  throws DaoException {
		try {
			connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			statement  = connection.prepareStatement(SQLUtil.VinculoFuncionario.INSERT_ALL);
			statement.setInt(1,notificacao_id);
			statement.setInt(2,funcionario_id);
			statement.execute();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR VINCULO FUNCIONARIO/NOTIFICA«√O CONTATE ADM");
		}
	}

	@Override
	public void salvarEndereco(Endereco endereco) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Endereco.INSERT_ALL);
			//rua, numero, bairro, cidade, cep, pais, estado, complemento
			statement.setString(1, endereco.getRua());
			statement.setString(2, endereco.getNumero());
			statement.setString(3, endereco.getBairro());
			statement.setString(4, endereco.getCidade());
			statement.setString(5, endereco.getCep());
			statement.setString(6, endereco.getPais());
			statement.setString(7, endereco.getEstado());
			statement.setString(8, endereco.getComplemento());
			statement.execute();
			this.connection.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR ENDERECO - Contate o ADM");
		}
	}

	@Override
	public void salvarContato(Telefone telefone, int id,Tabela tabela) throws DaoException {

		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			if(tabela == Tabela.CLIENTE)
				this.statement = connection.prepareStatement(SQLUtil.Telefone.INSERT_ALL_PARA_CLIENTE);
			else
				this.statement = connection.prepareStatement(SQLUtil.Telefone.INSERT_ALL_PARA_TESTEMUNHA);
			statement.setInt(1, telefone.getNumero());
			statement.setInt(2, telefone.getPrefixo());
			statement.setInt(3, id);
			statement.execute();
			this.connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR ENDERECO - Contate o ADM");
		}

	}

	@Override
	public List<Telefone> getContatos(Integer id) throws DaoException {

		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Telefone.SELECT_TELEFONE_CLIENTE);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();
			List<Telefone> contatos = new ArrayList<>();
			Telefone telefone;
			while (resultSet.next()) {
				//            	id ,cliente_id, numero, prefixo
				telefone = new Telefone();
				telefone.setId(resultSet.getInt(1));
				telefone.setPrefixo(resultSet.getInt(2));
				telefone.setNumero(resultSet.getInt(3));
				contatos.add(telefone);
			}
			this.connection.close();

			return contatos;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO CONSULTAR CONTATOS - Contate o ADM");
		}

	}

	@Override
	public List<Parte> getPartes(Integer id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Parte.SELECT_PARTE_CONTRATO_ID);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();

			List<Parte> partes = new ArrayList<>();
			Parte parte;
			
			while (resultSet.next()) {

				parte = new Parte();
				parte.setId(resultSet.getInt("id"));
				parte.setNome(resultSet.getString("nome"));
				parte.setTipo_parte(TipoParte.getTipoParte(resultSet.getString("tipo_parte")));
				parte.setTipo_participacao(TipoParticipacao.getValue(resultSet.getString("tipo_participacao")));
				parte.setSituacao(resultSet.getString("situacao"));
			
				partes.add(parte);
				
			}
			this.connection.close();

			return partes;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR PARTES - Contate o ADM");
		}
	}
	
	@Override
	public void salvarParte(Parte parte, Integer contrato_id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Parte.INSERT_ALL);
			//nome,tipo_parte,tipo_participacao,contrato_id
			statement.setString(2, parte.getTipo_parte().toString());
			statement.setString(3, parte.getTipo_participacao().toString());
			statement.setString(1, parte.getNome());
			statement.setInt(4, contrato_id);
			statement.execute();
			this.connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR PARTE - Contate o ADM");
		}

	}
	@Override
	public void salvarReceita(Receita receita, Integer financeiro_id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Receita.INSERT_ALL);
			//data_entrada,centro_custo,decricao,valor,status,tipo_pagamento,vencimento,financeiro_id
			statement.setDate(1,new Date(receita.getData_entrada().getTime()));
			statement.setString(2,receita.getCentro_custo());
			statement.setString(3,receita.getDescricao());
			statement.setFloat(4,receita.getValor());
			statement.setBoolean(5,receita.getStatus());
			statement.setString(6,receita.getTipo_pagamento().name());
			statement.setDate(7,new Date(receita.getVencimento().getTime()));
			statement.setInt(8,financeiro_id);
			statement.execute();
			this.connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR RECEITA - Contate o ADM");
		}


	}
	@Override
	public void salvarDespesa(Despesa despesa, Integer financeiro_id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Despesa.INSERT_ALL);

			statement.setDate(1,new Date(despesa.getData_retirada().getTime()));
			statement.setString(2,despesa.getCentro_custo());
			statement.setString(3,despesa.getDescricao());
			statement.setFloat(4,despesa.getValor());
			statement.setBoolean(5,despesa.getStatus());
			statement.setString(6,despesa.getTipo_gasto().name());
			statement.setDate(7,new Date(despesa.getVencimento().getTime()));
			statement.setInt(8,financeiro_id);
			statement.execute();
			this.connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR DESPESA - Contate o ADM");
		}


	}
	@Override
	public void salvarParcela(Parcela parcela, Integer contrato_id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Parcela.INSERT_ALL);
			//valor,tipo,estado,juros,multa,vencimento,contrato_id
			statement.setFloat(1,parcela.getValor());
			statement.setString(2,parcela.getTipo());
			statement.setString(3,parcela.getEstado().toString());
			statement.setFloat(4,parcela.getJuros());
			statement.setFloat(5,parcela.getMulta());
			statement.setDate(6,new Date(parcela.getVencimento().getTime()));
			statement.setInt(7,contrato_id);
			statement.execute();
			this.connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR DESPESA - Contate o ADM");
		}
	}
	@Override
	public void salvarAudiencia(Audiencia audiencia, Integer processo_id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Audiencia.INSERT_ALL);
			//data_audiencia,tipo,vara,orgao,status,processo_id
			statement.setDate(1,new Date(audiencia.getData_audiencia().getTime()));
			statement.setString(2,audiencia.getTipo().name());
			statement.setString(3,audiencia.getVara());
			statement.setString(4,audiencia.getOrgao());
			statement.setString(5,audiencia.getStatus().name());
			statement.setInt(6,processo_id);
			statement.execute();
			this.connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR AUDIENCIA - Contate o ADM");
		}
	}

	@Override
	public List<Receita> getReceita(Integer financeiro_id) throws DaoException {
		
		List<Receita> receitas = new ArrayList<>();
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Receita.SELECT_ID_FINANCEIRO);
			statement.setInt(1, financeiro_id);
			
			this.resultSet = statement.executeQuery();
			
			Receita receita;
			while(resultSet.next())
			{
				receita = new Receita();
				receita.setId(resultSet.getInt("id"));
				receita.setCentro_custo(resultSet.getString("centro_custo"));
				receita.setData_entrada(resultSet.getDate("data_entrada"));
				receita.setDescricao(resultSet.getString("descricao"));
				receita.setStatus(resultSet.getBoolean("status"));
				receita.setTipo_pagamento(TipoPagamento.getTipoPagamento(resultSet.getString("tipo_pagamento")));
				receita.setValor(resultSet.getFloat("valor"));
				receita.setVencimento(resultSet.getDate("vencimento"));
				
				receitas.add(receita);
				
			}
			
			this.connection.close();
			
			return receitas;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR AUDIENCIA - Contate o ADM");
		}
	}

	@Override
	public List<Despesa> getDespesa(Integer financeiro_id) throws DaoException {
		
		List<Despesa> despesas = new ArrayList<>();
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Despesa.SELECT_ID_FINANCEIRO);
			
			statement.setInt(1, financeiro_id);
			
			Despesa despesa;
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				despesa = new Despesa();
				
				despesa.setId(resultSet.getInt("id"));
				despesa.setCentro_custo(resultSet.getString("centro_custo"));
				despesa.setData_retirada(resultSet.getDate("data_retirada"));
				despesa.setDescricao(resultSet.getString("descricao"));
				despesa.setStatus(resultSet.getBoolean("status"));
				despesa.setTipo_gasto(TipoPagamento.getTipoPagamento(resultSet.getString("tipo_gasto")));
				despesa.setValor(resultSet.getFloat("valor"));
				despesa.setVencimento(resultSet.getDate("vencimento"));
				
				despesas.add(despesa);
			}
			
			this.connection.close();
			
			return despesas;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR AUDIENCIA - Contate o ADM");
		}	
	}

	@Override
	public Endereco getEndereco(int cliente_id) throws DaoException {
		
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Endereco.SELECT_ID_CLIENTE);
	
			statement.setInt(1, cliente_id);
			resultSet = statement.executeQuery();
			
			Endereco endereco = null;
			if(resultSet.next())
			{
				endereco = new Endereco();
				
				endereco.setId(resultSet.getInt("id"));
				endereco.setBairro(resultSet.getString("bairro"));
				endereco.setCep(resultSet.getString("cep"));
				endereco.setCidade(resultSet.getString("cidade"));
				endereco.setComplemento(resultSet.getString("complemento"));
				endereco.setEstado(resultSet.getString("estado"));
				endereco.setNumero(resultSet.getString("numero"));
				endereco.setPais(resultSet.getString("pais"));
				endereco.setRua(resultSet.getString("rua"));

			}
			
			this.connection.close();
			
			return endereco;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR ENDERECO - Contate o ADM");
		}
	}
	
}
