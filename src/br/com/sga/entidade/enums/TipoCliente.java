package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TipoCliente {
	
	FISICO,JURIDICO;
	
	public static TipoCliente getTipo(String tipo) {
		if(tipo.equalsIgnoreCase(TipoCliente.FISICO.toString()))
			return FISICO;
		else
			return JURIDICO;
	}
	
	public static ObservableList<TipoCliente> getValues(){
		
		ObservableList<TipoCliente> list = FXCollections.observableArrayList();
		
		list.add(FISICO);
		list.add(JURIDICO);
		
		return list;
		
	}
}
