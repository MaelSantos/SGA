package br.com.sga.view;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;

public class DateItem extends AnchorPane {
	
	private static List<java.util.Date> dates;
	
	// Date e Texto Associados ao pane
	private LocalDate date;
	private Text text;
	private List<NotificacaoAdapter> list;

	public DateItem(Node... children) {
		super(children);

		text = new Text("Vazio");
		text.setFont(new Font("Verdana", 12));
		text.setFill(Paint.valueOf("black"));
		setTopAnchor(text, 5.0);
		setLeftAnchor(text, 5.0);
		getChildren().add(text);
		
		setPrefSize(400,200);
		setStyle("-fx-background-color: #DCDCDC;");
		
		this.setOnMouseClicked(e -> {
				Dialogo.getInstance().DetalhesData(date);
		});

	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Text getText() {
		return text;
	}

	public void updateText(String texto) {
		
		this.text.setText(texto);
		if(date.getDayOfWeek().toString().equals("SUNDAY") && !this.text.getFill().equals(Paint.valueOf("#FF0000")))//evita modificar os que já estão com a cor certa
			this.text.setFill(Paint.valueOf("#FF0000"));
		
		if( (date.getMonthValue() > Calendario.correnteMesAno.getMonthValue() || date.getMonthValue() < Calendario.correnteMesAno.getMonthValue()) 
				&& !this.getStyle().equals("-fx-background-color: #008B8	'B;"))//evita modificar os que já estão com a cor certa
			setStyle("-fx-background-color: #008B8B;");
		else
			setStyle("-fx-background-color: #DCDCDC;");

		//atualiza a cor do pane que for referente ao dia atual
		if(date.getDayOfYear() == LocalDate.now().getDayOfYear() && date.getDayOfWeek() == LocalDate.now().getDayOfWeek())
			setStyle("-fx-background-color: #00BFFF;");
		else if(! (getStyle().equalsIgnoreCase("-fx-background-color: #DCDCDC;")))// comparação para evitar atualizar todos 
			setStyle("-fx-background-color: #DCDCDC;");
		
		try {
			if(compare()) {
				list = Fachada.getInstance().BuscarNotificacaoAdapterPorData(Date.valueOf(date), Date.valueOf(date));
				
				int quant;
				List<TipoNotificacao> not = new ArrayList<>();
				for(NotificacaoAdapter n : list)
				{
					not.add(n.getTipoNotificacao());
				}
				for(TipoNotificacao t : TipoNotificacao.values())
				{
					quant =  Collections.frequency(not, t);
					if(quant > 0)
						text.setText(text.getText() +"\n"+ t + "["+ quant+"]");		
				}
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean compare()
	{
		if(dates != null )
			for(java.util.Date d : dates)
				if(d.toString().equals(date.toString()))
					return true;
		return false;
	}
	
	public static void updateDates(YearMonth correnteMesAno)
	{
		try {
			dates = Fachada.getInstance().BuscarAllDataPorMes(correnteMesAno.getMonthValue(), correnteMesAno.getYear());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
