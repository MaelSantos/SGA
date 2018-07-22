package br.com.sga.entidade;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.Area;

public class Consulta {

	private Integer id; 

	private Area area;	
	private String descricao;
	private Date data_consulta;
	private float valor_honorario; 
	private String indicacao;

	private Cliente cliente;
	private Funcionario funcionario;
	private List<Testemunha> testemunhas;

	public Consulta() {}
	public Consulta(Integer id, Area area, String descricao, Date data_consulta, float valor_honorario,
			String indicacao, Cliente cliente, Funcionario funcionario, List<Testemunha> testemunhas) {
		this.id = id;
		this.area = area;
		this.descricao = descricao;
		this.data_consulta = data_consulta;
		this.valor_honorario = valor_honorario;
		this.indicacao = indicacao;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.testemunhas = testemunhas;
	}
	
	
	public Consulta(Integer id, String descricao ,Area area, Date data_consulta) {
		this.id = id;
		this.descricao = descricao;
		this.area = area;
		this.data_consulta = data_consulta;
	}
	public Consulta( Area area, String descricao, Date data_consulta, float valor_honorario,
			String indicacao, Cliente cliente, Funcionario funcionario, List<Testemunha> testemunhas) {
		this.area = area;
		this.descricao = descricao;
		this.data_consulta = data_consulta;
		this.valor_honorario = valor_honorario;
		this.indicacao = indicacao;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.testemunhas = testemunhas;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_consulta() {
		return data_consulta;
	}

	public void setData_consulta(Date data_consulta) {
		this.data_consulta = data_consulta;
	}

	public float getValor_honorario() {
		return valor_honorario;
	}

	public void setValor_honorario(float valor_honorario) {
		this.valor_honorario = valor_honorario;
	}

	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	public List<Testemunha> getTestemunhas() {
		return testemunhas;
	}

	public void setTestemunhas(List<Testemunha> testemunhas) {
		this.testemunhas = testemunhas;
	}
	
	@Override
	public String toString() {
		return "Area "+this.getArea()+" Data "+ this.getData_consulta().toString();
	}

	
	
	
}
