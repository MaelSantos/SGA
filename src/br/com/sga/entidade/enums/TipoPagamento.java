package br.com.sga.entidade.enums;

public enum TipoPagamento {
	
	BOLETO,CHEQUE,A_VISTA;
	
	public static TipoPagamento getTipoPagamento(String tipo) {
		if(tipo!=null) 
			for(TipoPagamento e : TipoPagamento.values())
				if(e.toString().equals(tipo))
					return e;
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString().replace("_"," ");
	}
}
