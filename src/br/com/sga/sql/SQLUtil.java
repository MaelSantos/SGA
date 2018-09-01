package br.com.sga.sql;

public class SQLUtil {

    public static final String URL_POSTGRES = "jdbc:postgresql://localhost:5432/SGA";
    public static final String USUARIO_POSTGRES = "postgres";
    public static final String SENHA_POSTGRES = "admin";
    
    public static class Processo{
    	public static final String INSERT_ALL = "INSERT INTO PROCESSO(numero,tipo_processo,fase,descricao,decisao,comarca,orgao_julgador,classe_judicial,data_atuacao,status, cliente_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    	public static final String SELECT_TIPO = "SELECT p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase FROM PROCESSO p WHERE p.tipo_processo = ?";
    	public static final String SELECT_ID = "SELECT * FROM PROCESSO P WHERE P.ID = ?";
    	public static final String SELECT_ADAPTER_ID_CLIENTE = "select p.id,p.numero,p.data_atuacao,p.comarca,p.decisao from processo p WHERE p.cliente_id = ?";
    	public static final String SELECT_ID_CONTRATO = "SELECT P.*,C.*,S.* FROM PROCESSO P, CONTRATO C, CONSULTA S, CLIENTE L, FUNCIONARIO F WHERE P.contrato_id = ? AND P.contrato_id = C.ID AND C.consulta_id = S.id AND S.funcionario_id = F.id AND S.cliente_id = L.id";
    	public static final String BUSCA_POR_BUSCA = "SELECT p.id,p.numero,p.data_atuacao,p.comarca,p.decisao,p.fase FROM PROCESSO p WHERE p.tipo_processo ILIKE ? or p.numero ilike ? OR to_char(p.data_atuacao, 'dd-MM-yyyy') ilike ? OR p.fase ilike ? OR p.comarca ilike ? OR p.classe_judicial like ? OR p.orgao_julgador ilike ?";
    }
    
    public static class Audiencia{
    	public static final String INSERT_ALL = "INSERT INTO AUDIENCIA(data_audiencia,tipo,vara,orgao,status,processo_id) VALUES(?,?,?,?,?,?)";
    	public static final String SELECT_PROCESSO_ID = "SELECT A.* FROM AUDIENCIA A, PROCESSO P WHERE P.ID = ? AND P.ID=A.PROCESSO_ID";
    }
    
    public static class Parcela{
    	public static final String INSERT_ALL = "INSERT INTO PARCELA(valor,tipo,estado,juros,multa,vencimento,contrato_id) VALUES(?,?,?,?,?,?,?)";    
    	public static final String SELECT_ID_CONTRATO = "SELECT * FROM PARCELA  WHERE  CONTRATO_ID = ? ";  
    	public static final String UPDATE_PARCELA = "UPDATE PARCELA SET ESTADO = ? WHERE ID= ?";
    }
    
    public static class Parte{
    	public static final String INSERT_ALL_CONTRATO = "INSERT INTO PARTE(nome,tipo_parte,tipo_participacao,contrato_id) VALUES(?,?,?,?)";
    	public static final String INSERT_ALL_PROCESSO = "INSERT INTO PARTE(nome,tipo_parte,tipo_participacao,processo_id) VALUES(?,?,?,?)";
    	public static final String SELECT_PARTE_CONTRATO_ID = "SELECT * FROM PARTE WHERE contrato_id = ?";
    	public static final String SELECT_PARTE_PROCESSO_ID = "SELECT * FROM PARTE WHERE processo_id = ?";
    }

    public static class Financeiro{
    	public static final String INSERT_ALL = "INSERT INTO FINANCEIRO(total_lucro,total_despesa,ano_coberto) VALUES(?,?,?)";
    	public static final String SELECT_ID_ANO = "SELECT id FROM FINANCEIRO WHERE ANO_COBERTO = ?";
    	public static final String SELECT_ANO = "SELECT * FROM FINANCEIRO WHERE ANO_COBERTO = ?";
    	public static final String UPDATE_ALL = "UPDATE FINANCEIRO SET ano_coberto = ?, total_despesa = ? , total_lucro = ? where id = ?";
    	public static final String UPDATE_TOTAL_RECEITA ="UPDATE FINANCEIRO SET total_lucro =  total_lucro + ? WHERE ID = ?";
    	public static final String UPDATE_TOTAL_DESPESA ="UPDATE FINANCEIRO SET total_despesa =  total_despesa + ? WHERE ID = ?";
    }
    
