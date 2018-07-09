package br.com.sga.entidade.enums;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum TipoPagamento {
	
	BOLETO,CHEQUE,A_VISTA;
	
	public static ObservableList<TipoPagamento> getValues(){
		
		ObservableList<TipoPagamento> list = FXCollections.observableArrayList();
		
		list.add(A_VISTA);
		list.add(BOLETO);
		list.add(CHEQUE);
		
		return list;
		
	}
}
