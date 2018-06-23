package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Usuario;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessUsuario {

	public void salvarUsuario(Usuario usuario) throws BusinessException;
    public void editarUsuario(Usuario usuario) throws BusinessException;
    public Usuario buscarUsuarioPorId(int id) throws BusinessException;
    public Usuario buscarUsuarioPorCodigo(String codigo) throws BusinessException;
    public List<Usuario> buscarUsuarioPorBusca(String busca) throws BusinessException;
	
}
