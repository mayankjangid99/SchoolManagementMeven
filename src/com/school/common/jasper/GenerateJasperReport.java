package com.school.common.jasper;

import java.io.File;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateJasperReport 
{

	private static Logger LOG = LoggerFactory.getLogger(GenerateJasperReport.class);
	
	public static void generateJasperReport(JasperPrint jasperPrint, String fileType, String filePath)
	{
		generateJasperReport(jasperPrint, fileType, filePath, false, null, null);
	}
	
	public static void generateJasperReportPwdProtected(JasperPrint jasperPrint, String fileType, String filePath, String ownerPwd, String userPwd)
	{
		generateJasperReport(jasperPrint, fileType, filePath, true, ownerPwd, userPwd);
	}
	
	
	private static void generateJasperReport(JasperPrint jasperPrint, String fileType, String filePath, boolean pwdProtected, String ownerPwd, String userPwd)
	{
		try
		{
			/*JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(jasperDataList);
			 
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			String realpath2 = request.getRealPath("/");
	
			String imagePath= realpath2+"images";
	
			realpath2 = realpath2 + "reports/";
			parameters.put("SUBREPORT_DIR", realpath2);
			parameters.put("IMAGEPATH", imagePath);
			 
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);*/
	//		JasperExportManager.exportReportToPdfFile(jasperPrint, "d:/test_jasper.pdf");
			
			
			if(fileType.equalsIgnoreCase("pdf"))
			{
				JRPdfExporter pdfexporter = new JRPdfExporter();
				if(pwdProtected)
				{
					pdfexporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, ownerPwd);
					pdfexporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, userPwd);
					pdfexporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
				}
				pdfexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
				pdfexporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				pdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filePath); //output file name (drive+filename+"."+filetype)
				pdfexporter.exportReport();
			}
			if(fileType.equalsIgnoreCase("xls"))
			{
				JRXlsExporter xlsexporter=new JRXlsExporter();
				/*if(protection.equalsIgnoreCase("yes"))
				{
					xlsexporter.setParameter(JRXlsExporterParameter.PASSWORD, userpwd);
				}*/
				xlsexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				xlsexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				xlsexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filePath); //output file name (drive+filename+"."+filetype)
				xlsexporter.exportReport();
			}
			if(fileType.equalsIgnoreCase("csv"))
			{
				JRCsvExporter  csvexporter = new JRCsvExporter();
				csvexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
				csvexporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				csvexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filePath); //output file name (drive+filename+"."+filetype)
				csvexporter.exportReport();
			}
			if(fileType.equalsIgnoreCase("xml"))
			{
				JRXmlExporter xmlexporter = new JRXmlExporter();
				xmlexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				xmlexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				xmlexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filePath); //output file name (drive+filename+"."+filetype)
				xmlexporter.exportReport();
			}
			if(fileType.equalsIgnoreCase("docx"))
			{
				JRDocxExporter docxexporter = new JRDocxExporter();
				docxexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				docxexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				docxexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filePath); //output file name (drive+filename+"."+filetype)
				docxexporter.exportReport();
			}
			if(fileType.equalsIgnoreCase("html"))
			{
				JRHtmlExporter htmlexporter = new JRHtmlExporter();
				htmlexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				htmlexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				htmlexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, filePath); //output file name (drive+filename+"."+filetype)
				htmlexporter.exportReport();
			}
		}
		catch(Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: generateJasperReport() in GenerateJasperReport", e);
		}
	}
}
