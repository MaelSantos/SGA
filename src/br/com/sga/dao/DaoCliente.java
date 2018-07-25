package br.com.sga.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.business.BusinessCliente;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Despesa;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.exceptions.BusinessException;
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
	
	public DaoCliente() {
		daoCommun = DaoCommun.getInstance();
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
			statement.setString(11, entidade.getTipoCliente().toString());
			statement.setInt(12, id_endereco);
			
			statement.execute();
			int id_cliente = daoCommun.getCurrentValorTabela(Tabela.CLIENTE);
			this.conexao.close();
			if(entidade.getTelefones() != null)
				for(Telefone telefone : entidade.getTelefones())
					daoCommun.salvarContato(telefone, id_cliente, Tabela.CLIENTE);
			entidade.setId(id_cliente);
			entidade.getEndereco().setId(id_endereco);

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO SALVAR CLIENTE - CONTATE O ADM");
		}
	}

	@Override
	public void editar(Cliente entidade) throws DaoException {
		
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Cliente.UPDATE_ALL);

			
//			UPDATE CLIENTE SET nome = ?, data_nascimento = ?, cpf_cnpj = ?, genero = ?, rg = ?, email = ?, 
//			estado_civil = ?, profissao = ?, filhos = ?, responsavel = ?, tipo = ? where id = ?";
			
			statement.setString(1,entidade.getNome());
			statement.setDate(2,new Date(entidade.getNascimento().getTime()));
			statement.setString(3,entidade.getCpf_cnpj());
			statement.setString(4,entidade.getGenero().name());
			statement.setString(5,entidade.getRg());
			statement.setString(6,entidade.getEmail());
			statement.setString(7,entidade.getEstado_civil());
			statement.setString(8,entidade.getProfissao());
			statement.setBoolean(9,entidade.isFilhos());
			statement.setString(10,entidade.getResponsavel());
			statement.setString(11,entidade.getTipoCliente().name());
			statement.setInt(12,entidade.getId());
						
			statement.executeUpdate();

			daoCommun.EditarEndereco(entidade.getEndereco());
			
			this.conexao.close();

		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("PROBLEMA AO ATUALIZAR DADOS FINANCEIROS - CONTATE O ADM");
		}


	}

	@Override
	public Cliente buscarPorId(int id) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Cliente.SELECT_ID);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();
			Cliente cliente;
			Endereco end;
			if (resultSet.next()) {
				cliente = new Cliente();
//				nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;	
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setNascimento(resultSet.getDate("data_nascimento"));
				cliente.setCpf_cnpj(resultSet.getString("cpf_cnpj"));
				cliente.setGenero(Sexo.getSexo(resultSet.getString("genero")));
				cliente.setRg(resultSet.getString("rg"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setEstado_civil(resultSet.getString("estado_civil"));
				cliente.setProfissao(resultSet.getString("profissao"));
				cliente.setFilhos(resultSet.getBoolean("filhos"));
				cliente.setResponsavel(resultSet.getString("responsavel"));
				cliente.setTipoCliente(TipoCliente.getTipo(resultSet.getString("tipo")));
//				end = new Endereco();

				end = daoCommun.getEndereco(cliente.getId());
				
				cliente.setEndereco(end);
				
				List<Telefone> list = daoCommun.getContatos(cliente.getId());
				cliente.setTelefones(list);
				this.conexao.close();			
				
				return cliente;
			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
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
//				nome; nascimento; cpf_cnpj; genero; rg; email; estado_civil; profissao; filhos; responsavel; tipo; id_endereco;	
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setNascimento(resultSet.getDate("data_nascimento"));
				cliente.setCpf_cnpj(resultSet.getString("cpf_cnpj"));
				cliente.setGenero(Sexo.getSexo(resultSet.getString("genero")));
				cliente.setRg(resultSet.getString("rg"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setEstado_civil(resultSet.getString("estado_civil"));
				cliente.setProfissao(resultSet.getString("profissao"));
				cliente.setFilhos(resultSet.getBoolean("filhos"));
				cliente.setResponsavel(resultSet.getString("responsavel"));
				cliente.setTipoCliente(TipoCliente.getTipo(resultSet.getString("tipo")));
				end = new Endereco();
				end.setId(resultSet.getInt("id_endereco"));
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
				
				return cliente;
			}
//			else {
//				throw new DaoException("CLIENTE NÃO EXISTE");
//			}

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
		return null;

	}
	
	@Override
	public List<Cliente> buscarPorBusca(String busca) throws DaoException {

		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Cliente.BUSCAR_ALL);
			this.statement.setString(1,"%"+busca+"%");
			this.statement.setString(2,busca);
			this.statement.setString(3,busca);
			this.statement.setString(4,"%"+busca+"%");
			this.statement.setString(5,busca);
			this.statement.setString(6,busca);
			resultSet = this.statement.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			Endereco endereco;
			while(resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setNascimento(resultSet.getDate("data_nascimento"));
				cliente.setCpf_cnpj(resultSet.getString("cpf_cnpj"));
				cliente.setGenero(Sexo.getSexo(resultSet.getString("genero")));
				cliente.setRg(resultSet.getString("rg"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setEstado_civil(resultSet.getString("estado_civil"));
				cliente.setProfissao(resultSet.getString("profissao"));
				cliente.setFilhos(resultSet.getBoolean("filhos"));
				cliente.setResponsavel(resultSet.getString("responsavel"));
				cliente.setTipoCliente(TipoCliente.getTipo(resultSet.getString("tipo")));
				
				endereco = daoCommun.getEndereco(resultSet.getInt("id_endereco"));
				cliente.setEndereco(endereco);
				
				List<Telefone> list = daoCommun.getContatos(cliente.getId());
				cliente.setTelefones(list);
				clientes.add(cliente);	
			}if(clientes.isEmpty())
				new DaoException("NÃO HÁ CLIENTES CADASTRADOS COM ESSES DADOS");

			this.conexao.close();			
			return clientes;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}

	}

	@Override
	public List<ClienteAdapter> buscarAdapterPorBusca(String busca) throws DaoException {
		try {
			this.conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONNECTION_POSTGRESS);
			this.statement = conexao.prepareStatement(SQLUtil.Cliente.BUSCAR_ALL_ADAPTER);
			this.statement.setString(1,"%"+busca+"%");
			this.statement.setString(2,busca);
			this.statement.setString(3,busca);
			this.statement.setString(4,"%"+busca+"%");
			this.statement.setString(5,busca);
			this.statement.setString(6,busca);
			resultSet = this.statement.executeQuery();
			
			List<ClienteAdapter> clientes = new ArrayList<>();
			
			while(resultSet.next()) {
				
				ClienteAdapter cliente = new ClienteAdapter();
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf_cnpj(resultSet.getString("cpf_cnpj"));
				cliente.setEmail(resultSet.getString("email"));
				
				clientes.add(cliente);	
			}if(clientes.isEmpty())
				new DaoException("NÃO HÁ CLIENTES CADASTRADOS COM ESSES DADOS");

			this.conexao.close();			
			return clientes;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}	
}
