package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IBusinessProcesso {

	public void salvarEditar(Processo entidade) throws BusinessException;
    public Processo buscarPorId(int id) throws BusinessException;
    public List<Processo> buscarPorBusca(String busca) throws BusinessException;
    public List<ProcessoAdapter> buscarAllAdapter(String tipo)throws BusinessException;
    public List<ProcessoAdapter> buscaPorClienteAdapter(int id_cliente) throws BusinessException;
    public List<ProcessoAdapter> buscarPorBusca(String[] busca) throws BusinessException;
}
