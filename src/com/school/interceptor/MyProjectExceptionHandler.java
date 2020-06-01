package com.school.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MyProjectExceptionHandler  implements MethodInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MyProjectExceptionHandler.class);
    
    
	/* (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {

        Object result = null;
        try {
            //System.out.println("Try Block in MethodInterceptor ");
        	 /*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();      	 
        	 HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        	 if(request.getSession().getAttribute("SessionManager") == null && request != null) {
            	 response.sendRedirect("login");
        	 }*/
        	LOG.info("invoke()");
            result = method.proceed();
        } catch(Exception e) {
        	LOG.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Exception Handler in MyProjectExceptionHandler @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            StringWriter stackTrace = new StringWriter();
            e.printStackTrace(new PrintWriter(stackTrace));
        	LOG.error("Exception in: " + method.getMethod().getName());
        	LOG.error("Class: " + method.getMethod().getDeclaringClass().getName());
        	LOG.error("StackTrace:\n" + stackTrace.getBuffer().toString());
        	
        	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        	ModelAndView nav = null;
        	if(request != null)
        		nav = new ModelAndView("error");
        	else
        		nav = new ModelAndView("outside/error");
        	
          	nav.addObject("datetime", new Date());
            nav.addObject("exception", e);
            nav.addObject("url", request.getRequestURL());
            return nav;
            
        	/*
        	List<DeveloperConfigurationEmailBean> list = dao.selectEmailConfig("Exception Occur");
        	if(!list.isEmpty()) {
	        	DeveloperConfigurationEmailBean emailBean = list.get(0);
	            StringWriter stackTrace = new StringWriter();
	            e.printStackTrace(new PrintWriter(stackTrace));
	            //System.out.println("Exception Occure");
				//System.out.println("[MyApp] Exception in '" + method.getMethod().getName() + "' method");
				//System.out.println("Exception in: " + method.getMethod().getName() + "\n\n");
				//System.out.println("Class: " + method.getMethod().getDeclaringClass().getName() + "\n\n");
				//System.out.println("StackTrace:\n" + stackTrace.getBuffer().toString());
				
				String to = emailBean.getReceiver();
	            String subject = "[United Engineers Pvt. Ltd.] Exception in '"+method.getMethod().getName()+"' method";
	            String message = "<b>Exception in: </b>"+method.getMethod().getName()+"<br><br>"
	            		+"<b>Class: </b>"+method.getMethod().getDeclaringClass().getName()+"<br><br>"
	            		+"<b>Message: </b>"+e.getMessage()+"<br><br>"
	            		+"<b>StackTrace: </b><br>"+stackTrace.getBuffer().toString();
	            
	            final String from = emailBean.getSender();
	            final String password = emailBean.getPassword();
	            Properties properties = System.getProperties();
	            properties.setProperty("mail.smtp.host", emailBean.getSmtp());
	            properties.setProperty("mail.smtp.port", emailBean.getPort());
	            properties.setProperty("mail.smtp.auth", "true");
	            Session ses = Session.getInstance(properties, new Authenticator() {
	                public PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(from, password);
	                }
	            });
	            
	            try {
		            MimeMessage mimemessage = new MimeMessage(ses);
	                mimemessage.setSentDate(new Date());
	                mimemessage.setFrom(new InternetAddress(from));
	                mimemessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
	                mimemessage.setSubject(subject);
	                mimemessage.setText(message, "UTF-8", "html");
	                Transport.send(mimemessage);
	            } catch(Exception e)  {
	                LOG.error(e.getMessage());
	            }
	            throw e;
	        }
	        */
        }
        return result;
    
	}
}
