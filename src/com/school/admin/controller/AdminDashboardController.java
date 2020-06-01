package com.school.admin.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.services.DashboardServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin"})
public class AdminDashboardController 
{
	@Autowired
	private DashboardServices services;
	
	
	@RequestMapping(value = "/resultDashboard", method = RequestMethod.GET)
	public String resultDashboard()
	{
		return "user/resultDashboard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/fetchActiveSessions", method = RequestMethod.GET)
	public JSONObject getActiveSessions()
	{
		return services.getActiveSessions();
	}
}
