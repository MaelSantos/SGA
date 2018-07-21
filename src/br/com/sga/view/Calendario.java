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
import java.util.List;

import br.com.sga.fachada.Fachada;

public class Calendario {

	private ArrayList<AnchorPaneDate> allCalendarioDias = new ArrayList<>(35);
	private VBox view;
	private Text calendarioTitulo;
	private YearMonth correnteMesAno;
	
	/**
	 * Create a calendar view
	 * @param MesAno year month to create the calendar of
	 */
	public Calendario(YearMonth MesAno) {
		
		correnteMesAno = MesAno;
		// Create the calendar grid pane
		GridPane calendario = new GridPane();
		calendario.setPrefSize(600, 400);
		// Create rows and columns with anchor panes for the calendar
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				AnchorPaneDate ap = new AnchorPaneDate();
//				ap.setPrefSize(200,200);
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
			txt.setFill(Paint.valueOf("white"));
			ap.setBottomAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			diasLabels.add(ap, col++, 0);
		}
		// Create calendarTitle and buttons to change current month
		calendarioTitulo = new Text();
//		Button btnVoltarMes = new Button("<<");
//		btnVoltarMes.setOnAction(e -> AnteriorMes());
//		Button btnAvancarMes = new Button(">>");
//		btnAvancarMes.setOnAction(e -> ProximoMes());
//		HBox tituloBar = new HBox(btnVoltarMes, calendarioTitulo, btnAvancarMes);
//		tituloBar.setAlignment(Pos.BASELINE_CENTER);
		// Populate calendar with the appropriate day numbers
		populateCalendar(MesAno);
		// Create the calendar view
		view = new VBox(diasLabels, calendario);
	}

	/**
	 * Set the days of the calendar to correspond to the appropriate date
	 * @param MesAno year and month of month to render
	 */
	public void populateCalendar(YearMonth MesAno) {
		// Get the date we want to start with on the calendar
		LocalDate calendarioData = LocalDate.of(MesAno.getYear(), MesAno.getMonthValue(), 1);
		// Dial back the day until it is SUNDAY (unless the month starts on a sunday)
		while (!calendarioData.getDayOfWeek().toString().equals("SUNDAY") ) {
			calendarioData = calendarioData.minusDays(1);
		}
		// Populate the calendar with day numbers
		for (AnchorPaneDate ap : allCalendarioDias) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0);
			}
			Text txt = new Text(String.valueOf(calendarioData.getDayOfMonth()));
			
			ap.getText().setText(String.valueOf(calendarioData.getDayOfMonth()));
			
//			ap.setText(txt);
			
			txt.setFont(new Font("Verdana", 13));
			if(calendarioData.getDayOfWeek().toString().equals("SUNDAY"))
				txt.setFill(Paint.valueOf("#FF0000"));
			ap.setDate(calendarioData);
			if(ap.getDate().getDayOfYear() == LocalDate.now().getDayOfYear())
				ap.setStyle("-fx-background-color: #DC143C;");
			else
				ap.setStyle("-fx-background-color: #DCDCDC;");
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			calendarioData = calendarioData.plusDays(1);
		}
		
		// Change the title of the calendar
		calendarioTitulo.setText(MesAno.getMonth().toString() + " " + String.valueOf(MesAno.getYear()));
	}

	/**
	 * Move the month back by one. Repopulate the calendar with the correct dates.
	 */
	public void AnteriorMes() {
		correnteMesAno = correnteMesAno.minusMonths(1);
		populateCalendar(correnteMesAno);
	}

	/**
	 * Move the month forward by one. Repopulate the calendar with the correct dates.
	 */
	public void ProximoMes() {
		correnteMesAno = correnteMesAno.plusMonths(1);
		populateCalendar(correnteMesAno);
	}

	public void updateDescricao(YearMonth yearMonth)
	{
		
		
		
	}
	
	public VBox getView() {
		return view;
	}

	public ArrayList<AnchorPaneDate> getAllCalendarioDias() {
		return allCalendarioDias;
	}

	public void setAllCalendarioDias(ArrayList<AnchorPaneDate> allCalendarioDias) {
		this.allCalendarioDias = allCalendarioDias;
	}

	public Text getCalendarioTitulo() {
		return calendarioTitulo;
	}
	
}
