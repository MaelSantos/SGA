package br.com.sga.controle;

import java.util.Date;

import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControleHome extends Controle {

	@FXML
    private TableView<NotificacaoAdapter> tblSemana;

    @FXML
    private TableColumn<NotificacaoAdapter, String> colDescricao1;

    @FXML
    private TableColumn<NotificacaoAdapter, TipoNotificacao> colTipo1;

    @FXML
    private TableColumn<NotificacaoAdapter, Andamento> colEstado1;

    @FXML
    private TableColumn<NotificacaoAdapter, Date> colData1;

    @FXML
    private TableView<NotificacaoAdapter> tblAtrasados;

    @FXML
    private TableColumn<NotificacaoAdapter, String> colDescricao2;

    @FXML
    private TableColumn<NotificacaoAdapter, TipoNotificacao> colTipo2;

    @FXML
    private TableColumn<NotificacaoAdapter, Andamento> colEstado2;

    @FXML
    private TableColumn<NotificacaoAdapter, Date> colData2;
	
	
	@Override
	public void atualizar(Tela tela, Object object) {
		

	}

	@Override
	public void init() {
		
		

	}

	@Override
	public void actionButton(ActionEvent event) {
		

	}

}
