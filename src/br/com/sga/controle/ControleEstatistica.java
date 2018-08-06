package br.com.sga.controle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.entidade.adapter.ReceitaAdapter;
import br.com.sga.entidade.enums.TipoEstatistica;
import br.com.sga.entidade.enums.TipoGrafico;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ControleEstatistica {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dePicker;

    @FXML
    private DatePicker atePicker;

    @FXML
    private ComboBox<TipoEstatistica> tipoBox;

    @FXML
    private ComboBox<TipoGrafico> graficoBox;

    @FXML
    private Button attButton;
    
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private BarChart<String, Number> barChart;
    
    private IFachada fachada;

    @FXML
    void actionButton(ActionEvent event) {
    	if(event.getSource() == attButton ) {
    		barChart.getData().clear();
    		
    		LocalDate ld = dePicker.getValue();
	    	Date de = new Date();
	    	Calendar c =  Calendar.getInstance();
	    	c.setTime(de);
	    	c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
	    	de = c.getTime();
	    	
	    	LocalDate ld2 = atePicker.getValue();
	    	Date ate = new Date();
	    	c.setTime(ate);
	    	c.set(ld2.getYear(), ld2.getMonthValue()-1, ld2.getDayOfMonth());
	    	ate  = c.getTime();
	    	
	    	System.out.println(de.toString());
	    	System.out.println(ate.toString());
	    	try {
	    		List<ReceitaAdapter> receitasPorMes  = fachada.buscarReceitasTotalMesPorIntervalo(de, ate);
	    		for(ReceitaAdapter receitaAdapter: receitasPorMes) {
	    			System.out.println(receitaAdapter.toString());
	    		}
				yAxis.setLabel("VALOR");
				xAxis.setLabel("MES/ANO");
				for(ReceitaAdapter receitaAdapter: receitasPorMes) {
					XYChart.Series<String, Number> p = new XYChart.Series<>();
					c.setTime(receitaAdapter.getMesAno());
					p.setName(c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
					p.getData().add(new XYChart.Data<String, Number>(c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR),
							receitaAdapter.getValorTotal()));
					barChart.getData().add(p);
				}
				
	    	
			} catch (BusinessException e) {
				Alerta.getInstance().showMensagem("Erro","",e.getMessage());
				e.printStackTrace();
			}
    	}
	    	
    }
    @FXML
    void initialize() {
        assert dePicker != null : "fx:id=\"dePicker\" was not injected: check your FXML file 'Estatistica.fxml'.";
        assert atePicker != null : "fx:id=\"atePicker\" was not injected: check your FXML file 'Estatistica.fxml'.";
        assert tipoBox != null : "fx:id=\"tipoBox\" was not injected: check your FXML file 'Estatistica.fxml'.";
        assert graficoBox != null : "fx:id=\"graficoBox\" was not injected: check your FXML file 'Estatistica.fxml'.";
        fachada = Fachada.getInstance();
        
        graficoBox.getItems().addAll(TipoGrafico.values());
        tipoBox.getItems().addAll(TipoEstatistica.values());
        graficoBox.setValue(TipoGrafico.BARRA);
        tipoBox.setValue(TipoEstatistica.RECEITAS_POR_MES);

    }
}
