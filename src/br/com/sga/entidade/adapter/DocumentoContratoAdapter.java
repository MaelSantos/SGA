package br.com.sga.entidade.adapter;

import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.enums.Instancia;

public class DocumentoContratoAdapter {

	private Contrato contrato;
	private String numeroProcesso;
	private Instancia tipo;
	private String comarca;

	public DocumentoContratoAdapter() {
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public Instancia getTipo() {
		return tipo;
	}

	public void setTipo(Instancia tipo) {
		this.tipo = tipo;
	}

	public String getComarca() {
		return comarca;
	}

	public void setComarca(String comarca) {
		this.comarca = comarca;
	}
}
