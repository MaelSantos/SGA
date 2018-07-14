package br.com.sga.controle;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCliente;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.EstadoCivil;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.entidade.enums.TipoTelefone;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCliente;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.view.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;

public class ControleCadastroCliente implements Initializable, Ouvinte{

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

	@FXML
	void actionButton(ActionEvent event) {

		Object obj = event.getSource();
		try {
			if(obj == btnAdd)
			{
				telefones.add(new Telefone(Integer.parseInt(tfdTelefone.getText().trim()), 
						Integer.parseInt(tfdPrefixo.getText().trim()), 
						TipoTelefone.getTipo(cbxTipoTelefone.getValue().toString())));

				tfdTelefone.setText("");
				tfdPrefixo.setText("");

			}
			if(obj == btnCadastrar)
			{
				Cliente cliente = criarCliente();	
				fachada.salvarEditarCliente(cliente);
				telefones.clear();

				Alerta.getInstance().showMensagem("Salvando...", "Salvo Com Sucesso", "Salvando...");
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
		} catch (BusinessException e) {
			Alerta.getInstance().showMensagem("Erro Ao Salvar", "", e.getMessage());
//			e.printStackTrace();
		}catch (NullPointerException e2) {
			Alerta.getInstance().showMensagem("Erro Ao Adicionar", "Informe Os Dados Necessarios", e2.getMessage());
		}
		catch (NumberFormatException e3) {
			Alerta.getInstance().showMensagem("Erro Ao Adicionar Telefone", "Informe Algum Um Valor Valido", e3.getMessage());
		}
		System.out.println(telefones);
	}


	@Override
	public void atualizar(Tela tela, Object object) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(this);

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

		UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

			@Override
			public TextFormatter.Change apply(TextFormatter.Change t) {

				if (t.isReplaced()) 
					if(t.getText().matches("[^0-9]"))
						t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));


				if (t.isAdded()) {
					if (t.getControlText().contains(".")) {
						if (t.getText().matches("[^0-9]")) {
							t.setText("");
						}
					} else if (t.getText().matches("[^0-9.]")) {
						t.setText("");
					}
				}

				return t;
			}
		};

		tfdCpfCnpj.setTextFormatter(new TextFormatter<>(filter));
		tfdCep.setTextFormatter(new TextFormatter<>(filter));
		tfdNumero.setTextFormatter(new TextFormatter<>(filter));
		tfdPrefixo.setTextFormatter(new TextFormatter<>(filter));
		tfdPrefixoResponsavel.setTextFormatter(new TextFormatter<>(filter));
		tfdRg.setTextFormatter(new TextFormatter<>(filter));
		tfdTelefone.setTextFormatter(new TextFormatter<>(filter));
		tfdTelefoneResponsavel.setTextFormatter(new TextFormatter<>(filter));

		tfdResponsavel.setVisible(false);
		tfdPrefixoResponsavel.setVisible(false);
		cbxTelefoneResposavel.setVisible(false);
		tfdTelefoneResponsavel.setVisible(false);
		
	}

	public TextFormatter<?> criarMascara()
	{
		UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

			@Override
			public TextFormatter.Change apply(TextFormatter.Change t) {

				if (t.isReplaced()) 
					if(t.getText().matches("[^0-9]"))
						t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));

				if (t.isAdded()) {
					if (t.getControlText().contains(".")) {
						if (t.getText().matches("[^0-9]")) {
							t.setText("");
						}
					} else if (t.getText().matches("[^0-9.]")) {
						t.setText("");
					}
				}

				return t;
			}
		};

		return new TextFormatter<>(filter);
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

}
