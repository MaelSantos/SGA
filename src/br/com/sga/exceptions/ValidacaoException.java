package br.com.sga.exceptions;

@SuppressWarnings("serial")
public class ValidacaoException extends Exception{

	public ValidacaoException(String message) {
        super(message.toUpperCase());
    }
    
    
}
