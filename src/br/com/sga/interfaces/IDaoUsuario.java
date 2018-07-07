package br.com.sga.interfaces;


import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.DaoException;

public interface IDaoUsuario extends IDao<Funcionario>{

	public Funcionario buscarPorLogin(String login,String senha)throws DaoException;
	public Funcionario buscarPorNome(String nome)throws DaoException;
}
