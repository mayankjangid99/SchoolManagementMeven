package com.school.admin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.micro.model.SalaryConfigModel;
import com.school.common.services.SalaryServices;

@Controller
@RequestMapping(value = {"/admin", "/supadmin"})
public class AdminSalaryConfigController {
	
	@Autowired
	private SalaryServices services;
	
	@RequestMapping(value = "/searchSalaryConfig", method = RequestMethod.GET)
	public ModelAndView searchSalaryConfig() {
		return services.searchSalaryConfig();
	}
	
	@RequestMapping(value = "/addSalaryConfig", method = RequestMethod.GET)
	public ModelAndView addSalaryConfig() {
		return services.addSalaryConfig();
	}
	
	
//	Save SalaryConfig Information
	@RequestMapping(value = "/saveSalaryConfig", method = RequestMethod.POST)
	public ModelAndView saveSalaryConfig(@ModelAttribute SalaryConfigModel salaryConfig) {
		return services.saveSalaryConfig(salaryConfig);
	}
	
	@RequestMapping(value = "/savedSalaryConfig", method = RequestMethod.GET)
	public ModelAndView savedSalaryConfig() {
		return services.savedSalaryConfig();
	}
	

//	result Salary Config Information
	@RequestMapping(value = "/resultSalaryConfig", method = RequestMethod.POST)
	public ModelAndView resultSalaryConfig(@ModelAttribute SalaryConfigModel salaryConfig, HttpServletRequest request) {
		return services.resultSalaryConfig(salaryConfig, request);
	}
	
	
//	Fetach Salary Config Information
	@ResponseBody
	@RequestMapping(value = "/fetchSalaryConfig", method = RequestMethod.GET)
	public ArrayList<SalaryConfigModel> fetchSalaryConfig(HttpServletRequest request) {
		return services.fetchSalaryConfig(request);
	}
	
	
//	Get One Salary Config
	@RequestMapping(value = "/editSalaryConfig", method = RequestMethod.POST)
	public ModelAndView editSalaryConfig(@ModelAttribute SalaryConfigModel salaryConfigModel) {
		return services.editSalaryConfig(salaryConfigModel);
	}
	
//	Update Salary Config
	@RequestMapping(value = "/updateSalaryConfig", method = RequestMethod.POST)
	public ModelAndView updateSalaryConfig(@ModelAttribute SalaryConfigModel salaryConfigModel) {
		return services.updateSalaryConfig(salaryConfigModel);
	}
	
//	Updated Salary Config
	@RequestMapping(value = "/updatedSalaryConfig", method = RequestMethod.GET)
	public ModelAndView updatedSalaryConfig() {
		return services.updatedSalaryConfig();
	}
	
	
//	View Salary Config
	@RequestMapping(value = "/viewSalaryConfig", method = RequestMethod.POST)
	public ModelAndView viewSalaryConfig(@ModelAttribute SalaryConfigModel salaryConfigModel) {
		ModelAndView model = services.viewSalaryConfig(salaryConfigModel);
		return model;
	}
}
