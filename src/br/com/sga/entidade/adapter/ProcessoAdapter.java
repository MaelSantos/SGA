package br.com.sga.entidade.adapter;

import java.util.Date;

import br.com.sga.entidade.Processo;

public class ProcessoAdapter {
  
//	p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase
	private Integer id;
	private Date data_atuacao; //data_atuacao  DATE NOT NULL,
	private	String numero; //numero varchar(255) UNIQUE NOT NULL, 
	private String comarca; //comarca VARCHAR(255) NOT NULL,
	private String decisao; //decisao  VARCHAR(255), 
	private String partes; //fase  VARCHAR(255) NOT NULL, 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		
	public String getPartes() {
		return partes;
	}
	public void setPartes(String partes) {
		this.partes = partes;
	}

	@Override
	public String toString() {
		return "NUMERO: ["+numero+"] DATA: ["+data_atuacao+"] COMARCA: ["+comarca+"]";
	}

	public static ProcessoAdapter ToAdapter(Processo processo)
	{
		
		ProcessoAdapter adapter = new ProcessoAdapter();
		
		adapter.setId(processo.getId());
		adapter.setComarca(processo.getComarca());
		adapter.setData_atuacao(processo.getData_atuacao());
		adapter.setDecisao(processo.getDecisao());
		adapter.setPartes(processo.getPartes().toString());
		adapter.setNumero(processo.getNumero());
		
		return adapter;
	}
	
}
