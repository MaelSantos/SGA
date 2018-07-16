package br.com.sga.entidade;

import java.util.Date;

import br.com.sga.entidade.enums.StatusAudiencia;
import br.com.sga.entidade.enums.TipoAudiencia;

public class Audiencia {

	private Integer id; //id SERIAL PRIMARY KEY,
	private StatusAudiencia status; //status VARCHAR(255), possivel enum
	private String vara; //vara VARCHAR(255) NOT NULL, possivel enum
	private String orgao; //orgao VARCHAR(255) NOT NULL possivel enum
	private TipoAudiencia tipo; //tipo VARCHAR(255) NOT NULL,  possivel enum
	private Date data_audiencia; //data_audiencia DATE,
	
	private Processo processo; //processo_id  INTEGER REFERENCES PROCESSO(id),
	
	 public Audiencia() {
		
	}
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusAudiencia getStatus() {
		return status;
	}

	public void setStatus(StatusAudiencia status) {
		this.status = status;
	}

	public String getVara() {
		return vara;
	}

	public void setVara(String vara) {
		this.vara = vara;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public TipoAudiencia getTipo() {
		return tipo;
	}

	public void setTipo(TipoAudiencia tipoAudiencia) {
		this.tipo = tipoAudiencia;
	}

	public Date getData_audiencia() {
		return data_audiencia;
	}

	public void setData_audiencia(Date data_audiencia) {
		this.data_audiencia = data_audiencia;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	
	



}
