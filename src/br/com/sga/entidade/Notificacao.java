package br.com.sga.entidade;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.TipoNotificacao;

public class Notificacao implements Comparable<Notificacao>{
	
	private Integer id;
	private TipoNotificacao tipoNotificacao;
	private Andamento estado;
	private String descricao;
	private Prioridade prioridade;
	private Date aviso_data;
	private List<Funcionario> funcionarios;
	
	/**
	 * Uma notificação esta para 1 ou mais usuarios, no momento de sua criação deve ser definido quem poderar ver 
	 * @param tipo
	 * @param prioridade
	 * @param descricao
	 * @param estado
	 * @param aviso_data
	 * @param id
	 * @param funcionario
	 */
	
	public Notificacao(TipoNotificacao tipoNotificacao, Prioridade prioridade, String descricao, Andamento estado, Date aviso_data, Integer id,List<Funcionario> funcionarios) {
		this.tipoNotificacao = tipoNotificacao;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
		this.id = id;
		this.funcionarios = funcionarios;
	}
	public Notificacao(TipoNotificacao tipoNotificacao, Prioridade prioridade, String descricao, Andamento estado, Date aviso_data, Integer id) {
		this.tipoNotificacao = tipoNotificacao;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
		this.id = id;
	}
	public Notificacao(TipoNotificacao tipoNotificacao, Prioridade prioridade, String descricao, Andamento estado, Date aviso_data,
			List<Funcionario> funcionarios) {
		this.tipoNotificacao = tipoNotificacao;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
		this.funcionarios = funcionarios;
	}
	
	public Notificacao(TipoNotificacao tipoNotificacao, Prioridade prioridade, String descricao, Andamento estado, Date aviso_data) {
		this.tipoNotificacao = tipoNotificacao;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.estado = estado;
		this.aviso_data = aviso_data;
	}
	
	
	public Notificacao() {
		// TODO Stub de construtor gerado automaticamente
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
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public TipoNotificacao getTipoNotificacao() {
		return tipoNotificacao;
	}
	public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}
	public Andamento getEstado() {
		return estado;
	}
	public void setEstado(Andamento estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Hora : "+ this.getAviso_data().toString() + " - "+ getDescricao() + " - "+"Prioridade : "+ this.getPrioridade().toString()+ " Estado : "+ this.getEstado().toString();
	}
	@Override
	public int compareTo(Notificacao arg0) {
		if(this.getAviso_data().getTime() > arg0.getAviso_data().getTime())
			return 1;
		else if(this.getAviso_data().getTime() < arg0.getAviso_data().getTime())
			return -1;
		else 
			return 0;
	}
	
}
