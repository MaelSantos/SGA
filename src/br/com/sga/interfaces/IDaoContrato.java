package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.exceptions.DaoException;

public interface IDaoContrato extends IDao<Contrato> {
	public List<ContratoAdapter> buscaPorClienteAdapter(String busca) throws DaoException;
	public List<ContratoAdapter> buscarAllAdapter() throws DaoException;
}
