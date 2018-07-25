package br.com.sga.entidade.adapter;

public class FuncionarioAdapter {

	private Integer id;
	
	private String nome;
    private String numero;
    private String email;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
	@Override
	public String toString() {
		return "Nome: [" + nome + "] Numero: [" + numero + "] Email: [" + email + "]";
	}
    
}
