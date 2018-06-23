package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoUsuario;
import br.com.sga.entidade.Usuario;
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
	public void salvarUsuario(Usuario usuario) throws BusinessException {
		
		try {
			
			validarUsuario(usuario);
			daoUsuario.salvar(usuario);
			
		} catch (Exception e) {
			throw new BusinessException("USUARIO J� EXISTE OU DADOS INFORMADOS EST�O INCORRETOS!!!");
		}		
	}

	@Override
	public void editarUsuario(Usuario usuario) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		
	}

	@Override
	public Usuario buscarUsuarioPorId(int id) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}

	@Override
	public Usuario buscarUsuarioPorCodigo(String codigo) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}

	@Override
	public List<Usuario> buscarUsuarioPorBusca(String busca) throws BusinessException {
		// TODO Stub de m�todo gerado automaticamente
		return null;
	}

	private void validarUsuario(Usuario usuario) throws ValidacaoException{
		// TODO Stub de m�todo gerado automaticamente
		
	}
	
}
