package br.com.sga.controle;

import br.com.sga.app.App;
import br.com.sga.dao.DaoXml;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.interfaces.IDaoXml;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class ControleConfiguracoes extends Controle{

	@FXML
	private Label lblNome;

	@FXML
	private Button btnPerfil;

	@FXML
	private Button btnAddAdm;

	@FXML
	private Button btnConfigurar;
	
    private Funcionario usuario;
    private IDaoXml daoXml;
    private Dialogo dialogo;
    
    @Override
    public void actionButton(ActionEvent event) {

    	Object obj = event.getSource();
    	
    	if(obj == btnAddAdm)
    		App.notificarOuvintes(Tela.CADASTRO, usuario);
    	else if(obj == btnPerfil) 
    		App.notificarOuvintes(Tela.PERFIL, usuario);
    	else if(obj == btnConfigurar)
		{
			String ip = dialogo.dialogoDeEntradaText("Configurar IP", "IP atual: "+daoXml.getIp(), "Escolha Um Novo IP");
			
			if(!ip.trim().isEmpty())
			{
				
				System.out.println("Retorno: "+ip);
				daoXml.SalvarEditarIP(ip);
				
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Concluido", "IP Alterado Para: "+ip, "");
			}
			else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Nada Alterado", "Nada Foi Modificado: IP Atual: "+daoXml.getIp(), "Informe Algum Dado!!!");
		}
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
		
		dialogo = Dialogo.getInstance();
		daoXml = DaoXml.getInstance();
		
	}
}
