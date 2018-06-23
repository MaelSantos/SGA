package br.com.sga.entidade;

import java.sql.Date;

public class Contrato {

	private Integer id;
	
	private String objeto;
	private String descricao; 
	private float valor_total;
	private Integer id_processo;
	private Integer id_cliente; 
	private String tipo_pagamento;
	private Date data_contrato;
	private String area;
	private String dados_banco;

	public Contrato(Integer id, String objeto, String descricao, float valor_total, Integer id_processo,
			Integer id_cliente, String tipo_pagamento, Date data_contrato, String area, String dados_banco) {
		this.id = id;
		this.objeto = objeto;
		this.descricao = descricao;
		this.valor_total = valor_total;
		this.id_processo = id_processo;
		this.id_cliente = id_cliente;
		this.tipo_pagamento = tipo_pagamento;
		this.data_contrato = data_contrato;
		this.area = area;
		this.dados_banco = dados_banco;
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

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
	}

	public Integer getId_processo() {
		return id_processo;
	}

	public void setId_processo(Integer id_processo) {
		this.id_processo = id_processo;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public Date getData_contrato() {
		return data_contrato;
	}

	public void setData_contrato(Date data_contrato) {
		this.data_contrato = data_contrato;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDados_banco() {
		return dados_banco;
	}

	public void setDados_banco(String dados_banco) {
		this.dados_banco = dados_banco;
	}
}
