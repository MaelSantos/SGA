package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.MaskFieldUtil;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleDetalhesProcesso extends Controle {

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdAtuacao;

	@FXML
	private TextField tfdClasse;

	@FXML
	private TextField tfdOrgao;

	@FXML
	private TextField tfdUsuario;

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
	private Button btnAtualizar;

	@FXML
	private Button btnAdd;

	private IDaoCommun daoCommun;
	private IFachada fachada;
	private Processo processo;
	private Funcionario funcionario;

	private boolean voltar;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (tela != Tela.CADASTRO_AUDIENCIA && tela != Tela.CADASTRO_PARTE && tela != Tela.CADASTRO_PROCESSO)
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

		if (tela != Tela.CADASTRO_AUDIENCIA && tela != Tela.CADASTRO_PARTE && tela != Tela.CADASTRO_PROCESSO)
			if (object instanceof Processo) {
				voltar = false;
				processo = (Processo) object;
				modificarCampos();
			}

		else if (object instanceof Audiencia) {
			Audiencia audiencia = (Audiencia) object;

			if (audiencia.getProcesso().getId() == processo.getId()) {
				this.processo.getAudiencias().add(audiencia);
				tblAudiencias.getItems().add(audiencia);
			}
		}

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;

		}

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAdd)
			App.notificarOuvintes(Tela.CADASTRO_AUDIENCIA, processo);
		if (obj == btnVoltar)
		{
			if(voltar)
				App.notificarOuvintes(Tela.PROCESSOS);
			else
				App.notificarOuvintes(Tela.CLIENTES);
		}
		if(obj == btnAtualizar)
		{
			
		}
	}

	@Override
	public void init() {
		
		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();

		colData.setCellValueFactory(new PropertyValueFactory<>("data_audiencia"));
		colOrgao.setCellValueFactory(new PropertyValueFactory<>("orgao"));
		colVara.setCellValueFactory(new PropertyValueFactory<>("vara"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

		colParticipantes1.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colParticipantes2.setCellValueFactory(new PropertyValueFactory<>("nome"));

		colTipo1.setCellValueFactory(new PropertyValueFactory<>("tipo_participacao"));
		colTipo2.setCellValueFactory(new PropertyValueFactory<>("tipo_participacao"));
		
	}

	private void modificarCampos() {
		try {
			tfdAtuacao.setText(processo.getData_atuacao().toString());
			tfdClasse.setText(processo.getClasse_judicial());
			tfdNumero.setText(processo.getNumero());
			tfdOrgao.setText(processo.getOrgao_julgador());
			tfdUsuario.setText(processo.getCliente().getNome());

			tblAudiencias.getItems().setAll(processo.getAudiencias());

			tblAtivo.getItems().clear();
			tblPassivo.getItems().clear();
			for (Parte p : processo.getPartes()) {
				if (p.getTipo_parte() == TipoParte.ATIVO)
					tblAtivo.getItems().add(p);
				if (p.getTipo_parte() == TipoParte.PASSIVO)
					tblPassivo.getItems().add(p);
			}

		} catch (Exception e) {
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro Com o Processo!!!", e.getMessage());
		}

	}

}
