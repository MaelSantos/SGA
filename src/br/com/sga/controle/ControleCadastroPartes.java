package br.com.sga.controle;

import java.util.ArrayList;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.exceptions.DaoException;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;

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
	
//	private IFachada fachada;
//	private Funcionario funcionario;
	private Processo processo;

	@Override
	public void atualizar(Tela tela, Object object) {

//		if (object instanceof Funcionario) {
//			funcionario = (Funcionario) object;
//		}

		if(tela == Tela.CADASTRO_PARTE)
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

//		fachada = Fachada.getInstance();
		
		cbxTipoParte.getItems().setAll(TipoParte.values());

		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colTipoParte.setCellValueFactory(new PropertyValueFactory<>("tipo_parte"));
		
		colNome.setCellFactory(TextFieldTableCell.forTableColumn());
		colTipoParte.setCellFactory(ComboBoxTableCell.forTableColumn(TipoParte.values()));
		
		colNome.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null ? event.getNewValue() :
                event.getOldValue();
            
            Parte parte = event.getTableView().getItems().get(event.getTablePosition().getRow());            
            parte.setNome(value);
            
            try {
            	if(parte.getId() != null)
            		DaoCommun.getInstance().editarParte(parte);
            } catch (DaoException e) {
            	e.printStackTrace();
            }

            tblPartes.refresh();
            
        });
		
		colTipoParte.setOnEditCommit(event -> {
            final TipoParte value = event.getNewValue() != null ? event.getNewValue() :
                event.getOldValue();
            
            Parte parte = event.getTableView().getItems().get(event.getTablePosition().getRow());
            
            parte.setTipo_parte(value);
            
            try {
            	if(parte.getId() != null)
            		DaoCommun.getInstance().editarParte(parte);
            } catch (DaoException e) {
            	e.printStackTrace();
            }
            
            tblPartes.refresh();
        });
		
        setTableEditable();
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

	private void setTableEditable() {
		tblPartes.setEditable(true);
		// allows the individual cells to be selected
		tblPartes.getSelectionModel().cellSelectionEnabledProperty().set(true);
		// when character or numbers pressed it will start edit in editable
		// fields
		tblPartes.setOnKeyPressed(event -> {
			if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
				editFocusedCell();
			} else if (event.getCode() == KeyCode.RIGHT ||
					event.getCode() == KeyCode.TAB) {
				tblPartes.getSelectionModel().selectNext();
				event.consume();
			} else if (event.getCode() == KeyCode.LEFT) {
				// work around due to
				// TableView.getSelectionModel().selectPrevious() due to a bug
				// stopping it from working on
				// the first column in the last row of the table
				selectPrevious();
				event.consume();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void editFocusedCell() {
		final TablePosition < Parte, ? > focusedCell = tblPartes
				.focusModelProperty().get().focusedCellProperty().get();
		tblPartes.edit(focusedCell.getRow(), focusedCell.getTableColumn());
	}

	@SuppressWarnings("unchecked")
	private void selectPrevious() {
		if (tblPartes.getSelectionModel().isCellSelectionEnabled()) {
			// in cell selection mode, we have to wrap around, going from
			// right-to-left, and then wrapping to the end of the previous line
			TablePosition < Parte, ? > pos = tblPartes.getFocusModel()
					.getFocusedCell();
			if (pos.getColumn() - 1 >= 0) {
				// go to previous row
				tblPartes.getSelectionModel().select(pos.getRow(),
						getTableColumn(pos.getTableColumn(), -1));
			} else if (pos.getRow() < tblPartes.getItems().size()) {
				// wrap to end of previous row
				tblPartes.getSelectionModel().select(pos.getRow() - 1,
						tblPartes.getVisibleLeafColumn(
								tblPartes.getVisibleLeafColumns().size() - 1));
			}
		} else {
			int focusIndex = tblPartes.getFocusModel().getFocusedIndex();
			if (focusIndex == -1) {
				tblPartes.getSelectionModel().select(tblPartes.getItems().size() - 1);
			} else if (focusIndex > 0) {
				tblPartes.getSelectionModel().select(focusIndex - 1);
			}
		}
	}

	private TableColumn < Parte, ? > getTableColumn(
			final TableColumn < Parte, ? > column, int offset) {
		int columnIndex = tblPartes.getVisibleLeafIndex(column);
		int newColumnIndex = columnIndex + offset;
		return tblPartes.getVisibleLeafColumn(newColumnIndex);
	}

	 
}
