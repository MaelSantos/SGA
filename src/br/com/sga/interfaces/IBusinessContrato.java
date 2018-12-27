package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessContrato {

	public void salvarEditar(Contrato entidade) throws BusinessException;
    public Contrato buscarPorId(int id) throws BusinessException;
    public List<Contrato> buscarPorBusca(String busca) throws BusinessException;
    public List<ContratoAdapter> buscarPorClienteAdapter(String busca) throws BusinessException;
    public List<ContratoAdapter> buscaAllAdapter() throws BusinessException;
    public void editarParcela(Parcela parcela)throws BusinessException;
    
}
