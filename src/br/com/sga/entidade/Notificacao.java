package br.com.sga.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.Prioridade;

public class Notificacao {
	private String tipo,descricao,estado;
	private Prioridade prioridade;
	private Date aviso_data;
	private Integer id,intervalo_repeticao;
	private List<Funcionario> funcionarios;
	
	/**
	 * Uma notificação esta para 1 ou mais usuarios, no momento de sua criação deve ser definido quem poderar ver 
	 * @param tipo
	 * @param prioridade
	 * @param descricao
	 * @param estado
	 * @param aviso_data
	 * @param id
	 * @param intervalo_repeticao
	 * @param funcionario
	 */
	public Notificacao(String tipo, Prioridade prioridade, String descricao, String estado, Date aviso_data, Integer id,
			Integer intervalo_repeticao,List<Funcionario> funcionarios) {
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
		this.id = id;
		this.intervalo_repeticao = intervalo_repeticao;
		this.funcionarios = funcionarios;
	}
	public Notificacao(String tipo, Prioridade prioridade, String descricao, String estado, Date aviso_data,
			Integer intervalo_repeticao,List<Funcionario> funcionarios) {
		this.tipo = tipo;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
		this.intervalo_repeticao = intervalo_repeticao;
		this.funcionarios = funcionarios;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
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
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
}
