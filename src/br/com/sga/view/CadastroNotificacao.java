package br.com.sga.view;


import java.time.LocalDate;

import br.com.sga.entidade.enums.Prioridade;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class CadastroNotificacao extends VBox{
	private RadioButton apenasMinRadio,todosRadio;
	private TextArea descricaoArea;
	private ComboBox<Prioridade> prioridadeBox;
	private ComboBox<Integer> horaBox;
	private DatePicker dataPicker;
	
	public CadastroNotificacao(LocalDate date) {
		
		//prioridade da tarefa
		prioridadeBox  = new ComboBox<>();
		prioridadeBox.getItems().addAll(Prioridade.values());
		prioridadeBox.setPromptText("Prioridade");
		
		// pane para descricao
		FlowPane descricaoPane = new FlowPane();
		Label descricaoLabel = new Label("Descrição ");
		descricaoArea = new TextArea();
		descricaoArea.setPrefHeight(100);
		descricaoPane.getChildren().addAll(descricaoLabel,descricaoArea);
		
		// pane para escolha de funcionarios
		FlowPane funcionariosPane = new FlowPane();
		Label funcionariosLabel =  new Label("A quem mostrar");
		apenasMinRadio = new RadioButton("Apénas a mim");
		todosRadio  = new RadioButton("todos os funcionarios");
		ToggleGroup tg = new ToggleGroup();
		apenasMinRadio.setToggleGroup(tg);
		apenasMinRadio.setSelected(true);
		todosRadio.setToggleGroup(tg);
		funcionariosPane.setHgap(5);
		funcionariosPane.getChildren().addAll(funcionariosLabel,apenasMinRadio,todosRadio);
		
		// pane para pegar data e hora
		FlowPane horarioPane = new FlowPane();
		dataPicker = new  DatePicker();
		dataPicker.setPromptText("Data para aviso");
		dataPicker.setValue(date);
		horaBox = new ComboBox<>();
		horaBox.setPromptText("Hora");
		for(int i = 0 ; i <= 23 ; i++)
			horaBox.getItems().add(i);
		horarioPane.setHgap(5);
		horarioPane.getChildren().addAll(dataPicker,horaBox);
		setSpacing(5);
		// adicionando a raiz
		getChildren().addAll(descricaoPane,prioridadeBox,funcionariosPane,horarioPane);
	}

	public RadioButton getApenasMinRadio() {
		return apenasMinRadio;
	}


	public RadioButton getTodosRadio() {
		return todosRadio;
	}


	public TextArea getDescricaoArea() {
		return descricaoArea;
	}


	public ComboBox<Prioridade> getPrioridadeBox() {
		return prioridadeBox;
	}


	public DatePicker getDataPicker() {
		return dataPicker;
	}

	public ComboBox<Integer> getHoraBox() {
		return horaBox;
	}
	
}
