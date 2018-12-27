package br.com.sga.entidade.adapter;

import java.util.Date;

import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.TipoNotificacao;

public class NotificacaoAdapter {

	private Integer id;
	private TipoNotificacao tipoNotificacao;
	private Andamento estado;
	private Date aviso_data;
	private String descricao;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAviso_data() {
		return aviso_data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setAviso_data(Date aviso_data) {
		this.aviso_data = aviso_data;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
