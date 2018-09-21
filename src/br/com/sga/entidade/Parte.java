package br.com.sga.entidade;

import br.com.sga.entidade.enums.TipoParte;

public class Parte {
	
	private Integer id; //	id SERIAL PRIMARY KEY,
	private TipoParte tipo_parte; //tipo_parte VARCHAR(255) NOT NULL,
	private String nome; //nome VARCHAR(255)

	private Contrato contrato; //contrato_id INTEGER REFERENCES CONTRATO(id),	
	
	public Parte(Integer id, TipoParte tipo_parte, String nome) {
		this.id = id;
		this.tipo_parte = tipo_parte;
		this.nome = nome;
	}
	
	public Parte(TipoParte tipo_parte,String nome) {
		this.tipo_parte = tipo_parte;
		this.nome = nome;
	}

	public Parte() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoParte getTipo_parte() {
		return tipo_parte;
	}
	public void setTipo_parte(TipoParte tipo_parte) {
		this.tipo_parte = tipo_parte;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Tipo: ["+tipo_parte+"] Nome: ["+nome+"]";
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
}
