package br.com.sga.interfaces;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.adapter.ContaAdapter;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessFinanceiro {
	public Financeiro buscarPorAno(Integer ano) throws BusinessException;
	public void salvarEditar(Financeiro financeiro) throws BusinessException;
    public Financeiro buscarPorId(int id) throws BusinessException;
    public List<Financeiro> buscarPorBusca(String busca) throws BusinessException;
    public Financeiro buscarPorIntervalo(Date de, Date ate) throws BusinessException;
    public List<ContaAdapter> buscarContaTotalMesPorIntervalo(Date de, Date ate,Tabela tabela) throws BusinessException;
 
}
