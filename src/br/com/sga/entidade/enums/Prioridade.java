package br.com.sga.entidade.enums;

public enum Prioridade {
	
	BAIXA,MEDIA,ALTA;
	
	public static Prioridade getTipo(String tipo) {
		if(tipo != null)
			for(Prioridade e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}
	
}
