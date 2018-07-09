package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TipoParte {
	ATIVO,PASSIVO;

	public static ObservableList<TipoParte> getValues(){
		
		ObservableList<TipoParte> list = FXCollections.observableArrayList();
		
		list.add(ATIVO);
		list.add(PASSIVO);
		
		return list;
		
	}
}
