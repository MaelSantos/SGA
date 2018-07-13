package br.com.sga.entidade.enums;

public enum TipoParticipacao {
	
	EXEQUENTE,EXECUTADO;
	
	public static TipoParticipacao getValue(String tipo)
	{
		if(tipo != null)
			for(TipoParticipacao t : values())
				if(t.toString().equalsIgnoreCase(tipo))
					return t;
		return null;
		
	}
	
}
