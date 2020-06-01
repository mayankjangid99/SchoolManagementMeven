package com.school.common.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.SchoolSessionDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.services.business.SchoolSessionBusiness;
import com.school.json.model.SchoolSessionJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolSessionServices 
{
	@Autowired
	private SchoolSessionDao schoolSessionDao;
	
	@Autowired
	private SchoolSessionBusiness schoolSessionBusiness;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
//	Switch School Session
	public ModelAndView switchSchoolSession(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("redirect:welcome");
		schoolSessionBusiness.switchSchoolSession(request);
		return model;
	}
	
	
	public ModelAndView searchSchoolSession() {
		ModelAndView model = new ModelAndView("supadmin/searchSchoolSession");
		return model;
	}
	
	
	public ModelAndView addSchoolSession() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolSession");
		setSchoolSessionDropdwon(model);
		return model;
	}

	
	private void setSchoolSessionDropdwon(ModelAndView model) {
		List<SessionDetailsModel> list = schoolSessionDao.getAllSessionDetails();
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		List<SchoolSessionModel> list2 = schoolSessionDao.getSchoolSessionBySchoolCode(schoolProfile);
		List<SessionDetailsModel> list3 = new ArrayList<SessionDetailsModel>();
		for(SessionDetailsModel sessionDetails : list) {
			boolean checkFlag = false;
			for(SchoolSessionModel schoolSession : list2) {
				if(sessionDetails.getSessionCode().equals(schoolSession.getSessionDetails().getSessionCode())) {
					checkFlag = true;
					break;
				}
			}
			if(!checkFlag)
				list3.add(sessionDetails);
		}
		model.addObject("SessionDetails", list3);
	}
	
	
	public ModelAndView saveSessionDetails(SessionDetailsModel sessionDetails) {
		ModelAndView model = new ModelAndView("redirect:savedSessionDetails");
		schoolSessionDao.saveSessionDetails(sessionDetails);
		return model;
	}
	

	
	public ModelAndView savedSessionDetails() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolSession");
		model.addObject("SuccessMessage", "Session Details Successfully Saved...!!!");
		setSchoolSessionDropdwon(model);
		return model;
	}
	
	
	public ModelAndView saveAllocateSchoolSession(SchoolSessionModel schoolSession) {

		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		schoolSession.setSchoolProfile(schoolProfile);
		ModelAndView model = new ModelAndView("redirect:savedAllocateSchoolSession");
		SchoolSessionModel schoolSessionModel = schoolSessionDao.getSchoolSessionBySessionCodeAndSchoolCode(schoolSession);
		if(schoolSessionModel != null) {
			model.setViewName("supadmin/addSchoolSession");
			model.addObject("ErrorMessage", "School Session already exist, please add other School Session...!!!");
			return model;
		} else {
			Integer maxSequence = schoolSessionDao.getSchoolSessionMaxSequence(schoolProfile);
			if(maxSequence == null) {
				maxSequence = 1;
			} else {
				maxSequence += 1;
				if("C".equalsIgnoreCase(schoolSession.getStatus()))
					schoolSessionDao.updateSchoolSessionStatusInPrivious(SessionManagerDataHelper.getSchoolCode());
			}
			schoolSession.setSequence(maxSequence);
			schoolSessionDao.saveSchoolSession(schoolSession);
			return model;
		}
	}
	
	
	public ModelAndView savedAllocateSchoolSession() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolSession");
		model.addObject("SuccessMessage", "Allocated Session Details to School Successfully Saved...!!!");
		setSchoolSessionDropdwon(model);
		return model;
	}
	
	
	
	public List<SchoolSessionJsonModel> fetchAllocatedSchoolSession(SchoolSessionModel schoolSession) {
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		schoolSession.setSchoolProfile(schoolProfile);
		List<SchoolSessionModel> list = schoolSessionDao.fetchAllocatedSchoolSession(schoolSession);
		List<SchoolSessionJsonModel> schoolSessionJsonList = schoolSessionBusiness.fetchAllocatedSchoolSession(list);
		return schoolSessionJsonList;
	}
	
	
	public ModelAndView editSessionDetails(SessionDetailsModel sessionDetails) {
		ModelAndView model = new ModelAndView("supadmin/editSchoolSession");
		SessionDetailsModel sessionDetailsModel = schoolSessionDao.getSessionDetailsBySessionCode(sessionDetails);
		model.addObject("SessionDetails", sessionDetailsModel);
		return model;
	}
	
	
	public ModelAndView updateSessionDetails(SessionDetailsModel sessionDetails) {
		ModelAndView model = new ModelAndView("redirect:updatedSessionDetails");
		schoolSessionDao.updateSessionDetails(sessionDetails);
		return model;
	}
	
	
	public ModelAndView updatedSessionDetails() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolSession");
		model.addObject("SuccessMessage", "Session Details Successfully Updated...!!!");
		return model;
	}
	
	
	public ModelAndView deleteSessionDetails(SessionDetailsModel sessionDetails) {
		ModelAndView model = new ModelAndView("redirect:deletedSessionDetails");
		schoolSessionDao.deleteSessionDetails(sessionDetails);
		return model;
	}
	
	
	public ModelAndView deletedSessionDetails() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolSession");
		model.addObject("SuccessMessage", "Session Details Successfully Deleted...!!!");
		return model;
	}
	
	
	public ModelAndView editSchoolSession(SchoolSessionModel schoolSession) {
		ModelAndView model = new ModelAndView("supadmin/editSchoolSession");
		SchoolSessionModel schoolSessionModel = schoolSessionDao.getSchoolSessionBySchoolSessionId(schoolSession);
		model.addObject("SchoolSession", schoolSessionModel);
		return model;
	}
	
	public ModelAndView updateAllocatedSchoolSession(SchoolSessionModel schoolSession) {
		ModelAndView model = new ModelAndView("redirect:updatedAllocatedSchoolSession");
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		schoolSessionDao.updateSchoolSessionBySessionCodeAndSchoolCode(schoolProfile, schoolSession);
		return model;
	}
	
	
	
	public ModelAndView updatedAllocatedSchoolSession() {
		ModelAndView model = new ModelAndView("supadmin/editSchoolSession");
		model.addObject("SuccessMessage", "School Session Details Successfully Updated...!!!");
		return model;
	}
	
	
	public boolean deleteAllocatedSchoolSession(SchoolSessionModel schoolSession) {
		return schoolSessionDao.deleteSchoolSessionByschoolSessionId(schoolSession);
	}
	
	
	
}
