package com.school.supadmin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.MicroServiceConfigModel;
import com.school.common.services.MicroServiceConfigServices;
import com.school.json.model.MicroServiceConfigJsonModel;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminMicroServiceConfigController {
	
	@Autowired
	private MicroServiceConfigServices services;
		
	
	@RequestMapping(value = "/searchMicroServiceConfig", method = RequestMethod.GET)
	public ModelAndView searchMicroServiceConfig() {
		return services.searchMicroServiceConfig();
	}
	
	
	@RequestMapping(value = "/addMicroServiceConfig", method = RequestMethod.GET)
	public ModelAndView addMicroServiceConfig() {
		return services.addMicroServiceConfig();
	}
	
	
//	Save MicroServiceConfig Information
	@RequestMapping(value = "/saveMicroServiceConfig", method = RequestMethod.POST)
	public ModelAndView saveMicroServiceConfig(@ModelAttribute MicroServiceConfigModel microServiceConfig) {
		return services.saveMicroServiceConfig(microServiceConfig);
	}
	
	@RequestMapping(value = "/savedMicroServiceConfig", method = RequestMethod.GET)
	public ModelAndView savedMicroServiceConfig() {
		return services.savedMicroServiceConfig();
	}
	

//	result MicroService Config Information
	@RequestMapping(value = "/resultMicroServiceConfig", method = RequestMethod.POST)
	public String resultMicroServiceConfig() {
		return "supadmin/resultMicroServiceConfig";
	}
	
	
//	Fetach MicroService Config Information
	@ResponseBody
	@RequestMapping(value = "/fetchMicroServiceConfig", method = RequestMethod.GET)
	public ArrayList<MicroServiceConfigJsonModel> fetchMicroServiceConfig(@ModelAttribute MicroServiceConfigModel microServiceConfig) {
		return services.fetchMicroServiceConfig(microServiceConfig);
	}
	
	
//	Get One MicroService Config
	@RequestMapping(value = "/editMicroServiceConfig", method = RequestMethod.POST)
	public ModelAndView editMicroServiceConfig(@ModelAttribute MicroServiceConfigModel microServiceConfigModel) {
		return services.editMicroServiceConfig(microServiceConfigModel);
	}
	
//	Update MicroService Config
	@RequestMapping(value = "/updateMicroServiceConfig", method = RequestMethod.POST)
	public ModelAndView updateMicroServiceConfig(@ModelAttribute MicroServiceConfigModel microServiceConfigModel) {
		return services.updateMicroServiceConfig(microServiceConfigModel);
	}
	
//	Updated MicroService Config
	@RequestMapping(value = "/updatedMicroServiceConfig", method = RequestMethod.GET)
	public ModelAndView updatedMicroServiceConfig() {
		return services.updatedMicroServiceConfig();
	}
	
	
//	View MicroService Config
	@RequestMapping(value = "/viewMicroServiceConfig", method = RequestMethod.POST)
	public ModelAndView viewMicroServiceConfig(@ModelAttribute MicroServiceConfigModel microServiceConfigModel) {
		ModelAndView model = services.viewMicroServiceConfig(microServiceConfigModel);
		return model;
	}
}
