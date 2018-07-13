package br.com.sga.controle;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoProcesso;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private TableColumn<Processo, Date> colData3;
	
	@FXML
	private Button btnCadastrar;

	private IFachada fachada;
	
	@FXML
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();
		
		if(obj == btnCadastrar)
			App.notificarOuvintes(Tela.cadastro_processo);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		colAndamento1.setCellValueFactory(
                new PropertyValueFactory<>("decisao"));
		colAndamento2.setCellValueFactory(
                new PropertyValueFactory<>("decisao"));
		colAndamento3.setCellValueFactory(
                new PropertyValueFactory<>("decisao"));
		
		colComarca1.setCellValueFactory(
                new PropertyValueFactory<>("comarca"));
		colComarca2.setCellValueFactory(
                new PropertyValueFactory<>("comarca"));
		colComarca3.setCellValueFactory(
                new PropertyValueFactory<>("comarca"));
		
		colData1.setCellValueFactory(
                new PropertyValueFactory<>("data_atuacao"));
		colData2.setCellValueFactory(
                new PropertyValueFactory<>("data_atuacao"));
		colData3.setCellValueFactory(
                new PropertyValueFactory<>("data_atuacao"));
		
		colPartes1.setCellValueFactory(
                new PropertyValueFactory<>("contrato"));
		colPartes2.setCellValueFactory(
                new PropertyValueFactory<>("contrato"));
		colPartes3.setCellValueFactory(
                new PropertyValueFactory<>("contrato"));
		
		colProcesso1.setCellValueFactory(
                new PropertyValueFactory<>("numero"));
		colProcesso2.setCellValueFactory(
                new PropertyValueFactory<>("numero"));
		colProcesso3.setCellValueFactory(
                new PropertyValueFactory<>("numero"));
		
//		try {
//			
//			tbl1Vara.getItems().setAll(fachada.buscarProcessoPorBusca(TipoProcesso.Vara_1.toString()));
//			tbl2Vara.getItems().setAll(fachada.buscarProcessoPorBusca(TipoProcesso.Vara_2.toString()));
//			tbl3Vara.getItems().setAll(fachada.buscarProcessoPorBusca(TipoProcesso.Vara_Criminal.toString()));
//			
//		} catch (BusinessException e) {
//			e.printStackTrace();
//			Alerta.getInstance().showMensagem("Erro!!!", "Erro Ao Carregar Processos", e.getMessage());
//		}
		
	}

	@Override
	public void atualizar(Tela tela, Funcionario usuario) {
		
		
	}

}
