/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sga.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoCommun implements IDaoCommun {

    private Connection conexao;
    private PreparedStatement statement;
    private ResultSet result;

    @Override
    public void salvarEndereco(Endereco endereco) throws DaoException {

        try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Endereco.INSERT_ALL);
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getNumero());
            statement.setString(3, endereco.getBairro());
            statement.setString(4, endereco.getCidade());
            statement.setString(5, endereco.getEstado());
            statement.execute();
            this.conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR ENDERECO - Contate o ADM");
        }
    }

    @Override
    public int getCurrentValorTabela(String nomeTabela) throws DaoException {

        try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement("select id from " + nomeTabela + " order by id desc limit 1");

            result = this.statement.executeQuery();
            int id;
            if (result.next()) {
                id = result.getInt(1);
            } else {
                throw new DaoException("Não há registro nessa tabela");
            }
            this.conexao.close();
            return id;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO CONSULTAR " + nomeTabela + " - Contate o ADM");
        }

    }

    @Override
    public void salvarContato(Telefone telefone, int id) throws DaoException {

        try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Telefone.INSERT_ALL);
            statement.setInt(1, telefone.getNumero());
            statement.setInt(2, telefone.getPrefixo());
            statement.setInt(3, id);
            statement.execute();
            this.conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR ENDERECO - Contate o ADM");
        }

    }

    @Override
    public List<Telefone> getContatos(Integer id) throws DaoException {

        try {
            this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
            this.statement = conexao.prepareStatement(SQLUtil.Telefone.SELECT_TELEFONE_CLIENTE);
            this.statement.setInt(1, id);

            result = this.statement.executeQuery();
            List<Telefone> contatos = new ArrayList<>();
            Telefone telefone;
            while (result.next()) {
//            	id ,cliente_id, numero, prefixo
                telefone = new Telefone();
                telefone.setId(result.getInt(1));
                telefone.setPrefixo(result.getInt(2));
                telefone.setNumero(result.getInt(3));
                contatos.add(telefone);
            }
            this.conexao.close();

            return contatos;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO CONSULTAR CONTATOS - Contate o ADM");
        }

    }
}
