package br.com.sga.entidade;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Funcionario {

	private String nome;
	private String email;
	private String login, senha;
	private String numero_oab;
	private ArrayList<Notificacao> notificacao;
	private BufferedImage imagem;
	
	public Funcionario(String nome, String email, String login, String senha,String numero_oab) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.numero_oab = numero_oab;
		notificacao = new ArrayList<>();
	}
	public Funcionario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		notificacao = new ArrayList<>();
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
	
	
	public String getNumero_oab() {
		return numero_oab;
	}
	public void setNumero_oab(String numero_oab) {
		this.numero_oab = numero_oab;
	}
	public ArrayList<Notificacao> getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(ArrayList<Notificacao> notificacao) {
		this.notificacao = notificacao;
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
