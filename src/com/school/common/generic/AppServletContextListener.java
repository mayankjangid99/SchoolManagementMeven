package com.school.common.generic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.school.common.facade.FacadeServicesManager;
import com.school.common.services.LoginServices;
import com.school.common.services.PaymentCategoryServices;
import com.school.common.services.UserServices;

public class AppServletContextListener implements ServletContextListener
{

	private static Logger LOG = LoggerFactory.getLogger(AppServletContextListener.class);
	
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOG.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ contextDestroyed() in AppServletContextListener @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	@Override
	public void contextInitialized(ServletContextEvent event)  {
		LOG.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ contextInitialized() in AppServletContextListener @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

		FacadeServicesManager facadeServicesManager = (FacadeServicesManager) ctx.getBean("facadeServicesManager");
		
		LoginServices loginServices = facadeServicesManager.getLoginServices();
		loginServices.updateAllUserLogin();
		UserServices userServices = facadeServicesManager.getUserServices();
		userServices.updateAllUserRoles();
		PaymentCategoryServices paymentCategoryServices = facadeServicesManager.getPaymentCategoryServices();
		paymentCategoryServices.updateCashPaymentCategory();
	}
	
}
