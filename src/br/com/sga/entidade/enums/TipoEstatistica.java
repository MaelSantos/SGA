package br.com.sga.entidade.enums;

public enum TipoEstatistica {
	
	RECEITAS_DESPESAS_POR_MES("RECEITAS E DESPESAS POR MÊS");
	
	private String value;
	
	
	private TipoEstatistica(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
