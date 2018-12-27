package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.exceptions.BusinessException;

public interface IBussinessCliente {

	public void salvar(Cliente entidade) throws BusinessException;

	public Cliente buscarPorId(int id) throws BusinessException;

	public List<Cliente> buscarPorBusca(String busca) throws BusinessException;
	
	public List<ClienteAdapter> buscarAdapterPorBusca(String busca) throws BusinessException;
	
	public Cliente buscarPorIdConsulta(int id_consulta) throws BusinessException;
}
