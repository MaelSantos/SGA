package br.com.sga.entidade.enums;

public enum Tela{

	login, cadastro, perfil, pesquisa, menu, informacoes, configuracoes, alertas, historico,
	estatiticas, financeiro, documentos, processos, contatos, clientes, agenda, editar_perfil,
	cadastro_cliente,cadastro_contrato, cadastro_processo, detalhes_processo,buscar_contrato, cadastro_audiencia;

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
		return super.toString().replace("_"," ");
	}
	
}
