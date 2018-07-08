package br.com.sga.entidade;

public class Testemunha {
	private Integer id;
	private Endereco endereco;
	private Telefone telefone;
	private String nome;
	
	public Testemunha(Integer id, Endereco endereco, Telefone telefone, String nome) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.telefone = telefone;
		this.nome = nome;
	}
	public Testemunha(Endereco endereco, Telefone telefone, String nome) {
		super();
		this.endereco = endereco;
		this.telefone = telefone;
		this.nome = nome;
	}
	
	public Testemunha(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Testemunha(String nome) {
		this.nome = nome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
