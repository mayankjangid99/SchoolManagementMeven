package com.school.common.generic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class FreemarkerTemplate {

	private static Logger LOG = LoggerFactory.getLogger(FreemarkerTemplate.class);
	@SuppressWarnings("deprecation")
	private static Configuration getConfiguration(){
		Configuration cfg = new Configuration();
		// Where do we load the templates from:
		cfg.setClassForTemplateLoading(FreemarkerTemplate.class, "/");

		// Some other recommended settings:
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return cfg;
	}
	
	public static Template getTemplate(String templateName){
		try {
			return getConfiguration().getTemplate(templateName);
		} catch (TemplateNotFoundException e) {
			//e.printStackTrace();
			LOG.error("ERROR: StudentFeeDetailsServices in sendFeeReceiptOnEmail()");
		} catch (MalformedTemplateNameException e) {
			//e.printStackTrace();
			LOG.error("ERROR: StudentFeeDetailsServices in sendFeeReceiptOnEmail()");
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error("ERROR: StudentFeeDetailsServices in sendFeeReceiptOnEmail()");
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("ERROR: StudentFeeDetailsServices in sendFeeReceiptOnEmail()");
		}
		return null;
	}
	
	public static Map<String, Object> getInputMap(){
		Map<String, Object> input = new HashMap<String, Object>();

		SchoolProfileUtils schoolProfileUtils = SessionManagerDataHelper.getSchoolProfileUtil();
		input.put("schoolName", schoolProfileUtils.getName());
		String address = schoolProfileUtils.getAddress();
		if(schoolProfileUtils.getDistrict() != null)
			address = address+ ", " + schoolProfileUtils.getDistrict();
		if(schoolProfileUtils.getCity() != null)
			address = address + ", " + schoolProfileUtils.getCity();
		if(schoolProfileUtils.getPostcode() != null)
			address = address + "<br/>Pin- " + schoolProfileUtils.getPostcode();
		if(schoolProfileUtils.getPhone() != null)
			address = address + "<br/>Phone: " + schoolProfileUtils.getPhone();
		if(schoolProfileUtils.getFax() != null)
			address = address + ", Fax: " + schoolProfileUtils.getFax();
		input.put("schoolAddress", address);
		String logoPath = DataHelper.getDomainWithContext() + "/upload/school_images" + schoolProfileUtils.getLogo();
		input.put("schoolLogo", logoPath);
		return input;
	}
}
