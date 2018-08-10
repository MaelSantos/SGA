package br.com.sga.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.EstadoCivil;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.entidade.enums.TipoTelefone;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControleCadastroCliente extends Controle{

	@FXML
	private ComboBox<TipoCliente> cbxTipoCliente;

	@FXML
	private TextField tfdCpfCnpj;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdRg;

	@FXML
	private DatePicker tfdNascimento;

	@FXML
	private ComboBox<Sexo> cbxGenero;

	@FXML
	private TextField tfdEmail;

	@FXML
	private TextField tfdProfissao;

	@FXML
	private ComboBox<EstadoCivil> cbxEstado_civil;

	@FXML
	private TextField tfdPrefixo;

	@FXML
	private TextField tfdTelefone;

	@FXML
	private ComboBox<TipoTelefone> cbxTipoTelefone;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField tfdPrefixoResponsavel;

	@FXML
	private TextField tfdTelefoneResponsavel;

	@FXML
	private ComboBox<TipoTelefone> cbxTelefoneResposavel;

	@FXML
	private RadioButton rbtSim;

	@FXML
	private RadioButton rbtNao;

	@FXML
	private TextField tfdResponsavel;

	@FXML
	private TextField tfdRua;

	@FXML
	private TextField tfdNumero;

	@FXML
	private ComboBox<Estado> cbxEstado;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCidade;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdPais;

	@FXML
	private TextField tfdComplemento;

	@FXML
	private Button btnVoltar;
	
	@FXML
	private Button btnCadastrar;

	private List<Telefone> telefones;

	private ToggleGroup group = new ToggleGroup();
	private IFachada fachada;
	private Funcionario funcionario;

	@FXML
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();
		Log log;
			if(obj == btnAdd)
			{
				try {
					
					telefones.add(new Telefone(Integer.parseInt(tfdTelefone.getText().trim()), 
							Integer.parseInt(tfdPrefixo.getText().trim()), 
							TipoTelefone.getTipo(cbxTipoTelefone.getValue().toString())));
					
					tfdTelefone.setText("");
					tfdPrefixo.setText("");
					
				} catch (Exception e) {
					Alerta.getInstance().showMensagem("Erro!!!", "Erro Ao Adicionar Telefone!!!", e.getMessage());
				}

			}
			if(obj == btnCadastrar)
			{
				try {
					Cliente cliente = criarCliente();	
					fachada.salvarEditarCliente(cliente);
					telefones.clear();					
					Alerta.getInstance().showMensagem("Salvando...", "Salvo Com Sucesso", "Salvando...");
					limparCampos();
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Novo Cliente: "+cliente.getCpf_cnpj(), StatusLog.COLCLUIDO);
				} catch (BusinessException e) {
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Novo Cliente: Erro", StatusLog.ERRO);
					e.printStackTrace();
				}
				
				try {
					fachada.salvarEditarLog(log);
				} catch (BusinessException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}
				
			}
			if(obj == btnVoltar)
				App.notificarOuvintes(Tela.clientes);
			if(obj == cbxTipoCliente)
			{
				if(cbxTipoCliente.getValue() == TipoCliente.FISICO)
				{
					tfdResponsavel.setVisible(false);
					tfdPrefixoResponsavel.setVisible(false);
					cbxTelefoneResposavel.setVisible(false);
					tfdTelefoneResponsavel.setVisible(false);
				}
				if(cbxTipoCliente.getValue() == TipoCliente.JURIDICO)
				{
					tfdResponsavel.setVisible(true);
					tfdPrefixoResponsavel.setVisible(true);
					cbxTelefoneResposavel.setVisible(true);
					tfdTelefoneResponsavel.setVisible(true);
				}
			}
	}


	private void limparCampos() {
		
		tfdBairro.setText("");
		tfdCep.setText("");
		tfdCidade.setText("");
		tfdComplemento.setText("");
		tfdCpfCnpj.setText("");
		tfdEmail.setText("");
		tfdNascimento.getEditor().setText("");
		tfdNome.setText("");
		tfdNumero.setText("");
		tfdPais.setText("");
		tfdPrefixo.setText("");
		tfdPrefixoResponsavel.setText("");
		tfdProfissao.setText("");
		tfdResponsavel.setText("");
		tfdRg.setText("");
		tfdRua.setText("");
		tfdTelefone.setText("");
		tfdTelefoneResponsavel.setText("");
	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			this.funcionario = (Funcionario) object;
			
		}
		
	}

	public Cliente criarCliente()
	{
		Cliente cliente = new Cliente();

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(tfdNascimento.getEditor().getText());
			cliente.setNascimento(data);

			cliente.setNome(tfdNome.getText().trim());
			cliente.setCpf_cnpj(tfdCpfCnpj.getText().trim());
			cliente.setGenero(Sexo.getSexo(cbxGenero.getValue().toString()));
			cliente.setRg(tfdRg.getText().trim());
			cliente.setEmail(tfdEmail.getText().trim());
			cliente.setEstado_civil(cbxEstado_civil.getValue().toString());
			cliente.setProfissao(tfdProfissao.getText().trim());
			cliente.setResponsavel(tfdResponsavel.getText().trim());
			cliente.setTipoCliente(TipoCliente.getTipo(cbxTipoCliente.getValue().toString()));

			if(rbtSim.isSelected())
				cliente.setFilhos(true);
			else if(rbtNao.isSelected())
				cliente.setFilhos(false);

			Endereco end = new Endereco();
			end.setBairro(tfdBairro.getText().trim());
			end.setCidade(tfdCidade.getText().trim());
			end.setRua(tfdRua.getText().trim());
			end.setEstado(cbxEstado.getValue().toString());
			end.setNumero(tfdNumero.getText().trim());
			end.setComplemento(tfdComplemento.getText().trim());
			end.setCep(tfdCep.getText().trim());
			end.setPais(tfdPais.getText().trim());
			cliente.setEndereco(end);

			cliente.setTelefones(telefones);

			return cliente;
		} catch (Exception e) {
			Alerta.getInstance().showMensagem("Erro Ao Cadastra!", "Informe Os Dados Do Cliente", e.getMessage());
		}
		return cliente;

	}


	@Override
	public void init() {
		telefones = new ArrayList<>();
		fachada = Fachada.getInstance();

		cbxTipoCliente.getItems().addAll(TipoCliente.values());
		cbxEstado_civil.getItems().addAll(EstadoCivil.values());
		cbxGenero.getItems().addAll(Sexo.values());
		cbxTipoTelefone.getItems().addAll(TipoTelefone.values());
		cbxTelefoneResposavel.getItems().addAll(TipoTelefone.values());
		cbxEstado.getItems().addAll(Estado.values());

		rbtNao.setToggleGroup(group);
		rbtSim.setToggleGroup(group);

		tfdResponsavel.setVisible(false);
		tfdPrefixoResponsavel.setVisible(false);
		cbxTelefoneResposavel.setVisible(false);
		tfdTelefoneResponsavel.setVisible(false);

		
	}

}
