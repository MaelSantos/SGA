package br.com.sga.controle;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import br.com.sga.entidade.Notificacao;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.view.Calendario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ControleAgenda extends Controle {

	@FXML
    public BorderPane calendarioPane;

    @FXML
    private Button BtnAnteriorMes;

    @FXML
    private Button btnProximoMes;
    
    @FXML
    private Label lblAno;
    
    private Calendario calendario;
	List<Notificacao> notificacoes;
    
	@Override
	public void atualizar(Tela tela, Object object) {

	}

	@Override
	public void init() {
		
		notificacoes = new ArrayList<>();
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

	}
	
}
