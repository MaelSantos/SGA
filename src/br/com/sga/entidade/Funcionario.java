package br.com.sga.entidade;

import java.awt.image.BufferedImage;

public class Funcionario {

	private String nome;
	private String email;
	private String login, senha;

	private BufferedImage imagem;
	
	public Funcionario(String nome, String email, String login, String senha) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Funcionario) {
			
			Funcionario temp = (Funcionario) obj;
			if(temp.getLogin().equalsIgnoreCase(getLogin()))
				return true;
		}
		
		return super.equals(obj);
	}
	
	//metodos de acesso
	public String getLogin() {
		return login;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public BufferedImage getImagem() {
		return imagem;
	}
	
}
