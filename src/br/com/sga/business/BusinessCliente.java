package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoCliente;
import br.com.sga.entidade.Cliente;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBussinessCliente;
import br.com.sga.interfaces.IDaoCliente;

public class BusinessCliente implements IBussinessCliente{

	private static BusinessCliente instance;
	
	private IDaoCliente daoCliente;
	private Validar validador;
	
	private BusinessCliente() {
		validador = Validar.getInstance();
		daoCliente = new DaoCliente();	
	}
	
	public static BusinessCliente getInstance() {
		if(instance == null)
			instance = new BusinessCliente();
		return instance;
	}
	
	@Override
	public void salvar(Cliente entidade) throws DaoException {
		
		try {
			validarCliente(entidade);
			daoCliente.salvar(entidade);
		} catch (ValidacaoException e) {
			throw new DaoException(e.getMessage());
		}
		
	}
	
	@Override
	public void editar(Cliente entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Cliente buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Cliente buscarPorCodigo(String codigo) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Cliente> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	private void validarCliente(Cliente cliente) throws ValidacaoException, DaoException {
		
		if(cliente.getNome().trim().equals("") ||
				cliente.getCpf_cnpj().trim().equals("") ||
				cliente.getRg().trim().equals(""))
			throw new ValidacaoException("INFORME TODOS OS DADOS NESCESSARIOS!!!");
		if(validador.isCPF(cliente.getCpf_cnpj()))
			throw new ValidacaoException("CPF NÃO EXISTENTE/ACEITO!!!");
		if(daoCliente.buscarPorCodigo(cliente.getCpf_cnpj()) != null)
			throw new ValidacaoException("CPF JÁ EXISTENTE NO BANCO DE DADOS!!!");
			
	}
	
	
	
}
