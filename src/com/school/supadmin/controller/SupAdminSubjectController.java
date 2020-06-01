package com.school.supadmin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSubjectModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SubjectDetailsModel;
import com.school.common.services.SubjectServices;
import com.school.json.model.ClassSubjectJsonModel;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminSubjectController 
{
	@Autowired
	private SubjectServices services;
	
	
	@RequestMapping(value = "/searchClassSubject", method = RequestMethod.GET)
	public ModelAndView searchClassSubject()
	{
		return services.searchClassSubject();
	}
	
	
	@RequestMapping(value = "/resultClassSubject", method = RequestMethod.POST)
	public ModelAndView resultClassSubject(HttpServletRequest request)
	{
		return services.resultClassSubject(request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchClassSubject", method = RequestMethod.GET)
	public List<ClassSubjectJsonModel> fetchClassSubject(@ModelAttribute SubjectDetailsModel subjectDetails, @ModelAttribute ClassSubjectModel classSubject, 
			@ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		return services.fetchClassSubject(classSubject, subjectDetails, classInformation, sectionInformation);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteAllocatedSubjectDetails", method = RequestMethod.POST)
	public String deleteAllocatedSubjectDetails(@ModelAttribute SubjectDetailsModel subjectDetails, @ModelAttribute ClassSubjectModel classSubject, 
			@ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		return services.deleteAllocatedSubjectDetails(classSubject, subjectDetails, classInformation, sectionInformation);
	}
	
	
	@RequestMapping(value = "/addClassSubject", method = RequestMethod.GET)
	public ModelAndView addClassSubject()
	{
		return services.addClassSubject();
	}
	
	
	@RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
	public ModelAndView saveSubject(@ModelAttribute SubjectDetailsModel subjectDetails)
	{
		return services.saveSubject(subjectDetails);
	}
	
	
	@RequestMapping(value = "/savedSubject", method = RequestMethod.GET)
	public ModelAndView savedSubject()
	{
		return services.savedSubject();
	}
	
	
	@RequestMapping(value = "/editSubjectDetails", method = RequestMethod.POST)
	public ModelAndView editSubjectDetails(@ModelAttribute SubjectDetailsModel subjectDetails)
	{
		return services.editSubjectDetails(subjectDetails);
	}
	
	
	@RequestMapping(value = "/updateSubjectDetails", method = RequestMethod.POST)
	public ModelAndView updateSubjectDetails(@ModelAttribute SubjectDetailsModel subjectDetails)
	{
		return services.updateSubjectDetails(subjectDetails);
	}
	
	
	@RequestMapping(value = "/updatedSubjectDetails", method = RequestMethod.GET)
	public ModelAndView updatedSubjectDetails()
	{
		return services.updatedSubjectDetails();
	}
	
	
	@RequestMapping(value = "/deleteSubjectDetails", method = RequestMethod.POST)
	public ModelAndView deleteSubjectDetails(@ModelAttribute SubjectDetailsModel subjectDetails)
	{
		return services.deleteSubjectDetails(subjectDetails);
	}
	
	
	@RequestMapping(value = "/deletedSubjectDetails", method = RequestMethod.GET)
	public ModelAndView deletedSubjectDetails()
	{
		return services.deletedSubjectDetails();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getSubjectDetailsForAllocateToClassSection", method = RequestMethod.POST)
	public Map<String, List<ClassSubjectJsonModel>> getSubjectDetailsForAllocateToClassSection(@ModelAttribute ClassInformationModel classInformation, 
			@ModelAttribute SectionInformationModel sectionInformation, @ModelAttribute ClassSubjectModel classSubject)
	{
		return services.getSubjectDetailsForAllocateToClassSection(classInformation, sectionInformation, classSubject);
	}
	
	
	
	@RequestMapping(value = "/saveAllocatedSubjectToClass", method = RequestMethod.POST)
	public ModelAndView saveAllocatedSubjectToClass(HttpServletRequest request, @ModelAttribute ClassInformationModel classInformation,  
			@ModelAttribute SectionInformationModel sectionInformation, @ModelAttribute ClassSubjectModel classSubject)
	{
		return services.saveAllocatedSubjectToClass(request, classSubject, classInformation, sectionInformation);
	}
	
	
	@RequestMapping(value = "/savedAllocatedSubjectToClass", method = RequestMethod.GET)
	public ModelAndView savedAllocatedSubjectToClass()
	{
		return services.savedAllocatedSubjectToClass();
	}
	
	
	@RequestMapping(value = "/editClassSubject", method = RequestMethod.POST)
	public ModelAndView editClassSubject(@ModelAttribute SubjectDetailsModel subjectDetails)
	{
		return services.editSubjectDetails(subjectDetails);
	}
}
