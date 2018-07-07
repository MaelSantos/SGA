package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.exceptions.BusinessException;

public interface IBusinessNotificacao {
	public void salvarNotificacao(Notificacao notificacao) throws BusinessException;
    public List<Notificacao> buscarPorFuncionario(List<Funcionario> funcionarios) throws BusinessException;
}
