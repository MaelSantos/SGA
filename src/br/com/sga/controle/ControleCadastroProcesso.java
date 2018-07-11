package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.enums.TipoProcesso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControleCadastroProcesso extends Controle{
	@FXML
	private ComboBox<TipoProcesso> cbxTipoProcesso;

	@FXML
	private ComboBox<TipoParticipacao> cbxParticipacao;

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
	private Button btnVoltar;

	@FXML
	private Button btnCadastrar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		cbxParticipacao.getItems().addAll(TipoParticipacao.values());
		cbxTipoProcesso.getItems().addAll(TipoProcesso.values());
		
	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == btnCadastrar)
		{
			
		}
		if(obj == btnVoltar)
			App.notificarOuvintes(Tela.processos);

	}

	@Override
	public void atualizar(Tela tela, Funcionario usuario) {
		// TODO Stub de método gerado automaticamente

	}


}
