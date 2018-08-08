package br.com.sga.controle;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Prioridade;
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

}
