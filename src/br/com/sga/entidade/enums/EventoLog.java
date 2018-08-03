package br.com.sga.entidade.enums;

public enum EventoLog {

	PAGAMENTO, RECEBIMENTO, EDITAR, SALVAR, CADASTRAR, APAGAR, BUSCAR;

	public static EventoLog getEvento(String evento)
	{
		for(EventoLog e : values())
			if(e.name().equalsIgnoreCase(evento))
				return e;
		return null;
	}
	
}

