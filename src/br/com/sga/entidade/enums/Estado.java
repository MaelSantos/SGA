package br.com.sga.entidade.enums;

public enum Estado {

	Acre("Acre","AC"), Alagoas("Alagoas","AL"), Amapá("Amapá","AP"), 
	Amazonas("Amazonas","AM"), Bahia("Bahia","BA"), Ceará("Ceará","CE"),
	Distrito_Federal("Distrito Federal","DF"), Espírito_Santo("Espírito Santo","ES"), Goiás("Goiás","GO"),
	Maranhão("Maranhão","MA"), Mato_Grosso("Mato_Grosso","MT"), Mato_Grosso_do_Sul("Mato Grosso do Sul","MS"),
	Minas_Gerais("Minas Gerais","MG"), Pará("Pará","PA"), Paraíba("Paraíba","PB"),
	Paraná("Paraná","PR"), Pernambuco("Pernambuco","PE"), Piauí("Piauí","PI"),
	Roraima("Roraima","RR"), Rondônia("Rondônia","RO"), Rio_de_Janeiro("Rio de Janeiro","RJ"),
	Rio_Grande_do_Norte("Rio Grande do Norte","RN"), Rio_Grande_do_Sul("Rio Grande do Sul","RS"), Santa_Catarina("Santa Catarina","SC"), 
	São_Paulo("São Paulo","SP"), Sergipe("Sergipe","SE"), Tocantins("Tocantins","TO");

	private String nome, sigla;
	Estado(String nome, String sigla)
	{
		this.nome = nome;
		this.sigla = sigla;	
	}
	
	public static Estado getEstado(String estado)
	{
		for(Estado e : values())
			if(e.toString().equalsIgnoreCase(estado))
				return e;
		return null;
	}
	
	@Override
	public String toString() {
		return nome+" - "+sigla;
	}
}
