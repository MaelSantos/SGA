package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Usuario;
import br.com.sga.exceptions.DaoException;

public interface IDaoUsuario extends IDao<Usuario>{

	public void salvar(Usuario usuario) throws DaoException;
    public void editar(Usuario usuario) throws DaoException;
    public Usuario buscarPorId(int id) throws DaoException;
    public Usuario buscarPorCodigo(String codigo) throws DaoException;
    public List<Usuario> buscarPorBusca(String busca) throws DaoException;
	
}
