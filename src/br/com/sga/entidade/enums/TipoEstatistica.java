package br.com.sga.entidade.enums;

public enum TipoEstatistica {
	
	RECEITAS_POR_MES;
	
	@Override
	public String toString() {
		return super.toString().replace("_"," ");
	}
	
}
