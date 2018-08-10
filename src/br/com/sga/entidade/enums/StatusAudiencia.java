package br.com.sga.entidade.enums;

public enum StatusAudiencia {

	DESIGNADO("DESIGNADO"), REDESIGNADA("REDESIGNADA"), CANCELADA("CANCELADA"), ANTECIPADA("ANTECIPADA"), 
	REALIZADO("REALIZADO"), NAO_REALIZADA("NÃO REALIZADA"), CONVERTIDA_EM_DILIGENCIA("CONVERTIDA EM DILIGÊNCIA");
	
	private String value;
	
	
	private StatusAudiencia(String value) {
		this.value = value;
	}

	public static StatusAudiencia getValor(String tipo)
	{
		for(StatusAudiencia s : values())
			if(s.toString().equalsIgnoreCase(tipo))
				return s;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
