package com.school.staff.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.ClassInformationModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.services.StudentDetailsServices;
import com.school.json.response.StudentDetailsJsonResponse;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff" })
public class StaffStudentDetailsController 
{

	@Autowired
	private StudentDetailsServices services;
	
	
	@RequestMapping(value = "/searchStudentDetails")
	public ModelAndView searchStudentDetailsPage()
	{
		return services.searchStudentDetailsPage();
	}

//	Result of All Student Details
	@RequestMapping(value = "/resultStudentDetails", method = RequestMethod.POST)
	public ModelAndView resultStudentDetails()
	{
		return services.resultStudentDetails();
	}
	
	
//	Fetch All Student Details on basis of school session, class name, sesction name 
	@ResponseBody
	@RequestMapping(value = "/fetchAllStudentDetails")
	public StudentDetailsJsonResponse fetchAllStudentsList(HttpServletRequest request)
	{
		StudentDetailsJsonResponse studentDetailsJsonResponse = services.fetchAllStudentsList(request);
		return studentDetailsJsonResponse;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getStudentsDetailsInAutocomplete", method = RequestMethod.GET)
	public JSONArray getStudentsDetailsInAutocomplete(HttpServletRequest request, @ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		JSONArray jsonResponse = services.getStudentsDetailsInAutocomplete(request, classInformation, sectionInformation);
		return jsonResponse;
	}
	
	
//	View Student Details
	/*@RequestMapping(value = "/viewStudentDetails", method = RequestMethod.POST)
	public ModelAndView viewStudentDetails(HttpServletRequest request)
	{
		ModelAndView model = services.viewStudentDetails(request);
		return model;
	}*/

}
