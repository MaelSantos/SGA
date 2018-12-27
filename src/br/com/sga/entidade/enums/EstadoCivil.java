package br.com.sga.entidade.enums;

public enum EstadoCivil {

	CASADO("CASADO"), SOLTEIRO("SOLTEIRO"), VIUVO("VIÚVO"), DIVORCIADO("DIVORCIADO");
	
	private String value;
	
	private EstadoCivil(String value) {
		this.value = value;
	}
	public static EstadoCivil getValor(String valor)
	{
		for(EstadoCivil e : values())
			if(e.toString().equalsIgnoreCase(valor))
				return e;
		return null;
	}
	@Override
	public String toString() {
		return value;
	}
	
}
