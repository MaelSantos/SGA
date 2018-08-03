package br.com.sga.entidade.enums;

public enum StatusLog {

	COLCLUIDO, ERROR;
	
	public static StatusLog getStatus(String evento)
	{
		for(StatusLog s : values())
			if(s.name().equalsIgnoreCase(evento))
				return s;
		return null;
	}
	
}
