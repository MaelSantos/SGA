package br.com.sga.entidade.enums;

public enum TipoAudiencia {

	ADMONITORIA("ADMONIT�RIA"), CONCILIACAO("CONCILIA��O"), INTERROGATORIO("INTERROGAT�RIO"), 
	JUSTIFICACAO("JUSTIFICA��O"), EXECUCAO("EXECU��O"), INICIAL("INICIAL") ,INSTRUCAO("INICIAL"),
	INSTRU�AO_JULGAMENTO("INSTRU��O JULGAMENTO"), JULGAMENTO("JULGAMENTO"), PRELIMINAR("PRELIMINAR"),
	UNA("UNA"), SINE_DIE("SINE DIE");
	
	private String value;

	
	private TipoAudiencia(String value) {
		this.value = value;
	}


	public static TipoAudiencia getValor(String tipo)
	{
		for(TipoAudiencia a : values())
			if(a.toString().equalsIgnoreCase(tipo))
				return a;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
	

	
}
