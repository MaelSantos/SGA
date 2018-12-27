package br.com.sga.entidade.adapter;

public class ClienteAdapter {

	private Integer id;
	
	private String nome;
    private String cpf_cnpj;
    private String email;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
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
		
		return "Nome: [" + nome + "] CPF/CNPJ: [" + cpf_cnpj + "] Email: [" + email + "]";
	}
    
}
