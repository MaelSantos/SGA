package br.com.sga.entidade.enums;

public enum TipoProcesso {

	Vara_1("1º Vara"), Vara_2("2º Vara"), Vara_Criminal("Vara Criminal");
	
	private String vara;
	
	private TipoProcesso(String vara) {
		this.vara = vara;
	}
	
	public static TipoProcesso getTipo(String tipo)
	{
		for(TipoProcesso t : values())
			if(t.toString().equalsIgnoreCase(tipo))
				return t;
		return null;
	}
	
	@Override
	public String toString() {
		return vara;
	}
	
}
