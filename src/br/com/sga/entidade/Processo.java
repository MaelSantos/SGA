package br.com.sga.entidade;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.enums.TipoProcesso;

public class Processo {

	private Integer id; //id SERIAL PRIMARY KEY,
	
	private Contrato contrato; //contrato_id INTEGER REFERENCES CONTRATO(id),
	private boolean status; //status  BOOLEAN,  
	private Date data_atuacao; //data_atuacao  DATE NOT NULL,
	private	String numero; //numero varchar(255) UNIQUE NOT NULL, 
	private String classe_judicial; //classe_judicial VARCHAR(255) NOT NULL,
	private String orgao_julgador; //orgao_julgador VARCHAR(255) NOT NULL,
	private String comarca; //comarca VARCHAR(255) NOT NULL,
	private String decisao; //decisao  VARCHAR(255), 
	private String descricao; //descricao  VARCHAR(255), 
	private String fase; //fase  VARCHAR(255) NOT NULL, 
	private TipoProcesso tipo_processo; //tipo_processo  VARCHAR(255) NOT NULL,
	private TipoParticipacao tipo_participacao; //tipo_participacao  VARCHAR(255)
	private List<Audiencia> audiencias;
	
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
	public TipoProcesso getTipo_processo() {
		return tipo_processo;
	}
	public void setTipo_processo(TipoProcesso tipo_processo) {
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
	
	@Override
	public String toString() {
		return "NUMERO: ["+numero+"] DATA: ["+data_atuacao+"] TIPO: ["+tipo_processo+"]";
	}
	
	
}
