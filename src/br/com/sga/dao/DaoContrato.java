package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoContrato;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoContrato implements IDaoContrato{
	Connection  connection ;
	ResultSet resultSet;
	PreparedStatement statement;
	DaoCommun daoCommun;
	
	public DaoContrato() {
		daoCommun = DaoCommun.getInstance();
	}
	@Override
	public void salvar(Contrato entidade) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Contrato.INSERT_ALL);
			//objeto,valor_total,tipo_pagamento,data_contrato,area,dados_banco,taxa_juros,taxa_multa,consulta_id
			statement.setString(1,entidade.getObjeto());
			statement.setFloat(2,entidade.getValor_total());
			statement.setString(3,entidade.getTipo_pagamento().toString());
			statement.setDate(4, new Date(entidade.getData_contrato().getTime()));
			statement.setString(5, entidade.getArea().toString());
			statement.setString(6, entidade.getDados_banco());
			statement.setFloat(7, entidade.getTaxa_juros());
			statement.setFloat(8, entidade.getTaxa_multa());
			statement.setInt(9, entidade.getConsulta().getId()); // deve-se passar a consulta já com o id 
			statement.setInt(10,entidade.getFinanceiro().getId());
			statement.execute();
			
			int  contrato_id = daoCommun.getCurrentValorTabela(Tabela.CONTRATO); 
			this.connection.close();
			
			for(Parte parte : entidade.getPartes())
				daoCommun.salvarParte(parte, contrato_id, Tabela.CONTRATO);
			for(Parcela parcela : entidade.getParcelas())
				daoCommun.salvarParcela(parcela, contrato_id);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR CONTRATO - CONTATE O ADM");
		}
	}
	
	@Override
	public void editar(Contrato entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Contrato buscarPorId(int id) throws DaoException {
		
		try {
			List<Parte> partes  = daoCommun.getPartes(id, Tabela.CONTRATO);
			List<Parcela> parcelas = daoCommun.getParcelas(id);
			
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Contrato.SELECT_CONTRATO_ID);
            this.statement.setInt(1, id);
            
            resultSet = statement.executeQuery();
           
            Contrato contrato = null;
            if(resultSet.next()) {
            	contrato = new Contrato();
            	contrato.setId(resultSet.getInt("id"));
            	contrato.setData_contrato(resultSet.getDate("data_contrato"));
            	contrato.setValor_total(resultSet.getFloat("valor_total"));
            	contrato.setObjeto(resultSet.getString("objeto"));
            	contrato.setTipo_pagamento(TipoPagamento.getTipoPagamento(resultSet.getString("tipo_pagamento")));
            	contrato.setArea(Area.getArea(resultSet.getString("area")));
            	contrato.setDados_banco(resultSet.getString("dados_banco"));
            	contrato.setPartes(partes);
            	contrato.setParcelas(parcelas);
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            return contrato;
            
		} catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR CONTRATOS POR ID - CONTATE O ADM");
        }

	}

	@Override
	public List<Contrato> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		
		return null;
	
	}
	@Override
	public List<ContratoAdapter> buscaPorClienteAdapter(String busca) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Contrato.BUSCA_POR_CLIENTE_ADAPTER);
            statement.setString(1,"%"+busca+"%");
            statement.setString(2,"%"+busca+"%");
            statement.setString(3,busca);
            statement.setString(4,busca);
            resultSet = statement.executeQuery();
            
            List<ContratoAdapter> lista = new ArrayList<>();
            while(resultSet.next()) {
            	ContratoAdapter adapter =new ContratoAdapter();
    			adapter.setId(resultSet.getInt("id"));
            	adapter.setNome_cliente(resultSet.getString("nome"));
            	adapter.setData_contrato(resultSet.getDate("data_contrato"));
            	adapter.setValor_total(resultSet.getFloat("valor_total"));
	            lista.add(adapter);
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            return lista;
		} catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR CONTRATOS DE CLIENTE - CONTATE O ADM");
        }
	}
	@Override
	public List<ContratoAdapter> buscarAllAdapter() throws DaoException {

		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = connection.prepareStatement(SQLUtil.Contrato.SELECT_CONTRATO_ADAPTER);

            resultSet = statement.executeQuery();
           
            List<ContratoAdapter> adapters = new ArrayList<>();
            while(resultSet.next()) {
            	
            	ContratoAdapter adapter = new ContratoAdapter();
            	adapter.setId(resultSet.getInt("id"));
            	adapter.setNome_cliente(resultSet.getString("nome"));
            	adapter.setData_contrato(resultSet.getDate("data_contrato"));
            	adapter.setValor_total(resultSet.getFloat("valor_total"));
            	adapters.add(adapter);
            	
            }
            this.connection.close();
            this.statement.close();
            this.resultSet.close();
            return adapters;
            
		} catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO BUSCAR CONTRATOS ADAPTERS - CONTATE O ADM");
        }

	}
	
	
	
}
