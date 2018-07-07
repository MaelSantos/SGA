/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sga.interfaces;

import java.util.List;

import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.exceptions.DaoException;

public interface IDaoCommun {

    public void salvarEndereco(Endereco endereco) throws DaoException;

    public int getCurrentValorTabela(String nomeTabela) throws DaoException;

    public void salvarContato(Telefone telefone, int id) throws DaoException;

    public List<Telefone> getContatos(Integer id) throws DaoException;

}
