package br.com.sga.fachada;


import java.util.List;

import br.com.sga.business.BusinessUsuario;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IBusinessUsuario;

public class Fachada implements IFachada {

    private IBusinessUsuario businessUsuario;

    private static Fachada fachada;

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    private Fachada() {
        businessUsuario = new BusinessUsuario();
    }

	@Override
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException {
		return businessUsuario.buscarPorLogin(login, senha);
	}

	@Override
	public void salvarUsuario(Funcionario usuario) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		
	}

	@Override
	public void editarUsuario(Funcionario usuario) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		
	}

	@Override
	public Funcionario buscarUsuarioPorId(int id) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}

	@Override
	public Funcionario buscarUsuarioPorCodigo(String codigo) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}

	@Override
	public List<Funcionario> buscarUsuarioPorBusca(String busca) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}

	

	
}