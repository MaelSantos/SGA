package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TipoNotificacao {
	
	LEMBRETE,FINANCEIRO,CLIENTE;
	
	public static ObservableList<TipoNotificacao> getValues(){
		
		ObservableList<TipoNotificacao> list = FXCollections.observableArrayList();
		
		list.add(LEMBRETE);
		list.add(FINANCEIRO);
		list.add(CLIENTE);
		
		return list;
		
	}
}
