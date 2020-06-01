package com.school.staff.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.ClassInformationModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.services.StudentAttendanceServices;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff" })
public class StaffStudentAttendanceController 
{
	@Autowired
	private StudentAttendanceServices services;
	
	
	@RequestMapping(value = "/searchMarkAttendance")
	public String gotoSerachMarkAttendance()
	{
		return "admin/searchMarkAttendance";
	}
	
	@RequestMapping(value = "/addMarkAttendance", method = RequestMethod.POST)
	public ModelAndView addMarkAttendance(HttpServletRequest request)
	{
		ModelAndView model = services.resultAllStudentDetailsForAttendance(request);
		return model;
	}
	
	@RequestMapping(value = "/saveMarkAttendance", method = RequestMethod.POST)
	public ModelAndView saveAttendance(HttpServletRequest request, @ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		ModelAndView model = services.saveMarkAttendance(request, classInformation, sectionInformation);
		return model;
	}
	
	@RequestMapping(value = "/savedMarkAttendance", method = RequestMethod.GET)
	public ModelAndView savedAttendance()
	{
		ModelAndView model = new ModelAndView("admin/searchMarkAttendance");
		model.addObject("SuccessMessage", "Attendance Information Successfully Saved ...!!!");
		return model;
	}

	@RequestMapping(value = "/editMarkAttendance", method = RequestMethod.POST)
	public ModelAndView editAttendanceDetails(HttpServletRequest request)
	{
		ModelAndView model = services.resultAttendanceDetails(request);
		model.setViewName("admin/editMarkAttendance");
		return model;
	}
	
	@RequestMapping(value = "/updateMarkAttendance", method = RequestMethod.POST)
	public ModelAndView updateAttendance(HttpServletRequest request, @ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		ModelAndView model = services.updateMarkAttendance(request, classInformation, sectionInformation);
		return model;
	}
	
	@RequestMapping(value = "/updatedMarkAttendance", method = RequestMethod.GET)
	public ModelAndView updatedAttendance()
	{
		ModelAndView model = new ModelAndView("admin/searchMarkAttendance");
		String msg = "Attendance Information Successfully Updated ...!!!";
		model.addObject("SuccessMessage", msg);
		return model;
	}
	
	
	@RequestMapping(value = "/searchAttendanceDetails")
	public String gotoSerachAttendanceDetails()
	{
		return "admin/searchAttendanceDetails";
	}
	
	
	@RequestMapping(value = "/resultAttendanceDetails", method = RequestMethod.POST)
	public ModelAndView resultAttendanceDetails(HttpServletRequest request)
	{
		return services.resultAttendanceDetails(request);
	}
		

	@RequestMapping(value = "/downloadBulkMarkAttendance", method = RequestMethod.POST)
	public void downloadBulkMarkAttendance(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ClassInformationModel classInformation, 
			@ModelAttribute SectionInformationModel sectionInformation)
	{		
		services.downloadBulkMarkAttendance(request, response, classInformation, sectionInformation);
	}
	
	
	@RequestMapping(value = "/uploadBulkMarkAttendance", method = RequestMethod.POST)
	public ModelAndView uploadBulkMarkAttendance(HttpServletRequest request, HttpServletResponse response, @RequestParam CommonsMultipartFile bulkFile)
	{
		return services.uploadBulkMarkAttendance(request, response, bulkFile);
	}
	
	
	@RequestMapping(value = "/uploadedBulkMarkAttendance", method = RequestMethod.GET)
	public ModelAndView uploadedBulkMarkAttendance()
	{
		ModelAndView model = new ModelAndView("admin/searchMarkAttendance");
		model.addObject("SuccessMessage", "Builk Attendance Information Successfully Updated ...!!!");
		return model;
	}
	
	

	
	
	

	/*
	@RequestMapping(value = "/searchStudentAttendanceDetails")
	public String searchStudentAttendanceDetails()
	{
		return "staff/searchStudentAttendanceDetails";
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
	}*/
}
