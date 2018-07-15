package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControleDetalhesProcesso extends Controle {

	@FXML
    private TextField tfdNumero;

    @FXML
    private TextField tfdAtuacao;

    @FXML
    private TextField tfdDistribuicao;

    @FXML
    private TextField tfdClasse;

    @FXML
    private TextField tfdOrgao;

    @FXML
    private TextField tfdValor;

    @FXML
    private TextField tfdUsuario;

    @FXML
    private TableView<Parte> tblAtivo;

    @FXML
    private TableColumn<Parte, String> colParticipantes1;

    @FXML
    private TableColumn<Parte, TipoParte> colTipo1;

    @FXML
    private TableColumn<Parte, String> colSituacao1;

    @FXML
    private TableView<Parte> tblPassivo;

    @FXML
    private TableColumn<Parte, String> colParticipantes2;

    @FXML
    private TableColumn<Parte, TipoParte> colTipo2;

    @FXML
    private TableColumn<Parte, String> colSituacao2;

    @FXML
    private TableView<Audiencia> tblAudiencias;

    @FXML
    private TableColumn<Audiencia, String> colVara;

    @FXML
    private TableColumn<Audiencia, String> colOrgao;

    @FXML
    private TableColumn<Audiencia, Date> colData;

    @FXML
    private TableColumn<Audiencia, String> colStatus;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnAdd;
	
    private IFachada fachada;
    
	
	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof ProcessoAdapter) {
			ProcessoAdapter adapter = (ProcessoAdapter) object;
		
			Processo processo = null;
			try {
				
				processo = fachada.buscarProcessoPorId(adapter.getId());
				
				tfdAtuacao.setText(processo.getData_atuacao().toString());
				tfdClasse.setText(processo.getClasse_judicial());
//			tfdDistribuicao.setText(processo.dis);
				tfdNumero.setText(processo.getNumero());
				tfdOrgao.setText(processo.getNumero());
				tfdUsuario.setText(processo.getContrato().getConsulta().getCliente().getNome());
				tfdValor.setText(processo.getContrato().getValor_total()+"");
				
				tblAudiencias.getItems().setAll(processo.getAudiencias());
				
				tblAtivo.getItems().clear();
				tblPassivo.getItems().clear();
				for(Parte p : processo.getContrato().getPartes())
				{
					if(p.getTipo_parte() == TipoParte.ATIVO)
						tblAtivo.getItems().add(p);
					if(p.getTipo_parte() == TipoParte.PASSIVO)
						tblPassivo.getItems().add(p);
				}
				
			} catch (BusinessException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}
			
		}

	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == btnAdd)
			App.notificarOuvintes(Tela.cadastro_audiencia);
		if(obj == btnVoltar)
			App.notificarOuvintes(Tela.processos);
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		
		
		
	}

}
