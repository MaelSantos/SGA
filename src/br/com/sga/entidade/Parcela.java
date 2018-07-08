package br.com.sga.entidade;

import java.util.Date;

public class Parcela {
   
	private Integer id; 
	private float valor; 
	private Date vencimento; 
	private float juros; 
	private float multa; 
	private String tipo; 
	private String estado;
	public Parcela(Integer id, float valor, Date vencimento, float juros, float multa, String tipo, String estado) {
		super();
		this.id = id;
		this.valor = valor;
		this.vencimento = vencimento;
		this.juros = juros;
		this.multa = multa;
		this.tipo = tipo;
		this.estado = estado;
	}
	public Parcela(float valor, Date vencimento, float juros, float multa, String tipo, String estado) {
		super();
		this.valor = valor;
		this.vencimento = vencimento;
		this.juros = juros;
		this.multa = multa;
		this.tipo = tipo;
		this.estado = estado;
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
	
	

	
}
