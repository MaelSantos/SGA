package br.com.sga.entidade;

import java.sql.Date;

public class Despesa {

	private Integer id;
	
	private float gastos; 
	private Date data; 
	private String descrição; 
	private float valor; 
	private String tipo_gastos; 
	private float dividas;

	private Integer id_financeiro;

	public Despesa(Integer id, float gastos, Date data, String descrição, float valor, String tipo_gastos,
			float dividas, Integer id_financeiro) {
		this.id = id;
		this.gastos = gastos;
		this.data = data;
		this.descrição = descrição;
		this.valor = valor;
		this.tipo_gastos = tipo_gastos;
		this.dividas = dividas;
		this.id_financeiro = id_financeiro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getGastos() {
		return gastos;
	}

	public void setGastos(float gastos) {
		this.gastos = gastos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public float getDividas() {
		return dividas;
	}

	public void setDividas(float dividas) {
		this.dividas = dividas;
	}

	public Integer getId_financeiro() {
		return id_financeiro;
	}

	public void setId_financeiro(Integer id_financeiro) {
		this.id_financeiro = id_financeiro;
	} 

	
	
}
