package br.com.sga.fachada;

import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;

public interface IFachada {

    public void salvarOuEditarCurso(Funcionario usuario)  throws BusinessException;

    public Funcionario buscarCursoPorId(int id) throws BusinessException;

    public Funcionario buscarCursoPorCodigo(String codigo)  throws BusinessException;

    public List<Funcionario> buscarCursoPorBusca(String busca)  throws BusinessException;



}
