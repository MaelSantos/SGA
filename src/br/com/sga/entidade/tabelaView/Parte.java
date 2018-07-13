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
    
    public Parte(Boolean selecionado ,String nome, String tipo_parte,String tipo_participacao) {
    	this.selecionado = new SimpleBooleanProperty(selecionado);
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

	
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public void setTipoParte(String tipo_parte) {
		this.tipo_parte.set(tipo_parte);
	}
	
	public void setTipoParticipacao(String tipo_participacao) {
		this.tipo_participacao.set(tipo_participacao);
	}
	
	public void setSelecionado(Boolean selecionado ) {
		this.selecionado.set(selecionado);
	}
	
	
    
}
