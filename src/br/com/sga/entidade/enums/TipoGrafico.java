package br.com.sga.entidade.enums;

public enum TipoGrafico {
	
	BARRA("BARRA"),COLUNA("COLUNA"),
	LINHA("LINHA"),PIZZA("PIZZA");
	
	private String value;

	private TipoGrafico(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
