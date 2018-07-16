package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Audiencia;
import br.com.sga.exceptions.DaoException;

public interface IDaoAudiencia extends IDao<Audiencia>{

	public List<Audiencia> buscarPorIdProcesso(int id)throws DaoException;
	
}
