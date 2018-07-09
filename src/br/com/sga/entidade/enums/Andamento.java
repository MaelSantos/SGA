package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Andamento{
	
	VENCIDO,CONCLUIDO,PENDENTE;
	
	public static ObservableList<Andamento> getValues(){
		
		ObservableList<Andamento> list = FXCollections.observableArrayList();
		
		list.add(VENCIDO);
		list.add(CONCLUIDO);
		list.add(PENDENTE);
		
		return list;
		
	}
}
