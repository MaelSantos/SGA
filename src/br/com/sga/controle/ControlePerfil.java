package br.com.sga.controle;

import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


import br.com.sga.app.App;
import br.com.sga.dao.DaoUsuario;
import br.com.sga.interfaces.Ouvinte;
import br.com.sga.view.Alerta;
import br.com.sga.view.CadastroNotificacao;
import br.com.sga.view.Dialogo;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ControlePerfil extends Controle{

    @FXML
    private Button editarPerfilButton;

    @FXML
    private Label lblNome;

    @FXML
    private Button cadastrarTarefaButton;

    @FXML
    private Pagination diasPagination;

    
    private IFachada fachada;
    
    private Funcionario funcionario;
    
    @FXML
	public void actionButton(ActionEvent event) {
    	if(event.getSource() == editarPerfilButton) 
    		App.notificarOuvintes(Tela.editar_perfil,funcionario);
    	else if(event.getSource() == cadastrarTarefaButton) {
    		
    		CadastroNotificacao cadastroNotificacao = Dialogo.getInstance().cadastroNotificacaoDialog();
    		Integer hora = cadastroNotificacao.getHoraBox().getSelectionModel().getSelectedItem();
    		Prioridade prioridade= cadastroNotificacao.getPrioridadeBox().getSelectionModel().getSelectedItem();
    		LocalDate ld = cadastroNotificacao.getDataPicker().getValue();
    		
    		if(hora != null && ld != null && prioridade != null) {
	        	Calendar c =  Calendar.getInstance();
	        	c.set(Calendar.YEAR,ld.getYear());
	        	c.set(Calendar.MONTH,ld.getMonthValue());
	        	c.set(Calendar.DAY_OF_MONTH,ld.getDayOfMonth());
	        	c.set(Calendar.HOUR_OF_DAY,hora);
	        	Date aviso_data = c.getTime();
	        	List<Funcionario> funcionarios = new ArrayList<>();
	        	if(cadastroNotificacao.getApenasMinRadio().isSelected()) {
	        		funcionarios.add(funcionario);
	        	}else {
	        		try {
						funcionarios = fachada.buscarUsuarioPorBusca("%_%");
					} catch (BusinessException e) {
						e.printStackTrace();
					}
	        	}
	    		try {
					fachada.salvarEditarNotificacao(new Notificacao(TipoNotificacao.TAREFA, prioridade,cadastroNotificacao.getDescricaoArea().getText(),Andamento.PENDENTE, 
					aviso_data, funcionarios));
					Alerta.getInstance().showMensagem("Confirmação","","Nova notificacao cadastrada com exito");
				} catch (BusinessException e) {
					e.printStackTrace();
					Alerta.getInstance().showMensagem("Alerta","",e.getMessage());
				}
    		}else
    			Alerta.getInstance().showMensagem("Alerta","","NADA FOI CADASTRADO");
    	}
    }	

	@Override
<<<<<<< HEAD
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		fachada = Fachada.getInstance();
		Calendar c = Calendar.getInstance();
		diasPagination.setPageCount(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		diasPagination.setCurrentPageIndex(Calendar.DAY_OF_MONTH);
		diasPagination.setPageFactory(new Callback<Integer, Node>() {
		       public Node call(Integer pageIndex) {
		           FlowPane pane = new FlowPane();
		           pane.getChildren().add(new Label(""+new SecureRandom().nextInt(1000)));
		           return pane;
		       }
		   });
=======
	public void init() {
		// TODO Stub de método gerado automaticamente
		
>>>>>>> bad7ebe6922950c4e29ca2ac361a7d0d31127392
	}

	@Override
	public void atualizar(Tela tela, Object usuario) {
		if(usuario != null) {
			if (usuario instanceof Funcionario) {
				funcionario = (Funcionario) usuario;
				lblNome.setText(funcionario.getNome());
				
			}
		}
		
	}
}