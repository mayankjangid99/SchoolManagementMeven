package com.school.common.jasper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoGenerateJasperReport 
{
	
	@RequestMapping(value = "/graphIndex")
	public String graphIndex()
	{
		return "graph/index";
	}
	
	@RequestMapping(value = "/graphGraph")
	public String graphGraph()
	{
		return "graph/graph";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="GenerateFile",method=RequestMethod.POST)
	public String generateJasperToOther(HttpServletRequest request,@ModelAttribute JasperBean bean)
	{
		String drive=request.getParameter("drive");
		String filetype=request.getParameter("filetype");
		String filename=request.getParameter("filename");
		String protection=request.getParameter("protection");
		String ownerpwd=request.getParameter("ownerpwd");
		String userpwd=request.getParameter("userpwd");
		String graphtype=request.getParameter("graphtype");
		
		String realpath;
		
		try
		{
			realpath = request.getRealPath("/");
			realpath = realpath + "reports/";	
			InputStream inputStream;
			if(graphtype!=null)
			{
				inputStream = new FileInputStream (realpath+"/"+graphtype+".jrxml");
			}
			else
			{
				inputStream = new FileInputStream (realpath+"/my.jrxml");
			}
			 
			ArrayList<JasperBean> jasperDataList=new ArrayList<JasperBean>();

			JasperBean jasperBean;
			if(graphtype!=null)
			{
				int yr=2014;
				int ord=200;
				int mnth=2;
				for(int i=0;i<9;i++)
				{
					jasperBean=new JasperBean();
					if(i%3==0)
					{
					jasperBean.setYear(yr+1);
					yr++;
					}
					else {
						jasperBean.setMonth(mnth);
						jasperBean.setYear(yr);
						
					}
	
					jasperBean.setMonth(i%3+1);
					jasperBean.setOrder(ord/(i+1));
					ord=ord+(15*i);
					jasperDataList.add(jasperBean);
				}
			}
			else
			{
				jasperDataList.add(bean);
			}
			 
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(jasperDataList);
			 
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			String realpath2 = request.getRealPath("/");
	
			String imagePath= realpath2+"static\\img";
	
			realpath2 = realpath2 + "reports/";
			parameters.put("SUBREPORT_DIR", realpath2);
			parameters.put("IMAGEPATH", imagePath);
			 
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	//		JasperExportManager.exportReportToPdfFile(jasperPrint, "d:/test_jasper.pdf");
			
			if(filetype.equalsIgnoreCase("pdf"))
			{
				JRPdfExporter pdfexporter=new JRPdfExporter();
				if(protection.equalsIgnoreCase("yes"))
				{
					pdfexporter.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, ownerpwd);
					pdfexporter.setParameter(JRPdfExporterParameter.USER_PASSWORD, userpwd);
					pdfexporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
				}
				pdfexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
				pdfexporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				pdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,drive+filename+"."+filetype); //output file name (drive+filename+"."+filetype)
				pdfexporter.exportReport();
			}
			if(filetype.equalsIgnoreCase("xls"))
			{
				JRXlsExporter xlsexporter=new JRXlsExporter();
				/*if(protection.equalsIgnoreCase("yes"))
				{
					xlsexporter.setParameter(JRXlsExporterParameter.PASSWORD, userpwd);
				}*/
				xlsexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				xlsexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				xlsexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,drive+filename+"."+filetype); //output file name (drive+filename+"."+filetype)
				xlsexporter.exportReport();
			}
			if(filetype.equalsIgnoreCase("csv"))
			{
				JRCsvExporter  csvexporter = new JRCsvExporter();
				csvexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
				csvexporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				csvexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,drive+filename+"."+filetype); //output file name (drive+filename+"."+filetype)
				csvexporter.exportReport();
			}
			if(filetype.equalsIgnoreCase("xml"))
			{
				JRXmlExporter xmlexporter = new JRXmlExporter();
				xmlexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				xmlexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				xmlexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,drive+filename+"."+filetype); //output file name (drive+filename+"."+filetype)
				xmlexporter.exportReport();
			}
			if(filetype.equalsIgnoreCase("docx"))
			{
				JRDocxExporter docxexporter = new JRDocxExporter();
				docxexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				docxexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				docxexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,drive+filename+"."+filetype); //output file name (drive+filename+"."+filetype)
				docxexporter.exportReport();
			}
			if(filetype.equalsIgnoreCase("html"))
			{
				JRHtmlExporter htmlexporter = new JRHtmlExporter();
				htmlexporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING,"UTF-8");
				htmlexporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
				htmlexporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,drive+filename+"."+filetype); //output file name (drive+filename+"."+filetype)
				htmlexporter.exportReport();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "graph/success";
	}
	
}
