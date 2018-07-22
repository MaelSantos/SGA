package br.com.sga.sql;

public class SQLUtil {

    public static final String URL_POSTGRES = "jdbc:postgresql://localhost:5432/SGA";
    public static final String USUARIO_POSTGRES = "postgres";
    public static final String SENHA_POSTGRES = "admin";
    
    public static class Processo{
    	public static final String INSERT_ALL = "INSERT INTO PROCESSO(numero,tipo_participacao,tipo_processo,fase,descricao,decisao,comarca,orgao_julgador,classe_judicial,data_atuacao,status,contrato_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    	public static final String SELECT_TIPO = "SELECT p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase FROM PROCESSO p WHERE p.tipo_processo = ?";
    	public static final String SELECT_ID = "SELECT P.*,C.valor_total, E.nome FROM PROCESSO P, CONTRATO C,CONSULTA D, CLIENTE E WHERE P.ID = ? AND P.CONTRATO_ID=C.ID AND C.CONSULTA_ID=D.ID AND D.CLIENTE_ID = E.ID";
    
    }
    
    public static class Audiencia{
    	public static final String INSERT_ALL = "INSERT INTO AUDIENCIA(data_audiencia,tipo,vara,orgao,status,processo_id) VALUES(?,?,?,?,?,?)";
    	public static final String SELECT_PROCESSO_ID = "SELECT A.* FROM AUDIENCIA A, PROCESSO P WHERE P.ID = ? AND P.ID=A.PROCESSO_ID";
    }
    
    public static class Parcela{
    	public static final String INSERT_ALL = "INSERT INTO PARCELA(valor,tipo,estado,juros,multa,vencimento,contrato_id) VALUES(?,?,?,?,?,?,?)";    
    }
    
    public static class Parte{
    	public static final String INSERT_ALL = "INSERT INTO PARTE(nome,tipo_parte,tipo_participacao,contrato_id) VALUES(?,?,?,?)";
    	public static final String SELECT_PARTE_CONTRATO_ID = "SELECT * FROM PARTE WHERE contrato_id = ?";
    }

    public static class Financeiro{
    	public static final String INSERT_ALL = "INSERT INTO FINANCEIRO(total_lucro,total_despesa,ano_coberto) VALUES(?,?,?)";
    	public static final String SELECT_ANO = "SELECT * FROM FINANCEIRO WHERE ANO_COBERTO = ?";
    	public static final String UPDATE_ALL = "UPDATE FINANCEIRO SET ano_coberto = ?, total_despesa = ? , total_lucro = ? where id = ?";
    }
    
    public static class Receita{
    	public static final String INSERT_ALL = "INSERT INTO RECEITA(data_entrada,centro_custo,descricao,valor,status,tipo_pagamento,vencimento,financeiro_id) VALUES(?,?,?,?,?,?,?,?)";
    	public static final String SELECT_ID_FINANCEIRO = "SELECT * FROM RECEITA WHERE financeiro_id = ?";
    
    }

    public static class Despesa{
    	public static final String INSERT_ALL = "INSERT INTO DESPESA(data_retirada,centro_custo,descricao,valor,status,tipo_gasto,vencimento,financeiro_id) VALUES(?,?,?,?,?,?,?,?)";
    	public static final String SELECT_ID_FINANCEIRO = "SELECT * FROM DESPESA WHERE financeiro_id = ?";
    }
    
    public static class Contrato{
    	public static final String INSERT_ALL = "INSERT INTO CONTRATO(objeto,valor_total,tipo_pagamento,data_contrato,area,dados_banco,consulta_id,financeiro_id) VALUES(?,?,?,?,?,?,?,?)";
    	public static final String BUSCAR_ATIVOS = "SELECT * FROM CONTRATO WHERE STATUS = TRUE";
    	public static final String SELECT_CONTRATO_ID = "SELECT * FROM CONTRATO WHERE id = ?";
    	public static final String BUSCA_POR_CLIENTE ="select cont.* from contrato cont, consulta cons, cliente clie "
    			+ "where clie.id = cons.cliente_id and cons.id = cont.consulta_id and clie.nome = ? or clie.email = ? "
    			+ "or clie.cpf_cnpj = ? or clie.rg = ?";
    	public static final String SELECT_CONTRATO_ADAPTER = "SELECT C.id,D.nome,C.data_contrato,C.valor_total FROM CONTRATO C, CLIENTE D, CONSULTA E WHERE C.CONSULTA_ID = E.ID AND D.ID = E.CLIENTE_ID";
    }
    
    public static class Testemunha{
    	public static final String INSERT_ALL = "INSERT INTO TESTEMUNHA(nome,endereco_id,consulta_id) VALUES(?,?,?)";
    }
    
