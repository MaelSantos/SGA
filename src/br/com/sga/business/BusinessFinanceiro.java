package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoFinanceiro;
import br.com.sga.entidade.Financeiro;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessFinanceiro;
import br.com.sga.interfaces.IDaoFinanceiro;

public class BusinessFinanceiro implements IBusinessFinanceiro {
	private IDaoFinanceiro daoFinanceiro;
	
	public BusinessFinanceiro() {
		daoFinanceiro = new DaoFinanceiro();
	}
	
	@Override
	public Financeiro buscarPorAno(Integer ano) throws BusinessException {
		try {
			return daoFinanceiro.buscarPorAno(ano);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void salvarEditar(Financeiro financeiro) throws BusinessException {
		try {
			if(financeiro.getId() == null)
					daoFinanceiro.salvar(financeiro);
			else
					daoFinanceiro.editar(financeiro);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Financeiro buscarPorId(int id) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Financeiro> buscarPorBusca(String busca) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

}
