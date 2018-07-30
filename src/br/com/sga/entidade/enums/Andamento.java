package br.com.sga.entidade.enums;

public enum Andamento{
	
	VENCIDO,CONCLUIDO,PENDENTE;
	
	public static Andamento getTipo(String tipo) {
		if(tipo != null)
			for(Andamento e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}
}
