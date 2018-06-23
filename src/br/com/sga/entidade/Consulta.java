package br.com.sga.entidade;

import java.sql.Date;
import java.sql.Time;

public class Consulta {

	private Integer id; 

	private String area;	
	private String descricao;
	private Date data_consulta;
	private float valor_honorario; 
	private String indicacao;

	private Integer id_contrato;
	private Integer id_funcionario; 
	private Integer id_cliente;
	private Integer id_agenda;

	public Consulta(Integer id, String area, String descricao, Date data_consulta, float valor_honorario,
			String indicacao, Integer id_contrato, Integer id_funcionario, Integer id_cliente, Integer id_agenda) {
		this.id = id;
		this.area = area;
		this.descricao = descricao;
		this.data_consulta = data_consulta;
		this.valor_honorario = valor_honorario;
		this.indicacao = indicacao;
		this.id_contrato = id_contrato;
		this.id_funcionario = id_funcionario;
		this.id_cliente = id_cliente;
		this.id_agenda = id_agenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(Integer id_contrato) {
		this.id_contrato = id_contrato;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public Integer getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(Integer id_agenda) {
		this.id_agenda = id_agenda;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
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

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	
}
