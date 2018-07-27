package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.enums.EstadoCivil;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControleCliente extends Controle{

	@FXML
	private TextField tfdBusca;

	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnConsultas;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCpfCnpj;

	@FXML
	private TextField tfdRg;

	@FXML
	private DatePicker tfdNascimento;

	@FXML
	private TextField tfdGenero;

	@FXML
	private TextField tfdProfissao;

	@FXML
	private TextField tfdEmail;

	@FXML
	private TextField tfdResponsavel;

	@FXML
	private Label lblReponsavel;

	@FXML
	private ComboBox<Telefone> cbxTelefones;

	@FXML
	private ComboBox<TipoCliente> cbxTipo;

	@FXML
	private ComboBox<EstadoCivil> cbxEstadoCivil;

	@FXML
	private ComboBox<Boolean> cbxFilhos;

	@FXML
	private TextField tfdRua;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCidade;

	@FXML
	private TextField tfdEstado;

	@FXML
	private TextField tfdPais;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdComplemento;

	@FXML
	private Button btnProcessos;

	@FXML
	private Button btnContratos;

	private IFachada fachada;
	private Dialogo dialogo;
	private Cliente cliente;

	@Override
	public void atualizar(Tela tela, Object object) {


	}

	@Override
	public void init() {

		dialogo = Dialogo.getInstance();
		fachada = Fachada.getInstance();

		cbxEstadoCivil.getItems().setAll(EstadoCivil.values());
		cbxFilhos.getItems().setAll(true, false);
		cbxTipo.getItems().setAll(TipoCliente.values());

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnBuscar)
		{
			try {
//				ClienteAdapter adapter = dialogo.selecao(fachada.buscarClienteAdapterPorBusca(tfdBusca.getText()),"Seleção de cliente","Selcione um cliente para mais detalhes");
				ClienteAdapter adapter = dialogo.selecionar(fachada.buscarClienteAdapterPorBusca(tfdBusca.getText()));
				
				cliente = fachada.buscarClientePorId(adapter.getId());
				System.out.println(adapter);
				System.out.println(adapter.getId());
				System.out.println(cliente);
				System.out.println(cliente.getEndereco());
				modificarCampos();
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem("Erro!", "", e.getMessage());
				e.printStackTrace();
			}
		}
		else if(obj == btnAdd)
			App.notificarOuvintes(Tela.cadastro_cliente);
		else if(obj == btnContratos)
		{
			if(cliente != null)
				App.notificarOuvintes(Tela.Detalhes_contrato,cliente);
			else
				Alerta.getInstance().showMensagem("Alerta", "", "Nehum cliente selecionado : \nfavor selecionar um clientes e após clikar para visualizar suas contratos");
		}
		else if(obj == btnProcessos)
		{
			//			App.notificarOuvintes(Tela.detalhes_processo, fachada.buscaproc);
		}
		else if(obj == btnConsultas ) {
			if(cliente != null)
				App.notificarOuvintes(Tela.Detalhes_consulta,cliente);
			else
				Alerta.getInstance().showMensagem("Alerta", "", "Nehum cliente selecionado : \nfavor selecionar um clientes e após clikar para visualizar suas consultas");
		}
		else if(obj == btnSalvar)
		{
			try {
				alterarCiente();
				fachada.salvarEditarCliente(cliente);
				Alerta.getInstance().showMensagem("Salvo", "", "Cliente Editado Com Sucesso!!!");
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem("Erro!!!", "Erro Ao Editar Cliente!!!", e.getMessage());
				e.printStackTrace();
			}
		}
		


	}

	private void alterarCiente() {

		//Endereco
		cliente.getEndereco().setBairro(tfdBairro.getText().trim());
		cliente.getEndereco().setCep(tfdCep.getText().trim());
		cliente.getEndereco().setCidade(tfdCidade.getText().trim());
		cliente.getEndereco().setComplemento(tfdComplemento.getText().trim());
		cliente.getEndereco().setEstado(tfdEstado.getText().trim());
		cliente.getEndereco().setNumero(tfdNumero.getText());
		cliente.getEndereco().setPais(tfdPais.getText().trim());
		cliente.getEndereco().setRua(tfdRua.getText().trim());

		//Cliente
		cliente.setNome(tfdNome.getText().trim());
		cliente.setCpf_cnpj(tfdCpfCnpj.getText().trim());
		cliente.setEmail(tfdEmail.getText().trim());
		cliente.setEstado_civil(cbxEstadoCivil.getValue().name());
		cliente.setGenero(Sexo.getSexo(tfdGenero.getText().trim()));
		
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(tfdNascimento.getEditor().getText().trim());
			cliente.setNascimento(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		cliente.setProfissao(tfdProfissao.getText().trim());
		cliente.setResponsavel(tfdResponsavel.getText().trim());
		cliente.setRg(tfdRg.getText().trim());
		cliente.setTipoCliente(cbxTipo.getValue());

		cliente.setFilhos(cbxFilhos.getValue());
		
		if(cliente.getTipoCliente() == TipoCliente.JURIDICO)
		{
			lblReponsavel.setVisible(true);
			tfdResponsavel.setVisible(true);
		}
		else
		{
			lblReponsavel.setVisible(false);
			tfdResponsavel.setVisible(false);
		}
	}

	private void modificarCampos() {

		//Endereco
		tfdBairro.setText(cliente.getEndereco().getBairro());
		tfdCep.setText(cliente.getEndereco().getCep());
		tfdCidade.setText(cliente.getEndereco().getCidade());
		tfdComplemento.setText(cliente.getEndereco().getComplemento());
		tfdEstado.setText(cliente.getEndereco().getEstado());
		tfdNumero.setText(cliente.getEndereco().getNumero());
		tfdPais.setText(cliente.getEndereco().getPais());
		tfdRua.setText(cliente.getEndereco().getRua());

		//Cliente
		tfdNome.setText(cliente.getNome());
		tfdCpfCnpj.setText(cliente.getCpf_cnpj());
		tfdEmail.setText(cliente.getEmail());
		cbxEstadoCivil.setValue(EstadoCivil.getValor(cliente.getEstado_civil()));
		tfdGenero.setText(cliente.getGenero().getSexo());
		
		try {
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			tfdNascimento.getEditor().setText(df.parse(cliente.getNascimento().toString()).toString());
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		
		tfdProfissao.setText(cliente.getProfissao());
		tfdResponsavel.setText(cliente.getResponsavel());
		tfdRg.setText(cliente.getRg());
		cbxTipo.setValue(cliente.getTipoCliente());
		cbxTelefones.getItems().setAll(cliente.getTelefones());

		cbxFilhos.setValue(cliente.isFilhos());

		if(cliente.getTipoCliente() == TipoCliente.JURIDICO)
		{
			lblReponsavel.setVisible(true);
			tfdResponsavel.setVisible(true);
		}
		else
		{
			lblReponsavel.setVisible(false);
			tfdResponsavel.setVisible(false);
		}
	}

}