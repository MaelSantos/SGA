package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IBusinessUsuario {

	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException;
	public void salvar(Funcionario usuario) throws BusinessException;
    public Funcionario buscarPorId(int id) throws BusinessException;
    public List<Funcionario> buscarPorBusca(String busca) throws BusinessException;
    public FuncionarioAdapter buscarPorConsultaAdapter(Integer consulta_id) throws BusinessException;
   // public Funcionario buscarUsuarioPorNome(String nome)throws BusinessException;
	
}
