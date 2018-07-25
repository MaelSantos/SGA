package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.exceptions.DaoException;

public interface IDaoConsulta extends IDao<Consulta> {
	public List<Consulta> buscaPorCliente(String busca) throws DaoException;
	public ConsultaAdapter buscarPorIdAdapter(int id) throws DaoException ;
}
