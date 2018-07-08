package br.com.sga.entidade;

import java.util.Date;

public class Receita {

	private Integer id;
	private Date data_entrada; 
	private Boolean status; // posivel enum
	private String centro_custo; // de onde foi gastado
	private String descricao; 
	private float valor;
	private String tipo_pagamento; // posivel enum
	private Date vencimento;
	
	public Receita(Integer id, Date data_entrada, Boolean status, String centro_custo, String descricao, float valor,
			String tipo_pagamento, Date vencimento) {
		super();
		this.id = id;
		this.data_entrada = data_entrada;
		this.status = status;
		this.centro_custo = centro_custo;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo_pagamento = tipo_pagamento;
		this.vencimento = vencimento;
	}
	
	public Receita(Date data_entrada, Boolean status, String centro_custo, String descricao, float valor,
			String tipo_pagamento, Date vencimento) {
		super();
		this.data_entrada = data_entrada;
		this.status = status;
		this.centro_custo = centro_custo;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo_pagamento = tipo_pagamento;
		this.vencimento = vencimento;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}

	public String getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getCentro_custo() {
		return centro_custo;
	}
	public void setCentro_custo(String centro_custo) {
		this.centro_custo = centro_custo;
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
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	
}
