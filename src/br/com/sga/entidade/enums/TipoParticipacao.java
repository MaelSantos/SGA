package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TipoParticipacao {
	
	EXEQUENTE,EXECUTADO;

	public static ObservableList<TipoParticipacao> getValues(){
		
		ObservableList<TipoParticipacao> list = FXCollections.observableArrayList();
		
		list.add(EXECUTADO);
		list.add(EXEQUENTE);
		
		return list;
		
	}
	
}
