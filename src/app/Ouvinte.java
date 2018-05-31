package app;

import model_vo.Tela;
import model_vo.Usuario;

@FunctionalInterface
public interface Ouvinte {
	public void atualizar(Tela tela, Usuario usuario);
}
