package br.com.sga.entidade.enums;

public enum EstadoCivil {

	CASADO, SOLTEIRO, VIUVO, DIVORCIADO;

	public static EstadoCivil getValor(String valor)
	{
		for(EstadoCivil e : values())
			if(e.toString().equalsIgnoreCase(valor))
				return e;
		return null;
	}
	
}
