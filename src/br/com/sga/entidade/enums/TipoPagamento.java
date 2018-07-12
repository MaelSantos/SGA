package br.com.sga.entidade.enums;

public enum TipoPagamento {
	
	BOLETO,CHEQUE,A_VISTA;
	@Override
	public String toString() {
		return super.toString().replace("_"," ");
	}
}
