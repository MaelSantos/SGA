package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public static ObservableList<String> getValues(){

		ObservableList<String> list = FXCollections.observableArrayList();

//		Acre("Acre","AC"), Alagoas("Alagoas","AL"), Amap�("Amap�","AP"), 
		list.add(Acre.toString());
		list.add(Alagoas.toString());
		list.add(Amap�.toString());
//		Amazonas("Amazonas","AM"), Bahia("Bahia","BA"), Cear�("Cear�","CE"),
		list.add(Amazonas.toString());
		list.add(Bahia.toString());
		list.add(Cear�.toString());
//		Distrito_Federal("Distrito Federal","DF"), Esp�rito_Santo("Esp�rito Santo","ES"), Goi�s("Goi�s","GO"),
		list.add(Distrito_Federal.toString());
		list.add(Esp�rito_Santo.toString());
		list.add(Goi�s.toString());
//		Maranh�o("Maranh�o","MA"), Mato_Grosso("Mato_Grosso","MT"), Mato_Grosso_do_Sul("Mato Grosso do Sul","MS")
		list.add(Maranh�o.toString());
		list.add(Mato_Grosso.toString());
		list.add(Mato_Grosso_do_Sul.toString());
//		Minas_Gerais("Minas Gerais","MG"), Par�("Par�","PA"), Para�ba("Para�ba","PB"),
		list.add(Minas_Gerais.toString());
		list.add(Par�.toString());
		list.add(Para�ba.toString());
//		Paran�("Paran�","PR"), Pernambuco("Pernambuco","PE"), Piau�("Piau�","PI"),
		list.add(Paran�.toString());
		list.add(Pernambuco.toString());
		list.add(Piau�.toString());
//		Roraima("Roraima","RR"), Rond�nia("Rond�nia","RO"), Rio_de_Janeiro("Rio de Janeiro","RJ"),
		list.add(Roraima.toString());
		list.add(Rond�nia.toString());
		list.add(Rio_de_Janeiro.toString());
//		Rio_Grande_do_Norte("Rio Grande do Norte","RN"), Rio_Grande_do_Sul("Rio Grande do Sul","RS"), Santa_Catarina("Santa Catarina","SC"),
		list.add(Rio_Grande_do_Norte.toString());
		list.add(Rio_Grande_do_Sul.toString());
		list.add(Santa_Catarina.toString());
//		S�o_Paulo("S�o Paulo","SP"), Sergipe("Sergipe","SE"), Tocantins("Tocantins","TO");
		list.add(S�o_Paulo.toString());
		list.add(Sergipe.toString());
		list.add(Tocantins.toString());

		return list;

//		ObservableList <String> estados = 
//			    FXCollections.observableArrayList (
//			    		"Acre � AC", "Alagoas � AL", "Amap� � AP", 
//			    		"Amazonas � AM", "Bahia � BA", "Cear� � CE",
//			    		"Distrito Federal � DF", "Esp�rito Santo � ES", "Goi�s � GO; Maranh�o � MA", 
//			    		"Mato Grosso � MT", "Mato Grosso do Sul � MS", "Minas Gerais � MG",
//			    		"Par� � PA", "Para�ba � PB", "Paran� � PR", "Pernambuco � PE", 
//			    		"Piau� � PI", "Roraima � RR", "Rond�nia � RO", "Rio de Janeiro � RJ", 
//			    		"Rio Grande do Norte � RN", "Rio Grande do Sul � RS", 
//			    		"Santa Catarina � SC", "S�o Paulo � SP", "Sergipe � SE", 
//			    		"Tocantins � TO"
//			    );
//		
//		return estados;
		
	}

	@Override
	public String toString() {
		return nome+" - "+sigla;
	}
}
