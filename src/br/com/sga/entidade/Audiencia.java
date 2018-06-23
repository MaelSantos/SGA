package br.com.sga.entidade;

import java.sql.Date;

public class Audiencia {

	private Integer id;
	
	private String status;
	private String vara; 
	private String orgao;
	private String tipo; 
	private Date data_audiencia;
	private String local; 
	private String juiz_respons�vel;

	private Integer id_processo; 
	private Integer id_agenda;

	public Audiencia(Integer id, String status, String vara, String orgao, String tipo, Date data_audiencia,
			String local, String juiz_respons�vel, Integer id_processo, Integer id_agenda) {
		this.id = id;
		this.status = status;
		this.vara = vara;
		this.orgao = orgao;
		this.tipo = tipo;
		this.data_audiencia = data_audiencia;
		this.local = local;
		this.juiz_respons�vel = juiz_respons�vel;
		this.id_processo = id_processo;
		this.id_agenda = id_agenda;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public String getJuiz_respons�vel() {
		return juiz_respons�vel;
	}


	public void setJuiz_respons�vel(String juiz_respons�vel) {
		this.juiz_respons�vel = juiz_respons�vel;
	}


	public Integer getId_processo() {
		return id_processo;
	}


	public void setId_processo(Integer id_processo) {
		this.id_processo = id_processo;
	}


	public Integer getId_agenda() {
		return id_agenda;
	}


	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	} 


}
