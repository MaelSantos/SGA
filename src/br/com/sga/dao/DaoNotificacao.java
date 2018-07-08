package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.Tabela;
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
		daoCommun = new DaoCommun();
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
			connection.close();
			for(Funcionario f : entidade.getFuncionarios()) 
				daoCommun.salvarVinculoFuncionario(notificacao_id,f.getId());
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException("Erro ao salvar notificacao");
		}
	}

	/*public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH,17);
		c.set(Calendar.MONTH,6);
		c.set(Calendar.YEAR,1998);
		c.set(Calendar.HOUR,4);
		c.set(Calendar.MINUTE,30);
		
		//Funcionario f1 = new Funcionario("Jose", "jose@gmail.com","jose5","1234","12345678");
		//Funcionario f2 = new Funcionario("Jose2", "jose2@gmail.com","jose7","12342","123456728");
		
		DaoUsuario dao = new DaoUsuario();
		try {
		//	dao.salvar(f1);
			//dao.salvar(f2);
			
			ArrayList<Funcionario> funcionariosComId = new ArrayList<>();
			funcionariosComId.add(dao.buscarPorNome("Jose2"));
			funcionariosComId.add(dao.buscarPorNome("Jose"));
			System.out.println(funcionariosComId);
			Notificacao n = new Notificacao("Alerta",Prioridade.ALTA,"mensagem de teste para alertas","ativa",c.getTime(),3, funcionariosComId);
			new DaoNotificacao().salvar(n);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}*/
	@Override
	public void editar(Notificacao entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Notificacao buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Notificacao buscarPorCodigo(String codigo) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Notificacao> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

}
