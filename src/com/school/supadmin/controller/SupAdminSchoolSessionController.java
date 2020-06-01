package com.school.supadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.services.SchoolSessionServices;
import com.school.json.model.SchoolSessionJsonModel;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminSchoolSessionController 
{
	@Autowired
	private SchoolSessionServices services;
	
	@RequestMapping(value = "/searchSchoolSession")
	public ModelAndView searchSchoolSession()
	{
		return services.searchSchoolSession();
	}
	
	
	@RequestMapping(value = "/resultSchoolSession")
	public String resultSchoolSession()
	{
		return "supadmin/resultSchoolSession";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchAllocatedSchoolSession", method = RequestMethod.GET)
	public List<SchoolSessionJsonModel> fetchAllocatedSchoolSession(@ModelAttribute SchoolSessionModel schoolSession)
	{
		return services.fetchAllocatedSchoolSession(schoolSession);
	}
	
	
	
	@RequestMapping(value = "/addSchoolSession")
	public ModelAndView addSchoolSession()
	{
		return services.addSchoolSession();
	}
	
	
	@RequestMapping(value = "/saveSessionDetails", method = RequestMethod.POST)
	public ModelAndView saveSessionDetails(@ModelAttribute SessionDetailsModel sessionDetails)
	{
		return services.saveSessionDetails(sessionDetails);
	}

	
	@RequestMapping(value = "/savedSessionDetails", method = RequestMethod.GET)
	public ModelAndView savedSessionDetails()
	{
		return services.savedSessionDetails();
	}	


	@RequestMapping(value = "/editSessionDetails", method = RequestMethod.POST)
	public ModelAndView editSessionDetails(@ModelAttribute SessionDetailsModel sessionDetails)
	{
		return services.editSessionDetails(sessionDetails);
	}
	
	
	@RequestMapping(value = "/updateSessionDetails", method = RequestMethod.POST)
	public ModelAndView updateSessionDetails(@ModelAttribute SessionDetailsModel sessionDetails)
	{
		return services.updateSessionDetails(sessionDetails);
	}
	
	
	@RequestMapping(value = "/updatedSessionDetails", method = RequestMethod.GET)
	public ModelAndView updatedSessionDetails()
	{
		return services.updatedSessionDetails();
	}
	
	
	@RequestMapping(value = "/deleteSessionDetails", method = RequestMethod.POST)
	public ModelAndView deleteSessionDetails(@ModelAttribute SessionDetailsModel sessionDetails)
	{
		return services.deleteSessionDetails(sessionDetails);
	}
	
	
	@RequestMapping(value = "/deletedSessionDetails", method = RequestMethod.GET)
	public ModelAndView deletedSessionDetails()
	{
		return services.deletedSessionDetails();
	}
	
	
	@RequestMapping(value = "/editSchoolSession", method = RequestMethod.POST)
	public ModelAndView editSchoolSession(@ModelAttribute SchoolSessionModel schoolSession)
	{
		return services.editSchoolSession(schoolSession);
	}
	
	
	@RequestMapping(value = "/saveAllocateSchoolSession", method = RequestMethod.POST)
	public ModelAndView saveAllocateSchoolSession(@ModelAttribute SchoolSessionModel schoolSession)
	{
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		schoolSession.setSchoolProfile(schoolProfile);
		return services.saveAllocateSchoolSession(schoolSession);
	}
	
	
	
	@RequestMapping(value = "/savedAllocateSchoolSession", method = RequestMethod.GET)
	public ModelAndView savedAllocateSchoolSession()
	{
		return services.savedAllocateSchoolSession();
	}

	
	@RequestMapping(value = "/updateAllocatedSchoolSession", method = RequestMethod.POST)
	public ModelAndView updateAllocatedSchoolSession(@ModelAttribute SchoolSessionModel schoolSession)
	{
		return services.updateAllocatedSchoolSession(schoolSession);
	}
	
	
	@RequestMapping(value = "/updatedAllocatedSchoolSession", method = RequestMethod.GET)
	public ModelAndView updatedAllocatedSchoolSession()
	{
		return services.updatedAllocatedSchoolSession();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteAllocatedSchoolSession", method = RequestMethod.POST)
	public boolean deleteAllocatedSchoolSession(@ModelAttribute SchoolSessionModel schoolSession)
	{
		return services.deleteAllocatedSchoolSession(schoolSession);
	}
	


}
