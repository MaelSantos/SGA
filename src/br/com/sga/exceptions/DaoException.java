package br.com.sga.exceptions;

@SuppressWarnings("serial")
public class DaoException extends Exception{

	public DaoException(String mensagem) {
		super(mensagem.toUpperCase());
	}
	
}
