package br.com.sga.entidade.enums;

public enum TipoAudiencia {

	ADMONITÓRIA, CONCILIAÇÃO, INTERROGATÓRIO, JUSTIFICAÇÃO, EXECUÇÃO, INICIAL ,INSTRUÇÃO,
	INSTRUÇÃO_JULGAMENTO, JULGAMENTO, PRELIMINAR, UNA, SINE_DIE;

	public static TipoAudiencia getValor(String tipo)
	{
		for(TipoAudiencia a : values())
			if(a.toString().equalsIgnoreCase(tipo))
				return a;
		return null;
	}

	
}
