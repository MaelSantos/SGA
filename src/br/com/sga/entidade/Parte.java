package br.com.sga.entidade;

import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;

public class Parte {
	
	private Integer id; //	id SERIAL PRIMARY KEY,
	private TipoParte tipo_parte; //tipo_parte VARCHAR(255) NOT NULL,
	private TipoParticipacao tipo_participacao; //tipo_participacao VARCHAR(255) NOT NULL,
	private String nome; //nome VARCHAR(255)
	private String situacao; //situacao VARCHAR(255)

	private Contrato contrato; //contrato_id INTEGER REFERENCES CONTRATO(id),	
	
	public Parte(Integer id, TipoParte tipo_parte, TipoParticipacao tipo_participacao, String nome) {
		this.id = id;
		this.tipo_parte = tipo_parte;
		this.tipo_participacao = tipo_participacao;
		this.nome = nome;
	}
	
	public Parte(TipoParte tipo_parte, TipoParticipacao tipo_participacao, String nome) {
		this.tipo_parte = tipo_parte;
		this.tipo_participacao = tipo_participacao;
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
	public TipoParticipacao getTipo_participacao() {
		return tipo_participacao;
	}
	public void setTipo_participacao(TipoParticipacao tipo_participacao) {
		this.tipo_participacao = tipo_participacao;
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

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
}
