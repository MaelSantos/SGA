package br.com.sga.interfaces;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.exceptions.DaoException;

public interface IDaoNotificacao extends IDao<Notificacao> {
	public List<Notificacao> buscarPorFuncionario(String busca) throws DaoException;
	public List<Notificacao> buscarPorData(Date data) throws DaoException;
	public List<Date> BuscarAllDataPorMes(int mes, int ano) throws DaoException;
	public List<NotificacaoAdapter> BuscarAdapterPorData(Date inicio, Date fim) throws DaoException;
	public List<NotificacaoAdapter> BuscarAdapterPorEstado(String estado) throws DaoException;
	public void validarNotificacoes(Date date) throws DaoException;
}
