package br.com.sga.entidade.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.entidade.enums.Area;

public class ConsultaAdapter {
   	private Integer id;
   	private Area area;
   	private Date data;
   	private Float valor_honorario;
   	private String nome_cliente;
	
   	public ConsultaAdapter(Integer id, Area area, Date data, Float valor_honorario,String nome_cliente) {
		super();
		this.id = id;
		this.area = area;
		this.data = data;
		this.valor_honorario = valor_honorario;
		this.nome_cliente = nome_cliente;
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
	public String getNome_cliente() {
		return nome_cliente;
	}
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
    
	@Override
	public String toString() {
		return "Nome do cliente: "+nome_cliente+ " | Consulta - Data: "+new SimpleDateFormat("dd/MM/yyyy").format(data)+" Area: "+area+" valor: "+valor_honorario; 
	}
	
}
