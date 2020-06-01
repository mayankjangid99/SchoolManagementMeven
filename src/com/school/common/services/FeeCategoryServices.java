package com.school.common.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.FeeCategoryDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.ClassFeeCategoryId;
import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.FeeCategoryModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.services.business.FeeCategoryBusiness;
import com.school.json.model.ClassFeeCategoryJsonModel;
import com.school.json.model.FeeCategoryJsonModel;
import com.school.json.response.FeeCategoryJsonResponse;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FeeCategoryServices 
{
	
	@Autowired
	private FeeCategoryDao feeCategoryDao;
	
	@Autowired
	private FeeCategoryBusiness feeCategoryBusiness;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	@Autowired
	private HttpServletRequest request;

	private static Logger LOG = LoggerFactory.getLogger(FeeCategoryServices.class);
	
	
	public ModelAndView searchFeeCategory() {
		ModelAndView model = new ModelAndView("supadmin/searchFeeCategory");
		List<FeeCategoryModel> list = feeCategoryDao.getFeeCategoryList();
		model.addObject("FeeCategories", list);
		return model;
	}
	
	
	public ModelAndView resultFeeCategory(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("supadmin/resultFeeCategory");
		model.addObject("queryString", BusinessLogicHelper.getQueryStringFromPost(request));
		return model;
	}
	
	
	public ModelAndView addFeeCategory() {
		ModelAndView model = new ModelAndView("supadmin/addFeeCategory");
		List<FeeCategoryModel> list = feeCategoryDao.getFeeCategoryList();
		model.addObject("FeeCategories", list);
		return model;
	}
	
	
	

	
	public ModelAndView deleteFeeCategory(FeeCategoryModel feeCategory)
	{
		ModelAndView model = new ModelAndView("redirect:deletedFeeCategory");
		feeCategoryDao.deleteFeeCategory(feeCategory);
		return model;
	}
	
	

	public ModelAndView deletedFeeCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/addFeeCategory");
		List<FeeCategoryModel> list = feeCategoryDao.getFeeCategoryList();
		model.addObject("FeeCategories", list);
		model.addObject("SuccessMessage", "Fee Category Information Successfully deleted...!!!");
		return model;
	}
	
	
	public ModelAndView updatedFeeCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/editFeeCategory");
		List<FeeCategoryModel> list = feeCategoryDao.getFeeCategoryList();
		model.addObject("FeeCategories", list);
		model.addObject("SuccessMessage", "Fee Category Information Successfully updated...!!!");
		return model;
	}
	
	
	public ModelAndView savedFeeCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/addFeeCategory");
		List<FeeCategoryModel> list = feeCategoryDao.getFeeCategoryList();
		model.addObject("FeeCategories", list);
		model.addObject("SuccessMessage", "Fee Category Information Successfully Saved...!!!");
		return model;
	}
	
	
	public ModelAndView saveFeeCategory(FeeCategoryModel feeCategory)
	{
		ModelAndView model = new ModelAndView("redirect:savedFeeCategory");
		if(StaticValue.FEE_CATEGORY_TRANSPORT.equalsIgnoreCase(feeCategory.getFeeCategoryCode())) {
			model.setViewName("supadmin/addFeeCategory");
			model.addObject("ErrorMessage", "Fee Category Code fixed for Transaport, please enter other Category Code information...!!!");
			return model;
		}
		List<FeeCategoryModel> feeCategorysList = feeCategoryDao.getFeeCategoryListByCategoryCode(feeCategory);
		if(feeCategorysList.size() > 0)
		{
			model.setViewName("supadmin/addFeeCategory");
			model.addObject("ErrorMessage", "Fee Category information already exist, please enter other Fee Category information...!!!");
			return model;
		}
		else
		{
			feeCategoryDao.saveFeeCategory(feeCategory);
			return model;
		}
	}
	
	
	public ModelAndView editFeeCategory(FeeCategoryModel feeCategory)
	{
		ModelAndView model = new ModelAndView("supadmin/editFeeCategory");
		List<FeeCategoryModel> feeCategorysList = feeCategoryDao.getFeeCategoryListByCategoryCode(feeCategory);
		if(feeCategorysList.size() > 0)
		{
			model.addObject("FeeCategoryInfo", feeCategorysList.get(0));
			return model;
		}
		return model;
	}
	
	
	
	public ModelAndView updateFeeCategory(FeeCategoryModel feeCategory)
	{
		ModelAndView model = new ModelAndView("redirect:updatedFeeCategory");
		boolean status = feeCategoryDao.updateFeeCategory(feeCategory);
		if(status)
		{
			return model;
		}
		else
		{
			model.setViewName("supadmin/editFeeCategory");
			model.addObject("ErrorMessage", "Fee Category information can't updated, please provide correct information...!!!");
			return model;
		}
	}
	
	public String deleteAllocatedFeeCategory(HttpServletRequest request)
	{
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String feeCategoryCode = request.getParameter("feeCategoryCode");
		String type = request.getParameter("type");
		boolean flag = feeCategoryDao.deleteAllocatedFeeCategory(schoolCode, SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, feeCategoryCode, type);
		if(flag)
			return "success";
		else
			return "failed";
	}
		
	
	
	
	public ModelAndView saveAllocatedFeeCategoryToClassSection(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("redirect:savedAllocatedFeeCategoryToClassSection");
//		String[] feeCategoryCodes = request.getParameterValues("selectedFeeCategory");
		String[] feeCategoryCodes = request.getParameterValues("feeCategoryCode");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String type = request.getParameter("type");
		String feeType = request.getParameter("feeType");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
		String status = request.getParameter("status");
		
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		
		List<ClassFeeCategoryModel> classFeeCategoryList = new ArrayList<ClassFeeCategoryModel>();
		ClassFeeCategoryModel classFeeCategoryModel = null;
		FeeCategoryModel feeCategoryModel = null;
		ClassFeeCategoryId classFeeCategoryId = null;
		SchoolProfileModel schoolProfileModel = new SchoolProfileModel();
		schoolProfileModel.setSchoolCode(schoolCode);
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		ClassInformationModel classInformationModel = new ClassInformationModel();
		classInformationModel.setClassCode(classCode);
		SectionInformationModel sectionInformationModel = new SectionInformationModel();
		sectionInformationModel.setSectionCode(sectionCode);
		
		if(feeCategoryCodes != null) {
			for(String feeCategoryCode : feeCategoryCodes) {
				classFeeCategoryModel = new ClassFeeCategoryModel();			
				feeCategoryModel = new FeeCategoryModel();
				classFeeCategoryId = new ClassFeeCategoryId();
				
				feeCategoryModel.setFeeCategoryCode(feeCategoryCode);
				classFeeCategoryId.setClassCode(classCode);
				classFeeCategoryId.setFeeCategoryCode(feeCategoryCode);
				classFeeCategoryId.setSchoolCode(schoolCode);
				classFeeCategoryId.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
				classFeeCategoryId.setSectionCode(sectionCode);
				
				classFeeCategoryModel.setClassInformation(classInformationModel);
				classFeeCategoryModel.setSectionInformation(sectionInformationModel);
				classFeeCategoryModel.setFeeCategory(feeCategoryModel);
				classFeeCategoryModel.setSchoolProfile(schoolProfileModel);
				classFeeCategoryModel.setSessionDetails(sessionDetails);
				classFeeCategoryModel.setId(classFeeCategoryId);
				classFeeCategoryModel.setType(type);
				classFeeCategoryModel.setFeeType(feeType);
				classFeeCategoryModel.setAmount(amount);
				classFeeCategoryModel.setStatus(status);
				classFeeCategoryList.add(classFeeCategoryModel);
			}
		}
		if(schoolCode != null && classCode != null && classFeeCategoryList.size() > 0)
			//feeCategoryDao.deleteAllocatedFeeCategory(schoolProfileModel.getSchoolCode(), sessionDetails.getSessionCode(), classInformationModel.getClassCode(), feeCategoryModel.getFeeCategoryCode(), classFeeCategoryModel.getType());
		
		for(ClassFeeCategoryModel classFeeCategoryModel2 : classFeeCategoryList)
		{
			feeCategoryDao.saveAllocatedFeeCategoryToClassSection(classFeeCategoryModel2);
		}
		return model;
	}
	
	
	public ModelAndView savedAllocatedFeeCategoryToClassSection()
	{
		ModelAndView model = new ModelAndView("supadmin/addFeeCategory");
		model.addObject("SuccessMessage", "Fee Category Information Successfully Alloacated to Class And Section...!!!");
		return model;
	}
	
	
	public Map<String, List<FeeCategoryJsonModel>> fetchFeeCategoryByClassForAllocateToClass(ClassInformationModel classInformation, SectionInformationModel sectionInformation, ClassFeeCategoryModel classFeeCategory)
	{
		//ClassInformationModel classInformationModel = facadeDaoManager.getClassSectionDao().getClassListByClassCode(classInformation);
		List<FeeCategoryModel> feeCategoryList = feeCategoryDao.getFeeCategoryList();
		
		//List<ClassFeeCategoryModel> classFeeCategotyList = feeCategoryDao.getFeeCategoryListByClassCodeSchoolCodeAndType(SessionManagerDataHelper.getUserActiveSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classInformation.getClassCode(), sectionInformation.getSectionCode(), classFeeCategory.getType());
		List<ClassFeeCategoryModel> classFeeCategotyList = feeCategoryDao.getFeeCategoryListByClassCodeSchoolCodeAndType(SessionManagerDataHelper.getUserActiveSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classInformation.getClassCode(), sectionInformation.getSectionCode(), null, null);
		Map<String, List<FeeCategoryJsonModel>> dataMap = feeCategoryBusiness.fetchFeeCategoryByClassForAllocateToClass(classFeeCategotyList, feeCategoryList);
		return dataMap;
	}
	
	
	
