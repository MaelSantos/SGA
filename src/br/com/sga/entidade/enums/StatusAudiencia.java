package br.com.sga.entidade.enums;

public enum StatusAudiencia {

	DESIGNADO, REDESIGNADA, CANCELADA, ANTECIPADA, REALIZADO, N�O_REALIZADA, CONVERTIDA_EM_DILIG�NCIA;
	
	public static StatusAudiencia getValor(String tipo)
	{
		for(StatusAudiencia s : values())
			if(s.toString().equalsIgnoreCase(tipo))
				return s;
		return null;
	}
	
}
