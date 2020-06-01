package com.school.common.generic;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;

public class AppSessionCreatedListenerService implements ApplicationListener<ApplicationEvent> {

	private static final Logger logger = LoggerFactory.getLogger(AppSessionCreatedListenerService.class);

	@Autowired
	HttpSession httpSession;
	
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		// TODO Auto-generated method stub
		if(applicationEvent instanceof HttpSessionCreatedEvent){ //If event is a session created event



	     }else if(applicationEvent instanceof HttpSessionDestroyedEvent){ //If event is a session destroy event
	        // handler.expireCart();

	         logger.debug(""+(Long)httpSession.getAttribute("userId"));

	         logger.debug(" Session is destory  :" ); //log data

	     }else if(applicationEvent instanceof AuthenticationSuccessEvent){ //If event is a session destroy event
	         logger.debug("  athentication is success  :" ); //log data
	     }else{
	         /*logger.debug(" unknown event occur  : " Source: " + );*/ //log data
	     }  
	}

}
