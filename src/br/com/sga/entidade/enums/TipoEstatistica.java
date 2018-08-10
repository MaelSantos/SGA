package br.com.sga.entidade.enums;

public enum TipoEstatistica {
	
	RECEITAS_POR_MES("RECEITAS POR M�S"),DESPESAS_POR_MES("DESPESAS POR M�S");;
	
	private String value;
	
	
	private TipoEstatistica(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
