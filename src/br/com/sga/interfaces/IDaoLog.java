package br.com.sga.interfaces;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Log;
import br.com.sga.exceptions.DaoException;

public interface IDaoLog extends IDao<Log>{

	public List<Log> buscarPorData(Date de, Date ate) throws DaoException;
	
}
