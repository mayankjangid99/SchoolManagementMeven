package com.school.common.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.ClassSectionDao;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSectionId;
import com.school.common.model.ClassSectionModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.services.business.ClassSectionBusiness;
import com.school.json.model.ClassSectionJsonModel;
import com.school.json.response.ClassSectionJsonResponse;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClassSectionServices
{
	@Autowired
	private ClassSectionDao classSectionDao;
	
	@Autowired
	private ClassSectionBusiness classSectionBusiness;
	
	
	public ModelAndView searchClassSection()
	{
		ModelAndView model = new ModelAndView("supadmin/searchClassSection");
		return model;
	}
	
	
	public ModelAndView resultClassSection(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("supadmin/resultClassSection");
		model.addObject("queryString", BusinessLogicHelper.getQueryStringFromPost(request));
		return model;
	}
	
	
//	Get Class List
	public ClassSectionJsonResponse getClassList(HttpServletRequest request) {
		String activeSchoolCode = SessionManagerDataHelper.getUserActiveSchoolCode();
		String fetchType = request.getParameter("fetchType");
		if("ALL".equalsIgnoreCase(fetchType))
			activeSchoolCode = null;
		List<ClassInformationModel> classInformationsList = classSectionDao.getClassListBySchoolCodeSessionCode(activeSchoolCode, SessionManagerDataHelper.getSchoolCurrentSession());
		ClassSectionJsonResponse classSectionJsonResponse = classSectionBusiness.getClassList(classInformationsList);
		return classSectionJsonResponse;
	}
	
	
//	Get Section List
	public ClassSectionJsonResponse getSectionList(ClassInformationModel classInformation) {
		List<SectionInformationModel> sectionInformationsList = classSectionDao.getSectionListByClassCodeAndSchoolCodeSessionCode(SessionManagerDataHelper.getUserActiveSchoolCode(), classInformation.getClassCode(), SessionManagerDataHelper.getSchoolCurrentSession());
		ClassSectionJsonResponse classSectionJsonResponse = classSectionBusiness.getSectionList(sectionInformationsList);
		return classSectionJsonResponse;
	}
	
	
	
	public ModelAndView addClassSection() {
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		List<ClassInformationModel> allClassInformationList = classSectionDao.getAllClassList();
		/*List<ClassInformationModel> remainingClassList = new ArrayList<ClassInformationModel>();
		List<ClassInformationModel> allocatedClassList = classSectionDao.getClassListBySchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		for(ClassInformationModel classInformationModel1 : allClassInformationList)
		{
			boolean checkFlag = false;
			for(ClassInformationModel classInformationModel2 : allocatedClassList)
			{
				if(classInformationModel2.getClassCode().equals(classInformationModel1.getClassCode()))
					checkFlag = true;
			}
			if(!checkFlag)
				remainingClassList.add(classInformationModel1);
		}*/
		model.addObject("InfoMessage", "Please add class in increase order");
		model.addObject("Classes", allClassInformationList);
		int classOrder = allClassInformationList != null && !allClassInformationList.isEmpty() ? allClassInformationList.size() + 1 : 1;
		model.addObject("classOrder", classOrder);
		return model;		
	}
	
	
	
	
	public ModelAndView saveClass(ClassInformationModel classInformation) {
		ModelAndView model = new ModelAndView("redirect:savedClass");
		ClassInformationModel classInformationsModel = classSectionDao.getClassInformationByClassCode(classInformation);
		if(classInformationsModel != null) {
			model.setViewName("supadmin/addClassSection");
			model.addObject("ErrorMessage", "Class information already exist, please enter other Class information...!!!");
			return model;
		} else {
			classSectionDao.saveClass(classInformation);
			return model;
		}
	}
	
	
	public ModelAndView savedClass() {
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		model.addObject("SuccessMessage", "Class Information Successfully Saved...!!!");
		model.addObject("InfoMessage", "Please add class in increase order");
		List<ClassInformationModel> allClassInformationList = classSectionDao.getAllClassList();
		model.addObject("Classes", allClassInformationList);
		int classOrder = allClassInformationList != null && !allClassInformationList.isEmpty() ? allClassInformationList.size() + 1 : 1;
		model.addObject("classOrder", classOrder);
		return model;
	}
	
	
	public ModelAndView editClass(ClassInformationModel classInformation) {
		ModelAndView model = new ModelAndView("supadmin/editClassSection");
		ClassInformationModel classInformationsModel = classSectionDao.getClassInformationByClassCode(classInformation);
		model.addObject("InfoMessage", "Please add class in increase order");
		model.addObject("ClassInfo", classInformationsModel);
		model.addObject("classOrder", classInformationsModel.getClassOrder());
		return model;
	}
	
	public ModelAndView updatedClass() {
		ModelAndView model = new ModelAndView("supadmin/editClassSection");
		model.addObject("SuccessMessage", "Class Information Successfully Updated...!!!");
		model.addObject("InfoMessage", "Please add class in increase order");
		return model;
	}
	
	public ModelAndView updateClass(ClassInformationModel classInformation) {
		ModelAndView model = new ModelAndView("redirect:updatedClass");
		classSectionDao.updateClass(classInformation);
		return model;
	}
	
	
	public ModelAndView deleteClass(ClassInformationModel classInformation) {
		ModelAndView model = new ModelAndView("redirect:deletedClass");
		classSectionDao.deleteClass(classInformation);
		return model;
	}
	
	
	public ModelAndView deletedClass()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		model.addObject("InfoMessage", "Please add class in increase order");
		model.addObject("SuccessMessage", "Class Information Successfully deleted...!!!");
		return model;
	}
	
	
	public ModelAndView deleteSection(SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("redirect:deletedSection");
		classSectionDao.deleteSection(sectionInformation);
		return model;
	}
	
	
	public ModelAndView deletedSection() {
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		model.addObject("SuccessMessage", "Section Information Successfully deleted...!!!");
		return model;
	}
	
	
	public ModelAndView saveSection(SectionInformationModel sectionInformation) {
		ModelAndView model = new ModelAndView("redirect:savedSection");
		SectionInformationModel sectionInformationsModel = classSectionDao.getSectionInformationBySectionCode(sectionInformation);
		if(sectionInformationsModel != null)
		{
			model.setViewName("supadmin/addClassSection");
			model.addObject("ErrorMessage", "Section information already exist, please enter other Section information...!!!");
			return model;
		}
		else
		{
			classSectionDao.saveSection(sectionInformation);
			return model;
		}
	}
	
	
	public ModelAndView savedSection()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		model.addObject("SuccessMessage", "Section Information Successfully Saved...!!!");
		List<ClassInformationModel> allClassInformationList = classSectionDao.getAllClassList();
		model.addObject("Classes", allClassInformationList);
		return model;
	}
	
	
	public ModelAndView editSection(SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("supadmin/editClassSection");
		SectionInformationModel sectionInformationsModel= classSectionDao.getSectionInformationBySectionCode(sectionInformation);
		if(sectionInformationsModel != null)
		{
			model.addObject("SectionInfo", sectionInformationsModel);
			return model;
		}
		return model;
	}
	
	
	public ModelAndView updateSection(SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("redirect:updatedSection");
		classSectionDao.updateSection(sectionInformation);
		return model;
	}
	
	
	
	public ModelAndView saveAllocatedSectionToClass(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("redirect:savedAllocatedSectionToClass");
		String[] sectionCodes = request.getParameterValues("selectedSection");
		String classCode = request.getParameter("classCode");
		
		List<ClassSectionModel> classSectionList = new ArrayList<ClassSectionModel>();
		ClassSectionModel classSectionModel = null;
		SectionInformationModel sectionInformationModel = null;
		ClassSectionId classSectionId = null;
		
		String schoolCode = SessionManagerDataHelper.getUserActiveSchoolCode();
		SchoolProfileModel schoolProfileModel = new SchoolProfileModel();
		schoolProfileModel.setSchoolCode(schoolCode);
		
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		
		ClassInformationModel classInformationModel = new ClassInformationModel();
		classInformationModel.setClassCode(classCode);
		if(sectionCodes != null)
		{
			for(String sectionCode : sectionCodes)
			{
				classSectionModel = new ClassSectionModel();			
				sectionInformationModel = new SectionInformationModel();
				classSectionId = new ClassSectionId();
				
				sectionInformationModel.setSectionCode(sectionCode);
				classSectionId.setClassCode(classCode);
				classSectionId.setSectionCode(sectionCode);
				classSectionId.setSchoolCode(schoolCode);
				classSectionId.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
				
				classSectionModel.setClassInformation(classInformationModel);
				classSectionModel.setSectionInformation(sectionInformationModel);
				classSectionModel.setSchoolProfile(schoolProfileModel);
				classSectionModel.setSessionDetails(sessionDetails);
				classSectionModel.setId(classSectionId);
				classSectionList.add(classSectionModel);
			}
		}
		if(schoolCode != null && classCode != null)
			classSectionDao.deleteAllocatedSectionToClassBySchoolCodeSessionCodeAndClassCode(schoolProfileModel, sessionDetails, classInformationModel);
		
		for(ClassSectionModel classSection : classSectionList)
		{
			classSectionDao.saveAllocatedSectionToClass(classSection);
		}
		
		return model;
	}
	
	
	
	public ModelAndView savedAllocatedSectionToClass()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		model.addObject("SuccessMessage", "Section Information Successfully Alloacated to Class...!!!");
		List<ClassInformationModel> allClassInformationList = classSectionDao.getAllClassList();
		model.addObject("Classes", allClassInformationList);
		return model;
	}
	
	
	
	public String deleteAllocatedSection(HttpServletRequest request)
	{
		String schoolCode = SessionManagerDataHelper.getUserActiveSchoolCode();
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		boolean flag = classSectionDao.deleteAllocatedSection(schoolCode, classCode, sectionCode);
		if(flag)
			return "success";
		else
			return "failed";
	}
	
	
	public Map<String, List<ClassSectionJsonModel>> fetchSectionInfoByClassForAllocateToClass(ClassInformationModel classInformation)
	{
		//ClassInformationModel classInformationsList = classSectionDao.getClassListByClassCode(classInformation);
		//Map<String, List<ClassSectionJsonModel>> dataMap = classSectionBusiness.fetchSectionInfoByClassForAllocateToClass(classInformationsList, sectionInformationList);
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		SessionDetailsModel sessionDetailsModel = new SessionDetailsModel();
		sessionDetailsModel.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		List<ClassSectionModel> classSectionList = classSectionDao.getClassListByClassCodeSessionCodeAndSchoolCode(schoolProfile, sessionDetailsModel, classInformation);
		List<SectionInformationModel> sectionInformationList = classSectionDao.getSectionList();
		Map<String, List<ClassSectionJsonModel>> dataMap = classSectionBusiness.fetchSectionInfoByClassForAllocateToClass(classSectionList, sectionInformationList);
		return dataMap;
	}
	
	
	
//	fetch Class  Section 
	public ClassSectionJsonResponse fetchClassSection(ClassInformationModel classInformation, SectionInformationModel sectionInformation) {
		//List<ClassInformationModel> classInformationsList = classSectionDao.fetchClassSection(classInformation);
		//ClassSectionJsonResponse classSectionJsonResponse = classSectionBusiness.fetchClassSection(classInformationsList, sectionInformation);
		List<ClassSectionModel> classSectionModelsList = classSectionDao.fetchClassSection(SessionManagerDataHelper.getUserActiveSchoolCode(), classInformation.getClassCode(), sectionInformation.getSectionCode());
		ClassSectionJsonResponse classSectionJsonResponse = classSectionBusiness.fetchClassSection(classSectionModelsList);
		return classSectionJsonResponse;
	}
	
	
	
}
