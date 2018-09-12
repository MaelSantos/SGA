package br.com.sga.entidade.enums;

public enum Tela{

	LOGIN("Login"), CADASTRO("Cadastro"), PERFIL("Perfil"), MENU("Menu"),
	INFORMACOES("Informa��es"), CONFIGURACOES("Configura��es"), HOME("Home"), HISTORICO("Hist�rico"),
	FINANCEIRO("Financeiro"), DOCUMENTOS("Documentos"), PROCESSOS("Processos"), CLIENTES("Clientes"),
	AGENDA("Agenda"), EDITAR_PERFIL("Editar Perfil"),CADASTRO_CLIENTE("Cadastro Cliente"),
	CADASTRO_CONTRATO("Cadastro Contrato"), CADASTRO_PROCESSO("Cadastro Processo"), CADASTRO_PARTE("Cadastro Parte"),
	DETALHES_PROCESSO("Detalhes Processo"),BUSCAR_CONTRATO("Buscar Contrato"), CADASTRO_AUDIENCIA("Cadastro Audi�ncia"),
	CADASTRO_CONSULTA("Cadastro Consulta"),CADASTRO_RECEITA_DESPESA("Cadastro Receita Despesa"),CONSULTA("Consulta"),
	DETALHES_CONSULTA("Detalhe Consulta"),DETALHES_CONTRATO("Detalhes Contrato"),ESTATISTICA("Estat�stica"),
	DETALHES_NOTIFICACAO("Detalhe Notifica��o");
	
	private String value;
	
	private Tela(String value) {
		this.value = value;
	}
	
	public static Tela getTela(String tela)
	{
		for(Tela t : values())
			if(t.toString().equalsIgnoreCase(tela))
			{
				return t;
			}
		return null;
	}
	@Override
	public String toString() {
		return value;
	}
	
}
