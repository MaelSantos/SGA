package br.com.sga.entidade.tabelaView;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Parte {
	private final SimpleStringProperty nome;
    private final SimpleStringProperty tipo_parte;
    private final SimpleStringProperty tipo_participacao;
    private final SimpleBooleanProperty selecionado;
    
    
    public Parte(String nome, String tipo_parte,String tipo_participacao) {
    	this.selecionado = new SimpleBooleanProperty(false);
    	this.nome = new SimpleStringProperty(nome);
    	this.tipo_parte = new SimpleStringProperty(tipo_parte);
    	this.tipo_participacao = new SimpleStringProperty(tipo_participacao);
    }

	public Boolean getSelecionado() {
		return selecionado.getValue();
	}

	public String getNome() {
		return nome.get();
	}

	public String getTipo_parte() {
		return tipo_parte.get();
	}

	public String getTipo_participacao() {
		return tipo_participacao.get();
	}

	public SimpleStringProperty getNomeProperty() {
		return nome;
	}

	public SimpleStringProperty getTipo_parteProperty() {
		return tipo_parte;
	}

	public SimpleStringProperty getTipo_participacaoProperty() {
		return tipo_participacao;
	}

	public SimpleBooleanProperty getSelecionadoProperty() {
		return selecionado;
	}
	
	
    
}
