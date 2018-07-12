package br.com.sga.fachada;


import java.util.List;

import br.com.sga.business.BusinessCliente;
import br.com.sga.business.BusinessConsulta;
import br.com.sga.business.BusinessUsuario;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessConsulta;
import br.com.sga.interfaces.IBusinessUsuario;
import br.com.sga.interfaces.IBussinessCliente;

public class Fachada implements IFachada {

    private IBusinessUsuario businessUsuario;
    private IBussinessCliente businessCliente;
    private IBusinessConsulta businessConsulta;

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
        businessConsulta = new BusinessConsulta();
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
	public void salvarEditarConsulta(Consulta consulta) throws BusinessException {
		businessConsulta.salvar(consulta);
	}

	@Override
	public Consulta buscarConsultaPorId(int id) throws BusinessException {
		return null;
	}

	@Override
	public List<Consulta> buscarConsultaPorCliente(String busca) throws BusinessException {
		return businessConsulta.buscarPorCliente(busca);
	}

}