    public static class Receita{
    	public static final String INSERT_ALL = "INSERT INTO RECEITA(data_entrada,centro_custo,descricao,valor,status,tipo_pagamento,vencimento,financeiro_id) VALUES(?,?,?,?,?,?,?,?)";
    	public static final String SELECT_ID_FINANCEIRO = "SELECT * FROM RECEITA WHERE financeiro_id = ?";
    	public static final String SELECT_INTERVALO = "SELECT R.* FROM FINANCEIRO F, RECEITA R WHERE (R.data_entrada BETWEEN ? AND ?)";
    	public static final String SELECT_INTERVALO_TOTAL_MES= "select sum(valor) as total,extract(year from data_entrada) as ano,extract( month from data_entrada)as mes from receita"
    			+ " where data_entrada between ? and ? group by mes,ano order by mes,ano";
    }

    public static class Despesa{
    	public static final String INSERT_ALL = "INSERT INTO DESPESA(data_retirada,centro_custo,descricao,valor,status,tipo_gasto,vencimento,financeiro_id) VALUES(?,?,?,?,?,?,?,?)";
    	public static final String SELECT_ID_FINANCEIRO = "SELECT * FROM DESPESA WHERE financeiro_id = ?";
    	public static final String SELECT_INTERVALO = "SELECT D.* FROM FINANCEIRO F, DESPESA D WHERE (D.data_retirada BETWEEN ? AND ?)";
    	public static final String SELECT_INTERVALO_TOTAL_MES= "select sum(valor) as total,extract(year from data_retirada) as ano,extract( month from data_retirada)as mes from despesa"
    			+ " where data_retirada between ? and ? group by mes,ano order by mes,ano";
    }
    
    public static class Contrato{
    	public static final String INSERT_ALL = "INSERT INTO CONTRATO(objeto,valor_total,tipo_pagamento,data_contrato,area,dados_banco,taxa_juros,taxa_multa,consulta_id,financeiro_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
    	public static final String BUSCAR_ATIVOS = "SELECT * FROM CONTRATO WHERE STATUS = TRUE";
    	public static final String SELECT_CONTRATO_ID = "SELECT * FROM CONTRATO WHERE id = ?";
    	public static final String BUSCA_POR_CLIENTE_ADAPTER ="select cont.id, clie.nome, cont.data_contrato, cont.valor_total from contrato cont, consulta cons, cliente clie "
    			+ "where clie.id = cons.cliente_id and cons.id = cont.consulta_id and (unaccent(clie.nome) ilike unaccent(?) or unaccent(clie.email) ilike unaccent(?) "
    			+ "or clie.cpf_cnpj = ? or clie.rg = ?)";
    	public static final String SELECT_CONTRATO_ADAPTER = "SELECT C.id,D.nome,C.data_contrato,C.valor_total FROM CONTRATO C, CLIENTE D, CONSULTA E WHERE C.CONSULTA_ID = E.ID AND D.ID = E.CLIENTE_ID";
    }
    
    public static class Testemunha{
    	public static final String INSERT_ALL = "INSERT INTO TESTEMUNHA(nome,endereco_id,consulta_id) VALUES(?,?,?)";
    	public static final String SELECT_ID_CONSULTA = "SELECT * FROM TESTEMUNHA WHERE consulta_id = ?";
    }
    
    public static class Consulta{
    	public static final String INSERT_ALL = "INSERT INTO CONSULTA(valor_honorario,descricao,area,indicacao,data_consulta,cliente_id,funcionario_id) VALUES(?,?,?,?,?,?,?)";
    	public static final String SELECT_ID_CONSULTA=" SELECT * FROM CONSULTA WHERE ID = ? ";
    	/*public static final String SELECT_ID_CONSULTA_FUNCIONARIO_ADAPTETER= "SELECT CON.DESCRICAO,CON.INDICACAO,FUN.NOME,FUN.NUMERO_OAB \r\n" + 
														"FROM CONSULTA CON, FUNCIONARIO FUN\r\n" + 
														"WHERE CON.FUNCIONARIO_ID  =FUN.ID \r\n" + 
														"AND CON.ID = ?";*/
    	public static final String BUSCA_POR_CLIENTE ="select con.id, con.area, con.data_consulta ,con.valor_honorario from cliente cli,"
    			+ " consulta con where con.cliente_id = cli.id and unaccent(cli.nome) ilike unaccent(?) or unaccent(cli.email) ilike unaccent(?)"
    			 +" or cli.cpf_cnpj ilike ? or cli.rg ilike ?";
    	public static final String BUSCA_POR_BUSCA = "select con.id, cli.nome ,con.area, con.data_consulta ,con.valor_honorario from cliente cli,\r\n" + 
    	"    			consulta con where con.cliente_id = cli.id and ((unaccent(cli.nome) ilike unaccent(?) or unaccent(cli.email) ilike unaccent(?)\r\n" + 
    	"    			 or cli.cpf_cnpj ilike ? or cli.rg ilike ?) and cli.tipo like ? and con.area like ?)";
    }
    
