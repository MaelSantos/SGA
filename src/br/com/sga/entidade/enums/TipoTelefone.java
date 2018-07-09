package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TipoTelefone {
	
	RESIDENCIAL,PESSOAL,COMERCIAL;
	
	public static ObservableList<TipoTelefone> getValues(){
		
		ObservableList<TipoTelefone> list = FXCollections.observableArrayList();
		
		list.add(RESIDENCIAL);
		list.add(PESSOAL);
		list.add(COMERCIAL);
		
		return list;
		
	}
}
