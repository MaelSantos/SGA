package br.com.sga.fachada;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Financeiro;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.exceptions.BusinessException;

public interface IFachada {
	
	//usuario
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException;
	public void salvarEditarUsuario(Funcionario usuario) throws BusinessException;
    public Funcionario buscarUsuarioPorId(int id) throws BusinessException;
    public List<Funcionario> buscarUsuarioPorBusca(String busca) throws BusinessException;

    //cliente
    public void salvarEditarCliente(Cliente cliente)throws BusinessException;
    public Cliente buscarClientePorId(int id) throws BusinessException;
    public List<Cliente> buscarClientePorBusca(String busca) throws BusinessException;
    public List<ClienteAdapter> buscarClienteAdapterPorBusca(String busca) throws BusinessException;
    
    // consulta
    public void salvarEditarConsulta(Consulta consulta) throws BusinessException;
    public Consulta buscarConsultaPorId(int id) throws BusinessException;
    public List<Consulta> buscarConsultaPorCliente(String busca) throws BusinessException;
    public ConsultaAdapter buscarConsultaPorIdAdapter(int id) throws BusinessException;
    
    //contrato
    public void salvarEditarContrato(Contrato entidade) throws BusinessException;
    public Contrato buscarContratoPorId(int id) throws BusinessException;
    public List<Contrato> buscarContratoPorBusca(String busca) throws BusinessException;
    public List<Contrato> buscarContratoPorCliente(String busca) throws BusinessException;
    public List<ContratoAdapter> buscaAllContratoAdapter() throws BusinessException;
    
    //processo
    public void salvarEditarProcesso(Processo entidade) throws BusinessException;
    public Processo buscarProcessoPorId(int id) throws BusinessException;
    public List<Processo> buscarProcessoPorBusca(String busca) throws BusinessException;
    public List<ProcessoAdapter> buscaAllProcessoAdapter(String tipo) throws BusinessException;
    
    //audiencia
    public void salvarEditarAudiencia(Audiencia entidade) throws BusinessException;
    public Audiencia buscarAudienciaPorId(int id) throws BusinessException;
    public List<Audiencia> buscarAudienciaPorBusca(String busca) throws BusinessException;
    public List<Audiencia> buscarAudienciaPorIdProcesso(int id) throws BusinessException;
    
    //financeiro
    public void salvarEditarFinanceiro(Financeiro entidade) throws BusinessException;
    public Financeiro buscarFinanceiroPorAno(Integer ano) throws BusinessException;
    
    // notificacao
    public void salvarEditarNotificacao(Notificacao notificacao) throws BusinessException;
    public List<Notificacao> buscarNotificacaoPorFuncionario(Funcionario funcionario) throws BusinessException;
    public List<Notificacao> buscarNotificacaoPorData(Date data) throws BusinessException;
    public List<Date> BuscarAllDataPorMes(int mes, int ano) throws BusinessException;
    public List<NotificacaoAdapter> BuscarNotificacaoAdapterPorData(Date data) throws BusinessException;
}
