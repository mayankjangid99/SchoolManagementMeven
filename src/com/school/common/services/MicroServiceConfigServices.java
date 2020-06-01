package com.school.common.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.MicroServiceConfigDao;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticData;
import com.school.common.model.MicroServiceConfigModel;
import com.school.common.services.business.MicroServiceConfigBusiness;
import com.school.json.model.MicroServiceConfigJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MicroServiceConfigServices 
{
	@Autowired
	private MicroServiceConfigDao microServiceConfigDao;

	@Autowired
	private MicroServiceConfigBusiness business;
	
	@Autowired
	private MessageSource messageSource;
	
	
	public ModelAndView searchMicroServiceConfig() {
		ModelAndView model = new ModelAndView("supadmin/searchMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		return model;
	}
	
	
	public ModelAndView addMicroServiceConfig() {
		ModelAndView model = new ModelAndView("supadmin/addMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		return model;
	}
	
//	Save MicroServiceConfig Information
	public ModelAndView saveMicroServiceConfig(MicroServiceConfigModel microServiceConfigModel) {
		ModelAndView model = new ModelAndView("redirect:savedMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		MicroServiceConfigModel microServiceConfig = microServiceConfigDao.getMicroServiceConfigByCode(microServiceConfigModel);
		if(microServiceConfig != null) {
			model.setViewName("supadmin/addMicroServiceConfig");
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.MicroServiceConfigCodeAlreadyUsed", null, null));
			return model;
		}
		microServiceConfigDao.saveMicroServiceConfig(microServiceConfigModel);
		return model;
	}
	
	public ModelAndView savedMicroServiceConfig() {
		ModelAndView model = new ModelAndView("supadmin/addMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		model.addObject("SuccessMessage", "MicroService Config Information Successfully Saved...!!!");
		return model;
	}
	
	
	public List<MicroServiceConfigModel> getAllMicroServiceConfigBySchoolCode() {
		return microServiceConfigDao.getAllMicroServiceConfigs();
	}
	
	
//	Fetch All MicroService Config
	public ArrayList<MicroServiceConfigJsonModel> fetchMicroServiceConfig(MicroServiceConfigModel microServiceConfig) {
		List<MicroServiceConfigModel> list = microServiceConfigDao.fetchMicroServiceConfig(SessionManagerDataHelper.getSchoolCode(), microServiceConfig);
		ArrayList<MicroServiceConfigJsonModel> microServiceConfigList  = business.fetchMicroServiceConfig(list);
		return microServiceConfigList;
	}
	
	
//	Get One MicroService Config
	public ModelAndView editMicroServiceConfig(MicroServiceConfigModel microServiceConfigModel) {
		ModelAndView model = new ModelAndView("supadmin/editMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		MicroServiceConfigModel microServiceConfig = microServiceConfigDao.editMicroServiceConfig(SessionManagerDataHelper.getSchoolCode(), microServiceConfigModel);
		model.addObject("MicroServiceConfig", microServiceConfig);
		return model;
	}
	
	
//	Update MicroService Config
	public ModelAndView updateMicroServiceConfig(MicroServiceConfigModel microServiceConfigModel) {
		ModelAndView model = new ModelAndView("redirect:updatedMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		microServiceConfigDao.updateMicroServiceConfig(microServiceConfigModel);
		return model;
	}
	
	
	public ModelAndView updatedMicroServiceConfig() {
		ModelAndView model = new ModelAndView("supadmin/editMicroServiceConfig");
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		model.addObject("SuccessMessage", "MicroService Configuration Successfully Updated...!!!");
		return model;
	}

	
//	View MicroService Config
	public ModelAndView viewMicroServiceConfig(MicroServiceConfigModel microServiceConfigModel) {
		ModelAndView model = editMicroServiceConfig(microServiceConfigModel);
		model.addObject("MicroServiceConfigParameters", StaticData.getMicroServiceConfigParametersData());
		model.addObject("Operation", "View");
		return model;
	}
}
