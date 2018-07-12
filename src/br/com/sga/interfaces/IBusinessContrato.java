package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Contrato;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessContrato {

	public void salvarEditar(Contrato entidade) throws BusinessException;
    public Contrato buscarPorId(int id) throws BusinessException;
    public Contrato buscarPorCodigo(String codigo) throws BusinessException;
    public List<Contrato> buscarPorBusca(String busca) throws BusinessException;
	
}
