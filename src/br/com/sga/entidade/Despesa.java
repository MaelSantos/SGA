package br.com.sga.entidade;

import java.util.Date;

public class Despesa {

	private Integer id; //id SERIAL PRIMARY KEY,
	private Date data_retirada; //data_retirada DATE NOT NULL,
	private Boolean status; //	status Boolean,  possivel enum
	private String centro_custo; //centro_custo VARCHAR(255), de onde foi gastado
	private String descricao; //descricao  VARCHAR(255) NOT NULL,
	private Float valor; //valor FLOAT NOT NULL,
	private String tipo_gasto; //tipo_gasto VARCHAR(50) NOT NULL, posivel enum
	private Date vencimento; //vencimento DATE NOT NULL,

//	financeiro_id INTEGER REFERENCES FINANCEIRO(id)
	
	public Despesa(Integer id, Date data_retirada, Boolean status, String centro_custo, String descricao, float valor,
			String tipo_gasto, Date vencimento) {
		super();
		this.id = id;
		this.data_retirada = data_retirada;
		this.status = status;
		this.centro_custo = centro_custo;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo_gasto = tipo_gasto;
		this.vencimento = vencimento;
	}
	public Despesa(Date data_retirada, Boolean status, String centro_custo, String descricao, float valor,
			String tipo_gasto, Date vencimento) {
		super();
		this.data_retirada = data_retirada;
		this.status = status;
		this.centro_custo = centro_custo;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo_gasto = tipo_gasto;
		this.vencimento = vencimento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData_retirada() {
		return data_retirada;
	}
	public void setData_retirada(Date data_retirada) {
		this.data_retirada = data_retirada;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getCentro_custo() {
		return centro_custo;
	}
	public void setCentro_custo(String centro_custo) {
		this.centro_custo = centro_custo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getTipo_gasto() {
		return tipo_gasto;
	}
	public void setTipo_gasto(String tipo_gasto) {
		this.tipo_gasto = tipo_gasto;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	
}
