package br.com.sga.fachada;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.adapter.ContaAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;

public interface IFachada {
	
	//usuario
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException;
	public void salvarEditarUsuario(Funcionario usuario) throws BusinessException;
    public Funcionario buscarUsuarioPorId(int id) throws BusinessException;
    public List<Funcionario> buscarUsuarioPorBusca(String busca) throws BusinessException;
    public FuncionarioAdapter buscarUsuarioPorConsultaAdapter(Integer consulta_id) throws BusinessException;
    public Funcionario buscarUsuarioPorIdConsulta(int id_consulta) throws BusinessException;

    //cliente
    public void salvarEditarCliente(Cliente cliente)throws BusinessException;
    public Cliente buscarClientePorId(int id) throws BusinessException;
    public List<Cliente> buscarClientePorBusca(String busca) throws BusinessException;
    public List<ClienteAdapter> buscarClienteAdapterPorBusca(String busca) throws BusinessException;
    public Cliente buscarClientePorIdConsulta(int id_consulta) throws BusinessException;
    
    // consulta
    public void salvarEditarConsulta(Consulta consulta) throws BusinessException;
    public Consulta buscarConsultaPorId(int id) throws BusinessException;
    public List<ConsultaAdapter> buscarConsultaPorClienteAdapter(String[] busca) throws BusinessException;
    
    //contrato
    public void salvarEditarContrato(Contrato entidade) throws BusinessException;
    public void editarParcela(Parcela parcela) throws BusinessException;
    public Contrato buscarContratoPorId(int id) throws BusinessException;
    public List<Contrato> buscarContratoPorBusca(String busca) throws BusinessException;
    public List<ContratoAdapter> buscarContratoPorClienteAdapter(String busca) throws BusinessException;
    public List<ContratoAdapter> buscaAllContratoAdapter() throws BusinessException;
    
    //processo
    public void salvarEditarProcesso(Processo entidade) throws BusinessException;
    public Processo buscarProcessoPorId(int id) throws BusinessException;
    public List<Processo> buscarProcessoPorBusca(String busca) throws BusinessException;
    public List<ProcessoAdapter> buscaAllProcessoAdapter(String tipo) throws BusinessException;
    public List<ProcessoAdapter> buscaProcessoPorClienteAdapter(int id_cliente) throws BusinessException;
    public List<Processo> buscarProcessoPorIdContrato(int contrato_id) throws BusinessException;
    public List<ProcessoAdapter> buscarProcessoPorBusca(String[] busca) throws BusinessException;
     
    //financeiro
    public void salvarEditarFinanceiro(Financeiro entidade) throws BusinessException;
    public Financeiro buscarFinanceiroPorAno(Integer ano) throws BusinessException;
    public Financeiro buscarFinanceiroPorIntervalo(Date de, Date ate) throws BusinessException;
    public List<ContaAdapter> buscarContaTotalMesPorIntervalo(Date de, Date ate,Tabela tabela) throws BusinessException;
    
    // notificacao
    public void salvarEditarNotificacao(Notificacao notificacao) throws BusinessException;
    public List<Notificacao> buscarNotificacaoPorFuncionario(Funcionario funcionario) throws BusinessException;
    public List<Notificacao> buscarNotificacaoPorData(Date data) throws BusinessException;
    public List<Date> BuscarAllDataPorMes(int mes, int ano) throws BusinessException;
    public List<NotificacaoAdapter> BuscarNotificacaoAdapterPorData(Date inicio, Date fim) throws BusinessException;
    public List<NotificacaoAdapter> BuscarNotificacaoAdapterPorEstado(String estado) throws BusinessException;
    public void validarNotificacoes(Date date) throws BusinessException;
    
    //Log
    public void salvarEditarLog(Log entidade) throws BusinessException;
    public Log buscarLogPorId(int id) throws BusinessException;
    public List<Log> buscarLogPorBusca(String busca) throws BusinessException;
    public List<Log> buscarLogPorData(Date de, Date ate, String evento, String status) throws BusinessException;
	
    
}
