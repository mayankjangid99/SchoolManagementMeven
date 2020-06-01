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
import com.school.common.model.SectionInformationModel;
import com.school.common.services.ClassSectionServices;
import com.school.json.model.ClassSectionJsonModel;
import com.school.json.response.ClassSectionJsonResponse;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminClassSectionController {
	
	@Autowired
	private ClassSectionServices services;
	
	
	@RequestMapping(value = "/searchClassSection", method = RequestMethod.GET)
	public ModelAndView searchClassSection() {
		return services.searchClassSection();
	}
	
	@RequestMapping(value = "/resultClassSection", method = RequestMethod.POST)
	public ModelAndView resultClassSection(HttpServletRequest request) {
		return services.resultClassSection(request);
	}
	

	@RequestMapping(value = "/saveClass", method = RequestMethod.POST)
	public ModelAndView saveClass(@ModelAttribute ClassInformationModel classInformation) {
		return services.saveClass(classInformation);
	}
	
	@RequestMapping(value = "/savedClass", method = RequestMethod.GET)
	public ModelAndView savedClass() {
		return services.savedClass();
	}
	
	
	@RequestMapping(value = "/editClass", method = RequestMethod.POST)
	public ModelAndView editClass(@ModelAttribute ClassInformationModel classInformation) {
		return services.editClass(classInformation);
	}
	
	
	@RequestMapping(value = "/updateClass", method = RequestMethod.POST)
	public ModelAndView updateClass(@ModelAttribute ClassInformationModel classInformation) {
		return services.updateClass(classInformation);
	}
	
	
	@RequestMapping(value = "/updatedClass", method = RequestMethod.GET)
	public ModelAndView updatedClass() {
		return services.updatedClass();
	}
	
	
	@RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
	public ModelAndView deleteClass(@ModelAttribute ClassInformationModel classInformation) {
		return services.deleteClass(classInformation);
	}
	
	
	@RequestMapping(value = "/deletedClass", method = RequestMethod.GET)
	public ModelAndView deletedClass() {
		return services.deletedClass();
	}
	
	
	
	@RequestMapping(value = "/deleteSection", method = RequestMethod.POST)
	public ModelAndView deleteSection(@ModelAttribute SectionInformationModel sectionInformation) {
		return services.deleteSection(sectionInformation);
	}
	
	@RequestMapping(value = "/deletedSection", method = RequestMethod.GET)
	public ModelAndView deletedSection() {
		return services.deletedSection();
	}
	
	
	@RequestMapping(value = "/editSection", method = RequestMethod.POST)
	public ModelAndView editSection(@ModelAttribute SectionInformationModel sectionInformation) {
		return services.editSection(sectionInformation);
	}
	
	
	@RequestMapping(value = "/updateSection", method = RequestMethod.POST)
	public ModelAndView updateSection(@ModelAttribute SectionInformationModel sectionInformation) {
		return services.updateSection(sectionInformation);
	}
	
	
	@RequestMapping(value = "/updatedSection", method = RequestMethod.GET)
	public ModelAndView updatedSection() {
		ModelAndView model = new ModelAndView("supadmin/editClassSection");
		model.addObject("SuccessMessage", "Section Information Successfully Updated...!!!");
		return model;
	}
	
	
	
	@RequestMapping(value = "/saveSection", method = RequestMethod.POST)
	public ModelAndView saveSection(@ModelAttribute SectionInformationModel sectionInformation) {
		return services.saveSection(sectionInformation);
	}
	
	@RequestMapping(value = "/savedSection", method = RequestMethod.GET)
	public ModelAndView savedSection() {
		return services.savedSection();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchSectionInfoByClassForAllocateToClass", method = RequestMethod.GET)
	public Map<String, List<ClassSectionJsonModel>> getSectionInfoByClassForAllocateToClass(@ModelAttribute ClassInformationModel classInformation) {
		return services.fetchSectionInfoByClassForAllocateToClass(classInformation);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchClassSection", method = RequestMethod.GET)
	public ClassSectionJsonResponse fetchClassSection(@ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation) {
		return services.fetchClassSection(classInformation, sectionInformation);
	}
	
	
	
	@RequestMapping(value = "/addClassSection", method = RequestMethod.GET)
	public ModelAndView addClassSection() {
		return services.addClassSection();
	}
	
	
	@RequestMapping(value = "/savedClassSection", method = RequestMethod.GET)
	public ModelAndView savedClassSection() {
		ModelAndView model = new ModelAndView("supadmin/addClassSection");
		model.addObject("SuccessMessage", "Class and Section Information Successfully Saved...!!!");
		return model;
	}
	
	@RequestMapping(value = "/editClassSection", method = RequestMethod.POST)
	public String editClassSection() {
		return "supadmin/editClassSection";
	}
	
	
	@RequestMapping(value = "/saveAllocatedSectionToClass", method = RequestMethod.POST)
	public ModelAndView saveAllocatedSectionToClass(HttpServletRequest request) {
		return services.saveAllocatedSectionToClass(request);
	}
	
	
	
	@RequestMapping(value = "/savedAllocatedSectionToClass", method = RequestMethod.GET)
	public ModelAndView savedAllocatedSectionToClass() {
		return services.savedAllocatedSectionToClass();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteAllocatedSection", method = RequestMethod.POST)
	public String deleteAllocatedSection(HttpServletRequest request) {
		return services.deleteAllocatedSection(request);
	}
	
	
	
}
