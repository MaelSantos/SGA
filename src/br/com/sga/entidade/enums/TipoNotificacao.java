package br.com.sga.entidade.enums;

public enum TipoNotificacao {
	
	TAREFA,FINANCEIRO,AUDIENCIA;
	
	public TipoNotificacao getTipoNotificacao(String tipo) {
		for(TipoNotificacao e : values()) 
			if(e.toString().equalsIgnoreCase(tipo))
				return e;
		return null;
	}
	
}
