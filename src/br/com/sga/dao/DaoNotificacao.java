package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoNotificacao;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoNotificacao implements IDaoNotificacao {

	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	DaoCommun daoCommun;

	public DaoNotificacao() {
		daoCommun = DaoCommun.getInstance();
	}	

	@Override
	public void salvar(Notificacao entidade) throws DaoException {

		try {
			connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			statement = connection.prepareStatement(SQLUtil.Notificacao.INSERT_ALL);
			//tipo,descricao,prioridade,estado,intervalo_repeticao,data_aviso
			statement.setString(1,entidade.getTipoNotificacao().toString());
			statement.setString(2,entidade.getDescricao());
			statement.setString(3,entidade.getPrioridade().toString());
			statement.setString(4,entidade.getEstado().toString());
			statement.setTimestamp(5,new Timestamp(entidade.getAviso_data().getTime()));
			statement.execute();

			//ter em mente que já peguei os funcionarios da quela notificacão a partir de um result set de funcionario
			int notificacao_id = daoCommun.getCurrentValorTabela(Tabela.NOTIFICACAO);
			entidade.setId(notificacao_id);
			
			connection.close();

			if(entidade.getFuncionarios() != null)
				for(Funcionario f : entidade.getFuncionarios()) 
					daoCommun.salvarVinculoFuncionario(notificacao_id,f.getId());

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException("ERRO AO SALVAR NOTIFICAÇÃO - CONTATE O ADM");
		}
	}

	@Override
	public void editar(Notificacao entidade) throws DaoException { 

		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Notificacao.UPDATE_ALL);

			//			UPDATE NOTIFICACAO SET tipo = ?, descricao = ?, prioridade = ?, estado = ?, data_aviso = ? WHERE ID = ?

			statement.setString(1,entidade.getTipoNotificacao().toString());
			statement.setString(2,entidade.getDescricao());
			statement.setString(3,entidade.getPrioridade().toString());
			statement.setString(4,entidade.getEstado().toString());
			statement.setTimestamp(5,new Timestamp(entidade.getAviso_data().getTime()));
			statement.setInt(6, entidade.getId());

			statement.executeUpdate();

			this.connection.close();

		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO ATUALIZAR NOTIFICAÇÃO - CONTATE O ADM");
		}


	}

	public void validarNotificacoes(Date date) throws DaoException  {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Notificacao.UPDATE_ESTADO);
			statement.setDate(1,new java.sql.Date(date.getTime()));
			statement.executeUpdate();
			this.connection.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO VEERIFICAR NOTIFICAÇÕES ATRASADAS - CONTATE O ADM");
		}
	}

	@Override
	public Notificacao buscarPorId(int id) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Notificacao.SELECT_ID);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();
			Notificacao notificacao;

			if (resultSet.next()) {
				notificacao = new Notificacao();

				notificacao.setId(resultSet.getInt("id"));
				notificacao.setAviso_data(resultSet.getTimestamp("data_aviso"));
				notificacao.setDescricao(resultSet.getString("descricao"));
				notificacao.setEstado(Andamento.getTipo(resultSet.getString("estado")));
				notificacao.setPrioridade(Prioridade.getTipo(resultSet.getString("prioridade")));
				notificacao.setTipoNotificacao(TipoNotificacao.getTipo(resultSet.getString("tipo")));

				this.connection.close();			

				return notificacao;
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
		return null;
	}


	@Override
	public List<Notificacao> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Notificacao> buscarPorFuncionario(String busca) throws DaoException {
		try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Notificacao.BUSCA_POR_FUNCIONARIO); // PESQUISA TODAS AS NOTIFICACOES ATIVAS PARA UM FUNCIONARIO A PARTIR DO SEU NUMERO AOB
            statement.setString(1,busca);
            resultSet = statement.executeQuery();
            
            List<Notificacao> lista = new ArrayList<>();
            while(resultSet.next()) {
            	lista.add(new Notificacao(TipoNotificacao.getTipo(resultSet.getString("tipo")),Prioridade.getTipo(resultSet.getString("prioridade")),
            			resultSet.getString("descricao"),Andamento.getTipo(resultSet.getString("estado")), 
            			resultSet.getTimestamp("data_aviso"),resultSet.getInt("id")));
            }if(lista.isEmpty()){
            	throw new DaoException("NÃO HÁ NOFICAÇÕES ATIVAS PARA ESSE USUARIO");
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            return lista;
		} catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO NOTIFICACOES DE FUNCIONARIO - CONTATE O ADM");
        }
	}

	@Override
	public List<Notificacao> buscarPorData(Date data) throws DaoException {

		try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Notificacao.SELECT_DATA);
            statement.setDate(1, new java.sql.Date(data.getTime()));
            
            resultSet = statement.executeQuery();
            List<Notificacao> notificacoes = new ArrayList<>();
            Notificacao notificacao;
            while(resultSet.next()) {
            	notificacao = new Notificacao();
            	
            	notificacao.setId(resultSet.getInt("id"));
            	notificacao.setAviso_data(resultSet.getTimestamp("data_aviso"));
            	notificacao.setDescricao(resultSet.getString("descricao"));
            	notificacao.setEstado(Andamento.getTipo(resultSet.getString("estado")));
            	notificacao.setPrioridade(Prioridade.getTipo(resultSet.getString("prioridade")));
            	notificacao.setTipoNotificacao(TipoNotificacao.getTipo(resultSet.getString("tipo")));
            	
            	notificacoes.add(notificacao);
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            return notificacoes;
		} catch (SQLException ex) {
            ex.printStackTrace();
            return null;
//            throw new DaoException("PROBLEMA AO BUSCAR NOTIFICAÇÕES - CONTATE O ADM");
        }
		
	}

	@Override
	public List<NotificacaoAdapter> BuscarAdapterPorData(Date inicio, Date fim) throws DaoException {

		try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Notificacao.SELECT_ADAPTER_DATA);
            statement.setDate(1, new java.sql.Date(inicio.getTime()));
            statement.setDate(2, new java.sql.Date(fim.getTime()));

            resultSet = statement.executeQuery();
         
            List<NotificacaoAdapter> adapters = new ArrayList<>();
            NotificacaoAdapter adapter;
            while(resultSet.next()) {
            	adapter = new NotificacaoAdapter();
            	
            	adapter.setId(resultSet.getInt("id"));
            	adapter.setEstado(Andamento.getTipo(resultSet.getString("estado")));
            	adapter.setTipoNotificacao(TipoNotificacao.getTipo(resultSet.getString("tipo")));
            	adapter.setAviso_data(resultSet.getTimestamp("data_aviso"));
            	adapter.setDescricao(resultSet.getString("descricao"));
            	
            	adapters.add(adapter);
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            
            return adapters;
		} catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR NOTIFICAÇÕES - CONTATE O ADM");
        }
	}

	@Override
	public List<Date> BuscarAllDataPorMes(int mes, int ano) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Notificacao.SELECT_DATA_MES_ANO);
			statement.setInt(1, mes);
			statement.setInt(2, ano);

			resultSet = statement.executeQuery();
			List<Date> dates = new ArrayList<Date>();
			Date date;
			while(resultSet.next()) {
				date = resultSet.getDate("data_aviso");

				dates.add(date);
			}
			this.connection.close();
			this.statement.close();
			this.resultSet.close();

			return dates;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO BUSCAR DATAS - CONTATE O ADM");
		}	
	}

	@Override
	public List<NotificacaoAdapter> BuscarAdapterPorEstado(String estado) throws DaoException {
		
		try {
            this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Notificacao.SELECT_ADAPTER_ESTADO);
            statement.setString(1, estado);

            resultSet = statement.executeQuery();
         
            List<NotificacaoAdapter> adapters = new ArrayList<>();
            NotificacaoAdapter adapter;
            while(resultSet.next()) {
            	adapter = new NotificacaoAdapter();
            	
            	adapter.setId(resultSet.getInt("id"));
            	adapter.setEstado(Andamento.getTipo(resultSet.getString("estado")));
            	adapter.setTipoNotificacao(TipoNotificacao.getTipo(resultSet.getString("tipo")));
            	adapter.setAviso_data(resultSet.getTimestamp("data_aviso"));
            	adapter.setDescricao(resultSet.getString("descricao"));
            	
            	adapters.add(adapter);
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            
            return adapters;
		} catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR NOTIFICAÇÕES - CONTATE O ADM");
        }
		
	}
}
