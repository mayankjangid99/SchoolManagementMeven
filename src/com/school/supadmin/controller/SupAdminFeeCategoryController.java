package com.school.supadmin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.FeeCategoryModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.services.FeeCategoryServices;
import com.school.json.model.FeeCategoryJsonModel;
import com.school.json.response.FeeCategoryJsonResponse;

@Controller
@RequestMapping(value = "supadmin")
public class SupAdminFeeCategoryController 
{

	@Autowired
	private FeeCategoryServices services;
	
	
	@RequestMapping(value = "/searchFeeCategory", method = RequestMethod.GET)
	public ModelAndView searchFeeCategory()
	{
		return services.searchFeeCategory();
	}
	
	@RequestMapping(value = "/resultFeeCategory", method = RequestMethod.POST)
	public ModelAndView resultFeeCategory(HttpServletRequest request)
	{
		return services.resultFeeCategory(request);
	}
	
	
	@RequestMapping(value = "/addFeeCategory", method = RequestMethod.GET)
	public ModelAndView addFeeCategory()
	{
		return services.addFeeCategory();
	}
	

	@RequestMapping(value = "/saveFeeCategory", method = RequestMethod.POST)
	public ModelAndView saveFeeCategory(@ModelAttribute FeeCategoryModel feeCategory)
	{
		return services.saveFeeCategory(feeCategory);
	}
	
	@RequestMapping(value = "/savedFeeCategory", method = RequestMethod.GET)
	public ModelAndView savedFeeCategory()
	{
		return services.savedFeeCategory();
	}
	
	
	@RequestMapping(value = "/editFeeCategory", method = RequestMethod.POST)
	public ModelAndView editFeeCategory(@ModelAttribute FeeCategoryModel feeCategory)
	{
		return services.editFeeCategory(feeCategory);
	}
	
	
	@RequestMapping(value = "/updateFeeCategory", method = RequestMethod.POST)
	public ModelAndView updateFeeCategory(@ModelAttribute FeeCategoryModel feeCategory)
	{
		return services.updateFeeCategory(feeCategory);
	}
	
	
	@RequestMapping(value = "/updatedFeeCategory", method = RequestMethod.GET)
	public ModelAndView updatedFeeCategory()
	{
		return services.updatedFeeCategory();
	}
	
	
	
	@RequestMapping(value = "/deleteFeeCategory", method = RequestMethod.POST)
	public ModelAndView deleteFeeCategory(FeeCategoryModel feeCategory)
	{
		return services.deleteFeeCategory(feeCategory);
	}
	
	
	@RequestMapping(value = "/deletedFeeCategory", method = RequestMethod.GET)
	public ModelAndView deletedFeeCategory()
	{
		return services.deletedFeeCategory();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchFeeCategoryByClassForAllocateToClass", method = RequestMethod.GET)
	public Map<String, List<FeeCategoryJsonModel>> fetchFeeCategoryByClassForAllocateToClass(@ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation, @ModelAttribute ClassFeeCategoryModel classFeeCategory)
	{
		return services.fetchFeeCategoryByClassForAllocateToClass(classInformation, sectionInformation, classFeeCategory);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchFeeCategoryWithClass", method = RequestMethod.GET)
	public FeeCategoryJsonResponse fetchFeeCategoryWithClass(@ModelAttribute FeeCategoryModel feeCategory, @ModelAttribute ClassFeeCategoryModel classFeeCategory, @ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		return services.fetchFeeCategoryWithClass(classInformation, sectionInformation, feeCategory, classFeeCategory);
	}

	
	
	@ResponseBody
	@RequestMapping(value = "/deleteAllocatedFeeCategory", method = RequestMethod.POST)
	public String deleteAllocatedFeeCategory(HttpServletRequest request)
	{
		return services.deleteAllocatedFeeCategory(request);
	}
	
	
	
	@RequestMapping(value = "/saveAllocatedFeeCategoryToClassSection", method = RequestMethod.POST)
	public ModelAndView saveAllocatedFeeCategoryToClass(HttpServletRequest request)
	{
		return services.saveAllocatedFeeCategoryToClassSection(request);
	}
	
	@RequestMapping(value = "/savedAllocatedFeeCategoryToClassSection", method = RequestMethod.GET)
	public ModelAndView savedAllocatedFeeCategoryToClassSection()
	{
		return services.savedAllocatedFeeCategoryToClassSection();
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeAllocatedFeeCategoryStatus", method = RequestMethod.POST)
	public String changeAllocatedFeeCategoryStatus()
	{
		return services.changeAllocatedFeeCategoryStatus();
	}


	/*@ResponseBody
	@RequestMapping(value = "/getAdditionaFeeCategories", method = RequestMethod.POST)
	public ResponseEntity<List<ClassFeeCategoryJsonModel>> getAdditionaFeeCategories() {
		return services.getAdditionaFeeCategories();
	}*/
}
