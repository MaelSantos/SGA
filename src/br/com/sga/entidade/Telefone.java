package br.com.sga.entidade;

import br.com.sga.entidade.enums.TipoTelefone;

public class Telefone {

	private Integer id; //	id SERIAL PRIMARY KEY,
	
	private Integer numero; //numero INTEGER,
	private Integer prefixo; //prefixo INTEGER
	private TipoTelefone tipo;

	private Cliente cliente; //cliente_id INTEGER REFERENCES CLIENTE(id), 
	
	public Telefone() {
	}
	
	public Telefone(Integer id, Integer numero, Integer prefixo, Cliente cliente) {
		this.id = id;
		this.numero = numero;
		this.prefixo = prefixo;
		this.cliente = cliente;
	}
	public Telefone(Integer numero, Integer prefixo, TipoTelefone tipo) {
		this.numero = numero;
		this.prefixo = prefixo;
		this.tipo = tipo;
	}

	//metodos de acesso
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(Integer prefixo) {
		this.prefixo = prefixo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return tipo+" ("+prefixo+")"+numero;
	}
}
