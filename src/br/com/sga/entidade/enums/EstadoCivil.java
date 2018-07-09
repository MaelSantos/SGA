package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum EstadoCivil {

	CASADO, SOLTEIRO, VIUVO, DIVORCIADO;
	
	public static ObservableList<EstadoCivil> getValues(){
		
		ObservableList<EstadoCivil> list = FXCollections.observableArrayList();
		
		list.add(SOLTEIRO);
		list.add(CASADO);
		list.add(DIVORCIADO);
		list.add(VIUVO);
		
		return list;
		
	}
	
}
