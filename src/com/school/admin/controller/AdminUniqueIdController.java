package com.school.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.UniqueIdServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin"})
public class AdminUniqueIdController {
	
	
	@Autowired
	private UniqueIdServices services;
	
	
	@RequestMapping(value = "/searchUniqueId", method = RequestMethod.GET)
	public ModelAndView searchUniqueId() {
		return services.searchUniqueId();
	}
	
}
