package br.com.sga.business;

import java.util.Date;
import java.util.List;

import br.com.sga.dao.DaoNotificacao;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessNotificacao;
import br.com.sga.interfaces.IDaoNotificacao;

public class BusinessNotificacao implements IBusinessNotificacao{
	
	private IDaoNotificacao daoNotificacao;
	
	public BusinessNotificacao() {
		daoNotificacao = new DaoNotificacao();
	}
	
	@Override
	public void salvarEditarNotificacao(Notificacao notificacao) throws BusinessException {
		try {
			validarNotificacao(notificacao);
			if(notificacao.getId() == null)
				daoNotificacao.salvar(notificacao);
			else
				daoNotificacao.editar(notificacao);
			
		} catch (ValidacaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	
	private void validarNotificacao(Notificacao notificacao) throws ValidacaoException{
		
		if(notificacao.getDescricao().trim().length() < 1)
			throw new ValidacaoException("DESCRIÇÃO FALTANDO OU INVALIDA!!!");
		if(notificacao.getTipoNotificacao() == null)
			throw new ValidacaoException("INFORME UM TIPO DE NOTIFICAÇÃO!!!");
		if(notificacao.getEstado() == null)
			throw new ValidacaoException("INFORME UM ESTADO INICIAL!!!");
		if(notificacao.getPrioridade() == null)
			throw new ValidacaoException("INFORME A PRIORIDADE!!!");
		if(notificacao.getAviso_data() == null)
			throw new ValidacaoException("INFORME UMA DATA!!!");
	}

	@Override
	public List<Notificacao> buscarPorNotificaoesPorFuncionario(Funcionario funcionario) throws BusinessException {
		try {
			return daoNotificacao.buscarPorFuncionario(funcionario.getNumero_oab());
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Notificacao> buscarPorData(Date data) throws BusinessException {
		try {
			return daoNotificacao.buscarPorData(data);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<NotificacaoAdapter> BuscarAdapterPorData(Date inicio, Date fim) throws BusinessException {
		try {
			return daoNotificacao.BuscarAdapterPorData(inicio, fim);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Date> BuscarAllDataPorMes(int mes, int ano) throws BusinessException {
		try {
			return daoNotificacao.BuscarAllDataPorMes(mes, ano);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<NotificacaoAdapter> BuscarAdapterPorEstado(String estado) throws BusinessException {
		try {
			return daoNotificacao.BuscarAdapterPorEstado(estado);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	
}
