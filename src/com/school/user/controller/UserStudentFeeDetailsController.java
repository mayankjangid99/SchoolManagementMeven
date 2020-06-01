package com.school.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.StudentFeeDetailsModel;
import com.school.common.services.StudentFeeDetailsServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user" })
public class UserStudentFeeDetailsController 
{
	@Autowired
	private StudentFeeDetailsServices services;
		
	@RequestMapping(value = "/resultStudentFeeDetails", method = RequestMethod.POST)
	public ModelAndView resultStudentFeeDetails(HttpServletRequest request)
	{
		return services.resultStudentFeeDetails(request);
	}
	
	@ResponseBody
	@RequestMapping(value = "/generateFeeReceipt", method = RequestMethod.POST)
	public void generateFeeReceipt(@ModelAttribute StudentFeeDetailsModel feeDetails)
	{
		services.generateFeeReceipt("D", feeDetails);
	}
	
	@RequestMapping(value = "/getStudentFeeTransactionHistoryModal", method = RequestMethod.POST)
	public ModelAndView getStudentFeeTransactionHistoryModal(@ModelAttribute StudentFeeDetailsModel studentFeeDetails){
		return services.getStudentFeeTransactionHistoryModal(studentFeeDetails);
	}
	
	
}
