package br.com.sga.entidade;

public class Modelo {

	private Integer id;
	
	private String descricao; 
	private String tipo;

	private Integer id_agenda;

	public Modelo(Integer id, String descricao, String tipo, Integer id_agenda) {
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.id_agenda = id_agenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	} 

	
	
}
