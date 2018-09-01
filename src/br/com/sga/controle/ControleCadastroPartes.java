package br.com.sga.controle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleCadastroPartes extends Controle {

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
	private Button btnSalvar;

	private IFachada fachada;
	private Funcionario funcionario;
	private Processo processo;

	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}

		if (object instanceof Processo) {
			processo = (Processo) object;
			tblPartes.getItems().setAll(processo.getPartes());
		}
		
	}

	@Override
	public void init() {
		
		fachada = Fachada.getInstance();

		cbxTipoParte.getItems().setAll(TipoParte.values());
		cbxTipoParticipacao.getItems().setAll(TipoParticipacao.values());

		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colTipoParte.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));
		colTipoParticipacao.setCellValueFactory(new PropertyValueFactory<>("tipo_participacao"));

		cbxTipoParte.setButtonCell(new ListCell<TipoParte>() {
			@Override
			protected void updateItem(TipoParte item, boolean empty) {
				super.updateItem(item, empty);
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
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Tipo de Participação");
				} else {
					setText(item.toString());
				}
			}
		});

		
	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();

		if (obj == btnAddParte) {
			if (cbxTipoParte.getValue() != null || cbxTipoParticipacao.getValue() != null
					|| !tfdNomeParte.getText().trim().equals("")) {
				
				Parte parte = new Parte(cbxTipoParte.getValue(), cbxTipoParticipacao.getValue(), tfdNomeParte.getText().trim());
				
				tblPartes.getItems().add(parte);				
				processo.getPartes().add(parte);
				
				limparCampos();
				
			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Ação Nescessaria!!!", "Informe Todos os Dados","");
		}
		
		else if (obj == btnSalvar) {
			
			processo.setPartes(tblPartes.getItems());
			Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido!!!", "Partes Adicionar Ao Seu Processo!!!", "");
			
		}
		else if(obj == btnVoltar)
		{
			App.notificarOuvintes(Tela.CADASTRO_PROCESSO, processo);
		}
	
	}

	private void limparCampos()
	{
		tfdNomeParte.setText("");

		cbxTipoParte.getSelectionModel().clearSelection();
		cbxTipoParticipacao.getSelectionModel().clearSelection();
		
	}
	
}
