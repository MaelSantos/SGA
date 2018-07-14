package br.com.sga.controle;

import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.Tela;
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
    private TableView<?> tblAtivo;

    @FXML
    private TableColumn<?, ?> colParticipantes1;

    @FXML
    private TableColumn<?, ?> colTipo1;

    @FXML
    private TableColumn<?, ?> colSituacao1;

    @FXML
    private TableView<?> tblPassivo;

    @FXML
    private TableColumn<?, ?> colParticipantes2;

    @FXML
    private TableColumn<?, ?> colTipo2;

    @FXML
    private TableColumn<?, ?> colSituacao2;

    @FXML
    private TableView<?> tblAudiencias;

    @FXML
    private TableColumn<?, ?> colVara;

    @FXML
    private TableColumn<?, ?> colOrgao;

    @FXML
    private TableColumn<?, ?> colData;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Button btnAdd;
	
	public ControleDetalhesProcesso() {
		// TODO Stub de construtor gerado automaticamente
	}

	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Processo) {
			Processo processo = (Processo) object;
		
			tfdAtuacao.setText(processo.getData_atuacao().toString());
			tfdClasse.setText(processo.getClasse_judicial());
//			tfdDistribuicao.setText(processo.dis);
			tfdNumero.setText(processo.getNumero());
			tfdOrgao.setText(processo.getNumero());
			tfdUsuario.setText(processo.getContrato().getConsulta().getCliente().getNome());
			tfdValor.setText(processo.getContrato().getValor_total()+"");
			
		}

	}

	@Override
	public void actionButton(ActionEvent event) {
		// TODO Stub de método gerado automaticamente

	}

}
