package br.com.sga.fachada;

import java.util.Date;
import java.util.List;

import br.com.sga.business.BusinessAudiencia;
import br.com.sga.business.BusinessCliente;
import br.com.sga.business.BusinessConsulta;
import br.com.sga.business.BusinessContrato;
import br.com.sga.business.BusinessFinanceiro;
import br.com.sga.business.BusinessNotificacao;
import br.com.sga.business.BusinessProcesso;
import br.com.sga.business.BusinessUsuario;
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
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.interfaces.IBusinessAudiencia;
import br.com.sga.interfaces.IBusinessConsulta;
import br.com.sga.interfaces.IBusinessContrato;
import br.com.sga.interfaces.IBusinessFinanceiro;
import br.com.sga.interfaces.IBusinessNotificacao;
import br.com.sga.interfaces.IBusinessProcesso;
import br.com.sga.interfaces.IBusinessUsuario;
import br.com.sga.interfaces.IBussinessCliente;

public class Fachada implements IFachada {

    private IBusinessUsuario businessUsuario;
    private IBussinessCliente businessCliente;
    private IBusinessConsulta businessConsulta;
    private IBusinessProcesso businessProcesso;
    private IBusinessContrato businessContrato;
    private IBusinessFinanceiro businessFinanceiro;
    private IBusinessNotificacao businessNotificacao;
    private IBusinessAudiencia businessAudiencia;

    private static Fachada fachada;

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    private Fachada() {
        businessUsuario = new BusinessUsuario();
        businessCliente = new BusinessCliente();
        businessConsulta = new BusinessConsulta();
        businessProcesso = new BusinessProcesso();
        businessContrato = new BusinessContrato();
        businessFinanceiro = new BusinessFinanceiro();
        businessNotificacao =  new BusinessNotificacao();
        businessAudiencia = new BusinessAudiencia();
    }

	@Override
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException {
		return businessUsuario.buscarPorLogin(login, senha);
	}

	@Override
	public void salvarEditarUsuario(Funcionario usuario) throws BusinessException {
		businessUsuario.salvar(usuario);
	}

	@Override
	public Funcionario buscarUsuarioPorId(int id) throws BusinessException {
		return businessUsuario.buscarPorId(id);
	}


	@Override
	public List<Funcionario> buscarUsuarioPorBusca(String busca) throws BusinessException {
		return businessUsuario.buscarPorBusca(busca);
	}

	@Override
	public void salvarEditarCliente(Cliente cliente) throws BusinessException{
		businessCliente.salvar(cliente);
	}

	@Override
	public Cliente buscarClientePorId(int id) throws BusinessException {
		return businessCliente.buscarPorId(id);
	}


	@Override
	public List<Cliente> buscarClientePorBusca(String busca) throws BusinessException {
		return businessCliente.buscarPorBusca(busca);
	}

	@Override
	public void salvarEditarConsulta(Consulta consulta) throws BusinessException {
		businessConsulta.salvarEditar(consulta);
	}

	@Override
	public Consulta buscarConsultaPorId(int id) throws BusinessException {
		return businessConsulta.buscarPorId(id);
	}

	@Override
	public List<ConsultaAdapter> buscarConsultaPorClienteAdapter(String[] busca) throws BusinessException {
		return businessConsulta.buscaPorClienteAdapter(busca);
	}
	
	public void salvarEditarProcesso(Processo entidade) throws BusinessException {
		businessProcesso.salvarEditar(entidade);
	}

	@Override
	public Processo buscarProcessoPorId(int id) throws BusinessException {
		return businessProcesso.buscarPorId(id);
	}


	@Override
	public List<Processo> buscarProcessoPorBusca(String busca) throws BusinessException {
		return businessProcesso.buscarPorBusca(busca);
	}

	@Override
	public void salvarEditarContrato(Contrato entidade) throws BusinessException {
		businessContrato.salvarEditar(entidade);
		
	}

	@Override
	public Contrato buscarContratoPorId(int id) throws BusinessException {
		return businessContrato.buscarPorId(id);
	}


	@Override
	public List<Contrato> buscarContratoPorBusca(String busca) throws BusinessException {
		return businessContrato.buscarPorBusca(busca);
	}

	@Override
	public List<ContratoAdapter> buscaAllContratoAdapter() throws BusinessException {
		return businessContrato.buscaAllAdapter();
	}
	
	@Override
	public void salvarEditarFinanceiro(Financeiro entidade) throws BusinessException {
		businessFinanceiro.salvarEditar(entidade);
	}

	@Override
	public Financeiro buscarFinanceiroPorAno(Integer ano) throws BusinessException {
		return businessFinanceiro.buscarPorAno(ano);
	}

	@Override
	public List<ContratoAdapter> buscarContratoPorClienteAdapter(String busca) throws BusinessException {
		return businessContrato.buscarPorClienteAdapter(busca);
	}

	@Override
	public void salvarEditarNotificacao(Notificacao notificacao) throws BusinessException {
		businessNotificacao.salvarEditarNotificacao(notificacao);
	}

	@Override
	public List<Notificacao> buscarNotificacaoPorFuncionario(Funcionario funcionario) throws BusinessException{
		return businessNotificacao.buscarPorNotificaoesPorFuncionario(funcionario);
	}
	
	public List<Notificacao> buscarPorFuncionario(List<Funcionario> funcionarios) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}
	public List<ProcessoAdapter> buscaAllProcessoAdapter(String tipo) throws BusinessException {
		return businessProcesso.buscarAllAdapter(tipo);
	}

	@Override
	public void salvarEditarAudiencia(Audiencia entidade) throws BusinessException {
		businessAudiencia.salvar(entidade);
	}

	@Override
	public Audiencia buscarAudienciaPorId(int id) throws BusinessException {
		return businessAudiencia.buscarPorId(id);
	}

	@Override
	public List<Audiencia> buscarAudienciaPorBusca(String busca) throws BusinessException {
		return businessAudiencia.buscarPorBusca(busca);
	}

	@Override
	public List<Audiencia> buscarAudienciaPorIdProcesso(int id) throws BusinessException {
		return businessAudiencia.buscarPorIdProcesso(id);
	}

	@Override
	public List<Notificacao> buscarNotificacaoPorData(Date data) throws BusinessException {
		return businessNotificacao.buscarPorData(data);
	}

	@Override
	public List<NotificacaoAdapter> BuscarNotificacaoAdapterPorData(Date data) throws BusinessException {
		return businessNotificacao.BuscarAdapterPorData(data);
	}

	@Override
	public List<Date> BuscarAllDataPorMes(int mes, int ano) throws BusinessException {
		return businessNotificacao.BuscarAllDataPorMes(mes, ano);
	}


	@Override
	public List<ClienteAdapter> buscarClienteAdapterPorBusca(String busca) throws BusinessException {
		return businessCliente.buscarAdapterPorBusca(busca);
	}

	@Override
	public Financeiro buscarFinanceiroPorIntervalo(Date de, Date ate) throws BusinessException {
		return businessFinanceiro.buscarPorIntervalo(de, ate);
	}

	@Override
	public FuncionarioAdapter buscarPorConsultaAdapter(Integer consulta_id) throws BusinessException {
		return businessUsuario.buscarPorConsultaAdapter(consulta_id);
	}

}
