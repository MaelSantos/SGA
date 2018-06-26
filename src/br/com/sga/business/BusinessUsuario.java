package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoUsuario;
import br.com.sga.entidade.Funcionario;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.exceptions.ValidacaoException;
import br.com.sga.interfaces.IBusinessUsuario;
import br.com.sga.interfaces.IDaoUsuario;

public class BusinessUsuario implements IBusinessUsuario {

	private IDaoUsuario daoUsuario;
	
	public BusinessUsuario() {
		
		daoUsuario = new DaoUsuario();
	}

	@Override
	public void salvarUsuario(Funcionario usuario) throws BusinessException{
		try {
			validarUsuario(usuario);
			daoUsuario.salvar(usuario);
		}catch (ValidacaoException e) {
			throw new BusinessException("USUARIO J� EXISTE OU DADOS INFORMADOS EST�O INCORRETOS!!!");
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());	
		}		
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

	private void validarUsuario(Funcionario usuario) throws ValidacaoException, DaoException{
		if(daoUsuario.buscarPorLogin(usuario.getLogin(),usuario.getSenha()) == null)
			throw new ValidacaoException("Usuario j� cadastrado na base");
		
	}

	@Override
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException {
		try {
			Funcionario f =  daoUsuario.buscarPorLogin(login, senha);
			if(f == null)
				throw new BusinessException("N�O H� USUARIOS CADASTRADOS COM ESSES DADOS");
			return f;
				
		} catch (DaoException e) {
			throw new BusinessException("ERRO NO BANCO - CONTATE ADM");
			
		}
		
	}
	
}
