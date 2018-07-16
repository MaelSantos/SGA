package br.com.sga.entidade.enums;

public enum TipoAudiencia {

	ADMONIT�RIA, CONCILIA��O, INTERROGAT�RIO, JUSTIFICA��O, EXECU��O, INICIAL ,INSTRU��O,
	INSTRU��O_JULGAMENTO, JULGAMENTO, PRELIMINAR, UNA, SINE_DIE;

	public static TipoAudiencia getValor(String tipo)
	{
		for(TipoAudiencia a : values())
			if(a.toString().equalsIgnoreCase(tipo))
				return a;
		return null;
	}

	public static void main(String[] args) {
		
		for(TipoAudiencia a : values())
			System.out.println(a.toString().toUpperCase());
		
	}
	
}
