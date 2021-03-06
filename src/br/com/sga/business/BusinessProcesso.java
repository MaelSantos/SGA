package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoProcesso;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessProcesso;
import br.com.sga.interfaces.IDaoProcesso;

public class BusinessProcesso implements IBusinessProcesso{

	private IDaoProcesso daoProcesso;
	
	public BusinessProcesso() {
		
		daoProcesso = new DaoProcesso();
	}

	@Override
	public void salvarEditar(Processo entidade) throws BusinessException {
		
		try {
			validar(entidade);
			if(entidade.getId() == null)
			{
				daoProcesso.salvar(entidade);
			}
			else
				daoProcesso.editar(entidade);
		} catch (Exception e) {

			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
	}
	@Override
	public Processo buscarPorId(int id) throws BusinessException {
		try {
			return daoProcesso.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Processo> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoProcesso.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ProcessoAdapter> buscarAllAdapter(String tipo) throws BusinessException {
		try {
			return daoProcesso.buscarAllAdapter(tipo);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ProcessoAdapter> buscaPorClienteAdapter(int id_cliente) throws BusinessException {
		try {
			return daoProcesso.buscaPorClienteAdapter(id_cliente);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<ProcessoAdapter> buscarPorBusca(String[] busca) throws BusinessException {
		try {
			return daoProcesso.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	private void validar(Processo entidade) throws ValidacaoException{
		
		if(entidade.getCliente() == null)
			throw new ValidacaoException("INFORME UM CLIENTE!!!");
		if(entidade.getPartes() == null || entidade.getPartes().isEmpty())
			throw new ValidacaoException("INFORME UMA PARTE!!!");
		if(entidade.getTipo_processo() == null)
			throw new ValidacaoException("INFORME O TIPO DE PROCESSO!!!");
		if(entidade.getComarca() == null || entidade.getComarca().trim().equals(""))
			throw new ValidacaoException("INFORME A COMARCA!!!");
		if(entidade.getData_atuacao() == null)
			throw new ValidacaoException("INFORME A DATA DE ATUA��O!!!");
		if(entidade.getDescricao() == null || entidade.getDescricao().trim().equals(""))
			throw new ValidacaoException("INFORME A DESCRI��O!!!");
		if(entidade.getClasse_judicial() == null || entidade.getClasse_judicial().trim().equals(""))
			throw new ValidacaoException("INFORME A CLASSE JUDICIAL!!!");
		if(entidade.getOrgao_julgador() == null || entidade.getOrgao_julgador().trim().equals(""))
			throw new ValidacaoException("INFORME O ORG�O JULGADOR!!!");
		if(entidade.getFase() == null || entidade.getFase().trim().equals(""))
			throw new ValidacaoException("INFORME A FASE!!!");
	}

	
}
