package br.com.sga.entidade.enums;

public enum TipoTelefone {
	
	RESIDENCIAL("RESIDENCIAL"),PESSOAL("PESSOAL"),COMERCIAL("COMERCIAL");
	
	private String value;
	
	
	private TipoTelefone(String value) {
		this.value = value;
	}

	public static TipoTelefone getTipo(String tipo) {
		if(tipo != null)
			for(TipoTelefone e : values()) 
				if(e.toString().equalsIgnoreCase(tipo))
					return e;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
