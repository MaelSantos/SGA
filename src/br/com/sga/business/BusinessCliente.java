package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoCliente;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBussinessCliente;
import br.com.sga.interfaces.IDaoCliente;

public class BusinessCliente implements IBussinessCliente{
	
	private IDaoCliente daoCliente;
	private Validar validador;
	
	public BusinessCliente() {
		validador = Validar.getInstance();
		daoCliente = new DaoCliente();	
	}
	
	@Override
	public void salvar(Cliente entidade) throws BusinessException {
		
		try {
			validarCliente(entidade);
			if (entidade.getId() == null) {
				
				daoCliente.salvar(entidade);

			} else {
				daoCliente.editar(entidade);
			}

		} catch (Exception e) {
//			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public Cliente buscarPorId(int id) throws BusinessException{
		try {
			return daoCliente.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Cliente> buscarPorBusca(String busca) throws BusinessException{
		try {
			return daoCliente.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	private void validarCliente(Cliente cliente) throws ValidacaoException, DaoException {
		
		if(cliente.getNome().trim().equals("") ||
				cliente.getCpf_cnpj().trim().equals("") ||
				cliente.getRg().trim().equals(""))
			throw new ValidacaoException("INFORME TODOS OS DADOS NESCESSARIOS!!!");
//		if(validador.isEmail(cliente.getEmail()))
//			throw new ValidacaoException("FORMATO DO EMAIL INFORMADO ESTA INCORRETO!!!");
		if(!validador.isCPF(cliente.getCpf_cnpj()) && !validador.isCNPJ(cliente.getCpf_cnpj()))
			throw new ValidacaoException("CPF/CNPJ NÃO EXISTENTE/ACEITO!!!");
//		if(daoCliente.buscarPorCodigo(cliente.getCpf_cnpj()) != null)
//			throw new ValidacaoException("CPF/CNPJ JÁ EXISTENTE NO BANCO DE DADOS!!!");			
	}

	@Override
	public List<ClienteAdapter> buscarAdapterPorBusca(String busca) throws BusinessException {
		try {
			return daoCliente.buscarAdapterPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
}
