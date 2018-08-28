package br.com.sga.entidade;

import br.com.sga.entidade.enums.Estado;

public class Endereco {

	private Integer id;	//id SERIAL PRIMARY KEY,
	
	private String rua; //rua VARCHAR(255)
	private String numero; //numero VARCHAR(255),
	private String bairro; //bairro VARCHAR(255), 
	private String cidade; //cidade VARCHAR(255),
	private Estado estado; //estado VARCHAR(255),
	private String pais; //pais VARCHAR(255), 
	private String complemento; //complemento VARCHAR(255), 
	private String cep; //cep VARCHAR(255),
	
	public Endereco(String rua, String numero, String bairro, String cidade, Estado estado, String pais,
			String complemento, String cep) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.complemento = complemento;
		this.cep = cep;
	}
	public Endereco() {}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade="
				+ cidade + ", estado=" + estado + ", pais=" + pais + ", complemento=" + complemento + ", cep=" + cep
				+ "]";
	}	
}
