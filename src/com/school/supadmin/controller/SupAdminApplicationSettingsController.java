package com.school.supadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.ApplicationSettingsServices;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminApplicationSettingsController {

	@Autowired
	private ApplicationSettingsServices services;
	
	@RequestMapping(value = "/addApplicationSettings", method = RequestMethod.GET)
	public ModelAndView addApplicationSettings(){
		return services.addApplicationSettings();
	}
	
	@ResponseBody
	@RequestMapping(value = "/addOrChangeEmailSettings", method = RequestMethod.POST)
	public String addOrChangeEmailSettings(HttpServletRequest request){
		return services.addOrChangeEmailSettings(request);
	}
}
