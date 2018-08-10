package br.com.sga.controle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.adapter.ReceitaAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
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
import javafx.scene.control.Label;

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
    
    @FXML
    private Label descricaoLabel;
    
    private IFachada fachada;
    private Funcionario funcionario;
    private TipoEstatistica tipoSelecionado;
   // private TipoGrafico tipoGraficoSelecionado;
    
    @FXML
    public void actionButton(ActionEvent event) {
    	if(event.getSource() == attButton ) {
    		if(dePicker.getValue() != null && atePicker.getValue() != null) {
	    		if(tipoBox.getSelectionModel().getSelectedItem() != null ) { 
	    			
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
	    	    	
	    	    	Log log = null;
	    	    	if(tipoSelecionado == TipoEstatistica.RECEITAS_DESPESAS_POR_MES) {
	    	    		try {
	    		    		List<ReceitaAdapter> receitasPorMes  = fachada.buscarReceitasTotalMesPorIntervalo(de, ate);
	    		    		yAxis.setLabel("VALOR");
	    					xAxis.setLabel("MES/ANO");
	    					for(ReceitaAdapter receitaAdapter: receitasPorMes) {
	    						System.out.println(receitaAdapter.toString());
	    						XYChart.Series<String, Number> p = new XYChart.Series<>();
	    						c.setTime(receitaAdapter.getMesAno());
	    						p.setName(c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
	    						p.getData().add(new XYChart.Data<String, Number>(c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR),
	    								receitaAdapter.getValorTotal()));
	    						barChart.getData().add(p);
	    					}
	    					log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Grafico: "+graficoBox.getValue()+" - "+tipoBox.getValue(), StatusLog.COLCLUIDO);
	    		    	
	    				} catch (Exception e) {
	    					Alerta.getInstance().showMensagem("Erro","",e.getMessage());
	    					e.printStackTrace();
	    					log = new Log(new Date(System.currentTimeMillis()), EventoLog.GERAR, funcionario.getNome(), "Gerar Grafico: Erro", StatusLog.ERRO);
	    				}
	    	    	}
	    	    	try {
	    	    		if(log != null)
	    	    			fachada.salvarEditarLog(log);
	    			} catch (BusinessException e) {
	    				e.printStackTrace();
	    			}
	    		}else
	  		    	Alerta.getInstance().showMensagem("Alerta","","N�o h� nenhum tipo de estat�stica selecionada");
    		}else 
	    		Alerta.getInstance().showMensagem("Alerta","","Selecione um periodo de tempo para pesquisa");
	    	
    	}else if(event.getSource() == tipoBox) {
    		tipoSelecionado = tipoBox.getSelectionModel().getSelectedItem();
    		descricaoLabel.setText(tipoSelecionado.toString().toLowerCase());
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
	}
}
