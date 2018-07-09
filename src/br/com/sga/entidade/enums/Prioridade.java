package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Prioridade {
	
	BAIXA,MEDIA,ALTA;
	
	public static ObservableList<Prioridade> getValues(){
		
		ObservableList<Prioridade> list = FXCollections.observableArrayList();
		
		list.add(BAIXA);
		list.add(MEDIA);
		list.add(ALTA);
		
		return list;
		
	}
}
