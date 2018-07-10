package br.com.sga.controle;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.interfaces.Ouvinte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControleCliente implements Initializable {

	@FXML
	private Button btnAdd;

	private Funcionario funcionario;
	@FXML
	void actionButton(ActionEvent event) {
		
		if (event.getSource() == btnAdd) {
			App.notificarOuvintes(Tela.cadastro_cliente,funcionario);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		App.addOuvinte(new Ouvinte() {
			@Override
			public void atualizar(Tela tela, Funcionario usuario) {
				funcionario = usuario;
			}
		});
	}

}
