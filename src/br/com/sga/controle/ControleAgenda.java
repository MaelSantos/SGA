package br.com.sga.controle;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import br.com.sga.view.Alerta;
import br.com.sga.view.CadastroNotificacao;
import br.com.sga.view.Calendario;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ControleAgenda extends Controle {

	@FXML
	private BorderPane calendarioPane;

	@FXML
	private Button cadastrarTarefaButton;

	@FXML
	private Button BtnAnteriorMes;

	@FXML
	private Label lblAno;

	@FXML
	private Button btnProximoMes;

	private Calendario calendario;
	
	private IFachada fachada;
    private Funcionario funcionario;

	@Override
	public void atualizar(Tela tela, Object object) {

		if (object instanceof Notificacao) {

			calendario.AtualizarMes();
		}
		
		if (object instanceof Funcionario) 
			if(object != null) 
				funcionario = (Funcionario) object;
	}

	@Override
	public void init() {
		
		fachada = Fachada.getInstance();
		calendario = new Calendario(YearMonth.now());
		calendarioPane.setCenter(calendario.getView());
		lblAno.setText(calendario.getCalendarioTitulo().getText());
	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if(obj == btnProximoMes)
		{
			calendario.ProximoMes();
			lblAno.setText(calendario.getCalendarioTitulo().getText());
		}
		if(obj == BtnAnteriorMes)
		{
			calendario.AnteriorMes();
			lblAno.setText(calendario.getCalendarioTitulo().getText());
		}
		else if(event.getSource() == cadastrarTarefaButton) {

			CadastroNotificacao cadastroNotificacao = Dialogo.getInstance().cadastroNotificacaoDialog(LocalDate.now());
			Integer hora = cadastroNotificacao.getHoraBox().getSelectionModel().getSelectedItem();
			Prioridade prioridade= cadastroNotificacao.getPrioridadeBox().getSelectionModel().getSelectedItem();
			LocalDate ld = cadastroNotificacao.getDataPicker().getValue();

			if(hora != null && ld != null && prioridade != null) {
				Calendar c =  Calendar.getInstance();
				c.setTime(new Date());
				c.set(Calendar.YEAR,ld.getYear());
				c.set(Calendar.MONTH,ld.getMonthValue()-1);
				c.set(Calendar.DAY_OF_MONTH,ld.getDayOfMonth());
				c.set(Calendar.HOUR_OF_DAY,hora);
				c.set(Calendar.SECOND,0);
				c.set(Calendar.MILLISECOND,0);
				c.set(Calendar.MINUTE,0);
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
				Log log;
				try {
					fachada.salvarEditarNotificacao(new Notificacao(TipoNotificacao.TAREFA, prioridade, cadastroNotificacao.getDescricaoArea().getText(), Andamento.PENDENTE, 
							aviso_data, funcionarios));
					Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Confirmação","","Nova notificação cadastrada com sucesso");
					calendario.AtualizarMes();
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova Tarefa: "+aviso_data, StatusLog.CONCLUIDO);
				} catch (BusinessException e) {
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(), "Nova Tarefa:  Erro", StatusLog.ERRO);
					e.printStackTrace();
					Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!","Erro Ao Cadastrar Tarefa!!!",e.getMessage());
				}
				
				try {
					if(log != null)
						fachada.salvarEditarLog(log);
				} catch (BusinessException e) {
					// TODO Bloco catch gerado automaticamente
					e.printStackTrace();
				}					
				
			}else
				Alerta.getInstance().showMensagem(AlertType.WARNING, "Alerta","","NADA FOI CADASTRADO");
		}


	}

}
