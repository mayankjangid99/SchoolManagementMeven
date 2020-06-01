package com.school.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.SalaryServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/account"})
public class AccountSalaryController {
	
	@Autowired
	private SalaryServices services;

	@RequestMapping(value = "searchSalaryStructure", method = RequestMethod.POST)
	public ModelAndView searchSalaryStructure() {
		return services.searchSalaryStructure();
	}
	
	@RequestMapping(value = "addSalaryStructure", method = RequestMethod.POST)
	public ModelAndView addSalaryStructure() {
		return services.addSalaryStructure();
	}
}
