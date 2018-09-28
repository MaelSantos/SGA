package br.com.sga.entidade.enums;

public enum Instancia {

	INSTANCIA_1("1° Instância"), INSTANCIA_2("2° Instância"), INSTANCIA_3("3° Instância");
	
	private String valor;
	
	private Instancia(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
	
}
