package br.com.sga.entidade.enums;

public enum TipoCliente {
	
	FISICO,JURIDICO;
	
	public static TipoCliente getTipo(String tipo) {
		if(tipo.equalsIgnoreCase(TipoCliente.FISICO.toString()))
			return FISICO;
		else
			return JURIDICO;
	}
	
}
