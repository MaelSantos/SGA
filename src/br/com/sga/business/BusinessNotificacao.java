package br.com.sga.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.sga.dao.DaoNotificacao;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.interfaces.IBusinessNotificacao;
import br.com.sga.interfaces.IDaoNotificacao;
import br.com.sga.interfaces.IDaoUsuario;

public class BusinessNotificacao implements IBusinessNotificacao{
	private IDaoNotificacao daoNotificacao;
	
	public BusinessNotificacao() {
		daoNotificacao = new DaoNotificacao();
	}
	
	@Override
	public void salvarNotificacao(Notificacao notificacao) throws BusinessException {
		try {
			daoNotificacao.salvar(notificacao);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Notificacao> buscarPorFuncionario(List<Funcionario> funcionarios) throws BusinessException {
			
		return null;
	}
	
	
}
