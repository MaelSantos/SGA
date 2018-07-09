package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Area {
	
	CIVIL,TRABALHISTA;
	
	public static ObservableList<Area> getValues(){
		
		ObservableList<Area> list = FXCollections.observableArrayList();
		
		list.add(CIVIL);
		list.add(TRABALHISTA);
		
		return list;
		
	}
}
