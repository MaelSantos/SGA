package model_vo;

public class Telefone {

	private int numero;
	private String tipo;
	
	public Telefone(int numero, String tipo) {
		
		this.numero = numero;
		this.tipo = tipo;
	}

	//metodos de acesso
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
