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
import com.school.common.model.UserModel;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Aspect
@Component
public class UserAspect 
{
	private static Logger LOG = LoggerFactory.getLogger(StudentAttendanceAspect.class);
	
	@After("execution(* com.school.common.services.UserServices.viewUser(..)) && args(..,userModel)")
	public void viewUser(JoinPoint joinPoint, UserModel userModel)
	{
		LOG.info("In UserAspect: @After UserServices.viewUser(..) " + userModel.getUserId());
	}
	
	
	@After("execution(* com.school.common.services.UserServices.updateUser(..)) && args(..,user)")
	public void updateUser(JoinPoint joinPoint, UserModel user)
	{
		LOG.info("In UserAspect: @After UserServices.updateUser(..) " + user.getUserId());
		if("Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_USER_DETAILS))){
			Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
			Map<String, Object> input = FreemarkerTemplate.getInputMap();			
			
			String message = "Hi " + user.getUserDetails().getFullname() + ", <br/><br/>Your information successfully updated.";
			input.put("content", message);
            try {
    			Writer consoleWriter = new StringWriter();
				template.process(input, consoleWriter);
	            String mailMessageBody = consoleWriter.toString();
				EmailUtil emailUtil = new EmailUtil();
				emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), user.getUserDetails().getEmail(), "Updated User Information", mailMessageBody);
			} catch (TemplateException e) {
				//e.printStackTrace();
				LOG.error("ERROR: In UserAspect updateUser() " + e);
			} catch (IOException e) {
				//e.printStackTrace();
				LOG.error("ERROR: In UserAspect updateUser() " + e);
			}
        }
	}
	
	
	@After("execution(* com.school.common.services.UserServices.saveUser(..)) && args(..,user)")
	public void saveUser(JoinPoint joinPoint, UserModel user)
	{
		LOG.info("In UserAspect: @After UserServices.saveUser(..) " + user.getUserId());
		if("Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_USER_DETAILS))){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String pwd = (String)request.getAttribute("UserPassword");
			
			Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
			Map<String, Object> input = FreemarkerTemplate.getInputMap();			
			
			String message = "Hi " + user.getUserDetails().getFullname() + ", <br/><br/>"
					+ "Your information successfully saved.<br/><br/>"
					+ "Please find the Login credential information below.<br/><br/> "
					+ "<b>Username:</b> " + user.getUsername() + "<br><br/>"
					+ "<b>Password:</b> " + pwd + "<br><br><br>";
			input.put("content", message);
            try {
    			Writer consoleWriter = new StringWriter();
				template.process(input, consoleWriter);
	            String mailMessageBody = consoleWriter.toString();
				EmailUtil emailUtil = new EmailUtil();
				emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), user.getUserDetails().getEmail(), "Login Credential Status", mailMessageBody);
			} catch (TemplateException e) {
				//e.printStackTrace();
				LOG.error("ERROR: In UserAspect saveUser() " + e);
			} catch (IOException e) {
				//e.printStackTrace();
				LOG.error("ERROR: In UserAspect updateUser() " + e);
			}
        }
	}
}
