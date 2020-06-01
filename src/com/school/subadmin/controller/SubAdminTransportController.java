package com.school.subadmin.controller;

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
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin"})
public class SubAdminTransportController 
{
	@Autowired
	private TransportServices services;
	
	
	/*
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
	*/
	
	
	@ResponseBody
	@RequestMapping(value = "/changeTransportStatus", method = RequestMethod.POST)
	public String changeTransportStatus() {
		return services.changeTransportStatus();
	}
	
	@RequestMapping(value = "/addTransport", method = RequestMethod.GET)
	public ModelAndView addTransport() {
		return services.addTransport();
	}

	
	@RequestMapping(value = "/saveTransport", method = RequestMethod.POST)
	public ModelAndView saveTransport(@ModelAttribute TransportModel transport) {
		return services.saveTransport(transport);
	}
	
	@RequestMapping(value = "/savedTransport", method = RequestMethod.GET)
	public ModelAndView savedTransport() {
		return services.savedTransport();
	}
	
	@RequestMapping(value = "/editTransport", method = RequestMethod.POST)
	public ModelAndView editTransport(@ModelAttribute TransportModel transport) {
		return services.editTransport(transport);
	}
	
	@RequestMapping(value = "/deleteTransport", method = RequestMethod.POST)
	public ModelAndView deleteTransport(@ModelAttribute TransportModel transport) {
		return services.deleteTransport(transport);
	}
	
	@RequestMapping(value = "/updateTransport", method = RequestMethod.POST)
	public ModelAndView updateTransport(@ModelAttribute TransportModel transport) {
		return services.updateTransport(transport);
	}
	
	@RequestMapping(value = "/updatedTransport", method = RequestMethod.GET)
	public ModelAndView updatedTransport() {
		return services.updatedTransport();
	}
}
