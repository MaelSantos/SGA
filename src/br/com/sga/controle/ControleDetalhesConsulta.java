package br.com.sga.controle;

import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.business.BusinessUtil;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.MaskFieldUtil;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.FuncionarioAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class ControleDetalhesConsulta extends Controle {


	@FXML
	private DatePicker dataConsultaPicker;

	@FXML
	private ComboBox<Area> areaBox;

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
	private Button salvarEditButton;

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
	private FlowPane paneEdit;
	
	@FXML
	private Button selectTestmunhaButton;

	private Cliente cliente;
	private Consulta consulta;
	private IFachada fachada;
	private Dialogo dialogo;

	private Funcionario funcionario;

	@Override
	public void actionButton(ActionEvent event) {

		if (voltarButton == event.getSource()) {
			if (selectConButton.isVisible()) { // siginifica ter vindo da tela de cliente
				App.notificarOuvintes(Tela.CLIENTES);
			} else {// siginifica ter vindo da tela de consulta
				App.notificarOuvintes(Tela.CONSULTA);
			}
		} else if (selectConButton == event.getSource()) {
			Log log = null;
			try {
				
				String busca[] = { cliente.getCpf_cnpj() };
				List<ConsultaAdapter> consultas = fachada.buscarConsultaPorClienteAdapter(busca);
				ConsultaAdapter consultaBasica = Dialogo.getInstance().selecionar(consultas);
				if(consultaBasica != null)
				{
					consulta = new Consulta();
					consulta.setId(consultaBasica.getId());
					atualizarDadosConsulta();
					
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
							"Buscar Consulta: " + busca, StatusLog.CONCLUIDO);					
				}
				else if(consultas.isEmpty())
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
							"Buscar Consulta: " + busca, StatusLog.SEM_RESULTADOS);

			} catch (BusinessException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
						"Buscar Consulta: Erro", StatusLog.ERRO);
				e.printStackTrace();
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}

		} else if (selectTestmunhaButton == event.getSource()) {
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
			estadoBox.setPromptText(e.getEstado().toString());
			paisField.setText(e.getPais());
			compField.setText(e.getComplemento());
			cepField.setText(e.getCep());
		}

	}
	
	private Boolean editando = false;
	
	@FXML
	public void editKeyTypedHandler(KeyEvent e) {
		attEdit();
	}
	@FXML
	public void editMouseCliked(MouseEvent e) {
		System.out.println("n");
		attEdit();
	}
	private void attEdit() {
		if(!editando)
			if(Alerta.getInstance().showConfirmacao("Edição","Confirmação de edição:","Tem certeza que deseja editar informações ?")) {
				editando = true;
				liberarCamposParaEdicao();
			}
	}
	
	private void liberarCamposParaEdicao() {
		
		paneEdit.setVisible(editando);
		
		honorarioField.setEditable(editando);
		descricaoField.setEditable(editando);
		indicacaoField.setEditable(editando);

		nomeTestemunhaField.setEditable(editando);
		telPreField.setEditable(editando);
		telNumField.setEditable(editando);
		ruaField.setEditable(editando);
		numField.setEditable(editando);
		bairroField.setEditable(editando);
		cidadeField.setEditable(editando);
		paisField.setEditable(editando);
		compField.setEditable(editando);
		cepField.setEditable(editando);
		
	}
	

	@Override
	public void atualizar(Tela tela, Object object) {
		if (tela == Tela.DETALHES_CONSULTA) {
			limparCampos();
			if (object instanceof Cliente) {
				Cliente cliente = (Cliente) object;
				if (this.cliente == null || !this.cliente.equals(cliente))
					this.cliente = cliente;
				selectConButton.setVisible(true);
			} else if (object instanceof ConsultaAdapter) {
				this.consulta = new Consulta();
				this.consulta.setId(((ConsultaAdapter) (object)).getId());
				selectConButton.setVisible(false);
				atualizarDadosConsulta();
				System.out.println("Adapter: "+consulta);
			}
			else if (object instanceof Consulta) {
				this.consulta = (Consulta) object;
				selectConButton.setVisible(true);
				atualizarDadosConsulta();
				System.out.println("Consulta: "+consulta);
				
			}
		} else {
			consulta = null;
		}

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;

		}

	}

	private void limparCampos() {

		dataConsultaPicker.setValue(null);
		honorarioField.setText("");
		descricaoField.setText("");
		indicacaoField.setText("");
		areaBox.setValue(null);

		nomeTestemunhaField.setText("");
		telPreField.setText("");
		telNumField.setText("");
		ruaField.setText("");
		numField.setText("");
		bairroField.setText("");
		cidadeField.setText("");
		estadoBox.setValue(null);
		paisField.setText("");
		compField.setText("");
		cepField.setText("");

	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();
		areaBox.getItems().addAll(Area.values());
		areaBox.setOpacity(1);
		dataConsultaPicker.setOpacity(1);
		estadoBox.setOpacity(1);
		MaskFieldUtil.numericField(honorarioField);
		MaskFieldUtil.numericField(telNumField);
		MaskFieldUtil.numericField(telPreField);
		MaskFieldUtil.numericField(cepField);
	}

	private void atualizarDadosConsulta() {

		Log log;
		try {
			// pegando demais dados da consulta;
			consulta = fachada.buscarConsultaPorId(consulta.getId());
			log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
					"Buscar Consulta: " + consulta.getArea(), StatusLog.CONCLUIDO);

			FuncionarioAdapter f = fachada.buscarUsuarioPorConsultaAdapter(consulta.getId());
			// adicionando dados que não podem ser editador tais nome e numero do
			// funcionario advindos do consulta adapter
			nomeFuncionarioField.setText(f.getNome());
			numeroOabField.setText(f.getNumero());
			dataConsultaPicker.setValue(BusinessUtil.toLocalDate(consulta.getData_consulta()));
			honorarioField.setText(consulta.getValor_honorario() + "");
			descricaoField.setText(consulta.getDescricao());
			indicacaoField.setText(consulta.getIndicacao());
			areaBox.setValue(consulta.getArea());
		} catch (BusinessException e) {
			log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
					"Buscar Consulta: Erro", StatusLog.ERRO);
			e.printStackTrace();
		}

		try {
			if (log != null)
				fachada.salvarEditarLog(log);
		} catch (BusinessException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
}
