package br.com.sga.entidade.enums;

public enum Sexo {

	MASCULINO("MASCULINO"),
	FEMININO("FEMININO"),
	OUTROS("OUTROS");
	
	private  String sexo;
	private Sexo(String sexo){
		this.sexo = sexo;
	}

	public static Sexo getSexo(String string) {

		switch (string) {
		case "MASCULINO":
			return MASCULINO;
		case "FEMININO":
			return FEMININO;
		case "OUTROS":
			return OUTROS;
		}
		return null;

	}
	
	@Override
	public String toString() {
		return sexo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
