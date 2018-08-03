package br.com.sga.interfaces;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Log;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessLog {

	public void salvarEditar(Log entidade) throws BusinessException;
    public Log buscarPorId(int id) throws BusinessException;
    public List<Log> buscarPorBusca(String busca) throws BusinessException;
    public List<Log> buscarPorData(Date de, Date ate) throws BusinessException;
	
}
