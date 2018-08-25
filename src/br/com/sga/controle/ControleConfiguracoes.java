package br.com.sga.controle;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControleConfiguracoes extends Controle{

	@FXML
	private Label lblNome;

	@FXML
	private Button btnPerfil;

	@FXML
	private Button btnAddAdm;

    private Funcionario usuario;
    
    @Override
    public void actionButton(ActionEvent event) {

    	Object obj = event.getSource();
    	
    	if(obj == btnAddAdm)
    		App.notificarOuvintes(Tela.CADASTRO, usuario);
    	if(obj == btnPerfil) 
    		App.notificarOuvintes(Tela.PERFIL, usuario);
    	
    }

	@Override
	public void atualizar(Tela tela, Object usuario) {
		if(usuario != null)
			if (usuario instanceof Funcionario) {
				this.usuario = (Funcionario) usuario;
				lblNome.setText(this.usuario.getNome());			

			}
	}

	@Override
	public void init() {
		// TODO Stub de método gerado automaticamente
		
	}
}
