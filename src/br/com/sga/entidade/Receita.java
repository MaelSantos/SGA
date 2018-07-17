package br.com.sga.entidade;

import java.util.Date;

public class Receita {

	private Integer id; //id SERIAL PRIMARY KEY,
	private Date data_entrada; //data_entrada DATE NOT NULL,
	private String centro_custo; //centro_custo VARCHAR(255), de onde foi gastado
	private String descricao; //descricao VARCHAR(255),
	private Float valor; //valor  FLOAT NOT NULL, 
	private Boolean status; //status VARCHAR(255), possivel enum
	private String tipo_pagamento; //tipo_pagamento VARCHAR(50) NOT NULL,  posivel enum
	private Date vencimento;// vencimento DATE NOT NULL,
	
//	financeiro_id INTEGER REFERENCES FINANCEIRO(id)
	
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
