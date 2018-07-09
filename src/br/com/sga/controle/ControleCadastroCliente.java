package br.com.sga.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
	private TextField tfdNascimento;

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
    			telefones.add(new Telefone(Integer.parseInt(tfdNumero.getText().trim()), Integer.parseInt(tfdPrefixo.getText().trim()), 
    					TipoTelefone.getTipo(cbxTipoCliente.getValue().toString())));    			
    		}
    		else
    			Alerta.getInstance().showMensagem("Erro", "", "Add um tipo");
    		if(obj == btnCadastrar)
    		{
    			Cliente cliente = criarCliente();	
    			fachada.salvarCliente(cliente);
    		}
//    		if(obj == btn)
    	} catch (BusinessException e) {
    		Alerta.getInstance().showMensagem("Erro Ao Salvar", "", e.getMessage());
			e.printStackTrace();
		}
    System.out.println(telefones);
    }

    @Override
    public void atualizar(Tela tela, Funcionario usuario) {
    	
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
	}

	@SuppressWarnings("deprecation")
	public Cliente criarCliente()
	{
		Cliente cliente = new Cliente();

		cliente.setNome(tfdNome.getText().trim());
		cliente.setNascimento(new Date(tfdNascimento.getText()));
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
		
	}
	
	
}
