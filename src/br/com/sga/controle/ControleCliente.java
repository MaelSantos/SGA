package br.com.sga.controle;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Telefone;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControleCliente extends Controle{


	@FXML
	private TextField tfdBusca;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEditar;
	
	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdCpfCnpj;

	@FXML
	private TextField tfdRg;

	@FXML
	private TextField tfdNascimento;

	@FXML
	private TextField tfdGenero;

	@FXML
	private TextField tfdEstadoCivil;

	@FXML
	private TextField tfdProfissao;

	@FXML
	private TextField tfdFilhos;

	@FXML
	private TextField tfdEmail;

	@FXML
	private TextField tfdResponsavel;

	@FXML
	private Label lblReponsavel;

	@FXML
	private ComboBox<Telefone> cbxTelefones;

	@FXML
	private TextField tfdTipo;

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
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public void init() {

		dialogo = Dialogo.getInstance();
		fachada = Fachada.getInstance();
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnBuscar)
		{
			try {
				cliente = dialogo.selecaoCliente(fachada.buscarClientePorBusca(tfdBusca.getText().trim()));
				modificarCampos();
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem("Erro!", "", e.getMessage());
				e.printStackTrace();
			}
		}
		if(obj == btnAdd)
			App.notificarOuvintes(Tela.cadastro_cliente);
		if(obj == btnContratos)
		{
			//			App.notificarOuvintes(Tela.cadastro_consulta, fachada.buscarContratoPorCliente(cliente.getNome()));
		}
		if(obj == btnProcessos)
		{
			//			App.notificarOuvintes(Tela.detalhes_processo, fachada.buscaproc);
		}
		if(obj == btnEditar)
		{
			
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
		tfdEstadoCivil.setText(cliente.getEstado_civil());
		tfdGenero.setText(cliente.getGenero().getSexo());
		tfdNascimento.setText(cliente.getNascimento().toString());
		tfdProfissao.setText(cliente.getProfissao());
		tfdResponsavel.setText(cliente.getResponsavel());
		tfdRg.setText(cliente.getRg());
		tfdTipo.setText(cliente.getTipoCliente().toString());
		cbxTelefones.getItems().setAll(cliente.getTelefones());
		
		if(cliente.isFilhos())
			tfdFilhos.setText("Sim");
		else
			tfdFilhos.setText("Não");
		
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