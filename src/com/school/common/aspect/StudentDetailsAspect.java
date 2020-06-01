package com.school.common.aspect;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.school.common.generic.EmailLookUpFile;
import com.school.common.generic.EmailUtil;
import com.school.common.generic.FreemarkerTemplate;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.services.UserServices;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Aspect
@Component
public class StudentDetailsAspect {
	

	private static Logger LOG = LoggerFactory.getLogger(StudentDetailsAspect.class);
	
	
	@After("execution(* com.school.common.services.StudentDetailsServices.saveStudentDetails(..)) && args(..,studentDetails)")
	public void saveStudentDetails(JoinPoint joinPoint, StudentDetailsModel studentDetails)
	{
		LOG.info("In StudentDetailsAspect: @After StudentDetailsServices.saveStudentDetails(..) " + studentDetails.getFirstName());

		if("Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_DETAILS))){
			try {
				EmailUtil emailUtil = new EmailUtil();
				Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
				Map<String, Object> input = FreemarkerTemplate.getInputMap();	
				StringBuffer message = new StringBuffer();
				ParentDetailsModel parentDetails = studentDetails.getParentDetails();
				message.append("Dear " + parentDetails.getFatherName() +", <br><br>");
				message.append("Your children admission successfully register. <br><br><br><br>");
				input.put("content", message);
	
				Writer consoleWriter = new StringWriter();
	            template.process(input, consoleWriter);
	            String mailMessageBody = consoleWriter.toString();
	            
	            emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), parentDetails.getFatherEmail(), "Admission Status", mailMessageBody);
				//emailUtil.sendEmailWithCCWithAttach(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipient, ccRecipient, subject, message, reportPath);
			} catch (TemplateException e) {
				//e.printStackTrace();
				LOG.error("ERROR: StudentDetailsAspect in saveStudentDetails()");
			} catch (IOException e) {
				//e.printStackTrace();
				LOG.error("ERROR: StudentDetailsAspect in saveStudentDetails()");
			}
		 
		}
	}
	
	@After("execution(* com.school.common.services.StudentDetailsServices.updateStudentDetails(..)) && args(..,studentDetails)")
	public void updateStudentDetails(JoinPoint joinPoint, StudentDetailsModel studentDetails)
	{
		LOG.info("In StudentDetailsAspect: @After StudentDetailsServices.updateStudentDetails(..) " + studentDetails.getFirstName());
		
		if("Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_DETAILS))){
			try {
				EmailUtil emailUtil = new EmailUtil();
				Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
				Map<String, Object> input = FreemarkerTemplate.getInputMap();	
				StringBuffer message = new StringBuffer();
				ParentDetailsModel parentDetails = studentDetails.getParentDetails();
				message.append("Dear " + parentDetails.getFatherName() +", <br><br>");
				message.append("Your information successfully updated. <br><br><br><br>");
				input.put("content", message);
	
				Writer consoleWriter = new StringWriter();
	            template.process(input, consoleWriter);
	            String mailMessageBody = consoleWriter.toString();
	            
	            emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), parentDetails.getFatherEmail(), "Admission Status", mailMessageBody);
				//emailUtil.sendEmailWithCCWithAttach(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipient, ccRecipient, subject, message, reportPath);
			} catch (TemplateException e) {
				//e.printStackTrace();
				LOG.error("ERROR: StudentDetailsAspect in saveStudentDetails()");
			} catch (IOException e) {
				//e.printStackTrace();
				LOG.error("ERROR: StudentDetailsAspect in saveStudentDetails()");
			}
		}
	  
	}
	
	@Pointcut("com.school.common.services.UserServices.viewUser, args(user)")
	public void modalValue(UserServices user)
	{}
	/*@Pointcut()
	public void pointCutValue()
	{}
*/
}
