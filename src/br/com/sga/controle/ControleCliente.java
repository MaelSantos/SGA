package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.MaskFieldUtil;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.adapter.ConsultaAdapter;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.EstadoCivil;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Sexo;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoCliente;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControleCliente extends Controle {

	@FXML
	private TextField tfdBusca;

	@FXML
	private Button btnBuscar;

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
	private DatePicker tfdNascimento;

	@FXML
	private ComboBox<Sexo> cbxGenero;

	@FXML
	private TextField tfdRua;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCidade;

	@FXML
	private TextField tfdPais;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdComplemento;

	@FXML
	private ComboBox<Estado> cbxEstado;

	@FXML
	private Button btnProcessos;

	@FXML
	private Button btnContratos;

	@FXML
	private Button btnConsultas;
	private IFachada fachada;
	private Dialogo dialogo;
	private Cliente cliente;
	private Funcionario funcionario;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {

			funcionario = (Funcionario) object;

		}

	}

	@Override
	public void init() {

		dialogo = Dialogo.getInstance();
		fachada = Fachada.getInstance();

		cbxEstadoCivil.getItems().setAll(EstadoCivil.values());
		cbxFilhos.getItems().setAll(true, false);
		cbxTipo.getItems().setAll(TipoCliente.values());
		cbxEstado.getItems().setAll(Estado.values());
		cbxGenero.getItems().setAll(Sexo.values());

		MaskFieldUtil.cpfCnpjField(tfdCpfCnpj);
		MaskFieldUtil.numericField(tfdCep);

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnBuscar) {
			Log log = null;
			try {
				if(!tfdBusca.getText().trim().isEmpty())
				{
					ClienteAdapter adapter = dialogo
							.selecionar(fachada.buscarClienteAdapterPorBusca(tfdBusca.getText().trim()));
					
					cliente = fachada.buscarClientePorId(adapter.getId());
					
					if (cliente != null)
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
								"Buscar Cliente: " + cliente.getCpf_cnpj(), StatusLog.CONCLUIDO);
					else
						log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
								"Buscar Cliente: Não Encontrado", StatusLog.SEM_RESULTADOS);
					modificarCampos();					
				}
				else
					Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!", "Por favor Informe Um Dado Para Pesquisa!!!", "");
				
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "", e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
						"Buscar Cliente: Erro", StatusLog.ERRO);
				e.printStackTrace();
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		} else if (obj == btnAdd)
			App.notificarOuvintes(Tela.CADASTRO_CLIENTE);
		else if (obj == btnContratos) {
			if (cliente != null) {
				ContratoAdapter adapter;
				try {
					adapter = dialogo.selecionar(fachada.buscarContratoPorClienteAdapter(cliente.getCpf_cnpj()));
					Contrato contrato = fachada.buscarContratoPorId(adapter.getId());
					if (adapter != null) {
						App.notificarOuvintes(Tela.DETALHES_CONTRATO, cliente);
						App.notificarOuvintes(Tela.DETALHES_CONTRATO, contrato);
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
						"Nenhum Cliente Selecionado : \nFavor Selecionar Um Cliente e Após Clicar Para Visualizar Seus Contratos");
		} else if (obj == btnProcessos) {
			if (cliente != null) {
				ProcessoAdapter adapter;
				try {
					adapter = dialogo
							.selecionar(fachada.buscaProcessoPorClienteAdapter(cliente.getId()));
					Processo processo = fachada.buscarProcessoPorId(adapter.getId());
					App.notificarOuvintes(Tela.DETALHES_PROCESSO, processo);
				} catch (BusinessException e) {
					e.printStackTrace();
				}

			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
						"Nenhum Cliente Selecionado : \nFavor Selecionar Um Cliente e Após Clicar Para Visualizar Seus Processos");
		} else if (obj == btnConsultas) {
			if (cliente != null) {
				ConsultaAdapter adapter;
				try {
					adapter = dialogo.selecionar(
							fachada.buscarConsultaPorClienteAdapter(new String[] { cliente.getCpf_cnpj() }));
					Consulta consulta = fachada.buscarConsultaPorId(adapter.getId());
					if (adapter != null) {
						App.notificarOuvintes(Tela.DETALHES_CONSULTA, cliente);
						App.notificarOuvintes(Tela.DETALHES_CONSULTA, consulta);
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}

			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta", "",
						"Nenhum Cliente Selecionado : \nFavor Selecionar Um Cliente e Após Clicar Para Visualizar Suas Consultas");
		} else if (obj == btnSalvar) {
			Log log = null;
			try {
				alterarCiente();
				fachada.salvarEditarCliente(cliente);
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Salvo", "", "Cliente Editado Com Sucesso!!!");
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(),
						"Editar Cliente: " + cliente.getCpf_cnpj(), StatusLog.CONCLUIDO);
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!!!", "Erro Ao Editar Cliente!!!", e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(),
						"Editar Cliente: " + cliente.getCpf_cnpj(), StatusLog.ERRO);
				e.printStackTrace();
			} catch (NullPointerException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!!!", "Por favor Selecione Primeiro Um Cliente!!!",
						e.getMessage());
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		}

	}

	private void alterarCiente() {

		// Endereco
		cliente.getEndereco().setBairro(tfdBairro.getText().trim());
		cliente.getEndereco().setCep(tfdCep.getText().trim());
		cliente.getEndereco().setCidade(tfdCidade.getText().trim());
		cliente.getEndereco().setComplemento(tfdComplemento.getText().trim());
		cliente.getEndereco().setEstado(cbxEstado.getValue());
		cliente.getEndereco().setNumero(tfdNumero.getText());
		cliente.getEndereco().setPais(tfdPais.getText().trim());
		cliente.getEndereco().setRua(tfdRua.getText().trim());

		// Cliente
		cliente.setNome(tfdNome.getText().trim());
		cliente.setCpf_cnpj(tfdCpfCnpj.getText().trim());
		cliente.setEmail(tfdEmail.getText().trim());
		cliente.setEstado_civil(cbxEstadoCivil.getValue().name());
		cliente.setGenero(cbxGenero.getValue());

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

		if (cliente.getTipoCliente() == TipoCliente.JURIDICO) {
			lblReponsavel.setVisible(true);
			tfdResponsavel.setVisible(true);
		} else {
			lblReponsavel.setVisible(false);
			tfdResponsavel.setVisible(false);
		}
	}

	private void modificarCampos() {

		// Endereco
		tfdBairro.setText(cliente.getEndereco().getBairro());
		tfdCep.setText(cliente.getEndereco().getCep());
		tfdCidade.setText(cliente.getEndereco().getCidade());
		tfdComplemento.setText(cliente.getEndereco().getComplemento());
		cbxEstado.setValue(cliente.getEndereco().getEstado());
		tfdNumero.setText(cliente.getEndereco().getNumero());
		tfdPais.setText(cliente.getEndereco().getPais());
		tfdRua.setText(cliente.getEndereco().getRua());

		// Cliente
		tfdNome.setText(cliente.getNome());
		tfdCpfCnpj.setText(cliente.getCpf_cnpj());
		tfdEmail.setText(cliente.getEmail());
		cbxEstadoCivil.setValue(EstadoCivil.getValor(cliente.getEstado_civil()));
		cbxGenero.setValue(cliente.getGenero());

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		tfdNascimento.getEditor().setText(df.format(cliente.getNascimento()).toString());

		tfdProfissao.setText(cliente.getProfissao());
		tfdResponsavel.setText(cliente.getResponsavel());
		tfdRg.setText(cliente.getRg());
		cbxTipo.setValue(cliente.getTipoCliente());
		cbxTelefones.getItems().setAll(cliente.getTelefones());

		cbxFilhos.setValue(cliente.isFilhos());

		if (cliente.getTipoCliente() == TipoCliente.JURIDICO) {
			lblReponsavel.setVisible(true);
			tfdResponsavel.setVisible(true);
		} else {
			lblReponsavel.setVisible(false);
			tfdResponsavel.setVisible(false);
		}
	}

}