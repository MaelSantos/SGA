package br.com.sga.entidade.enums;

public enum Area {
	
	CIVIL("CIVIL"),TRABALHISTA("TRABALHISTA");
	private String value;
	
	private Area(String value) {
		this.value = value;
	}

	public static Area getArea(String string) {
		for(Area e : Area.values())
			if(e.toString().equalsIgnoreCase(string))
				return e;
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
