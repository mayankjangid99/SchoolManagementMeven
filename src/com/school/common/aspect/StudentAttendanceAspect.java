package com.school.common.aspect;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.generic.EmailLookUpFile;
import com.school.common.generic.EmailUtil;
import com.school.common.generic.FreemarkerTemplate;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.SectionInformationModel;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Aspect
@Component
public class StudentAttendanceAspect {
	
	private static Logger LOG = LoggerFactory.getLogger(StudentAttendanceAspect.class);
	
	
	
	@After("execution(* com.school.common.services.StudentAttendanceServices.saveMarkAttendance(..)) && args(..,classInformation,sectionInformation)")
	public void saveMarkAttendance(JoinPoint joinPoint, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		LOG.info("In StudentAttendanceAspect: @After StudentAttendanceServices.saveMarkAttendance(..) Class Name = " + classInformation.getClassName() 
		+ " AND Section Name = " + sectionInformation.getSectionName());

		if("Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_ATTENDANCE))){
			try {
		
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				String recipientsEmailForAbsent = (String)request.getAttribute("recipientsEmailForAbsent");
				String recipientsSmsForAbsent = (String)request.getAttribute("recipientsSmsForAbsent");
				String recipientsEmailForPresent = (String)request.getAttribute("recipientsEmailForPresent");
				String recipientsSmsForPresent = (String)request.getAttribute("recipientsSmsForPresent");
				
				Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
				Map<String, Object> input = FreemarkerTemplate.getInputMap();
				EmailUtil emailUtil = new EmailUtil();
				Writer consoleWriter = null;
				String mailMessageBody = null;				
				String emailContent = null;
				
				if(recipientsEmailForAbsent != null && !"".equals(recipientsEmailForAbsent)) {
					emailContent = "Your children Absent Today";
					input.put("content", emailContent);
					consoleWriter = new StringWriter();
					template.process(input, consoleWriter);
		            mailMessageBody = consoleWriter.toString();
					emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipientsEmailForAbsent, "Student Attendance Status", mailMessageBody);
					LOG.info("StudentAttendanceAspect.java -> saveMarkAttendance() Absent send email = " + recipientsEmailForAbsent);
				}
				if(recipientsSmsForAbsent != null && !"".equals(recipientsSmsForAbsent)) {
					LOG.info("StudentAttendanceAspect.java -> saveMarkAttendance() Absent send sms = "+recipientsSmsForAbsent);					
				}
				if(recipientsEmailForPresent != null && !"".equals(recipientsEmailForPresent)) {
					emailContent = "Your children Present Today";		
					input.put("content", emailContent);
					consoleWriter = new StringWriter();
					template.process(input, consoleWriter);
					mailMessageBody = consoleWriter.toString();
					emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipientsEmailForPresent, "Student Attendance Status", mailMessageBody);
					LOG.info("StudentAttendanceAspect.java -> saveMarkAttendance() Present send email = "+recipientsEmailForPresent);
				}
				if(recipientsSmsForPresent != null && !"".equals(recipientsSmsForPresent)) {
					LOG.info("StudentAttendanceAspect.java -> saveMarkAttendance() Present send sms = "+recipientsSmsForPresent);					
				}
			} catch (TemplateException e) {
				//e.printStackTrace();
				LOG.error("ERROR: saveMarkAttendance() in StudentAttendanceAspect" + e);
			} catch (IOException e) {
				//e.printStackTrace();
				LOG.error("ERROR: saveMarkAttendance() in StudentAttendanceAspect" + e);
			}
		}
	}
	
	
	
	@After("execution(* com.school.common.services.StudentAttendanceServices.updateMarkAttendance(..)) && args(..,classInformation,sectionInformation)")
	public void updateMarkAttendance(JoinPoint joinPoint, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		LOG.info("In StudentAttendanceAspect: @After StudentAttendanceServices.updateMarkAttendance(..) Class Name = " + classInformation.getClassName() 
				+ " AND Section Name = " + sectionInformation.getSectionName());

		if("Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_ATTENDANCE))){
			try {
	
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				String recipientsEmailForAbsent = (String)request.getAttribute("recipientsEmailForAbsent");
				String recipientsSmsForAbsent = (String)request.getAttribute("recipientsSmsForAbsent");
				String recipientsEmailForPresent = (String)request.getAttribute("recipientsEmailForPresent");
				String recipientsSmsForPresent = (String)request.getAttribute("recipientsSmsForPresent");
				
				Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
				Map<String, Object> input = FreemarkerTemplate.getInputMap();
				EmailUtil emailUtil = new EmailUtil();
				Writer consoleWriter = null;
				String mailMessageBody = null;				
				String emailContent = null;
				
				if(recipientsEmailForAbsent != null && !"".equals(recipientsEmailForAbsent)) {
					emailContent = "Your children Absent Today";
					input.put("content", emailContent);
					consoleWriter = new StringWriter();
					template.process(input, consoleWriter);
		            mailMessageBody = consoleWriter.toString();
					emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipientsEmailForAbsent, "Student Attendance Status", mailMessageBody);
					LOG.info("StudentAttendanceAspect.java -> updateMarkAttendance() Absent send email = " + recipientsEmailForAbsent);
				}
				if(recipientsSmsForAbsent != null && !"".equals(recipientsSmsForAbsent)) {
					LOG.info("StudentAttendanceAspect.java -> updateMarkAttendance() Absent send sms = "+recipientsSmsForAbsent);					
				}
				if(recipientsEmailForPresent != null && !"".equals(recipientsEmailForPresent)) {
					emailContent = "Your children Present Today";		
					input.put("content", emailContent);
					consoleWriter = new StringWriter();
					template.process(input, consoleWriter);
					mailMessageBody = consoleWriter.toString();
					emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipientsEmailForPresent, "Student Attendance Status", mailMessageBody);
					LOG.info("StudentAttendanceAspect.java -> updateMarkAttendance() Present send email = "+recipientsEmailForPresent);
				}
				if(recipientsSmsForPresent != null && !"".equals(recipientsSmsForPresent)) {
					LOG.info("StudentAttendanceAspect.java -> updateMarkAttendance() Present send sms = "+recipientsSmsForPresent);					
				}
			} catch (TemplateException e) {
				//e.printStackTrace();
				LOG.error("ERROR: updateMarkAttendance() in StudentAttendanceAspect" + e);
			} catch (IOException e) {
				//e.printStackTrace();
				LOG.error("ERROR: updateMarkAttendance() in StudentAttendanceAspect" + e);
			}
		}
	}
}
