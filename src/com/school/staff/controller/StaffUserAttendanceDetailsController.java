package com.school.staff.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.UserAttendanceServices;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "subadmin", "staff"} )
public class StaffUserAttendanceDetailsController 
{
	
	@Autowired
	private UserAttendanceServices services;
	
	
	
	@RequestMapping(value = "/searchUserAttendanceDetails")
	public String searchUserAttendanceDetails()
	{
		return "staff/searchUserAttendanceDetails";
	}
	
	@RequestMapping(value = "/resultUserAttendanceDetails", method = RequestMethod.POST)
	public ModelAndView resultUserAttendanceDetails(HttpServletRequest request)
	{
		return services.resultUserAttendanceDetails(request);
	}
	
	@RequestMapping(value = "/fetchUserAttendanceDetails")
	public @ResponseBody AttendanceCalendarJsonResponse fetchUserAttendanceDetails(HttpServletRequest request)
	{
		return services.fetchUserAttendanceDetails(request);
	}
	
	
	@RequestMapping(value = "/fetchUserAttendanceYearly")
	public @ResponseBody AttendanceBarChartJsonResponse fetchUserAttendanceYearly(HttpServletRequest request)
	{
		return services.fetchUserAttendanceYearly(request);
	}
	
	
}
