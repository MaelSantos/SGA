package br.com.sga.entidade;

import java.sql.Date;
import java.util.List;

public class Financeiro {

	private Integer id;
  
	private float total_despesas; 
	private float total_lucro; 
	private List<Contrato> contratos;
	private List<Receita> receitas;
	private List<Despesa> despesas;
	public Financeiro(float total_despesas, float total_lucro, List<Contrato> contratos, List<Receita> receitas,
			List<Despesa> despesas) {
		super();
		this.total_despesas = total_despesas;
		this.total_lucro = total_lucro;
		this.contratos = contratos;
		this.receitas = receitas;
		this.despesas = despesas;
	}
	
	public Financeiro(Integer id, float total_despesas, float total_lucro, List<Contrato> contratos,
			List<Receita> receitas, List<Despesa> despesas) {
		super();
		this.id = id;
		this.total_despesas = total_despesas;
		this.total_lucro = total_lucro;
		this.contratos = contratos;
		this.receitas = receitas;
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

	
}
