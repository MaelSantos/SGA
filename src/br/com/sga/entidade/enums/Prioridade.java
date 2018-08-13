package br.com.sga.entidade.enums;

public enum Prioridade {
	
	BAIXA("Baixa"),MEDIA("Média"),ALTA("Alta");
	private String value;
	
	private Prioridade(String value) {
		this.value = value;
	}

	public static Prioridade getTipo(String tipo) {
		if(tipo != null)
			for(Prioridade e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
