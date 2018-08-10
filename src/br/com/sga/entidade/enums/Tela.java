package br.com.sga.entidade.enums;

public enum Tela{

	LOGIN("Login"), CADASTRO("Cadastro"), PERFIL("Perfil"), PESQUISA("Pesquisa"), MENU("Menu"),
	INFORMACOES("Informações"), CONFIGURACOES("Configurações"), HOME("Home"), HISTORICO("Histórico"),
	FINANCEIRO("Financeiro"), DOCUMENTOS("Documentos"), PROCESSOS("Processos"), CLIENTES("Clientes"),
	AGENDA("Agenda"), EDITAR_PERFIL("Editar Perfil"),CADASTRO_CLIENTE("Cadastro Cliente"),
	CADASTRO_CONTRATO("Cadastro Contrato"), CADASTRO_PROCESSO("Cadastro Processo"),
	DETALHES_PROCESSO("Detalhes Processo"),BUSCAR_CONTRATO("Buscar Contrato"), CADASTRO_AUDIENCIA("Cadastro Audiência"),
	CADASTRO_CONSULTA("Cadastro Consulta"),CADASTRO_RECEITA_DESPESA("Cadastro Receita Despesa"),CONSULTA("Consulta"),
	DETALHES_CONSULTA("Detalhe Consulta"),DETALHES_CONTRATO("Detalhes Contrat"),ESTATISTICA("Estatística");
	
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
