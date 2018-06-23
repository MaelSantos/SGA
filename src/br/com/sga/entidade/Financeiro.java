package br.com.sga.entidade;

import java.sql.Date;

public class Financeiro {

	private Integer id;
  
	private String status;
	private String tipo_pagamento;
	private Date vencimento;	
	private float total_despesas; 
	private float total_lucro; 
	private float caixa;
	private Date data_inicio; 
	private Date data_fim;
	
	private Integer id_agenda;

	public Financeiro(Integer id, float total_despesas, float total_lucro, float caixa, Date data_inicio, Date data_fim,
			Integer id_agenda) {
		this.id = id;
		this.total_despesas = total_despesas;
		this.total_lucro = total_lucro;
		this.caixa = caixa;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
		this.id_agenda = id_agenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getTotal_despesas() {
		return total_despesas;
	}

	public void setTotal_despesas(float total_despesas) {
		this.total_despesas = total_despesas;
	}

	public float getTotal_lucro() {
		return total_lucro;
	}

	public void setTotal_lucro(float total_lucro) {
		this.total_lucro = total_lucro;
	}

	public float getCaixa() {
		return caixa;
	}

	public void setCaixa(float caixa) {
		this.caixa = caixa;
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

	public Integer getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	} 
	
}
