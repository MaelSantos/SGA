package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoConsulta;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessConsulta;
import br.com.sga.interfaces.IDaoConsulta;

public class BusinessConsulta implements IBusinessConsulta{
	IDaoConsulta daoConsulta;
	
	public BusinessConsulta() {
		daoConsulta = new DaoConsulta();
	}
	@Override
	public void salvar(Consulta consulta) throws BusinessException {
		
	}

	@Override
	public Consulta buscarPorId(int id) throws BusinessException {
		return null;
	}

	@Override
	public List<Consulta> buscarPorCliente(String busca) throws BusinessException {
		try {
			return daoConsulta.buscaPorCliente(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
