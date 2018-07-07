package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.exceptions.DaoException;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.enums.Tabela;

public interface IDaoCommun {
	
	public int getCurrentValorTabela(Tabela tabela) throws DaoException;
	
	public void salvarVinculoFuncionario(Integer notificacao_id,Integer funcionario_id) throws DaoException;
	
    public void salvarEndereco(Endereco endereco) throws DaoException;

    public void salvarContato(Telefone telefone, int id) throws DaoException;

    public List<Telefone> getContatos(Integer id) throws DaoException;

}