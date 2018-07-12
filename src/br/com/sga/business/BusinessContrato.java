package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoContrato;
import br.com.sga.entidade.Contrato;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessContrato;
import br.com.sga.interfaces.IDaoContrato;

public class BusinessContrato implements IBusinessContrato {

	private IDaoContrato daoContrato;
	
	public BusinessContrato() {
		daoContrato = new DaoContrato();
	}

	@Override
	public void salvarEditar(Contrato entidade) throws BusinessException {
		
		try {
			validar(entidade);
			if(entidade.getId() == null)
				daoContrato.salvar(entidade);
			else
				daoContrato.editar(entidade);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Contrato buscarPorId(int id) throws BusinessException {
		try {
			return daoContrato.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Contrato buscarPorCodigo(String codigo) throws BusinessException {
		try {
			return daoContrato.buscarPorCodigo(codigo);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Contrato> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoContrato.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	private void validar(Contrato entidade) throws ValidacaoException{
		
	}

}
