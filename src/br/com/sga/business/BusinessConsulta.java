package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoConsulta;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.adapter.ConsultaAdapter;
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
	public void salvarEditar(Consulta consulta) throws BusinessException {
		try {
			if(consulta.getId() == null)
				daoConsulta.salvar(consulta);
			else
				daoConsulta.editar(consulta);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}


	@Override
	public List<ConsultaAdapter> buscaPorClienteAdapter(String[] busca) throws BusinessException {
		try {
			return daoConsulta.buscaPorClienteAdapter(busca);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	@Override
	public Consulta buscarPorId(int id) throws BusinessException {
		try {
			return daoConsulta.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
