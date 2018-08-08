package br.com.sga.controle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleDetalhesConsulta extends Controle{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dataConsultaField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField indicacaoField;

    @FXML
    private TextField honorarioField;

    @FXML
    private TextArea descricaoField;

    @FXML
    private Button selectConButton;
    
    @FXML
    private Button voltarButton;

    @FXML
    private TextField nomeFuncionarioField;

    @FXML
    private TextField numeroOabField;

    @FXML
    private TextField cepField;

    @FXML
    private TextField nomeTestemunhaField;

    @FXML
    private TextField telPreField;

    @FXML
    private TextField telNumField;

    @FXML
    private TextField ruaField;

    @FXML
    private TextField numField;

    @FXML
    private TextField bairroField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField paisField;

    @FXML
    private ComboBox<Estado> estadoBox;

    @FXML
    private TextField compField;

    @FXML
    private Button selectTestmunhaButton;
    
    private Cliente cliente;
    
    private Consulta consulta;
    
    private IFachada fachada ;
    
    private Dialogo dialogo;
	@Override
	public void actionButton(ActionEvent event) {
		
		if(voltarButton == event.getSource() ) {
			if(selectConButton.isVisible()) { // siginifica ter vindo da tela de cliente
				App.notificarOuvintes(Tela.clientes);
			}else {// siginifica ter vindo da tela de consulta
				App.notificarOuvintes(Tela.Consulta);
			}
		}
		else if(selectConButton == event.getSource()) {
			try {
				String busca[] = {cliente.getCpf_cnpj()};
				
				List<ConsultaAdapter> consultas = fachada.buscarConsultaPorClienteAdapter(busca);
				ConsultaAdapter consultaBasica = Dialogo.getInstance().selecionar(consultas);
				consulta = new Consulta();
				consulta.setId(consultaBasica.getId());
				atualizarDadosConsulta();
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}else if(selectTestmunhaButton== event.getSource()) {
			Testemunha testemunha = dialogo.selecionar(consulta.getTestemunhas());
			Telefone t = testemunha.getTelefone();
			Endereco e = testemunha.getEndereco();
			nomeTestemunhaField.setText(testemunha.getNome());
			telPreField.setText(t.getPrefixo().toString());
			telNumField.setText(t.getNumero().toString());
			ruaField.setText(e.getRua());
			numField.setText(e.getNumero());
			bairroField.setText(e.getBairro());
			cidadeField.setText(e.getCidade());
			estadoBox.setPromptText(e.getEstado());
			paisField.setText(e.getPais());
			compField.setText(e.getComplemento());
			cepField.setText(e.getCep());
		}
			
	}
	@Override
	public void atualizar(Tela tela, Object object) {
		if(tela == Tela.Detalhes_consulta) {
			limparCampos();
			if(object instanceof Cliente) {
				Cliente cliente = (Cliente) object;
				if(this.cliente == null || !this.cliente.equals(cliente) )
					this.cliente = cliente;
				selectConButton.setVisible(true);
			}else if(object instanceof ConsultaAdapter) {
				this.consulta = new Consulta(); 
				this.consulta.setId(((ConsultaAdapter)(object)).getId());
				selectConButton.setVisible(false);
				atualizarDadosConsulta();
			}
		}else {
			consulta = null;
		}
			
	}

	private void limparCampos() {
		
		dataConsultaField.setText("");
		honorarioField.setText("");
		descricaoField.setText("");
		indicacaoField.setText("");
		areaField.setText("");
		
		nomeTestemunhaField.setText("");
		telPreField.setText("");
		telNumField.setText("");
		ruaField.setText("");
		numField.setText("");
		bairroField.setText("");
		cidadeField.setText("");
		estadoBox.setPromptText("");
		paisField.setText("");
		compField.setText("");
		cepField.setText("");
		
	}
	@Override
	public void init() {
		fachada = Fachada.getInstance(); 
		dialogo = Dialogo.getInstance();
	}

	private void atualizarDadosConsulta() {
		try {
			// pegando demais dados da consulta;
			consulta = fachada.buscarConsultaPorId(consulta.getId());
			FuncionarioAdapter f = fachada.buscarPorConsultaAdapter(consulta.getId());
			// adicionando dados que não podem ser editador tais nome e numero do funcionario advindos do consulta adapter
			nomeFuncionarioField.setText(f.getNome());
			numeroOabField.setText(f.getNumero());
			
			dataConsultaField.setText(consulta.getData_consulta().toString());
			honorarioField.setText(consulta.getValor_honorario()+"");
			descricaoField.setText(consulta.getDescricao());
			indicacaoField.setText(consulta.getIndicacao());
			areaField.setText(consulta.getArea().toString());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
