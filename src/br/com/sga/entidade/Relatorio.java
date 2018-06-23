package br.com.sga.entidade;

import java.sql.Date;

public class Relatorio {

	private Integer id; 
	
	private String descricao; 
	private Date data_inicio; 
	private Date data_fim; 
	private String tipo;
	
	private Integer id_agenda;
	
	public Relatorio(Integer id, String descricao, Date data_inicio, Date data_fim, String tipo, Integer id_agenda) {
		this.id = id;
		this.descricao = descricao;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
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

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
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
