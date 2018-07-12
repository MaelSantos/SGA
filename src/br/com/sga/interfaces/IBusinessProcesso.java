package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Processo;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessProcesso {

	public void salvarEditar(Processo entidade) throws BusinessException;
    public Processo buscarPorId(int id) throws BusinessException;
    public Processo buscarPorCodigo(String codigo) throws BusinessException;
    public List<Processo> buscarPorBusca(String busca) throws BusinessException;
	
}
