package com.school.common.generic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class DataHelper 
{
	

	public static String getDomainWithContext()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String url = request.getRequestURL().toString();
		String cpath = request.getContextPath().toString();

		String tString = url.substring(0, url.indexOf(cpath));

		tString = tString + cpath;
		return tString;
	}
	
	
	
}
