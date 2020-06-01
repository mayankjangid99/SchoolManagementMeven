package com.school.common.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.GlobalConfigDao;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticData;
import com.school.common.model.GlobalConfigModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.services.business.GlobalConfigBusiness;
import com.school.json.model.GlobalConfigJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GlobalConfigServices 
{
	@Autowired
	private GlobalConfigDao globalConfigDao;

	@Autowired
	private GlobalConfigBusiness business;
	
	@Autowired
	private MessageSource messageSource;
	
	
	public ModelAndView searchGlobalConfig() {
		ModelAndView model = new ModelAndView("supadmin/searchGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		return model;
	}
	
	
	public ModelAndView addGlobalConfig() {
		ModelAndView model = new ModelAndView("supadmin/addGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		return model;
	}
	
//	Save GlobalConfig Information
	public ModelAndView saveGlobalConfig(GlobalConfigModel globalConfigModel)
	{
		ModelAndView model = new ModelAndView("redirect:savedGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		globalConfigModel.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
		GlobalConfigModel globalConfig = globalConfigDao.getGlobalConfigByCode(SessionManagerDataHelper.getSchoolCode(), globalConfigModel);
		if(globalConfig != null)
		{
			model.setViewName("supadmin/addGlobalConfig");
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.GlobalConfigCodeAlreadyUsed", null, null));
			return model;
		}
		SchoolProfileModel schoolProfileModel = new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode());
		globalConfigModel.setSchoolProfile(schoolProfileModel);
		globalConfigDao.saveGlobalConfig(globalConfigModel);
		return model;
	}
	
	public ModelAndView savedGlobalConfig()
	{
		ModelAndView model = new ModelAndView("supadmin/addGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		model.addObject("SuccessMessage", "Global Config Information Successfully Saved...!!!");
		return model;
	}
	
	
	public List<GlobalConfigModel> getAllGlobalConfigBySchoolCode()
	{
		return globalConfigDao.getAllGlobalConfigBySchoolCode(SessionManagerDataHelper.getSchoolCode());
	}
	
	
//	Fetch All Global Config
	public ArrayList<GlobalConfigJsonModel> fetchGlobalConfig(GlobalConfigModel globalConfig)
	{
		List<GlobalConfigModel> list = globalConfigDao.fetchGlobalConfig(SessionManagerDataHelper.getSchoolCode(), globalConfig);
		ArrayList<GlobalConfigJsonModel> globalConfigList  = business.fetchGlobalConfig(list);
		return globalConfigList;
	}
	
	
//	Get One Global Config
	public ModelAndView editGlobalConfig(GlobalConfigModel globalConfigModel)
	{
		ModelAndView model = new ModelAndView("supadmin/editGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		GlobalConfigModel globalConfig = globalConfigDao.editGlobalConfig(SessionManagerDataHelper.getSchoolCode(), globalConfigModel);
		model.addObject("GlobalConfig", globalConfig);
		return model;
	}
	
	
//	Update Global Config
	public ModelAndView updateGlobalConfig(GlobalConfigModel globalConfigModel)
	{
		ModelAndView model = new ModelAndView("redirect:updatedGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		SchoolProfileModel schoolProfileModel = new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode());
		globalConfigModel.setSchoolProfile(schoolProfileModel);
		globalConfigDao.updateGlobalConfig(globalConfigModel);
		return model;
	}
	
	
	public ModelAndView updatedGlobalConfig()
	{
		ModelAndView model = new ModelAndView("supadmin/editGlobalConfig");
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		model.addObject("SuccessMessage", "Global Configuration Successfully Updated...!!!");
		return model;
	}

	
//	View Global Config
	public ModelAndView viewGlobalConfig(GlobalConfigModel globalConfigModel)
	{
		ModelAndView model = editGlobalConfig(globalConfigModel);
		model.addObject("GlobalConfigParameters", StaticData.getGlobalConfigParametersData());
		model.addObject("Operation", "View");
		return model;
	}
}
