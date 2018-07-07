package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IBussinessCliente {

	public void salvar(Cliente entidade) throws BusinessException;

	public Cliente buscarPorId(int id) throws BusinessException;

	public Cliente buscarPorCodigo(String codigo) throws BusinessException;

	public List<Cliente> buscarPorBusca(String busca) throws BusinessException;
	
}
