package br.com.sga.entidade;

import java.util.Date;

import br.com.sga.entidade.enums.Andamento;

public class Parcela {

	private Integer id;
	private Float valor;
	private Date vencimento;
	private Float juros;
	private Float multa;
	private String tipo;
	private Andamento estado;
	public Integer dia_pagamento;

	public Parcela(Integer id, Float valor, Float juros, Float multa, Date vencimento, String tipo, Andamento estado) {
		super();
		this.id = id;
		this.valor = valor;
		this.vencimento = vencimento;
		this.juros = juros;
		this.multa = multa;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Parcela(Float valor, String tipo, Integer dia_pagamento) {
		super();
		this.valor = valor;
		this.juros = 0f;
		this.multa = 0f;
		this.tipo = tipo;
		this.estado = Andamento.PENDENTE;
		this.dia_pagamento = dia_pagamento;
	}

	public Parcela(Float valor, Date vencimento, Float juros, Float multa, String tipo, Andamento estado) {
		super();
		this.valor = valor;
		this.vencimento = vencimento;
		this.juros = juros;
		this.multa = multa;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Parcela() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public float getJuros() {
		return juros;
	}

	public void setJuros(Float juros) {
		this.juros = juros;
	}

	public float getMulta() {
		return multa;
	}

	public void setMulta(Float multa) {
		this.multa = multa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Andamento getEstado() {
		return estado;
	}

	public void setEstado(Andamento estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "DIA: [" + dia_pagamento + "] - VALOR: [" + valor + "] - ANDAMENTO: [" + estado + "]";
	}

}
