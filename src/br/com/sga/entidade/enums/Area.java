package br.com.sga.entidade.enums;

public enum Area {
	
	CIVIL,TRABALHISTA;
	
	public static Area getArea(String string) {
		for(Area e : Area.values())
			if(e.toString().equals(string))
				return e;
		return null;
	}
	
}
