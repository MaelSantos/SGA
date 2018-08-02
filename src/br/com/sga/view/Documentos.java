package br.com.sga.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Receita;
import br.com.sga.entidade.enums.Area;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
import br.com.sga.fachada.IFachada;
import br.com.sga.interfaces.IDaoCommun;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Documentos {

	public void gerar(String arquivo,Collection<? extends Object> list) throws JRException {
		
		//gerando o jasper design
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(arquivo);
		
		JasperDesign desenho = JRXmlLoader.load(inputStream);
		
		//compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport(desenho);
		
		/* Convert List to JRBeanCollectionDataSource */
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
		
		/* Map to hold Jasper report Parameters */
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ItemDataSource", itemsJRBean);
		
		/* Using compiled version(.jasper) of Jasper report to generate PDF */
		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parameters, itemsJRBean);
//		jasperPrint.
		
		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
		jasperViewer.setZoomRatio(0.75F);
		jasperViewer.setLocationRelativeTo(null);
		jasperViewer.show();		
		//		JasperViewer.viewReport(jasperPrint);

	}
	
	public static void main(String[] args) {
		
		IDaoCommun commun = DaoCommun.getInstance();
		IFachada fachada = Fachada.getInstance();
		try {
			List<Consulta> list = new ArrayList<>();
			
			Consulta consulta = new Consulta();
			consulta.setArea(Area.CIVIL);
			consulta.setData_consulta(new Date());
			consulta.setDescricao("Teste");
			consulta.setIndicacao("Wanderson");
		
			Cliente cliente = fachada.buscarClientePorId(1);
			
			consulta.setCliente(cliente);
			
			list.add(consulta);
			
			new Documentos().gerar("Ficha.jrxml", list);
		} catch (JRException | BusinessException e) {
			e.printStackTrace();
		}
		
	}
	

}
