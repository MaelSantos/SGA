package br.com.sga.controle;

import java.util.ArrayList;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
	private TableView<Parte> tblPartes;

	@FXML
	private TableColumn<Parte, String> colNome;

	@FXML
	private TableColumn<Parte, TipoParte> colTipoParte;

	@FXML
	private Button btnVoltar;

	private IFachada fachada;
	private Funcionario funcionario;
	private Processo processo;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}

		else if(tela == Tela.CADASTRO_PARTE)
			if (object instanceof Processo) {
				processo = (Processo) object;
				if(processo.getPartes() != null)
					tblPartes.getItems().setAll(processo.getPartes());
				else
					processo.setPartes(new ArrayList<>());
			}
	}

	@Override
	public void init() {

		fachada = Fachada.getInstance();

		cbxTipoParte.getItems().setAll(TipoParte.values());

		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colTipoParte.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnAddParte) {
			if (! (cbxTipoParte.getValue() == null|| tfdNomeParte.getText().trim().equals(""))) {

				Parte parte = new Parte(cbxTipoParte.getValue(), tfdNomeParte.getText().trim());
				
				tblPartes.getItems().add(parte);
				App.notificarOuvintes(Tela.CADASTRO_PARTE, parte);
//				if(processo.getPartes() != null)
//					processo.getPartes().add(parte);
//				else
//				{
//					processo.setPartes(new ArrayList<>());
//					processo.getPartes().add(parte);
//				}

				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Parte Adicionada Com Sucesso","");
				
				limparCampos();

			} else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "A��o Nescessaria!!!", "Informe Todos os Dados","");
		}

		else if(obj == btnVoltar)
		{
			if(processo.getId() == null)
				App.notificarOuvintes(Tela.CADASTRO_PROCESSO);
			else 
				App.notificarOuvintes(Tela.DETALHES_PROCESSO);
			
			limparCampos();
			processo = null;
			tblPartes.getItems().clear();
		}

	}

	private void limparCampos()
	{
		tfdNomeParte.setText("");
		cbxTipoParte.getSelectionModel().clearSelection();
		
	}

}
