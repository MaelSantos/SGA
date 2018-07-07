package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.enums.Tabela;
import br.com.sga.exceptions.DaoException;

public interface IDaoCommun{
	 public int getCurrentValorTabela(Tabela tabela) throws DaoException;
	 public void salvarVinculoFuncionario(Integer notificacao_id,Integer funcionario_id) throws DaoException;
}
