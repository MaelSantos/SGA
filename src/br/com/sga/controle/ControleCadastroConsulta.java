package br.com.sga.controle;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.sga.app.App;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Endereco;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Telefone;
import br.com.sga.entidade.Testemunha;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Estado;
import br.com.sga.entidade.enums.Tela;
import br.com.sga.entidade.enums.TipoTelefone;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleCadastroConsulta extends Controle{


    @FXML
    private TextField honorarioField;

    @FXML
    private TextField dadoClienteField;

    @FXML
    private ComboBox<Area> areaBox;

    @FXML
    private TextArea descricaoArea;

    @FXML
    private DatePicker dataConsultaPicker;

    @FXML
    private TableView<Testemunha> testemunhaTableView;

    @FXML
    private TableColumn<Testemunha,String> nomeTestemunhaColumn;

    @FXML
    private TableColumn<Testemunha,Telefone> telefoneTestemunhaColumn;

    @FXML
    private TableColumn<Testemunha,Endereco> enderecoTestemunhaColumn;

    @FXML
    private Button gerarDocumentoButton;

    @FXML
    private Button salvarConsulta;

    @FXML
    private Button buscarClienteButton;

    @FXML
    private TextField dadoFuncionarioField;

    @FXML
    private Button buscarFuncionarioButton;
    
    @FXML
    private Button voltarButton;

    @FXML
    private RadioButton funcionarioLogadoRadio;

    @FXML
    private RadioButton outroFuncionarioRadio;

    @FXML
    private TextField nomeIndicacaoField;

    @FXML
    private Button addTestemunhaField;

    @FXML
    private TextField ruaField;

    @FXML
    private TextField numeroField;

    @FXML
    private TextField bairroField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField paisField;

    @FXML
    private ComboBox<Estado> estadoBox;

    @FXML
    private TextField cepField;

    @FXML
    private TextField complementoField;

    @FXML
    private TextField nomeTestemunhaField;

    @FXML
    private TextField telefonePreField;

    @FXML
    private TextField telefoneNumeroField;

    @FXML
    private ComboBox<TipoTelefone> tipoTelefoneBox;


    private IFachada fachada ;
    private Funcionario funcionario,outroFuncionario;
    private Cliente cliente;
    
    public void actionButton(ActionEvent event) {
    	System.out.println("evento");
    	
    	if(voltarButton == event.getSource() ) {
    		App.notificarOuvintes(Tela.Consulta);
    	}
    	else if(event.getSource() == outroFuncionarioRadio) 
    	{
    		buscarFuncionarioButton.setVisible(true);
    		dadoFuncionarioField.setVisible(true);
    	}
    	else if(event.getSource() == funcionarioLogadoRadio)
    	{
    		buscarFuncionarioButton.setVisible(false);
    		dadoFuncionarioField.setVisible(false);
    		dadoFuncionarioField.setText("");
    		outroFuncionario = null;
    		System.gc();
    	}
    	else  if(event.getSource() == buscarClienteButton) 
    	{
    		try {
				List<Cliente> clientes = fachada.buscarClientePorBusca(dadoClienteField.getText().trim());
    			//cliente = Dialogo.getInstance().selecaoCliente(clientes);
//				cliente = Dialogo.getInstance().selecao(clientes,"Selecione de cliente","Selecione o cliente j� cadastrado para consulta");
				cliente = Dialogo.getInstance().selecionar(clientes);
    			dadoClienteField.setText(cliente.getNome());
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR,"Erro","",e.getMessage());
			}
    	}
		else  if(event.getSource() == buscarFuncionarioButton) 
		{
			try {
				List<Funcionario> funcionarios = fachada.buscarUsuarioPorBusca(dadoFuncionarioField.getText().trim());
    			outroFuncionario = Dialogo.getInstance().selecionar(funcionarios);
    			dadoFuncionarioField.setText(outroFuncionario.getNome());
			} catch (BusinessException e) {
				e.printStackTrace();
				Alerta.getInstance().showMensagem(AlertType.ERROR,"Erro","",e.getMessage());
			}
		}
		else  if(event.getSource() == addTestemunhaField) {
			if(nomeTestemunhaField.getText().trim().length() >0 && telefoneNumeroField.getText().trim().length()>0 
					&& telefonePreField.getText().trim().length() >0 && estadoBox.getSelectionModel().getSelectedItem() != null)
			{
				String nome = nomeTestemunhaField.getText().trim();
				Telefone telefone = new Telefone(Integer.parseInt(telefoneNumeroField.getText().trim()),
						Integer.parseInt(telefonePreField.getText().trim()),tipoTelefoneBox.getSelectionModel().getSelectedItem());
				Endereco endereco = new Endereco(ruaField.getText().trim(),numeroField.getText().trim(), bairroField.getText().trim()
						,cidadeField.getText().trim(), estadoBox.getSelectionModel().getSelectedItem().toString(),
						paisField.getText().trim(),complementoField.getText().trim(),cepField.getText().trim());
				
				testemunhaTableView.getItems().add(new Testemunha(endereco, telefone, nome));
			limparCamposTestemunha();
			}
			else
				Alerta.getInstance().showMensagem("Alerta","","Campos obrigatorios para testemunha vazios: \nH� um ou mais campos obrigatorios sem entrada");
				
			
		}
		else  if(event.getSource() == salvarConsulta) 
		{
			if(cliente  != null && dadoClienteField.getText().trim().length() !=0) {
				if(areaBox.getSelectionModel().getSelectedItem() != null && descricaoArea.getText().trim().length() >0
						&& honorarioField.getText().trim().length() >0) {
					try {
						Area area = areaBox.getSelectionModel().getSelectedItem();
				    	LocalDate ld = dataConsultaPicker.getValue();
				    	Calendar c =  Calendar.getInstance();
				    	c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
				    	Date data_consulta = c.getTime();
				    	String descricao = descricaoArea.getText().trim();
				    	String indicacao =  nomeIndicacaoField.getText().trim();
				    	Float valor_honorario =  Float.parseFloat(honorarioField.getText().trim());
				    	//apenas verificando qual dos dois tipos de opcionalidade para escolher o funcionario que fez a consulta foi selecionado
				    	Funcionario funcionario = null;
				    	if(funcionarioLogadoRadio.isSelected()) 
				    		 funcionario = this.funcionario;
						else 
							 funcionario = outroFuncionario;
				    	fachada.salvarEditarConsulta(new Consulta(area, descricao, data_consulta, valor_honorario, indicacao,
								cliente, funcionario,testemunhaTableView.getItems()));
				    	Alerta.getInstance().showMensagem("Consulta inserida","","Consulta com cadastrada com sucesso!");
				    	limparCamposConsulta();
					}catch (NumberFormatException e) {
						Alerta.getInstance().showMensagem("Erro","","Campo n�merico invalido:\nH� um ou mais campos com entradas invalidas");
					}catch (BusinessException e) {
						e.printStackTrace();
						Alerta.getInstance().showMensagem("Erro","",e.getMessage());
					}
				}else
					Alerta.getInstance().showMensagem("Alerta","","Campos obrigatorios vazios: \nH� um ou mais campos obrigatorios sem entrada");
			}else
				Alerta.getInstance().showMensagem("Alerta","","N�o h� um cliente selecionado: \nfavor selecionar cliente antes de salvar");
				
		}
		else  if(event.getSource() == gerarDocumentoButton) {}
    }

	@Override
	public void atualizar(Tela tela, Object object) {
		if(object instanceof Funcionario) {
			funcionario = (Funcionario) object;
		}
		dataConsultaPicker.setValue(LocalDate.now());
	}

	@Override
	public void init() {
		fachada = Fachada.getInstance();
    	
    	areaBox.getItems().addAll(Area.values());
		estadoBox.getItems().addAll(Estado.values());
		tipoTelefoneBox.getItems().addAll(TipoTelefone.values());
		
        nomeTestemunhaColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        telefoneTestemunhaColumn.setCellValueFactory( new PropertyValueFactory<>("telefone")); // utiliazra do to string de ambos
        enderecoTestemunhaColumn.setCellValueFactory( new PropertyValueFactory<>("endereco"));
        
        ToggleGroup tg = new ToggleGroup();
		funcionarioLogadoRadio.setToggleGroup(tg);
		funcionarioLogadoRadio.setSelected(true);
		outroFuncionarioRadio.setToggleGroup(tg);
	}
	  
    private void limparCamposTestemunha() {
    	nomeTestemunhaColumn.setText("");
		telefonePreField.setText("");
		telefoneNumeroField.setText("");
		ruaField.setText("");
		numeroField.setText("");
		bairroField.setText("");
		cidadeField.setText("");
		cepField.setText("");
		paisField.setText("");
		complementoField.setText("");
    }
    private void limparCamposConsulta() {
    	dadoClienteField.setText("");
    	cliente = null;
    	dadoFuncionarioField.setText("");
    	outroFuncionario = null;
    	descricaoArea.setText("");
    	nomeIndicacaoField.setText("");
    	honorarioField.setText("");
    	System.gc();
    	
    }
}
