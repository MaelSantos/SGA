package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Endereco;
import br.com.sga.exceptions.DaoException;

public interface IBusinessEndereco {

	public void salvarEndereco(Endereco entidade) throws DaoException;
    public void editarEndereco(Endereco entidade) throws DaoException;
    public Endereco buscarPorId(int id) throws DaoException;
    public Endereco buscarPorCep(String codigo) throws DaoException;
    public List<Endereco> buscarEnderecoPorBusca(String busca) throws DaoException;
	
}
