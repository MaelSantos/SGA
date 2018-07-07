package br.com.sga.business;

import java.util.List;

import br.com.sga.entidade.Endereco;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessEndereco;

public class BusinessEndereco implements IBusinessEndereco {

	public BusinessEndereco() {
		// TODO Stub de construtor gerado automaticamente
	}

	@Override
	public void salvarEndereco(Endereco entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public void editarEndereco(Endereco entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public Endereco buscarPorId(int id) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Endereco buscarPorCep(String codigo) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Endereco> buscarEnderecoPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

}
