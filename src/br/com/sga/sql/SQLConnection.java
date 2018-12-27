package br.com.sga.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.sga.dao.DaoXml;


public class SQLConnection {

    public static final String NOME_BD_CONNECTION_POSTGRESS = "POSTGRES";

    private static Connection conexao = null;

    private SQLConnection() {
    }

    public static synchronized Connection getConnectionInstance(String bd) {
        try {
            if (conexao == null || conexao.isClosed()) {

                switch (bd) {
                    case NOME_BD_CONNECTION_POSTGRESS: {

                        conexao = DriverManager.getConnection(
                                DaoXml.getInstance().getIp(),
                                SQLUtil.USUARIO_POSTGRES,
                                SQLUtil.SENHA_POSTGRES
                        );
                    }
                    break;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return conexao;

    }

}
