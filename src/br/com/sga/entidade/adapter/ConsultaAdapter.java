package br.com.sga.entidade.adapter;

import java.util.List;

import br.com.sga.entidade.Testemunha;

public class ConsultaAdapter {
	private String descricao;
	private String indicacao;
	private String nomeFuncionario,numeroOAB;
	private List<br.com.sga.entidade.Testemunha> Testemunhas;
	
	
	
	
	public ConsultaAdapter(String descricao, String indicacao, String nomeFuncionario, String numeroOAB) {
		this.descricao = descricao;
		this.indicacao = indicacao;
		this.nomeFuncionario = nomeFuncionario;
		this.numeroOAB = numeroOAB;
	}
	
	
	public List<br.com.sga.entidade.Testemunha> getTestemunhas() {
		return Testemunhas;
	}


	public void setTestemunhas(List<br.com.sga.entidade.Testemunha> testemunhas) {
		Testemunhas = testemunhas;
	}


	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIndicacao() {
		return indicacao;
	}
	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getNumeroOAB() {
		return numeroOAB;
	}
	public void setNumeroOAB(String numeroOAB) {
		this.numeroOAB = numeroOAB;
	}
	
	
}
