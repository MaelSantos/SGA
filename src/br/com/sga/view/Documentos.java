package br.com.sga.view;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sga.dao.DaoCommun;
import br.com.sga.entidade.Cliente;
import br.com.sga.entidade.Consulta;
import br.com.sga.entidade.Contrato;
import br.com.sga.entidade.Funcionario;
import br.com.sga.entidade.Parcela;
import br.com.sga.entidade.adapter.DocumentoContratoAdapter;
import br.com.sga.entidade.enums.Area;
import br.com.sga.entidade.enums.Instancia;
import br.com.sga.entidade.enums.Tabela;
import br.com.sga.entidade.enums.TipoPagamento;
import br.com.sga.exceptions.BusinessException;
import br.com.sga.exceptions.DaoException;
import br.com.sga.fachada.Fachada;
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

	@SuppressWarnings("deprecation")
	public void gerarDocumento(List<? extends Object> list, String layout) throws JRException, FileNotFoundException {

		// gerando o jasper design
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(layout);

		System.out.println(inputStream);
		JasperDesign desenho = JRXmlLoader.load(inputStream);

		// compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		/* Convert List to JRBeanCollectionDataSource */
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);

		/* Map to hold Jasper report Parameters */
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ItemDataSource", itemsJRBean);

		/* Using compiled version(.jasper) of Jasper report to generate PDF */
		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parameters, itemsJRBean);
	
		JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
		jasperViewer.setZoomRatio(1.25F);
		jasperViewer.setLocationRelativeTo(null);
		jasperViewer.show();
		// JasperViewer.viewReport(jasperPrint);
	
	}
	
	public static void main(String[] args) {
				
		try {
			List<DocumentoContratoAdapter> list = new ArrayList<>();
			
			Contrato contrato = new Contrato();
			contrato.setArea(Area.CIVIL);
			contrato.setDados_banco("BANCO DO BRASIL, AGÊNCIA 0000-0, CONTA CORRENTE 00.000-0, CPF 000.000.000-00");
			contrato.setObjeto("Prestação de serviços advocatícios");
			List<Parcela>parcelas = new ArrayList<>();
			parcelas.add(new Parcela());
			contrato.setParcelas(parcelas);
			contrato.setTipo_pagamento(TipoPagamento.A_VISTA);
			contrato.setValor_total(120000);
			contrato.setObjeto("apresentar defesa");
			contrato.setPartes(DaoCommun.getInstance().getPartes(7, Tabela.PROCESSO));
			Consulta consulta = new Consulta();
			Cliente cliente = Fachada.getInstance().buscarClientePorId(1);
			Funcionario funcionario = Fachada.getInstance().buscarPorLogin("mael_santos7", "07080209");
			consulta.setCliente(cliente);
			consulta.setFuncionario(funcionario);
			contrato.setConsulta(consulta);
			contrato.setData_contrato(new Date());
			
			DocumentoContratoAdapter adapter = new DocumentoContratoAdapter();
			adapter.setContrato(contrato);
			adapter.setNumeroProcesso("900989998");
			adapter.setTipo(Instancia.INSTANCIA_1);
			adapter.setComarca("serra talhada/pe");
			
			list.add(adapter);
						
			new Documentos().gerarDocumento(list, "ContratoSem.jrxml");
			
		} catch (FileNotFoundException | JRException | BusinessException | DaoException e) {
			e.printStackTrace();
		}
		
	}
}
