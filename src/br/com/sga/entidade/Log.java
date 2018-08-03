package br.com.sga.entidade;

import java.util.Date;

import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;

public class Log {

	private Integer id; //id SERIAL PRIMARY KEY,
	private Date data; //data DATE NOT NULL,
	private EventoLog evento;//evento VARCHAR(255),
	private String remetente;//remetente VARCHAR(255),
	private String destinatario;//destinatario VARCHAR(255),
	private StatusLog status;//status VARCHAR(255)
	
	public Log() {
	}
	
	public Log(Date data, EventoLog evento, String remetente, String destinatario, StatusLog status) {
		this.data = data;
		this.evento = evento;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public EventoLog getEvento() {
		return evento;
	}
	public void setEvento(EventoLog evento) {
		this.evento = evento;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public StatusLog getStatus() {
		return status;
	}
	public void setStatus(StatusLog status) {
		this.status = status;
	}

}

