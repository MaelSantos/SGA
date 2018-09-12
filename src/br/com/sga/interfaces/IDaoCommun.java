package br.com.sga.interfaces;

import java.util.Date;
import java.util.List;

import br.com.sga.exceptions.DaoException;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.adapter.ContaAdapter;
import br.com.sga.entidade.enums.Tabela;

public interface IDaoCommun {
	
	public int getCurrentValorTabela(Tabela tabela) throws DaoException;

	//Endereco
	public void salvarEndereco(Endereco endereco) throws DaoException;
	public Endereco getEndereco(int Id) throws DaoException;
	public void EditarEndereco(Endereco endereco) throws DaoException;
	
    //Telefone 
    public void salvarContato(Telefone telefone, int id,Tabela tabela) throws DaoException;
    public List<Telefone> getContatos(Integer id) throws DaoException;
    public void editarContato(Telefone telefone) throws DaoException;
    
    //Partes
    public void salvarParte(Parte parte,Integer id, Tabela tabela)throws DaoException;
    public List<Parte> getPartes(Integer id, Tabela tabela) throws DaoException;
    public void editarParte(Parte parte) throws DaoException;
    
    //Parcela
    public void salvarParcela(Parcela parcela, Integer contrato_id) throws DaoException;
    public void editarParcela(Parcela parcela)throws DaoException;
    
    //Financeiro
    public void salvarReceita(Receita receita,Integer financeiro_id)throws DaoException;
    public void salvarDespesa(Despesa despesa,Integer financeiro_id)throws DaoException;
    public void salvarVinculoFuncionario(Integer notificacao_id,Integer funcionario_id) throws DaoException;
    public List<Receita> getReceita(Integer financeiro_id)throws DaoException;
    public List<Despesa> getDespesa(Integer financeiro_id)throws DaoException;
    public List<Receita> getReceitaPorIntervalo(Date de, Date ate)throws DaoException;
    public List<Despesa> getDespesaPorIntervalo(Date de, Date ate)throws DaoException;
    public List<ContaAdapter> getContaTotalMesPorIntervalo(Date de, Date ate,Tabela tabela) throws DaoException;
    
    //audiencia
    public void salvarAudiencia(Audiencia audiencia, Integer processo_id) throws DaoException;
    public List<Audiencia> buscarAudienciaPorIdProcesso(int processo_id) throws DaoException;
    public void editarAudiencia(Audiencia audiencia) throws DaoException;
    
    // consulta
    public void salvarTestemunha(Testemunha entidade,Integer consulta_id) throws DaoException;
	public List<Testemunha> getTestemunhas(int consulta_id) throws DaoException;
	public void editarTestemunha(Testemunha entidade) throws DaoException;

	
    
}
