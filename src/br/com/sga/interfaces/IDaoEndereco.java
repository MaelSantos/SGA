package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Endereco;
import br.com.sga.exceptions.DaoException;

public interface IDaoEndereco extends IDao<Endereco>{

	public void salvar(Endereco entidade) throws DaoException;
    public void editar(Endereco entidade) throws DaoException;
    public Endereco buscarPorId(int id) throws DaoException;
    public Endereco buscarPorCodigo(String codigo) throws DaoException;
    public List<Endereco> buscarPorBusca(String busca) throws DaoException;
	
}
