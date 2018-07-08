package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.exceptions.DaoException;
import br.com.sga.interfaces.IDaoCliente;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.sql.SQLConnection;
import br.com.sga.sql.SQLUtil;

public class DaoCliente implements IDaoCliente {

	private Connection conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private IDaoCommun daoCommun;

	private static DaoCliente instance;
	private DaoCliente() {
		daoCommun = new DaoCommun();
	}

	public static DaoCliente getInstance() {
		if(instance == null)
			instance = new DaoCliente();
		return instance;
	}
	
	@Override
	public void salvar(Cliente entidade) throws DaoException {

		try {
			daoCommun.salvarEndereco(entidade.getEndereco());
			int id_endereco = daoCommun.getCurrentValorTabela(Tabela.ENDERECO);
			
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Cliente.INSERT_ALL);
			

			//  nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;
			statement.setString(1, entidade.getNome());
			statement.setDate(2, new Date(entidade.getNascimento().getTime()));
			statement.setString(3, entidade.getCpf_cnpj());
			statement.setString(4, entidade.getGenero().getSexo());
			statement.setString(5, entidade.getRg());
			statement.setString(6, entidade.getEmail());
			statement.setString(7, entidade.getEstado_civil());
			statement.setString(8, entidade.getProfissao());
			statement.setBoolean(9, entidade.isFilhos());
			statement.setString(10, entidade.getResponsavel());
			statement.setString(11, entidade.getResponsavel());
			statement.setInt(12, id_endereco);
			
			statement.execute();
			int id_cliente = daoCommun.getCurrentValorTabela(Tabela.CLIENTE);
			this.conexao.close();
			for(Telefone telefone : entidade.getTelefones())
				daoCommun.salvarContato(telefone, id_cliente,Tabela.CLIENTE);
			entidade.setId(id_cliente);
			entidade.getEndereco().setId(id_endereco);

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR CLIENTE - CONTATE O ADM");
		}
	}

	@Override
	public void editar(Cliente entidade) throws DaoException {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public Cliente buscarPorId(int id) throws DaoException {
		return null;
	}


	@Override
	public Cliente buscarPorCodigo(String cpf_cnpj) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Cliente.SELECT_CPF_CNPJ);
			this.statement.setString(1, cpf_cnpj);

			resultSet = this.statement.executeQuery();
			Cliente cliente;
			Endereco end;
			if (resultSet.next()) {
				cliente = new Cliente();
				//                nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;	
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setNascimento(resultSet.getDate("nascimento"));
				cliente.setCpf_cnpj(resultSet.getString("cpf_cnpj"));
				cliente.setGenero(Sexo.getSexo(resultSet.getString("genero")));
				cliente.setRg(resultSet.getString("rg"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setEstado_civil(resultSet.getString("estado_civil"));
				cliente.setProfissao(resultSet.getString("profissao"));
				cliente.setFilhos(resultSet.getBoolean("filhos"));
				cliente.setResponsavel(resultSet.getString("resposavel"));
				cliente.setTipoCliente(TipoCliente.getTipo(resultSet.getString("tipo")));
				end = new Endereco();
				end.setId(resultSet.getInt("endereco_id"));
				end.setBairro(resultSet.getString("bairro"));
				end.setCidade(resultSet.getString("cidade"));
				end.setRua(resultSet.getString("rua"));
				end.setEstado(resultSet.getString("estado"));
				end.setNumero(resultSet.getString("numero"));
				end.setComplemento(resultSet.getString("complemento"));
				end.setCep(resultSet.getString("cep"));
				end.setPais(resultSet.getString("pais"));
				cliente.setEndereco(end);

				List<Telefone> list = daoCommun.getContatos(cliente.getId());
				cliente.setTelefones(list);
				this.conexao.close();				
			}
			else {
				throw new DaoException("CLIENTE NÃO EXISTE");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;

	}

	@Override
	public List<Cliente> buscarPorBusca(String busca) throws DaoException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

}
