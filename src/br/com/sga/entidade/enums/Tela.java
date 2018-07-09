package br.com.sga.entidade.enums;

public enum Tela{

	login, cadastro, perfil, pesquisa, menu, informacoes, configuracoes, alertas, historico,
	estatiticas, financeiro, documentos, processos, contatos, clientes, agenda, editar_perfil,
	cadastro_cliente;

	public static Tela getTela(String tela)
	{
		for(Tela t : values())
			if(t.toString().equalsIgnoreCase(tela))
			{
				return t;
			}
		return null;
	}
	
}
