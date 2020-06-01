package com.school.common.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.ApplicationSettingsDao;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.ApplicationSettingsModel;
import com.school.common.services.business.ApplicationSettingsBusiness;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ApplicationSettingsServices {
	
	@Autowired
	private ApplicationSettingsBusiness applicationSettingsBusiness;
	
	@Autowired
	private ApplicationSettingsDao applicationSettingsDao;
	
	
	
	public ModelAndView addApplicationSettings(){
		ModelAndView model = new ModelAndView("supadmin/addApplicationSettings");
		List<ApplicationSettingsModel> listSchool = applicationSettingsDao.getApplicationSettingsForSchool(SessionManagerDataHelper.getSchoolCode());
		model.addObject("ApplicationSettings", listSchool);
		return model;
	}
	
	public String addOrChangeEmailSettings(HttpServletRequest request){
		ApplicationSettingsModel settingsModel = applicationSettingsBusiness.addOrChangeEmailSettings(request);
		boolean flag = applicationSettingsDao.saveAndUpdateApplicationSetting(settingsModel);
		if(flag)
			return "SUCCESS";
		else
			return "FAILED";
	}

}
