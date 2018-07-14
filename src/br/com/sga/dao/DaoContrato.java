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
		daoCommun = new DaoCommun();
	}
	@Override
	public void salvar(Contrato entidade) throws DaoException {
		try {
			this.connection = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = connection.prepareStatement(SQLUtil.Contrato.INSERT_ALL);
			//objeto,valor_total,tipo_pagamento,data_contrato,area,dados_banco,consulta_id
			statement.setString(1,entidade.getObjeto());
			statement.setFloat(2,entidade.getValor_total());
			statement.setString(3,entidade.getTipo_pagamento().toString());
			statement.setDate(4, new Date(entidade.getData_contrato().getTime()));
			statement.setString(5, entidade.getArea().toString());
			statement.setString(6, entidade.getDados_banco());
			statement.setInt(7, entidade.getConsulta().getId()); // deve-se passar a consulta já com o id 
			System.out.println(entidade.getFinanceiro().getId());
			statement.setInt(8,entidade.getFinanceiro().getId());
			statement.execute();
			
			int  contrato_id = daoCommun.getCurrentValorTabela(Tabela.CONTRATO); 
			this.connection.close();
			
			for(Parte parte : entidade.getPartes())
				daoCommun.salvarParte(parte, contrato_id);
			for(Parcela parcela : entidade.getParcelas())
				daoCommun.salvarParcela(parcela, contrato_id);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR CONTRATO - CONTATE O ADM");
		}
	}
	
	public static void main(String[] args) {
		/* testando salvar contrato
		 * 1- preciso de antemão uma consulta com id(ou seja ela já vai estar cadastrada no banco, no meu a duas consultas cadastradas com id 5 e 6 utulizarei da mesma)
		 * 2- crio um processo com seus dados e sua partes e parcelas
		 * 3- inserir novo contrato no banco
		 */
		List<Parte> partes = new ArrayList<>();
		partes.add(new Parte(TipoParte.PASSIVO,TipoParticipacao.EXECUTADO,"Jão Migué"));
		partes.add(new Parte(TipoParte.ATIVO,TipoParticipacao.EXEQUENTE,"Frederico Evandro"));
		List<Parcela> parcelas = new ArrayList<>();
		for(int i =0 ; i < 10; i ++) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH,i);
			parcelas.add(new Parcela(100.0f,c.getTime(),0.4f,0.1f,"tipo1",Andamento.PENDENTE));
		}
		Consulta consulta = new Consulta();
		consulta.setId(5); // criando consulta apenas para teste, esse id corresponde a uma consulta que cadastrei antes
		// adicionado todas as parcelas e partes agora associar ao contrato
		Financeiro financeiro = new Financeiro();
		financeiro.setId(4);
		Contrato c = new Contrato("a",1000f,TipoPagamento.BOLETO,Calendar.getInstance().getTime(),Area.CIVIL,"Banco conta tal ", partes, parcelas,consulta,financeiro);
		System.out.println(c);
		try {
			new DaoContrato().salvar(c);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editar(Contrato entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Contrato buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Contrato> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}
	
}
