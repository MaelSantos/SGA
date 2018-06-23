package br.com.sga.entidade;

import java.sql.Date;

public class Lucro {

	private Integer id;

	private float ganhos; 
	private Date data_entrada; 
	private String descricao; 
	private float valor; 
	private String tipo_pagamento; 
	private float contas_receber;
	private Date vencimento;
	private boolean status;

	private Integer id_financeiro;

	public Lucro(Integer id, float ganhos, Date data_entrada, String descricao, float valor, String tipo_pagamento,
			float contas_receber, Date vencimento, boolean status, Integer id_financeiro) {
		this.id = id;
		this.ganhos = ganhos;
		this.data_entrada = data_entrada;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo_pagamento = tipo_pagamento;
		this.contas_receber = contas_receber;
		this.vencimento = vencimento;
		this.status = status;
		this.id_financeiro = id_financeiro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getGanhos() {
		return ganhos;
	}

	public void setGanhos(float ganhos) {
		this.ganhos = ganhos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getContas_receber() {
		return contas_receber;
	}

	public void setContas_receber(float contas_receber) {
		this.contas_receber = contas_receber;
	}

	public Integer getId_financeiro() {
		return id_financeiro;
	}

	public void setId_financeiro(Integer id_financeiro) {
		this.id_financeiro = id_financeiro;
	} 


}
