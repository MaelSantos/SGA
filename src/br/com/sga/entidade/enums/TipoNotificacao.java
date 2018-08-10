package br.com.sga.entidade.enums;

public enum TipoNotificacao {
	
	TAREFA("TAREFA"),FINANCEIRO("FINANCEIRO"),AUDIENCIA("AUDIÊNCIA");
	
	private String value;
	
	
	private TipoNotificacao(String value) {
		this.value = value;
	}


	public static TipoNotificacao getTipo(String tipo) {
		if(tipo != null)
			for(TipoNotificacao e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
