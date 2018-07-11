package br.com.sga.controle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.interfaces.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControleProcesso extends Controle{

	@FXML
	private TableView<Processo> tbl1Vara;

	@FXML
	private TableColumn<Processo, Integer> colProcesso1;

	@FXML
	private TableColumn<Processo, String> colComarca1;

	@FXML
	private TableColumn<Processo, String> colPartes1;

	@FXML
	private TableColumn<Processo, Andamento> colAndamento1;

	@FXML
	private TableColumn<Processo, Date> colData1;

	@FXML
	private TableView<Processo> tbl2Vara;

	@FXML
	private TableColumn<Processo, Integer> colProcesso2;

	@FXML
	private TableColumn<Processo, String> colComarca2;

	@FXML
	private TableColumn<Processo, String> colPartes2;

	@FXML
	private TableColumn<Processo, Andamento> colAndamento2;

	@FXML
	private TableColumn<Processo, Date> colData2;

	@FXML
	private TableView<Processo> tbl3Vara;

	@FXML
	private TableColumn<Processo, Integer> colProcesso3;

	@FXML
	private TableColumn<Processo, String> colComarca3;

	@FXML
	private TableColumn<Processo, String> colPartes3;

	@FXML
	private TableColumn<Processo, Andamento> colAndamento3;

	@FXML
	private Button btnCadastrar;

	@FXML
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();
		
		if(obj == btnCadastrar)
			App.notificarOuvintes(Tela.cadastro_processo);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		
		
	}

	@Override
	public void atualizar(Tela tela, Funcionario usuario) {
		
		
	}

}
