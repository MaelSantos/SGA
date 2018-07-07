package br.com.sga.sql;


public class SQLUtil {

    public static String URL_POSTGRES = "jdbc:postgresql://localhost:5432/SGA";
    public static String USUARIO_POSTGRES = "postgres";
    public static String SENHA_POSTGRES = "admin";

    public static class Notificacao{
    	public static String INSERT_ALL = "INSERT INTO NOTIFICACAO(tipo,descricao,prioridade,estado,data_aviso) VALUES(?,?,?,?,?)";
    }
    public static class VinculoFuncionario{
    	public static String INSERT_ALL = "INSERT INTO vinculo_funcionario(notificacao_id,funcionario_id) VALUES( ? , ?) ";
    }
    public static class Funcionario {
	    public static String INSERT_ALL = "INSERT INTO funcionario(nome, senha, login,numero_oab) VALUES (?, ?, ?, ?);";
	    public static String SELECT_LOGIN_SENHA = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
	    public static String SELECT_NOME = "SELECT ID FROM FUNCIONARIO WHERE NOME = ?"; 
    }

    public static class Cliente {
        public static final String SELECT_ID = "select * from CLIENTE where id = ?";
        public static final String SELECT_ALL = "select * from CLIENTE";

    }
;
    private SQLUtil() {

    }

}
