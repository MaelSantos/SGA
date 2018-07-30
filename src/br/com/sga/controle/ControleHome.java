package br.com.sga.controle;

import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControleHome extends Controle {

	@FXML
    private TableView<?> tblSemana;

    @FXML
    private TableColumn<?, ?> colDescricao1;

    @FXML
    private TableColumn<?, ?> colTipo1;

    @FXML
    private TableColumn<?, ?> colEstado1;

    @FXML
    private TableColumn<?, ?> colData1;

    @FXML
    private TableView<?> tblAtrasados;

    @FXML
    private TableColumn<?, ?> colDescricao2;

    @FXML
    private TableColumn<?, ?> colTipo2;

    @FXML
    private TableColumn<?, ?> colEstado2;

    @FXML
    private TableColumn<?, ?> colData2;
	
	
	@Override
	public void atualizar(Tela tela, Object object) {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public void init() {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public void actionButton(ActionEvent event) {
		// TODO Stub de método gerado automaticamente

	}

}
