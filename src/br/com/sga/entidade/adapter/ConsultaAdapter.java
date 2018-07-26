package br.com.sga.entidade.adapter;

import java.util.Date;

import br.com.sga.entidade.enums.Area;

public class ConsultaAdapter {
   	private Integer id;
   	private Area area;
   	private Date data;
   	private Float valor_honorario;
	public ConsultaAdapter(Integer id, Area area, Date data, Float valor_honorario) {
		super();
		this.id = id;
		this.area = area;
		this.valor_honorario = valor_honorario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Float getValor_honorario() {
		return valor_honorario;
	}
	public void setValor_honorario(Float valor_honorario) {
		this.valor_honorario = valor_honorario;
	}
       	
}
