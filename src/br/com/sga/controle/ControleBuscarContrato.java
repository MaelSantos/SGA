package br.com.sga.controle;

import java.util.Date;
import java.util.List;

import br.com.sga.app.App;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControleBuscarContrato extends Controle{

    @FXML
    private TextField buscarField;

    @FXML
    private Button buscarButton;

    @FXML
    private TableView<ContratoAdapter> contratosTableView;

    @FXML
    private TableColumn<ContratoAdapter, Date> dataColumn;

    @FXML
    private TableColumn<ContratoAdapter, Float> valorColumn;

    @FXML
    private TableColumn<ContratoAdapter, String> nomeColumn;
   
    @FXML
    private Button cadastrarContratoButton;
    

    @FXML
    private Button detalhesButton;


    private IFachada fachada;	
    private Funcionario funcionario;
    
    @FXML
    public void actionButton(ActionEvent event) {
    	if(event.getSource() == buscarButton) {
    		String busca = buscarField.getText().trim();
    		Log log;
			try {
				contratosTableView.getItems().clear();
				List<ContratoAdapter> contratos =fachada.buscarContratoPorClienteAdapter(busca);
				contratosTableView.getItems().addAll(contratos);
				if(!contratos.isEmpty())
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Contrato Existente: "+busca, StatusLog.COLCLUIDO);
				else
					log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Contrato Existente: Não Encontrado - "+busca, StatusLog.SEM_RESULTADOS);
			} catch (BusinessException e) {
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.BUSCAR, funcionario.getNome(), "Contrato Existente: Erro - "+busca, StatusLog.ERRO);
				e.printStackTrace();
			}
			
			try {
				fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}
			
    	}else if(event.getSource() == cadastrarContratoButton) 
    		App.notificarOuvintes(Tela.cadastro_contrato);
    	else if(event.getSource() == detalhesButton) {
    		ContratoAdapter adapter = contratosTableView.getSelectionModel().getSelectedItem();
    		if(adapter != null) {
    			App.notificarOuvintes(Tela.Detalhes_contrato,adapter);
    		}else {
    			Alerta.getInstance().showMensagem("Alerta","","Não há nenhuma contrato selecionado ,\ncom isso não é possivel ver detalhes de contrato");
    		}
    	}
    		
    }
    @FXML
    void mouseEntered(MouseEvent event) {
    	((Button)(event.getSource())).setStyle("-fx-background-color : #386a78");
    }

    @FXML
    void mouseExited(MouseEvent event) {
    	((Button)(event.getSource())).setStyle("-fx-background-color : #008B8B");
    }
    @FXML
    void initialize() {
    }
	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Funcionario)	 {
			if(funcionario != null)
				this.funcionario = (Funcionario) object;	
		}
		
	}
	@Override
	public void init() {
		
		fachada = Fachada.getInstance();
		dataColumn.setCellValueFactory(new PropertyValueFactory<>("data_contrato"));
		valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
		nomeColumn.setCellValueFactory( new PropertyValueFactory<>("nome_cliente"));
		
	}
}
