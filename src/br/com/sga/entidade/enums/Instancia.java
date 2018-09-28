package br.com.sga.entidade.enums;

public enum Instancia {

	INSTANCIA_1("1� Inst�ncia"), INSTANCIA_2("2� Inst�ncia"), INSTANCIA_3("3� Inst�ncia");
	
	private String valor;
	
	private Instancia(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
	
}
