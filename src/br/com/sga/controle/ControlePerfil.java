package br.com.sga.controle;

import java.util.Date;

import br.com.sga.business.Validar;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
	private PasswordField senhaAtualField;

	@FXML
	private PasswordField novaSenhaField;

	@FXML
	private PasswordField confirmarSenhaField;

	@FXML
	private Button atualizarSenhaButton;

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
	private IFachada fachada;

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

		fachada = Fachada.getInstance();
		cbxEstado.getItems().setAll(Estado.values());

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();
		if(obj == btnEditarPerfil)
		{
			modificarUsuario();
			Log log;
			try {
				fachada.salvarEditarUsuario(funcionario);
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Usuario", StatusLog.CONCLUIDO);

				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Usuário Atualizado", "");
			} catch (BusinessException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Usuario - Erro: ", StatusLog.ERRO);
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Atualizar Usuário ", e.getMessage());
				e.printStackTrace();
			}
			
			try {
				if(log != null)
					fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		else if (obj == atualizarSenhaButton) {
			
			Funcionario copiaFuncionario = new Funcionario(funcionario.getId(), funcionario.getNome(),
					funcionario.getEmail(), funcionario.getLogin(), funcionario.getSenha(),
					funcionario.getNumero_oab());
			
			copiaFuncionario.setEndereco(funcionario.getEndereco());
			
			boolean sucesso = atualizarSenha(copiaFuncionario);
			
			if (sucesso) {				
				
				senhaAtualField.setText("");
				confirmarSenhaField.setText("");
				novaSenhaField.setText("");
				
				Log log;
				try {
					funcionario.setSenha(novaSenhaField.getText().trim());
					fachada.salvarEditarUsuario(copiaFuncionario);
					
					funcionario = copiaFuncionario;
					
					senhaAtualField.setText("");
					novaSenhaField.setText("");
					confirmarSenhaField.setText("");
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Usuario", StatusLog.CONCLUIDO);

					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "Usuário Atualizado", "");
				} catch (BusinessException e) {
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.EDITAR, funcionario.getNome(), "Editar Usuario - Erro: ", StatusLog.ERRO);
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Atualizar Usuário ", e.getMessage());
					e.printStackTrace();
				}
				
				try {
					if(log != null)
						fachada.salvarEditarLog(log);
				} catch (BusinessException e) {
					e.printStackTrace();
				}

			}
		}
		
	}

	private void modificarCampos() {

		tfdNome.setText(funcionario.getNome());
		tfdLogin.setText(funcionario.getLogin());
		tfdNumeroOab.setText(funcionario.getNumero_oab());
		tfdEmail.setText(funcionario.getEmail());

		tfdBairro.setText(funcionario.getEndereco().getBairro());
		tfdCep.setText(funcionario.getEndereco().getCep());
		tfdCidade.setText(funcionario.getEndereco().getCidade());
		tfdComplemento.setText(funcionario.getEndereco().getComplemento());
		tfdNumero.setText(funcionario.getEndereco().getNumero());
		tfdPais.setText(funcionario.getEndereco().getPais());
		tfdRua.setText(funcionario.getEndereco().getRua());

		cbxEstado.setValue(funcionario.getEndereco().getEstado());


	}

	private void modificarUsuario()
	{
		funcionario.setNome(tfdNome.getText().trim());
		funcionario.setLogin(tfdLogin.getText().trim());
		funcionario.setNumero_oab(tfdNumeroOab.getText().trim());
		funcionario.setEmail(tfdEmail.getText().trim());

		funcionario.getEndereco().setBairro(tfdBairro.getText().trim());
		funcionario.getEndereco().setCep(tfdCep.getText().trim());
		funcionario.getEndereco().setCidade(tfdCidade.getText().trim());
		funcionario.getEndereco().setComplemento(tfdComplemento.getText().trim());
		funcionario.getEndereco().setNumero(tfdNumero.getText().trim());
		funcionario.getEndereco().setPais(tfdPais.getText().trim());
		funcionario.getEndereco().setRua(tfdRua.getText().trim());

		funcionario.getEndereco().setEstado(cbxEstado.getSelectionModel().getSelectedItem());

	}

	private Boolean atualizarSenha(Funcionario usuario) {
		String senhaAtual, novaSenha, confirmarSenha;
		senhaAtual = senhaAtualField.getText().trim();
		novaSenha = novaSenhaField.getText().trim();
		confirmarSenha = confirmarSenhaField.getText().trim();

		if (senhaAtual.length() > 0 && novaSenha.length() > 0 && confirmarSenha.length() > 0) {
			String validacao = Validar.getInstance().validarSenha(novaSenha);
			if (validacao != null) {
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "", validacao);
				return false;
			}
			if (senhaAtual.equals(usuario.getSenha()))
				if (novaSenha.equals(confirmarSenha)) {
					usuario.setSenha(novaSenha);
					return true;
				} else
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "", "nova senha e sua confirmação não coincidem");
			else
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "", "Senha atual informada é diferente da original");
			return false;
		}
		Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "", "Nada foi alterado, entradas de texto estão vazias");
		return false;
	}

	
}