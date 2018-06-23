package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.DaoException;

public interface IDaoUsuario extends IDao<Funcionario>{

	public Funcionario buscarPorLogin(String login,String senha)throws DaoException;
	
	public void salvar(Funcionario usuario) throws DaoException;
    public void editar(Funcionario usuario) throws DaoException;
    public Funcionario buscarPorId(int id) throws DaoException;
    public Funcionario buscarPorCodigo(String codigo) throws DaoException;
    public List<Funcionario> buscarPorBusca(String busca) throws DaoException;
	
}
