package br.com.sga.controle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.adapter.ContaAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoEstatistica;
import br.com.sga.entidade.enums.TipoGrafico;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Pessoa;

public class ControleEstatistica extends Controle{

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
    
    private PieChart piechart;
   
    @FXML
    private GridPane graficosPane;
    
    @FXML
    private Label descricaoLabel;
    
    private IFachada fachada;
    private Funcionario funcionario;
    
    @FXML
    public void actionButton(ActionEvent event) {
    	if(event.getSource() == attButton ) {
    		if(dePicker.getValue() != null && atePicker.getValue() != null) {
	    		if(tipoBox.getSelectionModel().getSelectedItem() != null ) { 
	    			
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
	    	    	
	    	    	Log log = null;
	    	    	try {
		    	    	if(graficoBox.getValue() == TipoGrafico.BARRA) 
		    	    	{
		    	    		barChart.setTitle(tipoBox.getValue().toString() + " de "+ de.toString() +" até "+ ate.toString());
		    	    		if(tipoBox.getValue() == TipoEstatistica.RECEITAS_POR_MES) 
		    		    		gerarGraficoBarra(fachada.buscarContaTotalMesPorIntervalo(de, ate,Tabela.RECEITA),Tabela.RECEITA);
		    	    		else if(tipoBox.getValue() == TipoEstatistica.DESPESAS_POR_MES) 
		    	    			gerarGraficoBarra(fachada.buscarContaTotalMesPorIntervalo(de, ate,Tabela.DESPESAS),Tabela.DESPESAS);
		    	    	}else if(graficoBox.getValue() == TipoGrafico.PIZZA) {
		    	    		piechart.setTitle(tipoBox.getValue().toString() + " de "+ de.toString() +" até "+ ate.toString());
		    	    		if(tipoBox.getValue() == TipoEstatistica.RECEITAS_POR_MES) 
		    		    		gerarGraficoPizza(fachada.buscarContaTotalMesPorIntervalo(de, ate,Tabela.RECEITA),Tabela.RECEITA);
		    	    		else if(tipoBox.getValue() == TipoEstatistica.DESPESAS_POR_MES) 
		    	    			gerarGraficoPizza(fachada.buscarContaTotalMesPorIntervalo(de, ate,Tabela.DESPESAS),Tabela.DESPESAS);
		    	    	}
		    	    	
		    	    	log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Grafico: "
	    	    				+graficoBox.getValue()+" - "+tipoBox.getValue(), StatusLog.COLCLUIDO);
		    	    	
	    	    	} catch (Exception e) {
    					Alerta.getInstance().showMensagem("Erro","",e.getMessage());
    					e.printStackTrace();
    					log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Grafico: Erro", StatusLog.ERRO);
    				}
	    	    	try {
	    	    		if(log != null)
	    	    			fachada.salvarEditarLog(log);
	    			} catch (BusinessException e) {
	    				e.printStackTrace();
	    			}
	    		}else
	  		    	Alerta.getInstance().showMensagem("Alerta","","Nâo há nenhum tipo de estatística selecionada");
    		}else 
	    		Alerta.getInstance().showMensagem("Alerta","","Selecione um período de tempo para pesquisa");
	    	
    	}else if(event.getSource() == tipoBox) {
    		descricaoLabel.setText(tipoBox.getSelectionModel().getSelectedItem().toString().toLowerCase());
    	}else if(event.getSource() == graficoBox) {
    		if(graficoBox.getSelectionModel().getSelectedItem() == TipoGrafico.BARRA) {
    			graficosPane.getChildren().setAll(barChart);
    		}else if(graficoBox.getSelectionModel().getSelectedItem() == TipoGrafico.PIZZA) {
    			graficosPane.getChildren().setAll(piechart);
    		}
    	}
	    	
    }
    
    private void gerarGraficoBarra(List<ContaAdapter> contas,Tabela tabela) {
    	Calendar c = Calendar.getInstance();
    	XYChart.Series<String, Number> p = new XYChart.Series<>();
    	barChart.getData().clear();
    	p.setName(tabela.toString());
    	for(ContaAdapter conta: contas) 
		{
    		System.out.println(conta);
    		c.setTime(conta.getMesAno());
			p.getData().add(new XYChart.Data<String, Number>(c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR),conta.getValorTotal()));
		}
    	barChart.getData().add(p);
    }
    
    private void gerarGraficoPizza(List<ContaAdapter> contas,Tabela tabela) {
    	piechart.getData().clear();
    	
    	Calendar c = Calendar.getInstance();
    	for(ContaAdapter conta: contas) { 
			piechart.getData().add(new PieChart.Data(c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR),conta.getValorTotal()));
    	}
    }
    
	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Funcionario) {
			if(object != null)
				funcionario = (Funcionario) object;
		}
		
	}
	@Override
	public void init() {
		fachada = Fachada.getInstance();
        graficoBox.getItems().addAll(TipoGrafico.values());
        tipoBox.getItems().addAll(TipoEstatistica.values());
        graficoBox.setValue(TipoGrafico.BARRA);
        piechart = new PieChart();
	}
}