    public static class Notificacao{
    	public static final String INSERT_ALL = "INSERT INTO NOTIFICACAO(tipo,descricao,prioridade,estado,data_aviso) VALUES(?,?,?,?,?)";
    	public static final String BUSCA_POR_FUNCIONARIO ="select n.* from funcionario f, vinculo_funcionario v , "
    			+ "notificacao n where n.estado != 'COMPLETO' and n.id =  v.notificacao_id and v.funcionario_id ="
    			+ " f.id and f.numero_oab = ?";
    	public static final String SELECT_DATA = "SELECT * FROM NOTIFICACAO WHERE CAST(data_aviso AS DATE) = ? ";
    	public static final String SELECT_DATA_MES_ANO = "SELECT DISTINCT CAST(data_aviso AS DATE) FROM NOTIFICACAO WHERE (SELECT EXTRACT(MONTH FROM data_aviso)) = ? AND (SELECT EXTRACT(YEAR FROM data_aviso)) = ?;";
    	public static final String SELECT_ADAPTER_DATA = "SELECT ID,TIPO,ESTADO,DATA_AVISO,DESCRICAO FROM NOTIFICACAO WHERE (CAST(data_aviso AS DATE)) BETWEEN ? AND ?";
    	public static final String SELECT_ADAPTER_ESTADO = "SELECT ID,TIPO,ESTADO,DATA_AVISO,DESCRICAO FROM NOTIFICACAO WHERE estado = ?";
    	public static final String UPDATE_ESTADO = "UPDATE NOTIFICACAO SET ESTADO = 'VENCIDO' WHERE CAST(data_aviso AS DATE) = ? AND ESTADO = 'PENDENTE'" ;
    }
   
    public static class VinculoFuncionario{
    	public static final String INSERT_ALL = "INSERT INTO vinculo_funcionario(notificacao_id,funcionario_id) VALUES( ? , ?) ";
    }
   
    public static class Funcionario {
	    public static final String INSERT_ALL = "INSERT INTO funcionario(nome, senha, login,numero_oab,email,endereco_id) VALUES (?, ?, ?, ? , ? , ?)";
	    public static final String INSERT_SEM_ENDERECO = "INSERT INTO funcionario(nome, senha, login,numero_oab,email) VALUES (?, ?, ?, ? , ?)";
	    public static final String SELECT_CONSULTA_ID = "SELECT DISTINCT f.* FROM CONSULTA c, FUNCIONARIO f WHERE c.funcionario_id = f.id AND c.id = ?";
	    public static final String SELECT_LOGIN_SENHA = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
	    public static final String SELECT_NOME = "SELECT ID FROM FUNCIONARIO WHERE NOME = ?";
	    public static final String SELECT_ALL_BUSCA_ALL = "select * from funcionario where unaccent(nome) ilike unaccent(?) "
	    		+ "or senha ilike ? or login ilike ? or numero_oab ilike ? "
	    		+ "or unaccent(email) ilike unaccent(?)";
	    public static final String SELECT_ID_CONSULTA_ADAPTER = "SELECT F.ID, F.NOME, F.NUMERO_OAB, F.EMAIL FROM FUNCIONARIO F, CONSULTA C WHERE C.FUNCIONARIO_ID = F.ID AND C.ID = ?";
    	/* para depois : pesquisar colunas especificas )
    	 *  select * from funcionario where nome like '%_%' and 	senha like '%_%' and login like 'wan'and numero_oab like '%_%' and email like '%_%'
    	 */
	    public static final String UPDATE_ALL = "UPDATE FUNCIONARIO SET NOME = ?, LOGIN = ? , SENHA = ?, NUMERO_OAB = ?, EMAIL = ? where id = ?";
		public static final String SELECT_ID = "SELECT * FROM FUNCIONARIO WHERE id = ?";
    }
    
//  nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;	
    public static class Cliente {
        public static final String SELECT_ID = "SELECT * FROM CLIENTE WHERE id = ?";
        public static final String SELECT_ALL = "SELECT * FROM CLIENTE";
        public static final String BUSCAR_ALL ="Select * from cliente where unaccent(nome) ilike unaccent(?) or genero = ? or cpf_cnpj = ?  or unaccent(email) ilike unaccent(?) or unaccent(estado_civil) = unaccent(?) or tipo = ?";
        public static final String BUSCAR_ALL_ADAPTER ="Select id,nome,email,cpf_cnpj from cliente where unaccent(nome) ilike unaccent(?) or genero = ? or cpf_cnpj = ?  or unaccent(email) ilike unaccent(?) or unaccent(estado_civil) = unaccent(?) or tipo = ?";
        public static final String SELECT_CLIENTE_ID_CONSULTA = "SELECT DISTINCT e.* FROM CONSULTA o, CLIENTE e WHERE o.cliente_id = e.id AND o.id = ?";

