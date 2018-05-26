package model_vo;

import java.awt.image.BufferedImage;

public class Usuario {

	private String nome, sobrenome;
	private String email;
	private String login, senha;

	private BufferedImage imagem;
	
	public Usuario(String nome, String sobrenome, String email, String login, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Usuario) {
			
			Usuario temp = (Usuario) obj;
			if(temp.getLogin().equalsIgnoreCase(getLogin()))
				return true;
		}
		
		return super.equals(obj);
	}
	
	//metodos de acesso
	public String getLogin() {
		return login;
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

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public BufferedImage getImagem() {
		return imagem;
	}
	
}
