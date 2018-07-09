package br.com.sga.entidade;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.TipoParticipacao;

public class Processo {

	private Integer id;
	
	private Contrato contrato;
	private boolean status;
	private Date data_atuacao;
	private	String numero;
	private String classe_judicial;
	private String orgao_julgador;
	private String comarca;
	private String decisao;
	private String descricao;
	private String fase; 
	private String tipo_processo;
	private TipoParticipacao tipo_participacao;
	private List<Audiencia> audiencias;
	
	public Processo(Integer id, Contrato contrato, boolean status, Date data_atuacao, String numero,
			String classe_judicial, String orgao_julgador, String comarca, String decisao, String descricao,
			String fase, String tipo_processo, TipoParticipacao tipo_participacao,List<Audiencia> audiencias) {
		this.id = id;
		this.contrato = contrato;
		this.status = status;
		this.data_atuacao = data_atuacao;
		this.numero = numero;
		this.classe_judicial = classe_judicial;
		this.orgao_julgador = orgao_julgador;
		this.comarca = comarca;
		this.decisao = decisao;
		this.descricao = descricao;
		this.fase = fase;
		this.tipo_processo = tipo_processo;
		this.tipo_participacao = tipo_participacao;
		this.audiencias = audiencias;
	}
	
	
	public Processo(Contrato contrato, boolean status, Date data_atuacao, String numero, String classe_judicial,
			String orgao_julgador, String comarca, String decisao, String descricao, String fase, String tipo_processo,
			TipoParticipacao tipo_participacao,List<Audiencia> audiencias) {
		this.contrato = contrato;
		this.status = status;
		this.data_atuacao = data_atuacao;
		this.numero = numero;
		this.classe_judicial = classe_judicial;
		this.orgao_julgador = orgao_julgador;
		this.comarca = comarca;
		this.decisao = decisao;
		this.descricao = descricao;
		this.fase = fase;
		this.tipo_processo = tipo_processo;
		this.tipo_participacao = tipo_participacao;
		this.audiencias = audiencias;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getData_atuacao() {
		return data_atuacao;
	}
	public void setData_atuacao(Date data_atuacao) {
		this.data_atuacao = data_atuacao;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getClasse_judicial() {
		return classe_judicial;
	}
	public void setClasse_judicial(String classe_judicial) {
		this.classe_judicial = classe_judicial;
	}
	public String getOrgao_julgador() {
		return orgao_julgador;
	}
	public void setOrgao_julgador(String orgao_julgador) {
		this.orgao_julgador = orgao_julgador;
	}
	public String getComarca() {
		return comarca;
	}
	public void setComarca(String comarca) {
		this.comarca = comarca;
	}
	public String getDecisao() {
		return decisao;
	}
	public void setDecisao(String decisao) {
		this.decisao = decisao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public String getTipo_processo() {
		return tipo_processo;
	}
	public void setTipo_processo(String tipo_processo) {
		this.tipo_processo = tipo_processo;
	}
	public TipoParticipacao getTipo_participacao() {
		return tipo_participacao;
	}
	public void setTipo_participacao(TipoParticipacao tipo_participacao) {
		this.tipo_participacao = tipo_participacao;
	}


	public List<Audiencia> getAudiencias() {
		return audiencias;
	}

	public void setAudiencias(List<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}
	
	
	
	
}
