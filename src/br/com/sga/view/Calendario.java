package br.com.sga.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class Calendario {

	private ArrayList<DateItem> allCalendarioDias = new ArrayList<>(35);
	private VBox view;
	private Text calendarioTitulo;
	public static YearMonth correnteMesAno;
	
	@SuppressWarnings("static-access")
	public Calendario(YearMonth MesAno) {
		
		setCorrenteMesAno(MesAno);
		// Create the calendar grid pane
		GridPane calendario = new GridPane();
		calendario.setPrefSize(600, 400);
		// Create rows and columns with anchor panes for the calendar
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				DateItem ap = new DateItem();
				calendario.add(ap,j,i);
				allCalendarioDias.add(ap);
			}
		}		
		calendario.setGridLinesVisible(true);
				
		// Days of the week labels
		Text[] dayNames = new Text[]{
				new Text("Domingo"), new Text("Segunda"), new Text("Terça"),
				new Text("Quarta"), new Text("Quinta"), new Text("Sexta"),
				new Text("Sabado") };
		GridPane diasLabels = new GridPane();
		diasLabels.setPrefWidth(600);
		Integer col = 0;
		for (Text txt : dayNames) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefSize(200, 10);
			txt.setFont(new Font("Verdana", 14));
			if(txt.getText().equals("Domingo"))
				txt.setFill(Paint.valueOf("#FF0000"));
			else
				txt.setFill(Paint.valueOf("black"));			
			ap.setBottomAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			diasLabels.add(ap, col++, 0);
		}
		
		diasLabels.setStyle("-fx-background-color: #DCDCDC;");
		diasLabels.setGridLinesVisible(true);
		
		// Create calendarTitle and buttons to change current month
		calendarioTitulo = new Text();
		DateItem.updateDates(MesAno);
		populateCalendar(MesAno);
		// Create the calendar view
		view = new VBox(diasLabels, calendario);	
	}

	/**
	 * Set the days of the calendar to correspond to the appropriate date
	 * @param MesAno year and month of month to render
	 */
	public void populateCalendar(YearMonth MesAno) {
		// Pego a data referente o mes e o ano 
		LocalDate calendarioData = LocalDate.of(MesAno.getYear(), MesAno.getMonthValue(), 1);
		
		// Veririfico os dias que caem no domingo
		while (!calendarioData.getDayOfWeek().toString().equals("SUNDAY") ) {
			calendarioData = calendarioData.minusDays(1);
		}
		
		// Atualizo os dados do calendario (texto e numeros)
		for (DateItem ap : allCalendarioDias) {
			
			ap.setDate(calendarioData);
			ap.updateText(String.valueOf(calendarioData.getDayOfMonth()));
			calendarioData = calendarioData.plusDays(1);
		}
		
		//atualizo a o titulo com mes e ano
		calendarioTitulo.setText(MesAno.getMonth().toString() + " " + String.valueOf(MesAno.getYear()));
	}

	/**
	 * Move the month back by one. Repopulate the calendar with the correct dates.
	 */
	public void AnteriorMes() {
		setCorrenteMesAno(getCorrenteMesAno().minusMonths(1));
		DateItem.updateDates(getCorrenteMesAno());
		populateCalendar(getCorrenteMesAno());
	}

	public void AtualizarMes() {
		DateItem.updateDates(getCorrenteMesAno());
		populateCalendar(getCorrenteMesAno());
	}
	
	/**
	 * Move the month forward by one. Repopulate the calendar with the correct dates.
	 */
	public void ProximoMes() {
		setCorrenteMesAno(getCorrenteMesAno().plusMonths(1));
		DateItem.updateDates(getCorrenteMesAno());
		populateCalendar(getCorrenteMesAno());
	}

	public VBox getView() {
		return view;
	}

	public ArrayList<DateItem> getAllCalendarioDias() {
		return allCalendarioDias;
	}

	public void setAllCalendarioDias(ArrayList<DateItem> allCalendarioDias) {
		this.allCalendarioDias = allCalendarioDias;
	}

	public Text getCalendarioTitulo() {
		return calendarioTitulo;
	}

	public YearMonth getCorrenteMesAno() {
		return correnteMesAno;
	}

	@SuppressWarnings("static-access")
	public void setCorrenteMesAno(YearMonth correnteMesAno) {
		this.correnteMesAno = correnteMesAno;
	}
	
}