//	fetch Class  Section 
	public FeeCategoryJsonResponse fetchFeeCategoryWithClass(ClassInformationModel classInformation, SectionInformationModel sectionInformation, FeeCategoryModel feeCategory, ClassFeeCategoryModel classFeeCategory) {
		List<ClassFeeCategoryModel> classFeeCategoryList = feeCategoryDao.getClassFeeCategoryByClassCodeSecCodeFeeCodeAndType(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classInformation.getClassCode(), sectionInformation.getSectionCode(), feeCategory.getFeeCategoryCode(), classFeeCategory.getType(), classFeeCategory.getFeeType());
		FeeCategoryJsonResponse feeCategoryJsonResponse = feeCategoryBusiness.fetchFeeCategoryWithClass(classFeeCategoryList);
		return feeCategoryJsonResponse;
	}
	
	
	public String changeAllocatedFeeCategoryStatus()
	{
		try {
			String status = request.getParameter("status");
			String feeCategoryCode = request.getParameter("feeCategoryCode");
			String classCode = request.getParameter("classCode");
			String sectionCode = request.getParameter("sectionCode");
			if(status.equalsIgnoreCase("Y"))
				status = "N";
			else 
				status = "Y";

			feeCategoryDao.changeAllocatedFeeCategoryStatus(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, feeCategoryCode, status);
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: changeAllocatedFeeCategoryStatus() in FeeCategoryServices", e);
			return "failed";
		}
		return "changed";
	}
	
	
	public ResponseEntity<List<ClassFeeCategoryJsonModel>> getAdditionaFeeCategories() {
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		List<ClassFeeCategoryModel> list = feeCategoryDao.getFeeCategories(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, "A", "Y");
		return feeCategoryBusiness.getAdditionaFeeCategories(list);
	}
}
