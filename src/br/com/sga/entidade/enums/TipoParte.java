package br.com.sga.entidade.enums;

public enum TipoParte {
	
	ATIVO("ATIVO"),PASSIVO("PASSIVO");
	
	private String value;
	
	private TipoParte(String value) {
		this.value = value;
	}

	
	public static TipoParte getTipoParte(String tipo)
	{
		if(tipo != null)
			for(TipoParte t : values())
				if(t.toString().equals(tipo))
					return t;
		return null;
		
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
