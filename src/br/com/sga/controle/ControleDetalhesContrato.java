package  br.com.sga.controle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.Parte;
import br.com.sga.entidade.adapter.ContratoAdapter;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.view.Alerta;
import br.com.sga.view.Dialogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleDetalhesContrato extends Controle{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField dataField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField tipoPagaField;

    @FXML
    private TextField valorTotalField;

    @FXML
    private Button selectConButton;

    @FXML
    private TextField objetofField;

    @FXML
    private TextArea bancoField;

    @FXML
    private TextField valorField;

    @FXML
    private TextField jurosField;

    @FXML
    private Button selectParcelaButton;

    @FXML
    private TextField multaField;

    @FXML
    private TextField andamentoField;

    @FXML
    private TextField tipoParcelaField;

    @FXML
    private TextField nomeParteField;

    @FXML
    private TextField tipoParteField;

    @FXML
    private Button selectParteButton;

    @FXML
    private TextField tipoParticiField;

    @FXML
    private Button voltarButton;
    
    private Cliente cliente;
    
    private Contrato contrato;
    
    private IFachada fachada ;
    
    private Dialogo dialogo;

    @Override
    public  void actionButton(ActionEvent event) {
    	if(voltarButton == event.getSource() ) {
			if(selectConButton.isVisible()) { // siginifica ter vindo da tela de cliente
				App.notificarOuvintes(Tela.clientes);
			}else 
				App.notificarOuvintes(Tela.buscar_contrato);
		}
    	else if(selectConButton == event.getSource() ) {
    		try {
				List<ContratoAdapter> contratos = fachada.buscarContratoPorClienteAdapter(cliente.getCpf_cnpj());
				contrato = new Contrato();
				ContratoAdapter adapter = dialogo.selecao(contratos,"Selecione Contrato","Selecione um contrato para mais detalhes");
				contrato.setId(adapter.getId());
				atualizarDadosContrato();
			} catch (BusinessException e) {
				e.printStackTrace();
			}
    	}
    	else if(contrato!= null) {
    		if(selectParcelaButton == event.getSource() ) {
    			Parcela parcela =   dialogo.selecao(contrato.getParcelas(),"Selecione Parcelas","Selecione uma parcela para mais detalhes");
    			valorField.setText(parcela.getValor()+"");
    			tipoParcelaField.setText(parcela.getTipo().toString());
    			jurosField.setText(parcela.getJuros()+"");
    			multaField.setText(parcela.getMulta()+"");
    			andamentoField.setText(parcela.getEstado().toString());
    			
    		}else if(selectParteButton == event.getSource() ) {
	    		Parte parte  = dialogo.selecao(contrato.getPartes(),"Seleção de parte","Selecione uma das partes para mais detalhes ");
	    		nomeParteField.setText(parte.getNome());
	    		tipoParteField.setText(parte.getTipo_parte().toString());
	    		tipoParticiField.setText(parte.getTipo_participacao().toString());
    		}
    	}else
    		Alerta.getInstance().showMensagem("Alerta","Ação invilida","Não há contratos selecionados");
    }

 
	private void atualizarDadosContrato() {
		try {
			contrato = fachada.buscarContratoPorId(contrato.getId());
			
			dataField.setText(contrato.getData_contrato().toString());
			areaField.setText(contrato.getArea().toString());
			valorTotalField.setText(contrato.getValor_total()+"");
			bancoField.setText(contrato.getDados_banco());
			tipoPagaField.setText(contrato.getTipo_pagamento().toString());
			objetofField.setText(contrato.getObjeto());
		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void atualizar(Tela tela, Object object) {
		if(tela == Tela.Detalhes_contrato) {
			limparCampos();
			if(object instanceof Cliente) {
				Cliente cliente = (Cliente) object;
				if(this.cliente == null || !this.cliente.equals(cliente) )
					this.cliente = cliente;
				
				selectConButton.setVisible(true);
			}else if(object instanceof ContratoAdapter) {
				this.contrato = new Contrato(); 
				this.contrato.setId(((ContratoAdapter)(object)).getId());
				selectConButton.setVisible(false);
				atualizarDadosContrato();
			}
			
		}else {
			contrato = null;
		}
		
	}

	private void limparCampos() {
		dataField.setText("");
		areaField.setText("");
		valorTotalField.setText("");
		bancoField.setText("");
		tipoPagaField.setText("");
		objetofField.setText("");
		
		valorField.setText("");
		tipoParcelaField.setText("");
		jurosField.setText("");
		multaField.setText("");
		andamentoField.setText("");
		
		nomeParteField.setText("");
		tipoParteField.setText("");
		tipoParticiField.setText("");
	}


	@Override
	public void init() {
		fachada = Fachada.getInstance(); 
		dialogo = Dialogo.getInstance();
	}
}
