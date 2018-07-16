package br.com.sga.entidade.enums;

public enum StatusAudiencia {

	DESIGNADO, REDESIGNADA, CANCELADA, ANTECIPADA, REALIZADO, NÃO_REALIZADA, CONVERTIDA_EM_DILIGÊNCIA;
	
	public static StatusAudiencia getValor(String tipo)
	{
		for(StatusAudiencia s : values())
			if(s.toString().equalsIgnoreCase(tipo))
				return s;
		return null;
	}
	
}
