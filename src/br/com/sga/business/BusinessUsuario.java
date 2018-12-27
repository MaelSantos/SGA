package br.com.sga.business;

import java.util.List;

import br.com.sga.dao.DaoUsuario;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
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
	public void salvar(Funcionario usuario) throws BusinessException{
		try {
			
			validarUsuario(usuario);
			if(usuario.getId() == null)
				daoUsuario.salvar(usuario);
			else
				daoUsuario.editar(usuario);
		}catch (ValidacaoException e) {
			throw new BusinessException(e.getMessage());
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());	
		}		
	}
	
	@Override
	public Funcionario buscarPorId(int id) throws BusinessException {
		try {
			return daoUsuario.buscarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Funcionario> buscarPorBusca(String busca) throws BusinessException {
		try {
			return daoUsuario.buscarPorBusca(busca);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	private void validarUsuario(Funcionario usuario) throws ValidacaoException, DaoException{
		if(usuario.getLogin().trim().equals(""))
			throw new ValidacaoException("LOGIN EM BRANCO!!!");
		if(usuario.getSenha().trim().equals(""))
			throw new ValidacaoException("SENHA EM BRANCO!!!");
		if(usuario.getSenha().trim().length() < 8)
			throw new ValidacaoException("SENHA MUITO CURTA!!!");
		if(usuario.getNumero_oab().trim().equals(""))
			throw new ValidacaoException("INFORME O NUMERO DA OAB!!!");
		if (usuario.getNome().trim().equals(""))
			throw new ValidacaoException("INFORME SEU NOME!!!");
		if (usuario.getEndereco() == null)
			throw new ValidacaoException("INFORME SEU ENDEREÇO!!!");
		
		validarEndereco(usuario.getEndereco());
		
	}

	private void validarEndereco(Endereco endereco) throws ValidacaoException{
		
		if(endereco.getBairro() == null)
			throw new ValidacaoException("INFORME O BAIRRO!!!");
		if(endereco.getCep() == null)
			throw new ValidacaoException("INFORME O CEP!!!");
		if(endereco.getCidade() == null)
			throw new ValidacaoException("INFORME A CIDADE!!!");
		if(endereco.getEstado() == null)
			throw new ValidacaoException("INFORME O ESTADO!!!");
		if(endereco.getNumero() == null)
			throw new ValidacaoException("INFORME O NUMERO!!!");
		if(endereco.getPais() == null)
			throw new ValidacaoException("INFORME O PAIS!!!");
		if(endereco.getRua() == null)
			throw new ValidacaoException("INFORME  A RUA!!!");
	}
	
	@Override
	public Funcionario buscarPorLogin(String login, String senha) throws BusinessException {
		try {
			return  daoUsuario.buscarPorLogin(login, senha);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public FuncionarioAdapter buscarPorConsultaAdapter(Integer consulta_id) throws BusinessException {
		try {
			return  daoUsuario.buscarPorConsultaAdapter(consulta_id);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Funcionario buscarPorIdConsulta(int id_consulta) throws BusinessException {
		try {
			return daoUsuario.buscarPorIdConsulta(id_consulta);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
