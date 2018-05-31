package app;

import model_vo.Tela;

@FunctionalInterface
public interface Ouvinte {
	public void atualizar(Tela tela);
}
