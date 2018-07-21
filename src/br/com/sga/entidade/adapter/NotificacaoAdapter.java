package br.com.sga.entidade.adapter;

import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.TipoNotificacao;

public class NotificacaoAdapter {

	private Integer id;
	private TipoNotificacao tipoNotificacao;
	private Andamento estado;
	
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
	
}
