package br.com.sga.sql;

public class SQLUtil {

    public static String URL_POSTGRES = "jdbc:postgresql://localhost:5432/SGA";
    public static String USUARIO_POSTGRES = "postgres";
    public static String SENHA_POSTGRES = "admin";

    public static class Funcionario {
	    public static String INSERT_ALL = "INSERT INTO funcionario(nome, senha, login, numero_oab) VALUES (?, ?, ?, ?);";
	    public static String SELECT_LOGIN_SENHA = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
    }

}
