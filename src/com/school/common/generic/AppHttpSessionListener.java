package com.school.common.generic;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.school.common.facade.FacadeServicesManager;
import com.school.common.model.UserModel;
import com.school.common.services.LoginServices;

@Service
public class AppHttpSessionListener implements HttpSessionListener {
	
	private static Logger log = LoggerFactory.getLogger(AppHttpSessionListener.class);
	
	private static HttpSession session = null;
	
	private static long activeSessions = 0;
	
	private static long totalSessions = 0;
	
		
	public static long getActiveSessions() {
		return activeSessions;
	}

	public static void setActiveSessions(long activeSessions) {
		AppHttpSessionListener.activeSessions = activeSessions;
	}

	
	public static long getTotalSessions() {
		return totalSessions;
	}

	public static void setTotalSessions(long totalSessions) {
		AppHttpSessionListener.totalSessions = totalSessions;
	}



	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ sessionCreated() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		activeSessions++;
		totalSessions++;
		session = event.getSession();
	}

	
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ sessionDestroyed() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		if(activeSessions > 0)
			activeSessions--;
		
		HttpSession session = event.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		if(sessionManager !=  null) {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());

			FacadeServicesManager facadeServicesManager = (FacadeServicesManager) ctx.getBean("facadeServicesManager");
			
			LoginServices loginServices = facadeServicesManager.getLoginServices();

			UserProfileUtils userProfileUtils = sessionManager.getUserProfileUtils();
			if(userProfileUtils != null) {
				UserModel user = new UserModel();
				user.setUserId(userProfileUtils.getUserId());
				user.setLogin(false);
				loginServices.updateUserLogin(user);
			}
		}
	}
	
	
	public static HttpSession getSession() {
		return session;
	}
	
}
