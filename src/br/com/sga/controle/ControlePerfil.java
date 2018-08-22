package br.com.sga.controle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.view.Alerta;
import br.com.sga.view.CadastroNotificacao;
import br.com.sga.view.Dialogo;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.Prioridade;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
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
    		App.notificarOuvintes(Tela.EDITAR_PERFIL,funcionario);
    	else if(event.getSource() == cadastrarTarefaButton) {
    		
    		CadastroNotificacao cadastroNotificacao = Dialogo.getInstance().cadastroNotificacaoDialog();
    		Integer hora = cadastroNotificacao.getHoraBox().getSelectionModel().getSelectedItem();
    		Prioridade prioridade= cadastroNotificacao.getPrioridadeBox().getSelectionModel().getSelectedItem();
    		LocalDate ld = cadastroNotificacao.getDataPicker().getValue();
    		
    		if(hora != null && ld != null && prioridade != null) {
	        	Calendar c =  Calendar.getInstance();
	        	c.set(Calendar.YEAR,ld.getYear());
	        	c.set(Calendar.MONTH,ld.getMonthValue()-1);
	        	c.set(Calendar.DAY_OF_MONTH,ld.getDayOfMonth());
	        	c.set(Calendar.HOUR_OF_DAY,hora);
	        	c.set(Calendar.SECOND,0);
	        	c.set(Calendar.MILLISECOND,0);
	        	c.set(Calendar.MINUTE,0);
	        	Date aviso_data = c.getTime();
	        	List<Funcionario> funcionarios = new ArrayList<>();
	        	
	        	Log log;
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
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.SALVAR, funcionario.getNome(), "Salvar Tarefa: ", StatusLog.CONCLUIDO);
				} catch (Exception e) {
					e.printStackTrace();
					Alerta.getInstance().showMensagem("Alerta","",e.getMessage());
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.SALVAR, funcionario.getNome(), "Salvar Tarefa: Erro", StatusLog.ERRO);
				}
	    		
	    		try {
	    			if(log != null)
	    				fachada.salvarEditarLog(log);
				} catch (BusinessException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}
	    		
    		}else
    			Alerta.getInstance().showMensagem("Alerta","","NADA FOI CADASTRADO");
    		
    	}
    }	
	
	@Override
	public void init() {
		fachada = Fachada.getInstance();
		Calendar c = Calendar.getInstance();
		diasPagination.setPageCount(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		diasPagination.setCurrentPageIndex(Calendar.DAY_OF_MONTH);
	}
	
	@Override
	public void atualizar(Tela tela, Object usuario) {
		if(usuario != null) {
			if (usuario instanceof Funcionario) {
				funcionario = (Funcionario) usuario;
				lblNome.setText(funcionario.getNome());
				Calendar c = Calendar.getInstance();
				diasPagination.setPageFactory(new Callback<Integer, Node>() {
				       
					public Node call(Integer pageIndex) {
				    	   VBox v =  new VBox();
				    	   List<Notificacao> notificacoes = null;
				    	   try {
								notificacoes = fachada.buscarNotificacaoPorFuncionario(funcionario);
								Collections.sort(notificacoes);
				    	   } catch (BusinessException e1) {
								Alerta.getInstance().showMensagem("Alerta","",e1.getMessage());
							}
				    	   for(Notificacao e : notificacoes) {
				        	   c.setTime(e.getAviso_data());
				        	   if(pageIndex+1  == c.get(Calendar.DAY_OF_MONTH ))
				        		   v.getChildren().add(new Label(e.toString()));
				           }
				           return v;
				       }
				   });
			}
		}
		
	}

}