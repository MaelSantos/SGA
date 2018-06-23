package br.com.sga.fachada;

import java.util.List;

import br.com.sga.entidade.Usuario;
import br.com.sga.exceptions.BusinessException;

public interface IFachada {

    public void salvarOuEditarCurso(Usuario usuario)  throws BusinessException;

    public Usuario buscarCursoPorId(int id) throws BusinessException;

    public Usuario buscarCursoPorCodigo(String codigo)  throws BusinessException;

    public List<Usuario> buscarCursoPorBusca(String busca)  throws BusinessException;



}
