package com.school.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.SchoolSessionServices;

@Controller
@RequestMapping(value = {"/admin", "/supadmin"})
public class AdminSchoolSessionController 
{
	@Autowired
	private SchoolSessionServices services;
	
	@RequestMapping(value = "/switchSchoolSession", method = RequestMethod.GET)
	public ModelAndView switchSchoolSession(HttpServletRequest request)
	{
		ModelAndView model = services.switchSchoolSession(request);
		return model;
	}
}
