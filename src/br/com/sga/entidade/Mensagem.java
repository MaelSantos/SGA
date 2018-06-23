package br.com.sga.entidade;

public class Mensagem {

	private Integer id;
	
	private String prioridade; 
	private String tipo; 
	private String destinatário;
	private String descricao;
	
	private Integer id_agenda;

	public Mensagem(Integer id, String prioridade, String tipo, String destinatário, String descricao,
			Integer id_agenda) {
		this.id = id;
		this.prioridade = prioridade;
		this.tipo = tipo;
		this.destinatário = destinatário;
		this.descricao = descricao;
		this.id_agenda = id_agenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDestinatário() {
		return destinatário;
	}

	public void setDestinatário(String destinatário) {
		this.destinatário = destinatário;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	}

	
	
}
