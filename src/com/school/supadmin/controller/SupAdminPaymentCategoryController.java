package com.school.supadmin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.PaymentCategoryModel;
import com.school.common.model.SchoolPaymentCategoryModel;
import com.school.common.services.PaymentCategoryServices;
import com.school.json.model.SchoolPaymentCategoryJsonModel;

@Controller
@RequestMapping(value= "/supadmin")
public class SupAdminPaymentCategoryController 
{

	@Autowired
	private PaymentCategoryServices services;
	
	
	@RequestMapping(value = "/searchPaymentCategory", method = RequestMethod.GET)
	public ModelAndView searchPaymentCategory()
	{
		return services.searchPaymentCategory();
	}
	
	
	@RequestMapping(value = "/resultPaymentCategory", method = RequestMethod.POST)
	public ModelAndView resultPaymentCategory(HttpServletRequest request)
	{
		return services.resultPaymentCategory(request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchAllocatedPaymentCategory", method = RequestMethod.GET)
	public List<SchoolPaymentCategoryJsonModel> fetchAllocatedPaymentCategory(@ModelAttribute PaymentCategoryModel paymentCategory){
		return services.fetchAllocatedPaymentCategory(paymentCategory);
	}
	
	
	@RequestMapping(value = "/addPaymentCategory", method = RequestMethod.GET)
	public ModelAndView addPaymentCategory()
	{
		return services.addPaymentCategory();
	}
	
	
	@RequestMapping(value = "/savePaymentCategory", method = RequestMethod.POST)
	public ModelAndView savePaymentCategory(PaymentCategoryModel paymentCategory)
	{
		return services.savePaymentCategory(paymentCategory);
	}
	
	
	@RequestMapping(value = "/savedPaymentCategory", method = RequestMethod.GET)
	public ModelAndView savedPaymentCategory()
	{
		return services.savedPaymentCategory();
	}
	
	@RequestMapping(value = "/editPaymentCategory", method = RequestMethod.POST)
	public ModelAndView editPaymentCategory(@ModelAttribute PaymentCategoryModel paymentCategory)
	{
		return services.editPaymentCategory(paymentCategory);
	}
	
	@RequestMapping(value = "/updatePaymentCategory", method = RequestMethod.POST)
	public ModelAndView updatePaymentCategory(@ModelAttribute PaymentCategoryModel paymentCategory)
	{
		return services.updatePaymentCategory(paymentCategory);
	}
	
	@RequestMapping(value = "/updatedPaymentCategory", method = RequestMethod.GET)
	public ModelAndView updatedPaymentCategory()
	{
		return services.updatedPaymentCategory();
	}
	
	@RequestMapping(value = "/deletePaymentCategory", method = RequestMethod.POST)
	public ModelAndView deletePaymentCategory(@ModelAttribute PaymentCategoryModel paymentCategory)
	{
		return services.deletePaymentCategory(paymentCategory);
	}
	
	@RequestMapping(value = "/deletedPaymentCategory", method = RequestMethod.GET)
	public ModelAndView deletedPaymentCategory()
	{
		return services.deletedPaymentCategory();
	}
	
	
	@RequestMapping(value = "/saveAllocatePaymentCategory", method = RequestMethod.POST)
	public ModelAndView saveAllocatePaymentCategory(@ModelAttribute PaymentCategoryModel paymentCategory){
		return services.saveAllocatePaymentCategory(paymentCategory);
	}
	
	
	@RequestMapping(value = "/savedAllocatePaymentCategory", method = RequestMethod.GET)
	public ModelAndView savedAllocatePaymentCategory(){
		return services.savedAllocatePaymentCategory();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteAllocatedPaymentCategory", method = RequestMethod.POST)
	public boolean deleteAllocatedPaymentCategory(@ModelAttribute SchoolPaymentCategoryModel schoolPaymentCategory){
		return services.deleteAllocatedPaymentCategory(schoolPaymentCategory);
	}
}
