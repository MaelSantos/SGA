package br.com.sga.controle;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.sga.entidade.adapter.NotificacaoAdapter;
import br.com.sga.entidade.enums.Andamento;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoNotificacao;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleHome extends Controle {

	@FXML
    private TableView<NotificacaoAdapter> tblSemana;

    @FXML
    private TableColumn<NotificacaoAdapter, String> colDescricao1;

    @FXML
    private TableColumn<NotificacaoAdapter, TipoNotificacao> colTipo1;

    @FXML
    private TableColumn<NotificacaoAdapter, Andamento> colEstado1;

    @FXML
    private TableColumn<NotificacaoAdapter, Date> colData1;

    @FXML
    private TableView<NotificacaoAdapter> tblAtrasados;

    @FXML
    private TableColumn<NotificacaoAdapter, String> colDescricao2;

    @FXML
    private TableColumn<NotificacaoAdapter, TipoNotificacao> colTipo2;

    @FXML
    private TableColumn<NotificacaoAdapter, Andamento> colEstado2;

    @FXML
    private TableColumn<NotificacaoAdapter, Date> colData2;
	
	
    private IFachada fachada;
    
	@Override
	public void atualizar(Tela tela, Object object) {
		

	}

	@Override
	public void init() {
		
		fachada = Fachada.getInstance();
		
		colData1.setCellValueFactory(
                new PropertyValueFactory<>("aviso_data"));
		colData2.setCellValueFactory(
                new PropertyValueFactory<>("aviso_data"));
		
		colDescricao1.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
		colDescricao2.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
		
		colEstado1.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
		colEstado2.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
		
		colTipo1.setCellValueFactory(
                new PropertyValueFactory<>("tipoNotificacao"));
		colTipo2.setCellValueFactory(
                new PropertyValueFactory<>("tipoNotificacao"));
		
		try {
			
			Calendar calendar = new GregorianCalendar();
//			calendar.setTime(data);
			
			calendar.setTime(new Date());
			
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			Date primeiro = calendar.getTime();

			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			Date ultimo = calendar.getTime();
			
			System.out.println("Primeiro: "+primeiro+" Ultimo: "+ultimo);
			
			tblAtrasados.getItems().setAll(fachada.BuscarNotificacaoAdapterPorEstado(Andamento.VENCIDO.name()));
			tblSemana.getItems().setAll(fachada.BuscarNotificacaoAdapterPorData(primeiro, ultimo));
		} catch (BusinessException e) {
			
			Alerta.getInstance().showMensagem("Erro!", "Erro Ao Carregar Notificações!!!", e.getMessage());
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void actionButton(ActionEvent event) {
		

	}

}
