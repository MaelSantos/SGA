package br.com.sga.controle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlePerfil extends Controle {

	@FXML
	private Label lblNome;

	@FXML
	private TextField tfdNome;

	@FXML
	private TextField tfdEmail;

	@FXML
	private TextField tfdLogin;

	@FXML
	private TextField tfdNumeroOab;

	@FXML
	private TextField tfdRua;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdBairro;

	@FXML
	private TextField tfdCidade;

	@FXML
	private TextField tfdPais;

	@FXML
	private TextField tfdCep;

	@FXML
	private TextField tfdComplemento;

	@FXML
	private ComboBox<Estado> cbxEstado;

	@FXML
	private Button btnEditarPerfil;

	private Funcionario funcionario;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
			lblNome.setText(funcionario.getNome());
			modificarCampos();
		}

	}

	@Override
	public void init() {
		
		cbxEstado.getItems().setAll(Estado.values());

	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();
		if(obj == btnEditarPerfil)
			App.notificarOuvintes(Tela.EDITAR_PERFIL, funcionario);

	}

	private void modificarCampos() {

		tfdNome.setText(funcionario.getNome());
		tfdLogin.setText(funcionario.getLogin());
		tfdNumeroOab.setText(funcionario.getNumero_oab());
		tfdEmail.setText(funcionario.getEmail());
		
	}

}