package br.com.sga.interfaces;

import br.com.sga.entidade.enums.Tela;

@FunctionalInterface
public interface Ouvinte {
	public void atualizar(Tela tela, Object object);
}
