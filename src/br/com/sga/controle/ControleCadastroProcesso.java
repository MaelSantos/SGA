package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.enums.TipoProcesso;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ControleCadastroProcesso extends Controle {

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnCadastrar;

	@FXML
	private TextField tfdBusca;

	@FXML
	private Button btnBuscar;

	@FXML
	private Label lblCliente;

	@FXML
	private ComboBox<TipoProcesso> cbxTipoProcesso;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdComarca;

	@FXML
	private DatePicker tfdData;

	@FXML
	private TextField tfdDescricao;

	@FXML
	private TextField tfdFase;

	@FXML
	private TextField tfdClasse;

	@FXML
	private Button btnAddPartes;
	
	@FXML
	private Button btnAddAudiencias;
	
	@FXML
	private TextField tfdOrgao;
	
	private IFachada fachada;
	private Dialogo dialogo;
	private Processo processo;
	private Cliente cliente;
	private Funcionario funcionario;
	
	private List<Parte> partes;

	@Override
	public void init() {

		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();
		partes = new ArrayList<>();
		processo = new Processo();
		processo.setPartes(partes);
		
		cbxTipoProcesso.getItems().addAll(TipoProcesso.values());
		
		cbxTipoProcesso.setButtonCell(new ListCell<TipoProcesso>() {
			@Override
			protected void updateItem(TipoProcesso item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Processo");
				} else {
					setText(item.toString());
				}
			}
		});

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnCadastrar) {
			Log log = null;
			try {
				processo = criarProcesso();
				fachada.salvarEditarProcesso(processo);

				if(processo.getAudiencias() != null && !processo.getAudiencias().isEmpty())
					for(Audiencia audiencia : processo.getAudiencias())
					{
						Notificacao notificacao = new Notificacao(TipoNotificacao.AUDIENCIA, Prioridade.BAIXA,
								audiencia.getProcesso().getDescricao(), Andamento.PENDENTE, audiencia.getData_audiencia());

						fachada.salvarEditarNotificacao(notificacao);
						App.notificarOuvintes(Tela.CADASTRO_PROCESSO, notificacao);
					}
				
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Salvo", "Salvo Com Sucesso", "");
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
						"Novo Processo: " + processo.getNumero() + " - " + processo.getTipo_processo(),
						StatusLog.CONCLUIDO);
				
				limparCampos();
				
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro Ao Salvar", "Erro ao Salvar Processo",
						e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
						"Novo Processo: Erro", StatusLog.ERRO);
			} catch (ParseException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro Nos Dados!",
						"Algum Dado Pode Estar Faltando ou esta incorreto!!!", e.getMessage());
			}

			try {
				if (log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		}

		else if (obj == btnBuscar) {
			
			Log log = null;
			try {
				if (!tfdBusca.getText().trim().isEmpty()) {
					ClienteAdapter adapter = dialogo
							.selecionar(fachada.buscarClienteAdapterPorBusca(tfdBusca.getText().trim()));
					if (adapter != null) {
						cliente = fachada.buscarClientePorId(adapter.getId());
						lblCliente.setText(adapter + "");

						boolean add = true;
						for(Parte p : partes)
							if(p.getNome().equalsIgnoreCase(cliente.getNome()))
								add = false;
						if(add)
							partes.add(0, new Parte(TipoParte.ATIVO, TipoParticipacao.EXEQUENTE, cliente.getNome()));

						System.out.println(partes);
						System.out.println(processo.getPartes());
						System.out.println("add: "+add);

						log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(),
								"Busca Cliente: "+tfdBusca.getText().trim(), StatusLog.CONCLUIDO); 

						Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido",
								"Busca Concluida Com Sucesso", "");
					}
				} else
					Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!",
							"Informe Algum Dado Para Pesquisa!!!", "");
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Buscar Cliente!!!",
						e.getMessage());
				e.printStackTrace();
			}


			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		} 
		else if (obj == btnVoltar) {
			limparCampos();
			App.notificarOuvintes(Tela.PROCESSOS);

		}
		else if(obj == btnAddPartes)
			App.notificarOuvintes(Tela.CADASTRO_PARTE, processo);
		else if(obj == btnAddAudiencias)
			App.notificarOuvintes(Tela.CADASTRO_AUDIENCIA, processo);
	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}
		else if(tela == Tela.CADASTRO_PARTE)
		{
			if (object instanceof Parte) {
				Parte parte = (Parte) object;

				boolean add = true ;
				for(Parte p : partes)
				{
					if(p.getNome().equalsIgnoreCase(parte.getNome()))
						add = false;
				}
				if(add)
					partes.add(parte);
			}

		}
		else if(tela == Tela.CADASTRO_PROCESSO)
			limparCampos();

	}

	private Processo criarProcesso() throws ParseException, BusinessException {

		processo.setClasse_judicial(tfdClasse.getText().trim());
		processo.setComarca(tfdComarca.getText().trim());

		if (!tfdData.getEditor().getText().trim().isEmpty()) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(tfdData.getEditor().getText());
			processo.setData_atuacao(data);
		}

		processo.setPartes(partes);
		
		processo.setDescricao(tfdDescricao.getText().trim());
		processo.setFase(tfdFase.getText().trim());
		processo.setNumero(tfdNumero.getText().trim());
		processo.setOrgao_julgador(tfdOrgao.getText().trim());
		processo.setTipo_processo(cbxTipoProcesso.getValue());
		
		processo.setCliente(cliente);

		return processo;
	}

	private void limparCampos() {

		tfdClasse.setText("");
		tfdComarca.setText("");
		tfdData.getEditor().setText("");
		tfdDescricao.setText("");
		tfdFase.setText("");
		tfdNumero.setText("");
		tfdOrgao.setText("");
		tfdBusca.setText("");
		lblCliente.setText("");

		cbxTipoProcesso.getSelectionModel().clearSelection();

		processo = new Processo();
		partes.clear();
		cliente = null;

	}

	@FXML
	void mouseEntered(MouseEvent event) {
		((Button) (event.getSource())).setStyle("-fx-background-color : #386a78");
	}

	@FXML
	void mouseExited(MouseEvent event) {
		((Button) (event.getSource())).setStyle("-fx-background-color : #008B8B");
	}

}
