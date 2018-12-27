package br.com.sga.entidade.enums;

public enum TipoProcesso {

	VARA_1("1º Vara"), VARA_2("2º Vara"), VARA_CRIMINAL("Vara Criminal"), CIVIL("Civil"), TRABALHISTA("Trabalhista"),
	PREVIDENCIARIO("Previdenciário");
	
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
