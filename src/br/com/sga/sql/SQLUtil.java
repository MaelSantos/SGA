/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sga.sql;

/**
 *
 * @author prof Heldon
 */
public class SQLUtil {

    public static String URL_POSTGRES = "jdbc:postgresql://localhost:5432/SGA";
    public static String USUARIO_POSTGRES = "postgres";
    public static String SENHA_POSTGRES = "admin";

    public static class Funcionario {
	    public static String INSERT_ALL = "INSERT INTO funcionario(nome, senha, login,numero_oab) VALUES (?, ?, ?, ?);";
	    public static String SELECT_LOGIN_SENHA = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
    }

    public static class Cliente {

        public static final String INSERT_ALL = "insert into curso2 (nome, codigo, carga_horaria) "
                + "values (?,?,?) ";
        
        public static final String SELECT_ID = "select * from curso2 where id = ?";

    }
;
    private SQLUtil() {

    }

}
