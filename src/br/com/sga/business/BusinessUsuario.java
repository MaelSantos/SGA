package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoUsuario;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessUsuario;
import br.com.sga.interfaces.IDaoUsuario;

public class BusinessUsuario implements IBusinessUsuario {

	private IDaoUsuario daoUsuario;
	
	public BusinessUsuario() {
		
		daoUsuario = new DaoUsuario();
	}

	@Override
	public void salvarUsuario(Funcionario usuario) throws BusinessException {
		
		try {
			
			validarUsuario(usuario);
			daoUsuario.salvar(usuario);
			
		} catch (Exception e) {
			throw new BusinessException("USUARIO JÁ EXISTE OU DADOS INFORMADOS ESTÃO INCORRETOS!!!");
		}		
	}

	@Override
	public void editarUsuario(Funcionario usuario) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		
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

	private void validarUsuario(Funcionario usuario) throws ValidacaoException{
		// TODO Stub de método gerado automaticamente
		
	}
	
}
