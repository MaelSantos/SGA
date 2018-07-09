package br.com.sga.entidade;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.TipoPagamento;

public class Contrato {

	private Integer id;
	
	private String objeto;
	private float valor_total;
	private TipoPagamento tipo_pagamento;
	private Date data_contrato;
	private Area area;
	private String dados_banco;
	private List<Parte> partes;
	private List<Parcela> parcelas;
	private Consulta consulta;
	private Financeiro financeiro;
	

	public Contrato(Integer id, String objeto, float valor_total, TipoPagamento tipo_pagamento, Date data_contrato,
			Area area, String dados_banco, List<Parte> partes, List<Parcela> parcelas, Consulta consulta,Financeiro financeiro) {
		super();
		this.id = id;
		this.objeto = objeto;
		this.valor_total = valor_total;
		this.tipo_pagamento = tipo_pagamento;
		this.data_contrato = data_contrato;
		this.area = area;
		this.dados_banco = dados_banco;
		this.partes = partes;
		this.parcelas = parcelas;
		this.consulta = consulta;
		this.financeiro = financeiro;
	}
	

	public Contrato(String objeto, float valor_total, TipoPagamento tipo_pagamento, Date data_contrato, Area area,
			String dados_banco, List<Parte> partes, List<Parcela> parcelas, Consulta consulta,Financeiro financeiro) {
		super();
		this.objeto = objeto;
		this.valor_total = valor_total;
		this.tipo_pagamento = tipo_pagamento;
		this.data_contrato = data_contrato;
		this.area = area;
		this.dados_banco = dados_banco;
		this.partes = partes;
		this.parcelas = parcelas;
		this.consulta = consulta;
		this.financeiro = financeiro;
	}




	public Contrato() {
	}


	public List<Parcela> getParcelas() {
		return parcelas;
	}
	
	


	public Financeiro getFinanceiro() {
		return financeiro;
	}


	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}


	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public TipoPagamento getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(TipoPagamento tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public Date getData_contrato() {
		return data_contrato;
	}

	public void setData_contrato(Date data_contrato) {
		this.data_contrato = data_contrato;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getDados_banco() {
		return dados_banco;
	}

	public void setDados_banco(String dados_banco) {
		this.dados_banco = dados_banco;
	}

	public List<Parte> getPartes() {
		return partes;
	}

	public void setPartes(List<Parte> partes) {
		this.partes = partes;
	}
	
	
	

}
