package br.com.sga.business;

import java.util.Date;
import java.util.List;

import br.com.sga.dao.DaoCommun;
import br.com.sga.dao.DaoFinanceiro;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.adapter.ContaAdapter;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessFinanceiro;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.interfaces.IDaoFinanceiro;

public class BusinessFinanceiro implements IBusinessFinanceiro {
	private IDaoFinanceiro daoFinanceiro;
	private IDaoCommun daoCommun;
	public BusinessFinanceiro() {
		daoFinanceiro = new DaoFinanceiro();
		daoCommun = DaoCommun.getInstance();
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
		try {
			return daoFinanceiro.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Financeiro> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoFinanceiro.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Financeiro buscarPorIntervalo(Date de, Date ate) throws BusinessException {
		try {
			return daoFinanceiro.buscarPorIntervalo(de, ate);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	  public List<ContaAdapter> buscarContaTotalMesPorIntervalo(Date de, Date ate,Tabela tabela) throws BusinessException {
		try {
			return daoCommun.getContaTotalMesPorIntervalo(de, ate,tabela);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
