package br.com.sga.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

import br.com.sga.interfaces.IDaoXml;
import br.com.sga.view.Alerta;
import javafx.scene.control.Alert.AlertType;

public class DaoXml implements IDaoXml {

	private static DaoXml instance;
	private XStream xStream;
	private File file;
	
	private String ip;
	private DaoXml() {

		xStream = new XStream(new Dom4JDriver());
		xStream.alias("string", String.class);
		xStream.autodetectAnnotations(true);
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[] { 
				String.class
		});
		
		try {			
			file = new File(getClass().getClassLoader().getResource("ip.xml").getFile());
		} catch (Exception e) {
			file = new File("res/ip.xml");
		}
		
		
		ip = buscarIP();
		
	}

	public static DaoXml getInstance() {	
		if(instance == null)
			instance = new DaoXml();
		return instance;
	}

	@Override
	public synchronized String SalvarEditarIP(String ip) {

		
		try {
			if(!file.exists())
				file.createNewFile();
			else
			{
				file.delete();
				file.createNewFile();
			}
			
			OutputStream stream = new FileOutputStream(file);
			xStream.toXML("jdbc:postgresql://"+ip+":5432/SGA", stream);
			this.ip = "jdbc:postgresql://"+ip+":5432/SGA";

		} catch (IOException e) {
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Modificar IP", "");
			e.printStackTrace();
		}

		return ip;
	}

	@Override
	public synchronized String buscarIP() {

		String ip = null;
		
		try {
			if(!file.exists())
				file.createNewFile();
			else
				ip = (String) xStream.fromXML(file);
		} catch (Exception e) {
//			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Buscar IP", "");
			e.printStackTrace();
		}

//		if(ip == null)
//			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Arquivo de IP Vazio", "Contate o ADM");
		
		return ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
