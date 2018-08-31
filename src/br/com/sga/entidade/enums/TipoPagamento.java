package br.com.sga.entidade.enums;

public enum TipoPagamento {
	
	BOLETO("BOLETO"),CHEQUE("CHEQUE"),A_VISTA("A VISTA"), DEPOSITO_EM_CONTA("DEPOSITO EM CONTA");
	
	private String value;
	
	private TipoPagamento(String value) {
		this.value = value;
	}

	public static TipoPagamento getTipoPagamento(String tipo) {
		if(tipo!=null) 
			for(TipoPagamento e : TipoPagamento.values())
				if(e.toString().equals(tipo))
					return e;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
