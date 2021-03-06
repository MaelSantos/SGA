package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoProcesso;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.view.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleDetalhesProcesso extends Controle {

	@FXML
	private TextField tfdFase;

	@FXML
	private TextField tfdNumero;

	@FXML
	private DatePicker tfdAtuacao;

	@FXML
	private TextField tfdClasse;

	@FXML
	private TextField tfdOrgao;

	@FXML
	private TextField tfdUsuario;

	@FXML
	private Button btnAtualizar;

	@FXML
	private TextField tfdDescricao;

	@FXML
	private TextField tfdComarca;

	@FXML
	private TextField tfdDecisao;

	@FXML
	private ComboBox<TipoProcesso> cbxTipo;

	@FXML
	private TableView<Parte> tblAtivo;

	@FXML
	private TableColumn<Parte, String> colParticipantes1;

	@FXML
	private TableColumn<Parte, TipoParte> colTipo1;

	@FXML
	private TableView<Parte> tblPassivo;

	@FXML
	private TableColumn<Parte, String> colParticipantes2;

	@FXML
	private TableColumn<Parte, TipoParte> colTipo2;

	@FXML
	private TableView<Audiencia> tblAudiencias;

	@FXML
	private TableColumn<Audiencia, String> colVara;

	@FXML
	private TableColumn<Audiencia, String> colOrgao;

	@FXML
	private TableColumn<Audiencia, Date> colData;

	@FXML
	private TableColumn<Audiencia, String> colStatus;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnAddPartes;

	@FXML
	private Button btnAdd;

	private IFachada fachada;
	private IDaoCommun daoCommun;
	private Processo processo;
	private Funcionario funcionario;

	private boolean voltar;

	@Override
	public void atualizar(Tela tela, Object object) {

		
		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;

		}
		else if (tela == Tela.DETALHES_PROCESSO)
		{
			if (object instanceof ProcessoAdapter) {
				ProcessoAdapter adapter = (ProcessoAdapter) object;

				try {
					voltar = true;
					processo = fachada.buscarProcessoPorId(adapter.getId());
					processo.setCliente(fachada.buscarClientePorId(processo.getCliente().getId()));
					modificarCampos();
				} catch (BusinessException e) {
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Com o Processo!!!", e.getMessage());
					e.printStackTrace();
				}

			}

			else if (object instanceof Processo) {
				voltar = false;
				processo = (Processo) object;
				modificarCampos();
			}
		}

		else if(tela == Tela.CADASTRO_AUDIENCIA)
		{
			if (object instanceof Audiencia) {
//				Audiencia audiencia = (Audiencia) object;
				
				tblAudiencias.getItems().clear();
				tblAudiencias.getItems().addAll(processo.getAudiencias());
//				tblAudiencias.getItems().add(audiencia);
			}
		}

		if(tela == Tela.CADASTRO_PARTE)
		{
			if (object instanceof Parte) {
				Parte parte = (Parte) object;
				
				if (parte.getTipo_parte() == TipoParte.ATIVO)
					tblAtivo.getItems().add(parte);
				if (parte.getTipo_parte() == TipoParte.PASSIVO)
					tblPassivo.getItems().add(parte);
			}			
		}

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAdd)
			App.notificarOuvintes(Tela.CADASTRO_AUDIENCIA, processo);
		else if (obj == btnVoltar)
		{
			if(voltar)
				App.notificarOuvintes(Tela.PROCESSOS);
			else
				App.notificarOuvintes(Tela.CLIENTES);
			
			processo = null;
			tblAtivo.getItems().clear();
			tblPassivo.getItems().clear();
			tblAudiencias.getItems().clear();
		}
		else if(obj == btnAtualizar)
		{
			Log log;
			try {
				modificarProcesso();
				fachada.salvarEditarProcesso(processo);
				processo.setPartes(daoCommun.getPartes(processo.getId(), Tabela.PROCESSO));
				processo.setAudiencias(daoCommun.buscarAudienciaPorIdProcesso(processo.getId()));
				
				modificarCampos();
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Processo "+processo, StatusLog.CONCLUIDO);
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Processo Atualizado", "");
			} catch (BusinessException | DaoException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Processo "+processo, StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Atualizar Processo", e.getMessage());
				e.printStackTrace();
			}

			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		}
		else if(obj == btnAddPartes)
		{
			modificarProcesso();
			App.notificarOuvintes(Tela.CADASTRO_PARTE, processo);
		}

	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();
		voltar = true;

		colData.setCellValueFactory(new PropertyValueFactory<>("data_audiencia"));
		colOrgao.setCellValueFactory(new PropertyValueFactory<>("orgao"));
		colVara.setCellValueFactory(new PropertyValueFactory<>("vara"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		colParticipantes1.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colParticipantes2.setCellValueFactory(new PropertyValueFactory<>("nome"));

		colTipo1.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));
		colTipo2.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));

		cbxTipo.getItems().setAll(TipoProcesso.values());

		cbxTipo.setButtonCell(new ListCell<TipoProcesso>() {
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

		tblAudiencias.setOnMouseClicked(e -> {
			if (e.getClickCount() > 1)
				if (tblAudiencias.getSelectionModel().getSelectedItem() != null)
					App.notificarOuvintes(Tela.CADASTRO_AUDIENCIA, tblAudiencias.getSelectionModel().getSelectedItem());
		});
		
		colData.setCellFactory(coluna -> {
			
			return new TableCell<Audiencia, Date>() {
				protected void updateItem(Date item, boolean empty) {
					
					super.updateItem(item, empty);

					if (item == null || empty) {
					setText(null);
					} else {
					setText(new SimpleDateFormat("dd/MM/yyyy").format(item));
					}
					}
				};
		});
		
	}

	private void modificarCampos() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		tfdAtuacao.getEditor().setText(df.format(processo.getData_atuacao()));

		tfdComarca.setText(processo.getComarca());
		tfdDescricao.setText(processo.getDescricao());
		tfdFase.setText(processo.getFase());
		cbxTipo.setValue(processo.getTipo_processo());
		tfdClasse.setText(processo.getClasse_judicial());
		tfdNumero.setText(processo.getNumero());
		tfdOrgao.setText(processo.getOrgao_julgador());
		tfdUsuario.setText(processo.getCliente().getNome());

		if(processo.getDecisao() != null)
			tfdDecisao.setText(processo.getDecisao());
		else
			tfdDecisao.setText("");
		
		tblAudiencias.getItems().clear();
		tblAudiencias.getItems().setAll(processo.getAudiencias());

		tblAtivo.getItems().clear();
		tblPassivo.getItems().clear();
		for (Parte p : processo.getPartes()) {
			if (p.getTipo_parte() == TipoParte.ATIVO)
				tblAtivo.getItems().add(p);
			if (p.getTipo_parte() == TipoParte.PASSIVO)
				tblPassivo.getItems().add(p);
		}

	}

	private void modificarProcesso() {

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = df.parse(tfdAtuacao.getEditor().getText().trim());
			processo.setData_atuacao(data);
		} catch (ParseException e) {
			Alerta.getInstance().showMensagem(AlertType.WARNING, "Erro!", "Formato da Data Esta Incorreto!!!", "");
			e.printStackTrace();
		}

		processo.setDecisao(tfdDecisao.getText().trim());
		processo.setComarca(tfdComarca.getText().trim());
		processo.setDescricao(tfdDescricao.getText().trim());
		processo.setFase(tfdFase.getText().trim());
		processo.setTipo_processo(cbxTipo.getValue());
		processo.setClasse_judicial(tfdClasse.getText().trim());
		processo.setNumero(tfdNumero.getText().trim());
		processo.setOrgao_julgador(tfdOrgao.getText().trim());

		processo.setAudiencias(tblAudiencias.getItems());

		if(processo.getPartes() == null)
			processo.setPartes(new ArrayList<>());
		ObservableList<Parte> partes = FXCollections.observableArrayList();
		partes.addAll(tblAtivo.getItems());
		partes.addAll(tblPassivo.getItems());
		
		processo.setPartes(partes);
	}

}
