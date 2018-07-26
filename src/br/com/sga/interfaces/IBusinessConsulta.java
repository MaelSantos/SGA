package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IBusinessConsulta {
	public void salvarEditar(Consulta consulta) throws BusinessException;
    public Consulta buscarPorId(int id) throws BusinessException;
    public List<ConsultaAdapter> buscaPorClienteAdapter(String busca) throws BusinessException;
}
