package br.com.sga.entidade.enums;

public enum TipoParticipacao {
	
	EXEQUENTE,EXECUTADO;
	
	public static TipoParticipacao getValue(String tipo)
	{
		for(TipoParticipacao t : values())
			if(t.toString().equalsIgnoreCase(tipo))
				return t;
		return null;
		
	}
	
}
