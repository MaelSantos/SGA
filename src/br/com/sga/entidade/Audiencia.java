package br.com.sga.entidade;

import java.util.Date;

public class Audiencia {

	private Integer id;
	private String status; // possivel enum
	private String vara; // possivel enum
	private String orgao; // possivel enum
	private String tipo; // possivel enum
	private Date data_audiencia;
	
	public Audiencia(Integer id, String status, String vara, String orgao, String tipo, Date data_audiencia) {
		this.id = id;
		this.status = status;
		this.vara = vara;
		this.orgao = orgao;
		this.tipo = tipo;
		this.data_audiencia = data_audiencia;
	}
	

	public Audiencia(String status, String vara, String orgao, String tipo, Date data_audiencia) {
		super();
		this.status = status;
		this.vara = vara;
		this.orgao = orgao;
		this.tipo = tipo;
		this.data_audiencia = data_audiencia;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVara() {
		return vara;
	}

	public void setVara(String vara) {
		this.vara = vara;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData_audiencia() {
		return data_audiencia;
	}

	public void setData_audiencia(Date data_audiencia) {
		this.data_audiencia = data_audiencia;
	}
	
	
	



}
