package br.com.sga.entidade.enums;

public enum TipoAudiencia {

	ADMONITORIA("ADMONITÓRIA"), CONCILIACAO("CONCILIAÇÃO"), INTERROGATORIO("INTERROGATÓRIO"), 
	JUSTIFICACAO("JUSTIFICAÇÃO"), EXECUCAO("EXECUÇÃO"), INICIAL("INICIAL") ,INSTRUCAO("INICIAL"),
	INSTRUÇAO_JULGAMENTO("INSTRUÇÃO JULGAMENTO"), JULGAMENTO("JULGAMENTO"), PRELIMINAR("PRELIMINAR"),
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
