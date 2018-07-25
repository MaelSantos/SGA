package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoConsulta;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
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
	@Override
	public ConsultaAdapter buscarPorIdAdapter(int id) throws BusinessException {
		try {
			return daoConsulta.buscarPorIdAdapter(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
