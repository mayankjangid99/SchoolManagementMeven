package com.school.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.TransportModel;
import com.school.common.services.TransportServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user"})
public class UserTransportController 
{
	@Autowired
	private TransportServices services;
	
	
	@RequestMapping(value = "/searchTransport", method = RequestMethod.GET)
	public ModelAndView searchTransport() {
		return services.searchTransport();
	}
	
	@RequestMapping(value = "/resultTransport", method = RequestMethod.POST)
	public ModelAndView resultTransport(@ModelAttribute TransportModel transport) {
		return services.resultTransport(transport);
	}
	
	@ResponseBody
	@RequestMapping(value = "/fetchTransport", method = RequestMethod.GET)
	public List<TransportModel> fetchTransport() {
		return services.fetchTransport();
	}
	
		
}
