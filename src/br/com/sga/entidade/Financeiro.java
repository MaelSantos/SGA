package br.com.sga.entidade;

import java.util.List;

public class Financeiro {

	private Integer id; //id SERIAL PRIMARY KEY,
  
	private Float total_despesas; //total_despesa  FLOAT, 
	private Float total_lucro; //total_lucro FLOAT,
	private String ano_coberto; //ano_coberto VARCHAR(20) UNIQUE
	private List<Contrato> contratos;
	private List<Receita> receitas;
	private List<Despesa> despesas;
	
	public Financeiro(Integer id) {
		this.id = id;
	}

	public Financeiro(Integer id, float total_despesas, float total_lucro, String ano_conerto, List<Contrato> contratos,
			List<Receita> receitas, List<Despesa> despesas) {
		super();
		this.id = id;
		this.total_despesas = total_despesas;
		this.total_lucro = total_lucro;
		this.ano_coberto = ano_conerto;
		this.contratos = contratos;
		this.receitas = receitas;
		this.despesas = despesas;
	}
	
	
	public Financeiro(float total_despesas, float total_lucro, String ano_conerto) {
		super();
		this.total_despesas = total_despesas;
		this.total_lucro = total_lucro;
		this.ano_coberto = ano_conerto;
	}


	public Financeiro(float total_despesas, float total_lucro, String ano_conerto, List<Contrato> contratos,
			List<Receita> receitas, List<Despesa> despesas) {
		super();
		this.total_despesas = total_despesas;
		this.total_lucro = total_lucro;
		this.ano_coberto = ano_conerto;
		this.contratos = contratos;
		this.receitas = receitas;
		this.despesas = despesas;
	}
	

	public Financeiro() {
	}


	public String getAno_coberto() {
		return ano_coberto;
	}

	public void setAno_coberto(String ano_conerto) {
		this.ano_coberto = ano_conerto;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getTotal_despesas() {
		return total_despesas;
	}
	public void setTotal_despesas(float total_despesas) {
		this.total_despesas = total_despesas;
	}
	public float getTotal_lucro() {
		return total_lucro;
	}
	public void setTotal_lucro(float total_lucro) {
		this.total_lucro = total_lucro;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	@Override
	public String toString() {
		return "Financeiro [id=" + id + ", total_despesas=" + total_despesas + ", total_lucro=" + total_lucro
				+ ", ano_coberto=" + ano_coberto + ", contratos=" + contratos + ", receitas=" + receitas + ", despesas="
				+ despesas + "]";
	}

	
}
