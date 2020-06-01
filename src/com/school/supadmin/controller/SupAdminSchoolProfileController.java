package com.school.supadmin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.SchoolProfileModel;
import com.school.common.services.SchoolProfileServices;
import com.school.json.model.SchoolProfileJsonModel;
import com.school.json.response.SchoolProfileJsonResponse;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminSchoolProfileController 
{
	@Autowired
	private SchoolProfileServices services;

	@RequestMapping(value = "/searchSchoolProfile", method = RequestMethod.GET)
	public String searchSchoolProfile()
	{
		return "supadmin/searchSchoolProfile";
	}
	
	@RequestMapping(value = "/resultSchoolProfile", method = RequestMethod.POST)
	public ModelAndView resultSchoolProfile(HttpServletRequest request)
	{
		return services.resultSchoolProfile(request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchSchoolProfile", method = RequestMethod.GET)
	public List<SchoolProfileJsonModel> fetchSchoolProfile(@ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.fetchSchoolProfile(schoolProfile);
	}
	
	
	@RequestMapping(value = "/addSchoolProfile", method = RequestMethod.GET)
	public String addSchoolProfile()
	{
		return "supadmin/addSchoolProfile";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/validateSchoolCode", method = RequestMethod.GET)
	public boolean validateSchoolCode(@ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.validateSchoolCode(schoolProfile);
	}
	
	
	@RequestMapping(value = "/saveSchoolProfile", method = RequestMethod.POST)
	public ModelAndView saveSchoolProfile(@RequestParam CommonsMultipartFile pimage, @ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.saveSchoolProfile(pimage, schoolProfile);
	}
	
	
	@RequestMapping(value = "/savedSchoolProfile", method = RequestMethod.GET)
	public ModelAndView savedSchoolProfile()
	{
		return services.savedSchoolProfile();
	}
	

	@RequestMapping(value = "/editSchoolProfile", method = RequestMethod.POST)
	public ModelAndView editSchoolProfile(@ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.editSchoolProfile(schoolProfile);
	}
	
	
	@RequestMapping(value = "/viewSchoolProfile", method = RequestMethod.POST)
	public ModelAndView viewSchoolProfile(@ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.viewSchoolProfile(schoolProfile);
	}
	
	
	@RequestMapping(value = "/updateSchoolProfile", method = RequestMethod.POST)
	public ModelAndView updateSchoolProfile(@RequestParam CommonsMultipartFile pimage, @ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.updateSchoolProfile(pimage, schoolProfile);
	}
	
	
	@RequestMapping(value = "/updatedSchoolProfile", method = RequestMethod.GET)
	public ModelAndView updatedSchoolProfile()
	{
		return services.updatedSchoolProfile();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "getActiveSchoolWithAllSchool", method = RequestMethod.POST)
	public SchoolProfileJsonResponse getActiveSchoolWithAllSchool()
	{
		return services.getActiveSchoolWithAllSchool();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "changeActiveSchoolProfile", method = RequestMethod.POST)
	public String changeActiveSchoolProfile(@ModelAttribute SchoolProfileModel schoolProfile)
	{
		return services.changeActiveSchoolProfile(schoolProfile);
	}
	
	@RequestMapping(value = "updatePromoteSchoolData", method = RequestMethod.POST)
	public ModelAndView updatePromoteSchoolData(HttpServletRequest request) {
		return services.updatePromoteSchoolData(request);
	}
	
	
}
