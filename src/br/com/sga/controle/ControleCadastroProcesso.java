package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ClienteAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleCadastroProcesso extends Controle {

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
	private TextField tfdOrgao;

	@FXML
	private Button btnAddParte;

	@FXML
	private TextField tfdNomeParte;

	@FXML
	private ComboBox<TipoParte> cbxTipoParte;

	@FXML
	private ComboBox<TipoParticipacao> cbxTipoParticipacao;

	@FXML
	private TableView<Parte> tblPartes;

	@FXML
	private TableColumn<Parte, String> colNome;

	@FXML
	private TableColumn<Parte, TipoParte> colTipoParte;

	@FXML
	private TableColumn<Parte, TipoParticipacao> colTipoParticipacao;

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

	private IFachada fachada;
	private Dialogo dialogo;
	private Processo processo;
	private Cliente cliente;
	private Funcionario funcionario;

	@Override
	public void init() {

		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();

		cbxTipoProcesso.getItems().addAll(TipoProcesso.values());
		cbxTipoParte.getItems().setAll(TipoParte.values());
		cbxTipoParticipacao.getItems().setAll(TipoParticipacao.values());

		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colTipoParte.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));
		colTipoParticipacao.setCellValueFactory(new PropertyValueFactory<>("tipo_participacao"));
		
		cbxTipoParte.setButtonCell(new ListCell<TipoParte>() {
	        @Override
	        protected void updateItem(TipoParte item, boolean empty) {
	            super.updateItem(item, empty) ;
	            if (empty || item == null) {
	                setText("Tipo de Parte");
	            } else {
	                setText(item.toString());
	            }
	        }
	    });
		cbxTipoParticipacao.setButtonCell(new ListCell<TipoParticipacao>() {
	        @Override
	        protected void updateItem(TipoParticipacao item, boolean empty) {
	            super.updateItem(item, empty) ;
	            if (empty || item == null) {
	                setText("Tipo de Participação");
	            } else {
	                setText(item.toString());
	            }
	        }
	    });
		
		cbxTipoProcesso.setButtonCell(new ListCell<TipoProcesso>() {
	        @Override
	        protected void updateItem(TipoProcesso item, boolean empty) {
	            super.updateItem(item, empty) ;
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

		else if(obj == btnBuscar)
		{
			try {
				if(!tfdBusca.getText().trim().isEmpty())
				{
					ClienteAdapter adapter = dialogo.selecionar(fachada.buscarClienteAdapterPorBusca(tfdBusca.getText().trim()));
					if(adapter != null)
					{
						cliente = fachada.buscarClientePorId(adapter.getId());
						lblCliente.setText(adapter+"");
						
						if(tblPartes.getItems().isEmpty())
							tblPartes.getItems().add(0, new Parte(TipoParte.ATIVO, TipoParticipacao.EXEQUENTE, cliente.getNome()));
						else
							tblPartes.getItems().set(0, new Parte(TipoParte.ATIVO, TipoParticipacao.EXEQUENTE, cliente.getNome()));
						
						Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Busca Concluida Com Sucesso", "");
					}				
				}
				else 
					Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!", "Informe Algum Dado Para Pesquisa!!!", "");
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Ao Buscar Cliente!!!", e.getMessage());
				e.printStackTrace();
			}
		}
		else if(obj == btnAddParte)
		{
			if(cbxTipoParte.getValue() != null || cbxTipoParticipacao.getValue() != null || !tfdNomeParte.getText().trim().equals(""))
			{
				tblPartes.getItems().add(new Parte(cbxTipoParte.getValue(), cbxTipoParticipacao.getValue(), tfdNomeParte.getText().trim()));
				cbxTipoParte.getSelectionModel().clearSelection(); 
				cbxTipoParticipacao.getSelectionModel().clearSelection(); 
				tfdNomeParte.setText("");
			}
			else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!!!", "Informe Todos os Dados", "");
		}
		if (obj == btnVoltar) {
			limparCampos();
			App.notificarOuvintes(Tela.PROCESSOS);

		}
	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}
	}

	private Processo criarProcesso() throws ParseException, BusinessException {

		processo = new Processo();

		processo.setClasse_judicial(tfdClasse.getText().trim());
		processo.setComarca(tfdComarca.getText().trim());

		if(!tfdData.getEditor().getText().trim().isEmpty())
		{
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(tfdData.getEditor().getText());
			processo.setData_atuacao(data);			
		}

		processo.setDescricao(tfdDescricao.getText().trim());
		processo.setFase(tfdFase.getText().trim());
		processo.setNumero(tfdNumero.getText().trim());
		processo.setOrgao_julgador(tfdOrgao.getText().trim());
		processo.setTipo_processo(cbxTipoProcesso.getValue());

		processo.setCliente(cliente);

		processo.setPartes(tblPartes.getItems());

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
		tfdNomeParte.setText("");
		
		cbxTipoParte.getSelectionModel().clearSelection();
		cbxTipoParticipacao.getSelectionModel().clearSelection();
		cbxTipoProcesso.getSelectionModel().clearSelection();

		processo = null;
		cliente = null;

		tblPartes.getItems().clear();

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
