package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Audiencia;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IBusinessAudiencia {

	public void salvar(Audiencia entidade) throws BusinessException;
    public Audiencia buscarPorId(int id) throws BusinessException;
    public List<Audiencia> buscarPorBusca(String busca) throws BusinessException;
    public List<Audiencia> buscarPorIdProcesso(int id) throws BusinessException;
	
}
