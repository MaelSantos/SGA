package br.com.sga.entidade.enums;

public enum Estado {

	Acre("Acre","AC"), Alagoas("Alagoas","AL"), Amap�("Amap�","AP"), 
	Amazonas("Amazonas","AM"), Bahia("Bahia","BA"), Cear�("Cear�","CE"),
	Distrito_Federal("Distrito Federal","DF"), Esp�rito_Santo("Esp�rito Santo","ES"), Goi�s("Goi�s","GO"),
	Maranh�o("Maranh�o","MA"), Mato_Grosso("Mato_Grosso","MT"), Mato_Grosso_do_Sul("Mato Grosso do Sul","MS"),
	Minas_Gerais("Minas Gerais","MG"), Par�("Par�","PA"), Para�ba("Para�ba","PB"),
	Paran�("Paran�","PR"), Pernambuco("Pernambuco","PE"), Piau�("Piau�","PI"),
	Roraima("Roraima","RR"), Rond�nia("Rond�nia","RO"), Rio_de_Janeiro("Rio de Janeiro","RJ"),
	Rio_Grande_do_Norte("Rio Grande do Norte","RN"), Rio_Grande_do_Sul("Rio Grande do Sul","RS"), Santa_Catarina("Santa Catarina","SC"), 
	S�o_Paulo("S�o Paulo","SP"), Sergipe("Sergipe","SE"), Tocantins("Tocantins","TO");

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
