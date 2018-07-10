package br.com.sga.fachada;


import java.util.List;

import br.com.sga.business.BusinessCliente;
import br.com.sga.business.BusinessUsuario;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessUsuario;
import br.com.sga.interfaces.IBussinessCliente;

public class Fachada implements IFachada {

    private IBusinessUsuario businessUsuario;
    private IBussinessCliente bussinessCliente;

    private static Fachada fachada;

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    private Fachada() {
        businessUsuario = new BusinessUsuario();
        bussinessCliente = BusinessCliente.getInstance();
    }

	@Override
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException {
		return businessUsuario.buscarPorLogin(login, senha);
	}

	@Override
	public void salvarUsuario(Funcionario usuario) throws BusinessException {
		businessUsuario.salvarUsuario(usuario);
	}

	@Override
	public void editarUsuario(Funcionario usuario) throws BusinessException {
		businessUsuario.editarUsuario(usuario);
	}

	@Override
	public Funcionario buscarUsuarioPorId(int id) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Funcionario buscarUsuarioPorCodigo(String codigo) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Funcionario> buscarUsuarioPorBusca(String busca) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public void salvarCliente(Cliente cliente) throws BusinessException{
		bussinessCliente.salvar(cliente);
	}

}
