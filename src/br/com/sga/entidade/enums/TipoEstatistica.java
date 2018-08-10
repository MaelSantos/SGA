package br.com.sga.entidade.enums;

public enum TipoEstatistica {
	
	RECEITAS_POR_MES("RECEITAS POR MÊS"),DESPESAS_POR_MES("DESPESAS POR MÊS");;
	
	private String value;
	
	
	private TipoEstatistica(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
