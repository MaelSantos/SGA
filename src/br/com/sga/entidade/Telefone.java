package br.com.sga.entidade;

public class Telefone {

	private Integer id;
	
	private String numero;
	private String tipo;
	private Integer id_cliente;

	public Telefone(Integer id, String numero, String tipo, Integer id_cliente) {
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.id_cliente = id_cliente;
	}

	//metodos de acesso
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
}
