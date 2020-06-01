package com.school.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.StudentDetailsServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user" })
public class UserStudentDetailsController {

	@Autowired
	private StudentDetailsServices services;
	

//	View Student Details
	@RequestMapping(value = "/viewStudentDetails", method = RequestMethod.POST)
	public ModelAndView viewStudentDetails(HttpServletRequest request)
	{
		ModelAndView model = services.viewStudentDetails(request);
		return model;
	}
}
