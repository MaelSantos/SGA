package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.exceptions.DaoException;

public interface IBussinessCliente {

	public void salvar(Cliente entidade) throws DaoException;

	public void editar(Cliente entidade) throws DaoException;

	public Cliente buscarPorId(int id) throws DaoException;

	public Cliente buscarPorCodigo(String codigo) throws DaoException;

	public List<Cliente> buscarPorBusca(String busca) throws DaoException;
	
}
