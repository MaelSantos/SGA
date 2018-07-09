package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public static ObservableList<String> getValues(){

		ObservableList<String> list = FXCollections.observableArrayList();

//		Acre("Acre","AC"), Alagoas("Alagoas","AL"), Amapá("Amapá","AP"), 
		list.add(Acre.toString());
		list.add(Alagoas.toString());
		list.add(Amapá.toString());
//		Amazonas("Amazonas","AM"), Bahia("Bahia","BA"), Ceará("Ceará","CE"),
		list.add(Amazonas.toString());
		list.add(Bahia.toString());
		list.add(Ceará.toString());
//		Distrito_Federal("Distrito Federal","DF"), Espírito_Santo("Espírito Santo","ES"), Goiás("Goiás","GO"),
		list.add(Distrito_Federal.toString());
		list.add(Espírito_Santo.toString());
		list.add(Goiás.toString());
//		Maranhão("Maranhão","MA"), Mato_Grosso("Mato_Grosso","MT"), Mato_Grosso_do_Sul("Mato Grosso do Sul","MS")
		list.add(Maranhão.toString());
		list.add(Mato_Grosso.toString());
		list.add(Mato_Grosso_do_Sul.toString());
//		Minas_Gerais("Minas Gerais","MG"), Pará("Pará","PA"), Paraíba("Paraíba","PB"),
		list.add(Minas_Gerais.toString());
		list.add(Pará.toString());
		list.add(Paraíba.toString());
//		Paraná("Paraná","PR"), Pernambuco("Pernambuco","PE"), Piauí("Piauí","PI"),
		list.add(Paraná.toString());
		list.add(Pernambuco.toString());
		list.add(Piauí.toString());
//		Roraima("Roraima","RR"), Rondônia("Rondônia","RO"), Rio_de_Janeiro("Rio de Janeiro","RJ"),
		list.add(Roraima.toString());
		list.add(Rondônia.toString());
		list.add(Rio_de_Janeiro.toString());
//		Rio_Grande_do_Norte("Rio Grande do Norte","RN"), Rio_Grande_do_Sul("Rio Grande do Sul","RS"), Santa_Catarina("Santa Catarina","SC"),
		list.add(Rio_Grande_do_Norte.toString());
		list.add(Rio_Grande_do_Sul.toString());
		list.add(Santa_Catarina.toString());
//		São_Paulo("São Paulo","SP"), Sergipe("Sergipe","SE"), Tocantins("Tocantins","TO");
		list.add(São_Paulo.toString());
		list.add(Sergipe.toString());
		list.add(Tocantins.toString());

		return list;

//		ObservableList <String> estados = 
//			    FXCollections.observableArrayList (
//			    		"Acre – AC", "Alagoas – AL", "Amapá – AP", 
//			    		"Amazonas – AM", "Bahia – BA", "Ceará – CE",
//			    		"Distrito Federal – DF", "Espírito Santo – ES", "Goiás – GO; Maranhão – MA", 
//			    		"Mato Grosso – MT", "Mato Grosso do Sul – MS", "Minas Gerais – MG",
//			    		"Pará – PA", "Paraíba – PB", "Paraná – PR", "Pernambuco – PE", 
//			    		"Piauí – PI", "Roraima – RR", "Rondônia – RO", "Rio de Janeiro – RJ", 
//			    		"Rio Grande do Norte – RN", "Rio Grande do Sul – RS", 
//			    		"Santa Catarina – SC", "São Paulo – SP", "Sergipe – SE", 
//			    		"Tocantins – TO"
//			    );
//		
//		return estados;
		
	}

	@Override
	public String toString() {
		return nome+" - "+sigla;
	}
}
