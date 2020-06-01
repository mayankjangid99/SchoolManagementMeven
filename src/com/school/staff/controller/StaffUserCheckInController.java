package com.school.staff.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.services.UserCheckInServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "subadmin", "staff"} )
public class StaffUserCheckInController 
{
	@Autowired
	private UserCheckInServices services;
	
	
	@ResponseBody
	@RequestMapping(value = "/checkInUser", method = RequestMethod.GET)
	public JSONObject checkInUser(HttpServletRequest request)
	{
		return services.checkInUser(request);
	}
}
