package br.com.sga.entidade.enums;

public enum TipoParticipacao {
	
	EXEQUENTE("EXEQUENTE"),EXECUTADO("EXECUTADO"),ADVOGADO("ADVOGADO");
	
	private String value;
	
	
	
	private TipoParticipacao(String value) {
		this.value = value;
	}



	public static TipoParticipacao getValue(String tipo)
	{
		if(tipo != null)
			for(TipoParticipacao t : values())
				if(t.toString().equalsIgnoreCase(tipo))
					return t;
		return null;
		
	}
	
	@Override
	public String toString() {
		return value;
	}
}
