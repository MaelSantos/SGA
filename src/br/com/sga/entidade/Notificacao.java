package br.com.sga.entidade;

import java.util.ArrayList;
import java.util.Date;

public class Notificacao {
	private String tipo,prioridade,descricao,estado;
	private Date aviso_data;
	private Integer id,intervalo_repeticao;
	private ArrayList<Funcionario> funcionarios;
	
	
	public Notificacao(String tipo, String prioridade, String descricao, String estado, Date aviso_data, Integer id,
			Integer intervalo_repeticao) {
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
		this.id = id;
		this.intervalo_repeticao = intervalo_repeticao;
		this.funcionarios = new ArrayList<>();
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getAviso_data() {
		return aviso_data;
	}
	public void setAviso_data(Date aviso_data) {
		this.aviso_data = aviso_data;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntervalo_repeticao() {
		return intervalo_repeticao;
	}
	public void setIntervalo_repeticao(Integer intervalo_repeticao) {
		this.intervalo_repeticao = intervalo_repeticao;
	}
	
	
}
