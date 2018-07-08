package br.com.sga.entidade;

import java.util.Date;
import java.util.List;

import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.TipoCliente;

public class Cliente {

	private Integer id; //id SERIAL PRIMARY KEY,
	
	private String nome; //nome VARCHAR(255) NOT NULL,
	private Date nascimento; //data_nascimento DATE,
	private String cpf_cnpj; //CPF_CNPJ  VARCHAR(255) UNIQUE NOT NULL,
	private Sexo genero; //genero VARCHAR(255),
	private String rg; //RG  VARCHAR(255) UNIQUE NOT NULL, 
	private String email; //email VARCHAR(255) UNIQUE,
	private String estado_civil; //	estado_civil  VARCHAR(255),
	private String profissao; //profissao  VARCHAR(255),
	private boolean filhos; //filhos  Boolean,
	private String responsavel; //responsavel VARCHAR(255)
	private TipoCliente tipoCliente; //tipo VARCHAR(255),
	
	private Endereco endereco; //endereco_id INTEGER REFERENCES ENDERECO(id),

	private List<Telefone> telefones;
	private List<Consulta> consultas;

	public Cliente() {
	}

	public Cliente(String nome, List<Telefone> telefones) {
		super();
		this.nome = nome;
		this.telefones = telefones;
	}
	public Cliente(String nome, List<Telefone> telefones,List<Consulta> consultas) {
		super();
		this.nome = nome;
		this.telefones = telefones;
		this.consultas = consultas;
	}
	



	public Cliente(String nome, Date nascimento, String cpf_cnpj, Sexo genero, String rg, String email,
			String estado_civil, String profissao, boolean filhos, String responsavel,TipoCliente tipoCliente, Endereco endereco,
			List<Telefone> telefones) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.cpf_cnpj = cpf_cnpj;
		this.genero = genero;
		this.rg = rg;
		this.email = email;
		this.estado_civil = estado_civil;
		this.profissao = profissao;
		this.filhos = filhos;
		this.responsavel = responsavel;
		this.tipoCliente = tipoCliente;
		this.endereco = endereco;
		this.telefones = telefones;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public Sexo getGenero() {
		return genero;
	}
	public void setGenero(Sexo genero) {
		this.genero = genero;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstado_civil() {
		return estado_civil;
	}
	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}
	public String getResposavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getProfissao() {
		return profissao;
	}
	
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	public boolean isFilhos() {
		return filhos;
	}
	
	public void setFilhos(boolean filhos) {
		this.filhos = filhos;
	}
	
	public String getResponsavel() {
		return responsavel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
}