    public static class Consulta{
    	public static final String INSERT_ALL = "INSERT INTO CONSULTA(valor_honorario,descricao,area,indicacao,data_consulta,cliente_id,funcionario_id) VALUES(?,?,?,?,?,?,?)";
    	public static final String BUSCA_POR_CLIENTE ="select con.id, con.area, con.data_consulta ,con.descricao from cliente cli, consulta con where con.cliente_id = cli.id and cli.nome like ? or cli.email like ?"
    			 +" or cli.cpf_cnpj like ? or cli.rg like ?";
    }
    
    public static class Notificacao{
    	public static final String INSERT_ALL = "INSERT INTO NOTIFICACAO(tipo,descricao,prioridade,estado,data_aviso) VALUES(?,?,?,?,?)";
    	public static final String BUSCA_POR_FUNCIONARIO ="select n.* from funcionario f, vinculo_funcionario v , "
    			+ "notificacao n where n.estado != 'COMPLETO' and n.id =  v.notificacao_id and v.funcionario_id ="
    			+ " f.id and f.numero_oab = ?";
    	public static final String SELECT_DATA = "SELECT * FROM NOTIFICACAO WHERE CAST(data_aviso AS DATE) = ? ";
    	public static final String SELECT_ADAPTER_DATA = "SELECT ID,TIPO,ESTADO FROM NOTIFICACAO WHERE CAST(data_aviso AS DATE) = ?";
    	public static final String SELECT_DATA_MES_ANO = "SELECT DISTINCT CAST(data_aviso AS DATE) FROM NOTIFICACAO WHERE (SELECT EXTRACT(MONTH FROM data_aviso)) = ? AND (SELECT EXTRACT(YEAR FROM data_aviso)) = ?;";
    }
   
    public static class VinculoFuncionario{
    	public static final String INSERT_ALL = "INSERT INTO vinculo_funcionario(notificacao_id,funcionario_id) VALUES( ? , ?) ";
    }
   
    public static class Funcionario {
	    public static final String INSERT_ALL = "INSERT INTO funcionario(nome, senha, login,numero_oab,email,endereco_id) VALUES (?, ?, ?, ? , ? , ?);";
	    public static final String INSERT_SEM_ENDERECO = "INSERT INTO funcionario(nome, senha, login,numero_oab,email) VALUES (?, ?, ?, ? , ?);";
	    
	    public static final String SELECT_LOGIN_SENHA = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
	    public static final String SELECT_NOME = "SELECT ID FROM FUNCIONARIO WHERE NOME = ?";
	    public static final String SELECT_ALL_BUSCA_ALL = "select * from funcionario where nome like ? or senha like ? or login like  ? or numero_oab like ? or email like ?";
    	/* para depois : pesquisar colunas especificas )
    	 *  select * from funcionario where nome like '%_%' and 	senha like '%_%' and login like 'wan'and numero_oab like '%_%' and email like '%_%'
    	 */
	    public static final String UPDATE_ALL = "UPDATE FUNCIONARIO SET NOME = ?, LOGIN = ? , SENHA = ?, NUMERO_OAB = ?, EMAIL = ? where id = ?";
    }
    
//  nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;	
    public static class Cliente {
        public static final String SELECT_ID = "SELECT * FROM CLIENTE WHERE id = ?";
        public static final String SELECT_ALL = "SELECT * FROM CLIENTE";
        public static final String BUSCAR_ALL ="Select * from cliente where nome like ? or genero = ? or cpf_cnpj = ?  or email like ? or estado_civil = ? or tipo = ?";

	    public static final String INSERT_ALL = "INSERT INTO CLIENTE(nome, data_nascimento, cpf_cnpj, genero, rg, email,"
	    		+ "estado_civil, profissao, filhos, responsavel, tipo, id_endereco) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    public static final String SELECT_CPF_CNPJ = "SELECT * FROM CLIENTE e, ENDERECO d WHERE e.cpf_cnpj = ? AND d.id = e.id_endereco";
    }
    
//    id, estado, numero, cep, cidade, bairro, complemento, pais, rua;
    public static class Endereco {
	    public static final String INSERT_ALL = "INSERT INTO ENDERECO(rua, numero, bairro, cidade, cep, pais, estado, complemento ) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	    public static final String SELECT_CEP = "SELECT * FROM ENDERECO WHERE cep = ?";
	    
	    public static final String SELECT_CLIENTE_ENDERECO_CPF = "SELECT * FROM CLIENTE a, ENDERECO e where a.endereco_id=e.id AND cpf_cnpj = ?";
    }
    
//    id ,cliente_id, numero, prefixo
    public static class Telefone {
	    public static final String INSERT_ALL_PARA_CLIENTE = "INSERT INTO TELEFONE(numero, prefixo, cliente_id) VALUES (?, ?, ?)";
	    public static final String INSERT_ALL_PARA_TESTEMUNHA = "INSERT INTO TELEFONE(numero, prefixo, testemunha_id) VALUES (?, ?, ?)";
	    public static final String SELECT_TELEFONE_CLIENTE = "SELECT * FROM TELEFONE WHERE cliente_id = ?";
	    public static final String SELECT_NUMERO = "SELECT * FROM TELEFONE WHERE numero = ?";
    }
    
    
    
}
