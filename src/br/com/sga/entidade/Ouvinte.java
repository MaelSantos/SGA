package br.com.sga.entidade;

@FunctionalInterface
public interface Ouvinte {
	public void atualizar(Tela tela, Funcionario usuario);
}
