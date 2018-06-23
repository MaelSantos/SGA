package br.com.sga.entidade;

import java.sql.Date;

public class Agenda {

	private Integer id;
	
	private Date data_tarefa; 
	private Date data_audiencia; 
	private Date data_consulta; 
	private String tarefas;
	
	public Agenda(Integer id, Date data_tarefa, Date data_audiencia, Date data_consulta, String tarefas) {
		this.id = id;
		this.data_tarefa = data_tarefa;
		this.data_audiencia = data_audiencia;
		this.data_consulta = data_consulta;
		this.tarefas = tarefas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData_tarefa() {
		return data_tarefa;
	}

	public void setData_tarefa(Date data_tarefa) {
		this.data_tarefa = data_tarefa;
	}

	public Date getData_audiencia() {
		return data_audiencia;
	}

	public void setData_audiencia(Date data_audiencia) {
		this.data_audiencia = data_audiencia;
	}

	public Date getData_consulta() {
		return data_consulta;
	}

	public void setData_consulta(Date data_consulta) {
		this.data_consulta = data_consulta;
	}

	public String getTarefas() {
		return tarefas;
	}

	public void setTarefas(String tarefas) {
		this.tarefas = tarefas;
	}

}
