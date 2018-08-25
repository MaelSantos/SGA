package br.com.sga.exceptions;

public class DaoException extends Exception{

	public DaoException(String mensagem) {
		super(mensagem.toUpperCase());
	}
	
}
