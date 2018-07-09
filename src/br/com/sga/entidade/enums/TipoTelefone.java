package br.com.sga.entidade.enums;

public enum TipoTelefone {
	
	RESIDENCIAL,PESSOAL,COMERCIAL;
	
	public static TipoTelefone getTipo(String tipo)
	{	
		switch (tipo) {
		case "RESIDENCIAL":
			return RESIDENCIAL;
		case "PESSOAL":
			return PESSOAL;
		case "COMERCIAL":
			return COMERCIAL;
		default:
			break;
		}
		return null;
		
	}
}
