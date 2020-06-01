package com.school.common.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.facade.FacadeServicesManager;
import com.school.common.generic.SessionManager;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;

@Aspect
@Component
public class LoginAspect 
{
	@Autowired
	private FacadeServicesManager facadeServicesManager;

	private static Logger LOG = LoggerFactory.getLogger(LoginAspect.class);
	
	
	@Before("execution(* com.school.common.services.LoginServices.logout(..)) && args(..)")
	public void logoutAdvise(JoinPoint joinPoint)
	{
		LOG.info("In LoginAspect: @Before LoginServices.logout(..) ");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		if(sessionManager !=  null)
		{
			UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
			UserModel user = new UserModel();
			user.setUserId(userProfileUtils.getUserId());
			user.setLogin(false);
			facadeServicesManager.getLoginServices().updateUserLogin(user);
			UserDetailsModel userDetails = new UserDetailsModel();
			userDetails.setUserDetailsId(new Long(userProfileUtils.getUserDetailsId()));
			facadeServicesManager.getLoginServices().updateUserDetailsToken(userDetails);
		}
	}
}
