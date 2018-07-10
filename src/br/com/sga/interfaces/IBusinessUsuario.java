package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IBusinessUsuario {

	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException;
	public void salvar(Funcionario usuario) throws BusinessException;
    public void editar(Funcionario usuario) throws BusinessException;
    public Funcionario buscarPorId(int id) throws BusinessException;
    public Funcionario buscarPorCodigo(String codigo) throws BusinessException;
    public List<Funcionario> buscarPorBusca(String busca) throws BusinessException;
   // public Funcionario buscarUsuarioPorNome(String nome)throws BusinessException;
	
}
