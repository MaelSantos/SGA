package br.com.sga.entidade;

import java.sql.Date;

public class Parcela {
   
	private Integer id; 
	private float valor; 
	private Date vencimento; 
	private float juros; 
	private float multa; 
	private String tipo; 
	private String estado;

	private Integer id_contrato;
	private Integer id_agenda;

	public Parcela(Integer id, float valor, Date vencimento, float juros, float multa, String tipo, String estado,
			Integer id_contrato,Integer id_agenda) {
		this.id = id;
		this.valor = valor;
		this.vencimento = vencimento;
		this.juros = juros;
		this.multa = multa;
		this.tipo = tipo;
		this.estado = estado;
		this.id_contrato = id_contrato;
		this.id_agenda = id_agenda;
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

	public void setValor(float valor) {
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

	public void setJuros(float juros) {
		this.juros = juros;
	}

	public float getMulta() {
		return multa;
	}

	public void setMulta(float multa) {
		this.multa = multa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
	}

	public Integer getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	}
	
	
}
