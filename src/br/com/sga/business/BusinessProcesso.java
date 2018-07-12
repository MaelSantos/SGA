package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoProcesso;
import br.com.sga.entidade.Processo;
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
			if(entidade.getId() == null)
			{
				validar(entidade);
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
	public Processo buscarPorCodigo(String codigo) throws BusinessException {
		try {
			return daoProcesso.buscarPorCodigo(codigo);
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

	private void validar(Processo entidade) throws ValidacaoException{
		
		if(entidade.getComarca() == null || entidade.getComarca().trim().equals(""))
			throw new ValidacaoException("INFORME A COMARCA!!!");
		if(entidade.getData_atuacao() == null)
			throw new ValidacaoException("INFORME A DATA DE ATUAÇÃO!!!");
		if(entidade.getNumero() == null)
			throw new ValidacaoException("INFORME O NUMERO DO PROCESSO!!!");
		if(entidade.getClasse_judicial() == null)
			throw new ValidacaoException("INFORME A CLASSE JUDICIAL!!!");
		if(entidade.getOrgao_julgador() == null)
			throw new ValidacaoException("INFORME O ORGÃO JULGADOR!!!");
		if(entidade.getFase() == null)
			throw new ValidacaoException("INFORME A FASE!!!");
		if(entidade.getTipo_processo() == null)
			throw new ValidacaoException("INFORME O TIPO DE PROCESSO!!!");
	}
	
}
