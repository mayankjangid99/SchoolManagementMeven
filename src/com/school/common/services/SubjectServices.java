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

import com.school.common.dao.SubjectDao;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSubjectId;
import com.school.common.model.ClassSubjectModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.SubjectDetailsModel;
import com.school.common.services.business.SubjectBusiness;
import com.school.json.model.ClassSubjectJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubjectServices 
{
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private SubjectBusiness subjectBusiness;
	
	
	public ModelAndView searchClassSubject()
	{
		ModelAndView model = new ModelAndView("supadmin/searchClassSubject");
		setDropdown(model);
		return model;
	}
	
	
	public ModelAndView resultClassSubject(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("supadmin/resultClassSubject");
		String queryString = BusinessLogicHelper.getQueryStringFromPost(request);
		model.addObject("queryString", queryString);
		return model;
	}
	
	
	public List<ClassSubjectJsonModel> fetchClassSubject(ClassSubjectModel classSubject, SubjectDetailsModel subjectDetails , ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		List<ClassSubjectModel> list = subjectDao.fetchClassSubjectBySchoolCodeClassCodeSectionCodeAndType(schoolProfile, sessionDetails, classInformation, sectionInformation, subjectDetails, classSubject);
		List<ClassSubjectJsonModel> jsonModelsList = subjectBusiness.fetchClassSubject(list);
		return jsonModelsList;
	}
	
	
	public String deleteAllocatedSubjectDetails(ClassSubjectModel classSubject, SubjectDetailsModel subjectDetails , ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		classSubject.setSchoolProfile(schoolProfile);
		classSubject.setSessionDetails(sessionDetails);
		classSubject.setClassInformation(classInformation);
		classSubject.setSectionInformation(sectionInformation);
		classSubject.setSubjectDetails(subjectDetails);
		boolean flag = subjectDao.deleteAllocatedSubjectDetailsToClassBySchoolCodeClassCodeSectionCodeAndType(classSubject);
		if(flag)
			return "success";
		else
			return "failed";
	}
	
	
	public ModelAndView addClassSubject()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSubject");
		setDropdown(model);
		return model;
	}
	
	
	public ModelAndView saveSubject(SubjectDetailsModel subjectDetails)
	{
		ModelAndView model = new ModelAndView("redirect:savedSubject");
		subjectDao.saveSubject(subjectDetails);
		return model;
	}
	
	
	public ModelAndView savedSubject()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSubject");
		model.addObject("SuccessMessage", "Subject Details Successfully saved...!!!");
		setDropdown(model);
		return model;
	}
	
	
	public ModelAndView editSubjectDetails(SubjectDetailsModel subjectDetails)
	{
		ModelAndView model = new ModelAndView("supadmin/editClassSubject");
		SubjectDetailsModel subjectDetailsModel = subjectDao.getSubjectDetailsBySubjectCode(subjectDetails);
		model.addObject("Subject", subjectDetailsModel);
		return model;
	}
	
	

	public ModelAndView updateSubjectDetails(SubjectDetailsModel subjectDetails)
	{
		ModelAndView model = new ModelAndView("redirect:updatedSubjectDetails");
		subjectDao.updateSubjectDetails(subjectDetails);
		return model;
	}
	
	
	public ModelAndView updatedSubjectDetails()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSubject");
		model.addObject("SuccessMessage", "Subject Details Successfully updated...!!!");
		setDropdown(model);
		return model;
	}
	
	
	public ModelAndView deleteSubjectDetails(SubjectDetailsModel subjectDetails)
	{
		ModelAndView model = new ModelAndView("redirect:deletedSubjectDetails");
		subjectDao.deleteSubjectDetails(subjectDetails);
		return model;
	}
	
	
	public ModelAndView deletedSubjectDetails()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSubject");
		model.addObject("SuccessMessage", "Subject Details Successfully deleted...!!!");
		return model;
	}
	
	
	public Map<String, List<ClassSubjectJsonModel>> getSubjectDetailsForAllocateToClassSection(ClassInformationModel classInformation, SectionInformationModel sectionInformation, ClassSubjectModel classSubject)
	{
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		List<SubjectDetailsModel> subjectDetailsList = subjectDao.getAllSubjectDetails();
		List<ClassSubjectModel> classSubjectList = subjectDao.getClassSubjectBySchoolCodeClassCodeSectionCodeAndType(schoolProfile, sessionDetails, classInformation, sectionInformation, classSubject);
		Map<String, List<ClassSubjectJsonModel>> mapData = subjectBusiness.fetchSectionInfoByClassForAllocateToClass(classSubjectList, subjectDetailsList);
		return mapData;
	}
	
	
	private void setDropdown(ModelAndView model)
	{
		List<SubjectDetailsModel> list = subjectDao.getAllSubjectDetails();
		model.addObject("Subjects", list);
	}
	
	
	public ModelAndView saveAllocatedSubjectToClass(HttpServletRequest request, ClassSubjectModel classSubject, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("redirect:savedAllocatedSubjectToClass");
		String[] subjectCodes = request.getParameterValues("selectedSubject");
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		
		List<ClassSubjectModel> classSubjectList = new ArrayList<ClassSubjectModel>();
		SubjectDetailsModel subjectDetails = null;
		ClassSubjectModel classSubjectModel = null;
		if(subjectCodes != null)
		{
			for(String subjectCode : subjectCodes)
			{
				classSubjectModel = new ClassSubjectModel();
				classSubjectModel.setType(classSubject.getType());
				classSubjectModel.setClassInformation(classInformation);
				classSubjectModel.setSectionInformation(sectionInformation);
				classSubjectModel.setSchoolProfile(schoolProfile);
				classSubjectModel.setSessionDetails(sessionDetails);
				
				subjectDetails = new SubjectDetailsModel();
				subjectDetails.setSubjectCode(subjectCode);
				classSubjectModel.setSubjectDetails(subjectDetails);
				
				ClassSubjectId classSubjectId = new ClassSubjectId();
				classSubjectId.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
				classSubjectId.setClassCode(classInformation.getClassCode());
				classSubjectId.setSectionCode(sectionInformation.getSectionCode());
				classSubjectId.setSubjectCode(subjectCode);
				classSubjectId.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
				classSubjectModel.setId(classSubjectId);
				
				classSubjectList.add(classSubjectModel);
			}
		}
		else
		{
			classSubjectModel = new ClassSubjectModel();
			classSubjectModel.setType(classSubject.getType());
			classSubjectModel.setClassInformation(classInformation);
			classSubjectModel.setSectionInformation(sectionInformation);
			classSubjectModel.setSchoolProfile(schoolProfile);
			classSubjectModel.setSessionDetails(sessionDetails);
		}
		if(classSubject.getType() != null && classInformation.getClassCode() != null && sectionInformation.getSectionCode() != null)
			subjectDao.deleteClassSubjectBySchoolCodeClassCodeSectionCodeAndType(classSubjectModel);
		
		for(ClassSubjectModel classSubjectModel2 : classSubjectList)
			subjectDao.saveAllocatedSubjectToClass(classSubjectModel2);
		return model;
	}
	
	
	public ModelAndView savedAllocatedSubjectToClass()
	{
		ModelAndView model = new ModelAndView("supadmin/addClassSubject");
		model.addObject("SuccessMessage", "Subject Details Successfully Allocated to Class and Section...!!!");
		return model;
	}
	
	
	
}
