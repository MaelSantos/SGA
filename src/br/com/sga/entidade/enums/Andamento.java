package br.com.sga.entidade.enums;

public enum Andamento{
	
	VENCIDO("VENCIDO"), CONCLUIDO("CONCLUÍDO"), PENDENTE("PENDENTE");
	
	private String value;
	
	
	private Andamento(String value) {
		this.value = value;
	}

	public static Andamento getTipo(String tipo) {
		if(tipo != null)
			for(Andamento e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}

	@Override
	public String toString() {
		return value;
	}
}
