package com.school.subadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
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
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin" })
public class SubAdminStudentFeeDetailsController 
{
	@Autowired
	private StudentFeeDetailsServices services;
	
	
	/*@RequestMapping(value = "/searchStudentFeeDetails", method = RequestMethod.GET)
	public ModelAndView searchStudentFeeDetails()
	{
		return services.searchStudentFeeDetails();
	}*/
	
	
	@RequestMapping(value = "/addStudentFeeDetails", method = RequestMethod.POST)
	public ModelAndView addStudentFeeDetails(HttpServletRequest request)
	{
		return services.addStudentFeeDetails(request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saveStudentFeeDetails", method = RequestMethod.POST)
	public JSONObject saveStudentFeeDetails(@ModelAttribute StudentFeeDetailsModel studentFeeDetails, HttpServletRequest request)
	{
		return services.saveStudentFeeDetails(request, studentFeeDetails);
	}
	
	
	/*@RequestMapping(value = "/resultClassFeeDetails", method = RequestMethod.POST)
	public ModelAndView resultClassFeeDetails(HttpServletRequest request)
	{
		return services.resultClassFeeDetails(request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchClassFeeDetails", method = RequestMethod.GET)
	public List<StudentDetailsJsonModel> fetchClassFeeDetails(@ModelAttribute StudentFeeDetailsModel feeDetails, HttpServletRequest request)
	{
		return services.fetchClassFeeDetails(feeDetails, request);
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
	

	@ResponseBody
	@RequestMapping(value = "/generateClassFeeDetailsReport", method = RequestMethod.POST)
	public void generateClassFeeDetailsReport(@ModelAttribute StudentFeeDetailsModel studentFeeDetails, HttpServletRequest request)
	{
		services.generateClassFeeDetailsReport("D", studentFeeDetails, request);
	}


	@ResponseBody
	@RequestMapping(value = "/sendFeeReceiptOnEmail", method = RequestMethod.POST)
	public JSONObject sendFeeReceiptOnEmail(@ModelAttribute StudentFeeDetailsModel feeDetails, HttpServletRequest request){
		return services.sendFeeReceiptOnEmail(feeDetails, request);
	}*/
}
