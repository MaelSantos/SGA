package br.com.sga.entidade.enums;

public enum TipoNotificacao {
	
	TAREFA,FINANCEIRO,AUDIENCIA;
	
	public static TipoNotificacao getTipo(String tipo) {
		if(tipo != null)
			for(TipoNotificacao e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}
	
}
