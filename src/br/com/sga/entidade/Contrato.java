package br.com.sga.entidade;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.TipoPagamento;

public class Contrato {

	private Integer id;
	
	private String objeto;
	private Float valor_total, taxa_juros, taxa_multa;
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
	
	public Contrato(Integer id, String objeto, float valor_total, TipoPagamento tipo_pagamento, Date data_contrato,
			Area area, String dados_banco) {
		super();
		this.id = id;
		this.objeto = objeto;
		this.valor_total = valor_total;
		this.tipo_pagamento = tipo_pagamento;
		this.data_contrato = data_contrato;
		this.area = area;
		this.dados_banco = dados_banco;
	}
	

	public Contrato(String objeto, float valor_total, TipoPagamento tipo_pagamento, Date data_contrato, Area area,
			String dados_banco, List<Parte> partes, List<Parcela> parcelas,Float taxa_multa,Float taxa_juros,Consulta consulta,Financeiro financeiro) {
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
		this.taxa_juros = taxa_juros;
		this.taxa_multa = taxa_multa;
	}


	public Contrato() {
	}

	
	public String getInformacaoPartes()
	{
		String partes = "";
		
		for(int i = 0; i < this.partes.size(); i++)
		{
			Parte parte = this.partes.get(i);
			if(i < this.partes.size()-1)
				partes += parte.getNome()+", ";
			else
				partes += parte.getNome();
		}
		
		return partes;
	}
	
	public Float getTaxa_juros() {
		return taxa_juros;
	}

	public void setTaxa_juros(Float taxa_juros) {
		this.taxa_juros = taxa_juros;
	}

	public Float getTaxa_multa() {
		return taxa_multa;
	}

	public void setTaxa_multa(Float taxa_multa) {
		this.taxa_multa = taxa_multa;
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
	
	
	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}
	
	@Override
	public String toString() {
		return "AREA: ["+area+"] - DATA: ["+data_contrato+"] - OBJETO: ["+objeto+"]";
	}
}
