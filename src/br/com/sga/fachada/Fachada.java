package br.com.sga.fachada;


import java.util.List;

import br.com.sga.business.BusinessCliente;
import br.com.sga.business.BusinessContrato;
import br.com.sga.business.BusinessProcesso;
import br.com.sga.business.BusinessUsuario;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Processo;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessContrato;
import br.com.sga.interfaces.IBusinessProcesso;
import br.com.sga.interfaces.IBusinessUsuario;
import br.com.sga.interfaces.IBussinessCliente;

public class Fachada implements IFachada {

    private IBusinessUsuario businessUsuario;
    private IBussinessCliente businessCliente;
    private IBusinessProcesso businessProcesso;
    private IBusinessContrato businessContrato;

    private static Fachada fachada;

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    private Fachada() {
        businessUsuario = new BusinessUsuario();
        businessCliente = BusinessCliente.getInstance();
        businessProcesso = new BusinessProcesso();
        businessContrato = new BusinessContrato();
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
	public Funcionario buscarUsuarioPorCodigo(String codigo) throws BusinessException {
		return businessUsuario.buscarPorCodigo(codigo);
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
	public Cliente buscarClientePorCodigo(String codigo) throws BusinessException {
		return businessCliente.buscarPorCodigo(codigo);
	}

	@Override
	public List<Cliente> buscarClientePorBusca(String busca) throws BusinessException {
		return businessCliente.buscarPorBusca(busca);
	}

	@Override
	public void salvarEditarProcesso(Processo entidade) throws BusinessException {
		businessProcesso.salvarEditar(entidade);
	}

	@Override
	public Processo buscarProcessoPorId(int id) throws BusinessException {
		return businessProcesso.buscarPorId(id);
	}

	@Override
	public Processo buscarProcessoPorCodigo(String codigo) throws BusinessException {
		return businessProcesso.buscarPorCodigo(codigo);
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
		return buscarContratoPorId(id);
	}

	@Override
	public Contrato buscarContratoPorCodigo(String codigo) throws BusinessException {
		return businessContrato.buscarPorCodigo(codigo);
	}

	@Override
	public List<Contrato> buscarContratoPorBusca(String busca) throws BusinessException {
		return businessContrato.buscarPorBusca(busca);
	}

}
