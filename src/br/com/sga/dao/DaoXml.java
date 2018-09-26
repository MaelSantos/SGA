package br.com.sga.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import br.com.sga.interfaces.IDaoXml;
import br.com.sga.view.Alerta;
import javafx.scene.control.Alert.AlertType;

public class DaoXml implements IDaoXml {

	private static DaoXml instance;
	private XStream xStream;
	private File file;
	private OutputStream stream;
	
	private String ip;

	private DaoXml() {

		xStream = new XStream(new Dom4JDriver());
		xStream.alias("ip", String.class);
		xStream.autodetectAnnotations(true);
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[] { 
				String.class
		});
		xStream.addPermission(NoTypePermission.NONE);
		xStream.addPermission(NullPermission.NULL);
		xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		file = new File("res/ip.xml");
		try {
			stream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro No Arquivo de IP", "Contate o ADM");
			e.printStackTrace();
		}

		ip = buscarIP();
		
	}

	public static DaoXml getInstance() {	
		if(instance == null)
			instance = new DaoXml();
		return instance;
	}

	@Override
	public String SalvarEditarIP(String ip) {

		try {
			if(!file.exists())
				file.createNewFile();
			else
			{
				file.delete();
				file.createNewFile();
			}

			xStream.toXML("jdbc:postgresql://"+ip+":5432/SGA", stream);	

		} catch (IOException e) {
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Modificar IP", "");
			e.printStackTrace();
		}

		return ip;
	}

	@Override
	public String buscarIP() {

		String ip = null;

		try {
			if(!file.exists())
				file.createNewFile();
			else
			{
				ip = (String) xStream.fromXML(file);
				this.ip = ip;
			}
		} catch (IOException e) {
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Erro ao Buscar IP", "");
			e.printStackTrace();
		}

		if(ip == null)
			Alerta.getInstance().showMensagem(AlertType.ERROR, "Erro!", "Arquivo de IP Encontrasse Vazio", "Contate o ADM");
		
		return ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
