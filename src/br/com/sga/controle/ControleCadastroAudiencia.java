package br.com.sga.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sga.app.App;
import br.com.sga.entidade.Audiencia;
import br.com.sga.entidade.Processo;
import br.com.sga.entidade.enums.StatusAudiencia;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoAudiencia;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControleCadastroAudiencia extends Controle {

	@FXML
	private ComboBox<TipoAudiencia> cbxTipo;
	
	@FXML
	private ComboBox<StatusAudiencia> cbxStatus;

	@FXML
	private TextField tfdProcesso;

	@FXML
	private TextField tfdVara;

	@FXML
	private TextField tfdOrgao;

	@FXML
	private DatePicker tfdData;

	@FXML
	private Button btnCadastrar;

	@FXML
    private Button btnVoltar;
	
	private IFachada fachada;
	private Audiencia audiencia;

	@Override
	public void atualizar(Tela tela, Object object) {
		
		if (object instanceof Processo) {
			Processo processo = (Processo) object;
			
			audiencia = new Audiencia();
			audiencia.setProcesso(processo);
			tfdProcesso.setText(processo.toString());
			
		}

	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
		
		cbxStatus.getItems().addAll(StatusAudiencia.values());
		cbxTipo.getItems().addAll(TipoAudiencia.values());

	}

	@Override
	public void actionButton(ActionEvent event) {
		
		Object obj = event.getSource();
		
		if(obj == btnCadastrar)
		{
			
			try {
				Audiencia audiencia = criarAudiencia();
				fachada.salvarEditarAudiencia(audiencia);
				Alerta.getInstance().showMensagem("Salvo", "", "Audiencia Cadastrada Com Sucesso");
				App.notificarOuvintes(Tela.cadastro_audiencia,audiencia);
				limparCampos();
			} catch (BusinessException | ParseException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem("Erro!", "Erro Ao Salvar Audiencia", e.getMessage());
			}
			
		}
		if(obj == btnVoltar)
			App.notificarOuvintes(Tela.detalhes_processo);
		

	}

	private void limparCampos() {
		
		tfdData.getEditor().setText("");
		tfdOrgao.setText("");
		tfdProcesso.setText("");
		tfdVara.setText("");
		
	}

	private Audiencia criarAudiencia() throws ParseException {

//		audiencia = new Audiencia();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date data = df.parse(tfdData.getEditor().getText());
		audiencia.setData_audiencia(data);

		audiencia.setStatus(cbxStatus.getValue());
		audiencia.setOrgao(tfdOrgao.getText().trim());
		audiencia.setTipo(cbxTipo.getValue());
		audiencia.setVara(tfdVara.getText().trim());
		
//		audiencia.setProcesso();
		
		return audiencia;
	}

}
