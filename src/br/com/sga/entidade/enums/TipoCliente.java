package br.com.sga.entidade.enums;

public enum TipoCliente {
	
	FISICO("F�SICO"), JURIDICO("JUR�DICO");
	
	private String value;
	
	
	private TipoCliente(String value) {
		this.value = value;
	}


	public static TipoCliente getTipo(String tipo) {
		if(tipo.equalsIgnoreCase(TipoCliente.FISICO.toString()))
			return FISICO;
		else
			return JURIDICO;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
