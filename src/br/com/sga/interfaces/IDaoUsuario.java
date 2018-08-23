package br.com.sga.interfaces;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.exceptions.DaoException;

public interface IDaoUsuario extends IDao<Funcionario>{

	public Funcionario buscarPorLogin(String login,String senha)throws DaoException;
	public FuncionarioAdapter buscarPorConsultaAdapter(Integer consulta_id) throws DaoException;
	public Funcionario buscarPorIdConsulta(int id_consulta) throws DaoException;
}
