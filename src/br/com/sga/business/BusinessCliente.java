package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoCliente;
import br.com.sga.entidade.Cliente;
import br.com.sga.exceptions.BusinessException;
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
		// TODO Stub de método gerado automaticamente
		return null;
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
		if(validador.isEmail(cliente.getEmail()))
			throw new ValidacaoException("FORMATO DO EMAIL INFORMADO ESTA INCORRETO!!!");
		if(!validador.isCPF(cliente.getCpf_cnpj()) && !validador.isCNPJ(cliente.getCpf_cnpj()))
			throw new ValidacaoException("CPF/CNPJ NÃO EXISTENTE/ACEITO!!!");
		if(daoCliente.buscarPorCodigo(cliente.getCpf_cnpj()) != null)
			throw new ValidacaoException("CPF/CNPJ JÁ EXISTENTE NO BANCO DE DADOS!!!");			
	}
	
}
