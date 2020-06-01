package com.school.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.UserModel;
import com.school.common.services.UserServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user" })
public class UserUserController 
{
	@Autowired
	private UserServices services;
	

	@RequestMapping(value = "/viewUser", method = RequestMethod.POST)
	public ModelAndView viewUser(HttpServletRequest request, @ModelAttribute UserModel user)
	{
		return services.viewUser(request, user);
	}
}
