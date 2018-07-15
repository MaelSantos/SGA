package br.com.sga.entidade.adapter;

import java.util.Date;

public class ContratoAdapter {

	private Integer id;
	private String nome_cliente;
	private Float valor_total;
	private Date data_contrato;
	
	public String getNome_cliente() {
		return nome_cliente;
	}
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	public Float getValor_total() {
		return valor_total;
	}
	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}
	public Date getData_contrato() {
		return data_contrato;
	}
	public void setData_contrato(Date data_contrato) {
		this.data_contrato = data_contrato;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CLIENTE: ["+nome_cliente+"] - DATA: ["+data_contrato+"] - VALOR: ["+valor_total+"]";
	}
}
