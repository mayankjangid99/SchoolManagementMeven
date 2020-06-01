package com.school.supadmin.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.GlobalConfigModel;
import com.school.common.services.GlobalConfigServices;
import com.school.json.model.GlobalConfigJsonModel;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminGlobalConfigController 
{
	@Autowired
	private GlobalConfigServices services;
		
	
	@RequestMapping(value = "/searchGlobalConfig", method = RequestMethod.GET)
	public ModelAndView searchGlobalConfig()
	{
		return services.searchGlobalConfig();
	}
	
	
	@RequestMapping(value = "/addGlobalConfig", method = RequestMethod.GET)
	public ModelAndView addGlobalConfig()
	{
		return services.addGlobalConfig();
	}
	
	
//	Save GlobalConfig Information
	@RequestMapping(value = "/saveGlobalConfig", method = RequestMethod.POST)
	public ModelAndView saveGlobalConfig(@ModelAttribute GlobalConfigModel globalConfig)
	{
		return services.saveGlobalConfig(globalConfig);
	}
	
	@RequestMapping(value = "/savedGlobalConfig", method = RequestMethod.GET)
	public ModelAndView savedGlobalConfig()
	{
		return services.savedGlobalConfig();
	}
	

//	result Global Config Information
	@RequestMapping(value = "/resultGlobalConfig", method = RequestMethod.POST)
	public String resultGlobalConfig()
	{
		return "supadmin/resultGlobalConfig";
	}
	
	
//	Fetach Global Config Information
	@ResponseBody
	@RequestMapping(value = "/fetchGlobalConfig", method = RequestMethod.GET)
	public ArrayList<GlobalConfigJsonModel> fetchGlobalConfig(@ModelAttribute GlobalConfigModel globalConfig)
	{
		return services.fetchGlobalConfig(globalConfig);
	}
	
	
//	Get One Global Config
	@RequestMapping(value = "/editGlobalConfig", method = RequestMethod.POST)
	public ModelAndView editGlobalConfig(@ModelAttribute GlobalConfigModel globalConfigModel)
	{
		return services.editGlobalConfig(globalConfigModel);
	}
	
//	Update Global Config
	@RequestMapping(value = "/updateGlobalConfig", method = RequestMethod.POST)
	public ModelAndView updateGlobalConfig(@ModelAttribute GlobalConfigModel globalConfigModel)
	{
		return services.updateGlobalConfig(globalConfigModel);
	}
	
//	Updated Global Config
	@RequestMapping(value = "/updatedGlobalConfig", method = RequestMethod.GET)
	public ModelAndView updatedGlobalConfig()
	{
		return services.updatedGlobalConfig();
	}
	
	
//	View Global Config
	@RequestMapping(value = "/viewGlobalConfig", method = RequestMethod.POST)
	public ModelAndView viewGlobalConfig(@ModelAttribute GlobalConfigModel globalConfigModel)
	{
		ModelAndView model = services.viewGlobalConfig(globalConfigModel);
		return model;
	}
}
