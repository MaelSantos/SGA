package br.com.sga.business;

import java.util.Date;
import java.util.List;

import br.com.sga.dao.DaoLog;
import br.com.sga.entidade.Log;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessLog;
import br.com.sga.interfaces.IDaoLog;

public class BusinessLog implements IBusinessLog{

	private IDaoLog daoLog;
	
	public BusinessLog() {
		
		daoLog = new DaoLog();
		
	}

	@Override
	public void salvarEditar(Log log) throws BusinessException {
		
			try {
				validar(log);
				if(log.getId() == null)
					daoLog.salvar(log);
				else
					daoLog.editar(log);
			} catch (DaoException e) {
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}
		
	}

	@Override
	public Log buscarPorId(int id) throws BusinessException {
		try {
			return daoLog.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Log> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoLog.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Log> buscarPorData(Date de, Date ate) throws BusinessException {
		try {
			return daoLog.buscarPorData(de, ate);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	private void validar(Log log) {
		// TODO Stub de método gerado automaticamente
		
	}
	
}
