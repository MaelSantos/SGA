package br.com.sga.sql;

public class SQLUtil {

    public static final String URL_POSTGRES = "jdbc:postgresql://localhost:5432/SGA";
    public static final String USUARIO_POSTGRES = "postgres";
    public static final String SENHA_POSTGRES = "admin";
    
    

    public static class Testemunha{
    	public static final String INSERT_ALL = "INSERT INTO TESTEMUNHA(nome,endereco_id,consulta_id) VALUES(?,?,?)";
    }
    public static class Consulta{
    	public static final String INSERT_ALL = "INSERT INTO CONSULTA(valor_honorario,descricao,area,indicacao,data_consulta,cliente_id,funcionario_id) VALUES(?,?,?,?,?,?,?)";
    }
    public static class Notificacao{
    	public static final String INSERT_ALL = "INSERT INTO NOTIFICACAO(tipo,descricao,prioridade,estado,data_aviso) VALUES(?,?,?,?,?)";
    }
    public static class VinculoFuncionario{
    	public static final String INSERT_ALL = "INSERT INTO vinculo_funcionario(notificacao_id,funcionario_id) VALUES( ? , ?) ";
    }
    public static class Funcionario {
	    public static final String INSERT_ALL = "INSERT INTO funcionario(nome, senha, login,numero_oab,endereco_id) VALUES (?, ?, ?, ?,?);";
	    public static final String SELECT_LOGIN_SENHA = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
	    public static final String SELECT_NOME = "SELECT ID FROM FUNCIONARIO WHERE NOME = ?"; 
    }
    
//  nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;	
    public static class Cliente {
        public static final String SELECT_ID = "select * from CLIENTE where id = ?";
        public static final String SELECT_ALL = "select * from CLIENTE";

	    public static final String INSERT_ALL = "INSERT INTO CLIENTE(nome, data_nascimento, cpf_cnpj, genero, rg, email,"
	    		+ "estado_civil, profissao, filhos, responsavel, tipo, id_endereco) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    public static final String SELECT_CPF_CNPJ = "SELECT * FROM CLIENTE WHERE cpf_cnpj = ?";
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
