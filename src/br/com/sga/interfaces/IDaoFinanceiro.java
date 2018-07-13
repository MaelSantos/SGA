package br.com.sga.interfaces;

import br.com.sga.entidade.Financeiro;
import br.com.sga.exceptions.DaoException;

public interface IDaoFinanceiro extends IDao<Financeiro>{
	public Financeiro buscarPorAno(Integer ano) throws DaoException;
}
