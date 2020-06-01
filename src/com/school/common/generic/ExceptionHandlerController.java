package com.school.common.generic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController 
{
	public static final String DEFAULT_ERROR_VIEW = "error";
	public static final String DEFAULT_OUTSIDE_ERROR_VIEW = "outside/error";
	
	private static Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@ExceptionHandler(value = Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)
    {
        LOG.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Start Exception Handler in ExceptionHandlerController @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        /*StringWriter stackTrace = new StringWriter();
        e.printStackTrace(new PrintWriter(stackTrace));*/
    	LOG.error("Exception in: " + e);
    	/*LOG.error("Class: " + method.getMethod().getDeclaringClass().getName());
    	LOG.error("StackTrace:\n" + stackTrace.getBuffer().toString());*/
    	
        UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
    	ModelAndView nav = null;
    	if(userProfileUtils != null)
    		nav = new ModelAndView(DEFAULT_ERROR_VIEW);
    	else
    		nav = new ModelAndView(DEFAULT_OUTSIDE_ERROR_VIEW);
        nav.addObject("datetime", new Date());
        nav.addObject("exception", e);
        nav.addObject("url", request.getRequestURL());
        return nav;
    }
}
