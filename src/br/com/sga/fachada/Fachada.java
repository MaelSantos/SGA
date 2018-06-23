package br.com.sga.fachada;


import java.util.List;

import br.com.sga.business.BusinessUsuario;
import br.com.sga.entidade.Usuario;
import br.com.sga.exceptions.BusinessException;
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
	public void salvarOuEditarCurso(Usuario usuario) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public Usuario buscarCursoPorId(int id) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Usuario buscarCursoPorCodigo(String codigo) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Usuario> buscarCursoPorBusca(String busca) throws BusinessException {
		// TODO Stub de método gerado automaticamente
		return null;
	}


}
