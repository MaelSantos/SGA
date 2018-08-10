package br.com.sga.controle;

import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ProcessoAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParte;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleDetalhesProcesso extends Controle {

	@FXML
    private TextField tfdNumero;

    @FXML
    private TextField tfdAtuacao;

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
	
    private IDaoCommun daoCommun;
    private IFachada fachada;
    private Processo processo;
	private Funcionario funcionario;
    
	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof ProcessoAdapter) {
			ProcessoAdapter adapter = (ProcessoAdapter) object;
		
			Processo processo = null;
			try {
				
				processo = fachada.buscarProcessoPorId(adapter.getId());
				tfdAtuacao.setText(processo.getData_atuacao().toString());
				tfdClasse.setText(processo.getClasse_judicial());
				tfdNumero.setText(processo.getNumero());
				tfdOrgao.setText(processo.getOrgao_julgador());
				tfdUsuario.setText(processo.getContrato().getConsulta().getCliente().getNome());
				tfdValor.setText(processo.getContrato().getValor_total()+"");
				
				processo.setAudiencias(daoCommun.buscarAudienciaPorIdProcesso(processo.getId()));
				if(processo.getAudiencias() != null)
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
				
				this.processo = processo;
				
			} catch (DaoException | BusinessException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}
			
		}
		
		else if (object instanceof Audiencia) {
			Audiencia audiencia = (Audiencia) object;
			
			if(audiencia.getProcesso().getId() == processo.getId())
			{
				this.processo.getAudiencias().add(audiencia);
				tblAudiencias.getItems().add(audiencia);
			}
		}

		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
			
		}
		
	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == btnAdd)
			App.notificarOuvintes(Tela.cadastro_audiencia, processo);
		if(obj == btnVoltar)
			App.notificarOuvintes(Tela.processos);
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		daoCommun = DaoCommun.getInstance();
		
		colData.setCellValueFactory(
                new PropertyValueFactory<>("data_audiencia"));
		colOrgao.setCellValueFactory(
                new PropertyValueFactory<>("orgao"));
		colVara.setCellValueFactory(
				new PropertyValueFactory<>("vara"));
		colStatus.setCellValueFactory(
				new PropertyValueFactory<>("status"));
		
		colParticipantes1.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
		colParticipantes2.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
		
		colSituacao1.setCellValueFactory(
                new PropertyValueFactory<>("situacao"));
		colSituacao2.setCellValueFactory(
                new PropertyValueFactory<>("situacao"));
		
		colTipo1.setCellValueFactory(
                new PropertyValueFactory<>("tipo_participacao"));
		colTipo2.setCellValueFactory(
                new PropertyValueFactory<>("tipo_participacao"));
	}

	
	
	
}
