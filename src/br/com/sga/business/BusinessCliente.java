package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoCliente;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBussinessCliente;
import br.com.sga.interfaces.IDaoCliente;

public class BusinessCliente implements IBussinessCliente {

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
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Cliente buscarPorId(int id) throws BusinessException {
		try {
			return daoCliente.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Cliente> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoCliente.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
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

	@Override
	public Cliente buscarPorIdConsulta(int id_consulta) throws BusinessException {
		try {
			return daoCliente.buscarPorIdConsulta(id_consulta);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	private void validarCliente(Cliente cliente) throws ValidacaoException, DaoException {

		if (cliente.getNome().trim().equals("") || cliente.getCpf_cnpj().trim().equals("")
				|| cliente.getRg().trim().equals("") || cliente.getNome() == null || cliente.getCpf_cnpj() == null
				|| cliente.getRg() == null)
			throw new ValidacaoException("INFORME TODOS OS DADOS NESCESSARIOS!!!");
		if (cliente.getEmail() != null && !cliente.getEmail().trim().equals(""))
			if (!validador.isEmail(cliente.getEmail()))
				throw new ValidacaoException("FORMATO DO EMAIL INFORMADO ESTA INCORRETO!!!");
		if (!validador.isCPF(cliente.getCpf_cnpj()) && !validador.isCNPJ(cliente.getCpf_cnpj()))
			throw new ValidacaoException("CPF/CNPJ NÃO EXISTENTE/ACEITO!!!");
		if(cliente.getEndereco() == null)
			throw new ValidacaoException("INFORME UM ENDEREÇO!!!");
		
		validarEndereco(cliente.getEndereco());
	}

	private void validarEndereco(Endereco endereco) throws ValidacaoException{
		
		if(endereco.getBairro() == null)
			throw new ValidacaoException("INFORME O BAIRRO!!!");
		if(endereco.getCep() == null)
			throw new ValidacaoException("INFORME O CEP!!!");
		if(endereco.getCidade() == null)
			throw new ValidacaoException("INFORME A CIDADE!!!");
		if(endereco.getEstado() == null)
			throw new ValidacaoException("INFORME O ESTADO!!!");
		if(endereco.getNumero() == null)
			throw new ValidacaoException("INFORME O NUMERO!!!");
		if(endereco.getPais() == null)
			throw new ValidacaoException("INFORME O PAIS!!!");
		if(endereco.getRua() == null)
			throw new ValidacaoException("INFORME  A RUA!!!");
	}
	
	

}
