package com.school.staff.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.StudentFeeDetailsModel;
import com.school.common.services.StudentReportCardServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff" })
public class StaffStudentReportCardController {

	@Autowired
	private StudentReportCardServices services;
	
	@RequestMapping(value = "/searchStudentReportCard", method = RequestMethod.GET)
	public ModelAndView searchStudentReportCard(){
		return services.searchStudentReportCard();
	}
	
	@RequestMapping(value = "/addStudentReportCard", method = RequestMethod.POST)
	public ModelAndView addStudentReportCard(HttpServletRequest request){
		return services.addStudentReportCard(request);
	}

	@RequestMapping(value = "/saveStudentReportCard", method = RequestMethod.POST)
	public ModelAndView saveStudentReportCard(HttpServletRequest request) {
		return services.saveStudentReportCard(request);
	}
	
	@ResponseBody
	@RequestMapping(value = "/generateStudentReportCard", method = RequestMethod.POST)
	public void generateStudentReportCard(HttpServletRequest request) {
		services.generateStudentReportCard(request);
	}
}
