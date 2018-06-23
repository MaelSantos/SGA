package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessUsuario {

	public void salvarUsuario(Funcionario usuario) throws BusinessException;
    public void editarUsuario(Funcionario usuario) throws BusinessException;
    public Funcionario buscarUsuarioPorId(int id) throws BusinessException;
    public Funcionario buscarUsuarioPorCodigo(String codigo) throws BusinessException;
    public List<Funcionario> buscarUsuarioPorBusca(String busca) throws BusinessException;
	
}
