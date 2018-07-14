package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Contrato;
import br.com.sga.exceptions.DaoException;

public interface IDaoContrato extends IDao<Contrato> {
	public List<Contrato> buscaPorCliente(String busca) throws DaoException;
}
