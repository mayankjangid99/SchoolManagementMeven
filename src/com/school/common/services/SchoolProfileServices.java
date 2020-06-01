package com.school.common.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.SchoolProfileDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.CommonConstaint;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.generic.UploadFilesUtils;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.UniqueIdModel;
import com.school.common.services.business.SchoolProfileBusiness;
import com.school.json.model.SchoolProfileJsonModel;
import com.school.json.response.SchoolProfileJsonResponse;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolProfileServices 
{

	private static Logger LOG = LoggerFactory.getLogger(SchoolProfileServices.class);
	
	@Autowired
	private MessageSource messageSource;	
	
	
	@Autowired
	private SchoolProfileDao schoolProfileDao;
	
	@Autowired
	private SchoolProfileBusiness schoolProfileBusiness;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	
	public ModelAndView resultSchoolProfile(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("supadmin/resultSchoolProfile");
		model.addObject("queryString", BusinessLogicHelper.getQueryStringFromPost(request));
		return model;
	}
	
	public List<SchoolProfileJsonModel> fetchSchoolProfile(SchoolProfileModel schoolProfile)
	{
		List<SchoolProfileModel> schoolProfileList = schoolProfileDao.fetchSchoolProfile(schoolProfile);
		List<SchoolProfileJsonModel> schoolProfileJsonList = schoolProfileBusiness.fetchSchoolProfile(schoolProfileList);
		return schoolProfileJsonList;
	}
	
	public boolean validateSchoolCode(SchoolProfileModel schoolProfile)
	{
		SchoolProfileModel schoolProfileModel = schoolProfileDao.getSchoolProfileBySchoolCode(schoolProfile);
		if(schoolProfileModel != null)
			return false;
		else
			return true;
	}
	
	
	public ModelAndView saveSchoolProfile(CommonsMultipartFile pimage, SchoolProfileModel schoolProfile) {
		ModelAndView model = new ModelAndView("redirect:savedSchoolProfile");
		try {
			String schoolCode = schoolProfile.getSchoolCode();
			String logo = UploadFilesUtils.uploadFileOnServer(schoolCode, pimage, CommonConstaint.SCHOOL_IMAGE_PATH, schoolProfile.getSchoolCode());
			schoolProfile.setLogo(logo);
			UniqueIdModel uniqueIdModel = new UniqueIdModel();
			uniqueIdModel.setCreatedBy(SessionManagerDataHelper.getUsername());
			uniqueIdModel.setCreatedOn(new Date());
			uniqueIdModel.setTemplateId(StaticValue.FEE_RECEIPT);
			uniqueIdModel.setTemplateName(schoolProfile.getSchoolCode() + "_" + StaticValue.FEE_RECEIPT_ID);
			uniqueIdModel.setIdWidth(10l);
			uniqueIdModel.setLastId(0l);
			uniqueIdModel.setSchoolProfile(schoolProfile);
			Set<UniqueIdModel> uniqueIds = new HashSet<UniqueIdModel>();
			uniqueIds.add(uniqueIdModel);
			schoolProfile.setUniqueIds(uniqueIds);
			schoolProfileDao.saveSchoolProfile(schoolProfile);
			return model;			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: saveSchoolProfile() in SchoolProfileDao ", e);
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		return model;
	}
	
	
	public ModelAndView savedSchoolProfile() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolProfile");
		model.addObject("SuccessMessage", "School Profile Successfully Saved...!!!");
		return model;
	}
	
	
	public ModelAndView editSchoolProfile(SchoolProfileModel schoolProfile) {
		ModelAndView model = new ModelAndView("supadmin/editSchoolProfile");
		SchoolProfileModel schoolProfileModel = schoolProfileDao.getSchoolProfileBySchoolCode(schoolProfile);
		model.addObject("SchoolProfile", schoolProfileModel);
		List<SessionDetailsModel> sessionList = facadeDaoManager.getSchoolSessionDao().getAllSessionDetails();
		model.addObject("SessionList", sessionList);
		return model;
	}
	
	
	public ModelAndView viewSchoolProfile(SchoolProfileModel schoolProfile) {
		ModelAndView model = new ModelAndView("supadmin/editSchoolProfile");
		SchoolProfileModel schoolProfileModel = schoolProfileDao.getSchoolProfileBySchoolCode(schoolProfile);
		model.addObject("SchoolProfile", schoolProfileModel);
		model.addObject("Operation", "View");
		List<SessionDetailsModel> sessionList = facadeDaoManager.getSchoolSessionDao().getAllSessionDetails();
		model.addObject("SessionList", sessionList);
		return model;
	}
	
	
	public ModelAndView updateSchoolProfile(CommonsMultipartFile pimage, SchoolProfileModel schoolProfile) {
		ModelAndView model = new ModelAndView("redirect:updatedSchoolProfile");
		try {
			if(pimage != null && !"".equals(pimage.getOriginalFilename())) {
				String schoolCode = schoolProfile.getSchoolCode();
				String logo = UploadFilesUtils.uploadFileOnServer(schoolCode, pimage, CommonConstaint.SCHOOL_IMAGE_PATH, schoolProfile.getSchoolCode());
				schoolProfile.setLogo(logo);
			}
			schoolProfileDao.updateSchoolProfile(schoolProfile);
			return model;			
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		return model;
	}
	
	
	public ModelAndView updatedSchoolProfile() {
		ModelAndView model = new ModelAndView("supadmin/addSchoolProfile");
		model.addObject("SuccessMessage", "School Profile Successfully updated...!!!");
		return model;
	}
	
	public SchoolProfileJsonResponse getActiveSchoolWithAllSchool() {
		List<SchoolProfileModel> schoolProfileList = schoolProfileDao.getAllSchoolProfileCacheable();
		SchoolProfileJsonResponse schoolProfileJsonResponse = schoolProfileBusiness.getActiveSchoolWithAllSchool(schoolProfileList);
		return schoolProfileJsonResponse;
	}
	
	public String changeActiveSchoolProfile(SchoolProfileModel schoolProfile) {
		schoolProfile = schoolProfileDao.getSchoolProfileBySchoolCode(schoolProfile);
		List<SchoolSessionModel> sessionList = facadeDaoManager.getSchoolSessionDao().getSchoolSessionBySchoolCode(schoolProfile);
		schoolProfileBusiness.changeActiveSchoolProfile(schoolProfile, sessionList);
		facadeDaoManager.getUserDao().updateUserSchoolProfile(schoolProfile, Long.parseLong(SessionManagerDataHelper.getUserProfileUtil().getUserDetailsId()));
		return "success";
	}
	
	
	public ModelAndView updatePromoteSchoolData(HttpServletRequest request) {
		String schoolCode = request.getParameter("schoolCode");
		ModelAndView model = editSchoolProfile(new SchoolProfileModel(schoolCode));
		String sessionCode = request.getParameter("sessionCode");
		try {
			boolean isExistPromotedData = schoolProfileDao.existPromotedSchoolData(schoolCode, sessionCode);
			if(isExistPromotedData) {
				model.addObject("ErrorMessage", "School Data already exist for selected session for promote " + sessionCode + ".");
			} else {
				Map<String, Object> mapExistingData = schoolProfileDao.getPromoteSchoolData(schoolCode, sessionCode);
				
				Map<String, Object> updatePromoteSchoolData = schoolProfileBusiness.updatePromoteSchoolData(mapExistingData, schoolCode, sessionCode);
				boolean flag = schoolProfileDao.updatePromoteSchoolData(updatePromoteSchoolData);
				if(flag)
					model.addObject("SuccessMessage", "School Data successfully promoted for session " + sessionCode + ".");
				else
					model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
			}
		} catch (Exception e) {
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
			e.printStackTrace();
		}
		return model;
	}
	
}