	    public static final String INSERT_ALL = "INSERT INTO CLIENTE(nome, data_nascimento, cpf_cnpj, genero, rg, email,"
	    		+ "estado_civil, profissao, filhos, responsavel, tipo, id_endereco) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    public static final String SELECT_CPF_CNPJ = "SELECT * FROM CLIENTE e, ENDERECO d WHERE e.cpf_cnpj = ? AND d.id = e.id_endereco";
	    
	    public static final String UPDATE_ALL = "UPDATE CLIENTE SET nome = ?, data_nascimento = ?, cpf_cnpj = ?, genero = ?, rg = ?, email = ?, estado_civil = ?, profissao = ?, filhos = ?, responsavel = ?, tipo = ? where id = ?";
    }
    
//    id, estado, numero, cep, cidade, bairro, complemento, pais, rua;
    public static class Endereco {
	    public static final String INSERT_ALL = "INSERT INTO ENDERECO(rua, numero, bairro, cidade, cep, pais, estado, complemento) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	    public static final String SELECT_CEP = "SELECT * FROM ENDERECO WHERE cep = ?";
	    
	    public static final String SELECT_CLIENTE_ENDERECO_CPF = "SELECT * FROM CLIENTE a, ENDERECO e where a.endereco_id=e.id AND cpf_cnpj = ?";

	    public static final String SELECT_ID = "SELECT * FROM ENDERECO WHERE ID = ?";
	    public static final String SELECT_ID_CLIENTE = "SELECT e.* FROM ENDERECO e, CLIENTE c WHERE c.id = ? AND c.id_endereco = e.id";
	    
	    public static final String UPDATE_ALL = "UPDATE ENDERECO SET rua = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, pais = ?, estado = ?, complemento = ? where id = ?";
    }
    
//    id ,cliente_id, numero, prefixo
    public static class Telefone {
	    public static final String INSERT_ALL_PARA_CLIENTE = "INSERT INTO TELEFONE(numero, prefixo, tipo, cliente_id) VALUES (?, ?, ?, ?)";
	    public static final String INSERT_ALL_PARA_TESTEMUNHA = "INSERT INTO TELEFONE(numero, prefixo, tipo, testemunha_id) VALUES (?, ?, ?, ?)";
	    public static final String SELECT_TELEFONE_CLIENTE = "SELECT * FROM TELEFONE WHERE cliente_id = ?";
	    public static final String SELECT_TELEFONE_TESTEMUNHA = "SELECT * FROM TELEFONE WHERE testemunha_id = ?";
	    public static final String SELECT_NUMERO = "SELECT * FROM TELEFONE WHERE numero = ?";
    }
    
    public static class Log {
    	
    	
    	public static final String INSERT_ALL = "INSERT INTO LOG(data, evento, remetente, destinatario, status) VALUES (?, ?, ?, ?, ?)";
    	public static final String SELECT_DATA_INTERVALO = "SELECT * FROM LOG WHERE (data BETWEEN ? AND ?) AND evento ilike ? AND status ilike ?";
    	
    }
    
}
