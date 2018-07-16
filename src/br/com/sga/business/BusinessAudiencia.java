package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoAudiencia;
import br.com.sga.entidade.Audiencia;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessAudiencia;
import br.com.sga.interfaces.IDaoAudiencia;

public class BusinessAudiencia implements IBusinessAudiencia {

	private IDaoAudiencia daoAudiencia;
	
	public BusinessAudiencia() {

		daoAudiencia = new DaoAudiencia();
	}

	@Override
	public void salvar(Audiencia entidade) throws BusinessException {
			try {
				validar(entidade);
				if(entidade.getId() == null)
					daoAudiencia.salvar(entidade);
				else
					daoAudiencia.editar(entidade);
				
				
			} catch (DaoException | ValidacaoException e) {
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}
	}

	@Override
	public Audiencia buscarPorId(int id) throws BusinessException {
		try {
			return daoAudiencia.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Audiencia> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoAudiencia.buscarPorBusca(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Audiencia> buscarPorIdProcesso(int id) throws BusinessException {
		try {
			return daoAudiencia.buscarPorIdProcesso(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	private void validar(Audiencia entidade) throws ValidacaoException{
		
		if(entidade.getVara() == null || entidade.getVara().trim().equalsIgnoreCase(""))
			throw new ValidacaoException("INFORMA플O OBRIGATORIA - INFORME A VARA!!!");
		if(entidade.getOrgao() == null || entidade.getOrgao().trim().equalsIgnoreCase(""))
			throw new ValidacaoException("INFORMA플O OBRIGATORIA - INFORME O ORG홒!!!");
		if(entidade.getTipo() == null)
			throw new ValidacaoException("INFORMA플O OBRIGATORIA - INFORME O TIPO!!!");
		if(entidade.getData_audiencia() == null)
			throw new ValidacaoException("INFORMA플O OBRIGATORIA - INFORME A DATA!!!");
		if(entidade.getStatus() == null)
			throw new ValidacaoException("INFORMA플O OBRIGATORIA - INFORME O STATUS!!!");
	}
	
}
