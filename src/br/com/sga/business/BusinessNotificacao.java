package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoNotificacao;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessNotificacao;
import br.com.sga.interfaces.IDaoNotificacao;

public class BusinessNotificacao implements IBusinessNotificacao{
	private IDaoNotificacao daoNotificacao;
	
	public BusinessNotificacao() {
		daoNotificacao = new DaoNotificacao();
	}
	
	@Override
	public void salvarEditarNotificacao(Notificacao notificacao) throws BusinessException {
		try {
			validarNotificacao(notificacao);
			if(notificacao.getId() == null)
				daoNotificacao.salvar(notificacao);
			else
				daoNotificacao.editar(notificacao);
			
		} catch (ValidacaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	public List<Notificacao> buscarPorFuncionario(List<Funcionario> funcionarios) throws BusinessException {
			
		return null;
	}
	
	private void validarNotificacao(Notificacao notificacao) throws ValidacaoException{
		
		if(notificacao.getDescricao().trim().length() <1) {
			new ValidacaoException("DESCRI��O INVALIDA");
		}
	}
	
	
}
