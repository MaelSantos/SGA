package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	public static ObservableList<Sexo> getValues(){
		
		ObservableList<Sexo> list = FXCollections.observableArrayList();
		
		list.add(MASCULINO);
		list.add(FEMININO);
		
		return list;
		
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
