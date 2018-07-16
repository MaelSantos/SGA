package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Notificacao;
import br.com.sga.exceptions.DaoException;

public interface IDaoNotificacao extends IDao<Notificacao> {
	public List<Notificacao> buscarPorFuncionario(String busca) throws DaoException;
}
