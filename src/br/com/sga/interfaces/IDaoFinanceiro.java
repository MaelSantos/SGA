package br.com.sga.interfaces;

import java.util.Date;

import br.com.sga.entidade.Financeiro;
import br.com.sga.exceptions.DaoException;

public interface IDaoFinanceiro extends IDao<Financeiro>{
	
	public Financeiro buscarPorAno(Integer ano) throws DaoException;
	public Financeiro buscarPorIntervalo(Date de, Date ate) throws DaoException;
}
