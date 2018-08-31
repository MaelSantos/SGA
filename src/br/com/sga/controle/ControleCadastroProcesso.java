package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Log;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.EventoLog;
import br.com.sga.entidade.enums.StatusLog;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoParticipacao;
import br.com.sga.entidade.enums.TipoProcesso;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ControleCadastroProcesso extends Controle {

	@FXML
	private ComboBox<TipoProcesso> cbxTipoProcesso;

	@FXML
	private TextField tfdNumero;

	@FXML
	private TextField tfdComarca;

	@FXML
	private DatePicker tfdData;

	@FXML
	private TextField tfdDescricao;

	@FXML
	private TextField tfdFase;

	@FXML
	private TextField tfdClasse;

	@FXML
	private TextField tfdOrgao;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnCadastrar;

	private IFachada fachada;
	private Dialogo dialogo;
	private Processo processo;
	private Contrato contrato;
	private Funcionario funcionario;

	@Override
	public void init() {

		fachada = Fachada.getInstance();
		dialogo = Dialogo.getInstance();

		cbxTipoProcesso.getItems().addAll(TipoProcesso.values());

	}

	@Override
	public void actionButton(ActionEvent event) {

		Object obj = event.getSource();

		if (obj == btnCadastrar) {
			Log log = null;
			try {
				processo = criarProcesso();
				fachada.salvarEditarProcesso(processo);
				App.notificarOuvintes(Tela.CADASTRO_PROCESSO, processo);
				Alerta.getInstance().showMensagem(AlertType.INFORMATION, "Salvo", "Salvando...", "Salvo Com Seucesso");
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
						"Novo Processo: " + processo.getNumero() + " - " + processo.getTipo_processo(),
						StatusLog.CONCLUIDO);
				limparCampos();
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro Ao Salvar", "Erro ao Salvar Processo",
						e.getMessage());
				log = new Log(new Date(System.currentTimeMillis()), EventoLog.CADASTRAR, funcionario.getNome(),
						"Novo Processo: Erro", StatusLog.ERRO);
			} catch (ParseException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro Nos Dados!",
						"Erro Algum Dado Pode Estar Faltando ou esta incorreto!!!", e.getMessage());
			}

			try {
				fachada.salvarEditarLog(log);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		}
		if (obj == btnVoltar) {
			contrato = null;
			processo = null;
			System.gc();
			App.notificarOuvintes(Tela.PROCESSOS);

		}
	}

	@Override
	public void atualizar(Tela tela, Object object) {

		if (tela == Tela.PROCESSOS)
			if (object instanceof Contrato) {
				Contrato contrato = (Contrato) object;

				ContratoAdapter adapter = new ContratoAdapter();
				adapter.setData_contrato(contrato.getData_contrato());
				adapter.setNome_cliente(contrato.getConsulta().getCliente().getNome());
				adapter.setValor_total(contrato.getValor_total());
			}
		if (object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}
	}

	private Processo criarProcesso() throws ParseException, BusinessException {

		processo = new Processo();

		processo.setClasse_judicial(tfdClasse.getText().trim());
		processo.setComarca(tfdComarca.getText().trim());

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date data = df.parse(tfdData.getEditor().getText());
		processo.setData_atuacao(data);

		processo.setDescricao(tfdDescricao.getText().trim());
		processo.setFase(tfdFase.getText().trim());
		processo.setNumero(tfdNumero.getText().trim());
		processo.setOrgao_julgador(tfdOrgao.getText().trim());
		processo.setTipo_processo(cbxTipoProcesso.getValue());

		processo.setContrato(contrato);

		return processo;
	}

	private void limparCampos() {
		
		tfdClasse.setText("");
		tfdComarca.setText("");
		tfdData.getEditor().setText("");
		tfdDescricao.setText("");
		tfdFase.setText("");
		tfdNumero.setText("");
		tfdOrgao.setText("");

		processo = null;
		contrato = null;

	}
}
