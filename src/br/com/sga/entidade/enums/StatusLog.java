package br.com.sga.entidade.enums;

public enum StatusLog {

	CONCLUIDO, ERRO, SEM_RESULTADOS;
	
	public static StatusLog getStatus(String evento)
	{
		for(StatusLog s : values())
			if(s.name().equalsIgnoreCase(evento))
				return s;
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString().replace("_"," ");
	}
}
