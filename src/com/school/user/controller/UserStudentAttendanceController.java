package com.school.user.controller;

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
import com.school.common.services.StudentAttendanceServices;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user" })
public class UserStudentAttendanceController 
{
	@Autowired
	private StudentAttendanceServices services;
	
	
	
	@RequestMapping(value = "/searchStudentAttendanceDetails")
	public String gotoSerachStudentAttendanceDetails()
	{
		return "user/searchStudentAttendanceDetails";
	}
	
	@RequestMapping(value = "/resultStudentAttendanceDetails", method = RequestMethod.POST)
	public ModelAndView resultStudentAttendanceDetails(HttpServletRequest request, @ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		return services.resultStudentAttendanceDetails(request);
	}
	
	@RequestMapping(value = "/fetchStudentAttendanceDetails")
	public @ResponseBody AttendanceCalendarJsonResponse fetchStudentAttendanceDetails(HttpServletRequest request)
	{
		return services.fetchStudentAttendanceDetails(request);
	}
	
	
	@RequestMapping(value = "/fetchStudentAttendanceYearly")
	public @ResponseBody AttendanceBarChartJsonResponse fetchStudentAttendanceYearly(HttpServletRequest request)
	{
		return services.fetchStudentAttendanceDetailsYearly(request);
	}
	
	

	
}
