package br.com.sga.entidade.enums;

public enum TipoFinanceiro {

	DESPESA("DESPESA"), RECEITA("RECEITA");
	
	private String valor;
	
	private TipoFinanceiro(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}
