package com.school.staff.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.SendEmailModalServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff" })
public class StaffSendEmailModalController {
	
	@Autowired
	private SendEmailModalServices services;
	
	@ResponseBody
	@RequestMapping(value = "/sendEmailModal", method = RequestMethod.POST)
	public ModelAndView sendEmailModal(HttpServletRequest request){
		return services.sendEmailModal(request);
	}
}
