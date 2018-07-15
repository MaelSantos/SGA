package br.com.sga.entidade;

import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;

public class Parte {
	private Integer id;
	private TipoParte tipo_parte;
	private TipoParticipacao tipo_participacao;
	private String nome;
	
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
	
}
