package com.school.common.generic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppFilter implements Filter {
	
	private static Logger LOG = LoggerFactory.getLogger(AppFilter.class);
	
	@Override
	public void destroy() {
		LOG.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ filter destroy() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//log.info("filter called");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession(false);

    	String url = req.getRequestURL().toString();
    	String contextPath = req.getContextPath();
    	String contextWithURI = req.getRequestURI();
    	
		String domainWithContext = url.substring(0, url.indexOf(contextPath));
		domainWithContext = domainWithContext + contextPath + "/";
		
		String reqURI = req.getRequestURI().substring(contextPath.length());		
        
		//Start Check Cookie
        String cookieFlag = "N";
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("SMUser".equalsIgnoreCase(cookie.getName())) {
					cookieFlag = "Y";
					//log.info("Cookie found");
				}
			}
		}
		if("N".equalsIgnoreCase(cookieFlag)) {
			//log.info("Cookie not found");
			Cookie cookie = new Cookie("SMUser", "");
			cookie.setMaxAge(60*60*24*30);
			res.addCookie(cookie);
		}
		//End Check Cookie

        
        if (session != null && !session.isNew()) {
        	//log.info("Session is not New");
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
    		//System.out.println("PathInfo: " + req.getPathInfo()+"-conPath"+req.getContextPath()+"-uri"+req.getRequestURI());
        	
			/*
			1. This code use when session time expire and sessionManager will null.
			2. if user hits any URL then USER Auth but SessionManager has been null due to session time out.
			3. if USER Auth then redirect to Re-Auth as well set SessionManager in Session.
			*/
			if(!"anonymousUser".equals(auth.getName()) && sessionManager == null && !(contextPath + "/welcome").equalsIgnoreCase(contextWithURI)) {
            	req.getRequestDispatcher("/welcome").forward(request, response);
            }
			else if(!"anonymousUser".equals(auth.getName()) && sessionManager != null && !(contextPath + "/welcome").equalsIgnoreCase(contextWithURI)) {
				if(sessionManager.getSchoolProfileUtils() == null)
					req.getRequestDispatcher("/welcome").forward(request, response);
			}
            chain.doFilter(request, response);
        } else if(session != null && session.isNew()) {
        	//log.info("Session is New");
        } else  {
            //log.info("Has timed out");
        	System.out.println("url="+url+",domainWithContext="+domainWithContext+",contextWithURI="+contextWithURI);
    		System.out.println("reqURI="+reqURI);
    		//String queryString = req.getQueryString();
        	//System.out.println(queryString);
    		
    		req.getRequestDispatcher(reqURI).forward(request, response);
        	/*if(url.equalsIgnoreCase(tString)) {
        		req.getRequestDispatcher("/").forward(request, response);
        	} else {
        		//req.getRequestDispatcher(uri + "?" + queryString).forward(request, response);
        		req.getRequestDispatcher(uri).forward(request, response);
        	}*/
        	
        }
        
	}
	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LOG.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ filter init() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	
}
