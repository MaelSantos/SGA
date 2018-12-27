package br.com.sga.entidade.adapter;

import java.util.Date;

public class ContaAdapter {
	private Float valorTotal;
	private Date mesAno;
	public ContaAdapter(Float valorTotal, Date mesAno) {
		super();
		this.valorTotal = valorTotal;
		this.mesAno = mesAno;
	}
	public Float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getMesAno() {
		return mesAno;
	}
	public void setMesAno(Date mesAno) {
		this.mesAno = mesAno;
	}
	@Override
	public String toString() {
		return valorTotal +""+ mesAno; 
	}
	
	
	
}
