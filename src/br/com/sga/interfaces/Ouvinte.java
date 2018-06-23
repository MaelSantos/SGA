package br.com.sga.interfaces;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Tela;

@FunctionalInterface
public interface Ouvinte {
	public void atualizar(Tela tela, Funcionario usuario);
}
