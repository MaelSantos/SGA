package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.exceptions.DaoException;

public interface IDao<T> {

	public void salvar(T entidade) throws DaoException;
    public void editar(T entidade) throws DaoException;
    public T buscarPorId(int id) throws DaoException;
    public List<T> buscarPorBusca(String busca) throws DaoException;
  
}
