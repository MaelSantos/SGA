package br.com.sga.entidade.tabelaView;

import javafx.beans.property.SimpleStringProperty;

public class Contrato {
	private final SimpleStringProperty data;
    private final SimpleStringProperty area;
    private final SimpleStringProperty objeto;
	public Contrato(String data, String area, String objeto) {
		super();
		this.data =  new SimpleStringProperty(data);
		this.area = new SimpleStringProperty(area);
		this.objeto = new SimpleStringProperty(objeto);
	}
	public String getData() {
		return data.get();
	}
	public String getArea() {
		return area.get();
	}
	public String getObjeto() {
		return objeto.get();
	}
    
	public void getData(String data) {
		this.data.set(data);
	}
	public  void getArea(String area) {
		this.area.set(area);
	}
	public  void getObjeto(String objeto) {
		this.objeto.set(objeto);
	}
    
	
	
    
}
