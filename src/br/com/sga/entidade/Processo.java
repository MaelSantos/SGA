package br.com.sga.entidade;

import java.sql.Date;
import java.sql.Time;

public class Processo {

	private Integer id;
	
	private boolean status;
	private Time tempo; 
	private boolean disponível; 
	private Integer numero;
	private String decisão; 
	private String fase; 
	private String tipo_processo;
	private String tipo_participacao;
	private String comarca;
	private Date data_atuacao;
	private String classe_judicial;
	private String orgao_julgador;
	
	private Integer id_agenda;

	public Processo(Integer id, boolean status, Time tempo, boolean disponível, Integer numero, String decisão,
			String fase, String tipo_processo, String tipo_participacao, String comarca, Date data_atuacao,
			String classe_judicial, String orgao_julgador, Integer id_agenda) {
		this.id = id;
		this.status = status;
		this.tempo = tempo;
		this.disponível = disponível;
		this.numero = numero;
		this.decisão = decisão;
		this.fase = fase;
		this.tipo_processo = tipo_processo;
		this.tipo_participacao = tipo_participacao;
		this.comarca = comarca;
		this.data_atuacao = data_atuacao;
		this.classe_judicial = classe_judicial;
		this.orgao_julgador = orgao_julgador;
		this.id_agenda = id_agenda;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Time getTempo() {
		return tempo;
	}
	public void setTempo(Time tempo) {
		this.tempo = tempo;
	}
	public boolean isDisponível() {
		return disponível;
	}
	public void setDisponível(boolean disponível) {
		this.disponível = disponível;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getDecisão() {
		return decisão;
	}
	public void setDecisão(String decisão) {
		this.decisão = decisão;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	
	public Integer getId_agenda() {
		return id_agenda;
	}
	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	}

	public String getTipo_processo() {
		return tipo_processo;
	}

	public void setTipo_processo(String tipo_processo) {
		this.tipo_processo = tipo_processo;
	}

	public String getTipo_participacao() {
		return tipo_participacao;
	}

	public void setTipo_participacao(String tipo_participacao) {
		this.tipo_participacao = tipo_participacao;
	}

	public String getComarca() {
		return comarca;
	}

	public void setComarca(String comarca) {
		this.comarca = comarca;
	}

	public Date getData_atuacao() {
		return data_atuacao;
	}

	public void setData_atuacao(Date data_atuacao) {
		this.data_atuacao = data_atuacao;
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

	
	
}
