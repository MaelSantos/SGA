package br.com.sga.interfaces;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessNotificacao {
	
	public void salvarEditarNotificacao(Notificacao notificacao) throws BusinessException;
    public List<Notificacao> buscarPorNotificaoesPorFuncionario(Funcionario funcionario) throws BusinessException;
    public List<Notificacao> buscarPorData(Date data) throws BusinessException;
    public List<NotificacaoAdapter> BuscarAdapterPorData(Date inicio, Date fim) throws BusinessException;
    public List<Date> BuscarAllDataPorMes(int mes, int ano) throws BusinessException;
    public List<NotificacaoAdapter> BuscarAdapterPorEstado(String estado) throws BusinessException;
    public void validarNotificacoes(Date date) throws BusinessException;
    public Notificacao buscarNotificacaoPorId(int id) throws BusinessException;
    
}
