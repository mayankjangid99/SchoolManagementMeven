package com.school.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.CommonServices;
import com.school.json.model.ClassFeeCategoryJsonModel;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user" })
public class CommonController {
	
	@Autowired
	private CommonServices services;

	@ResponseBody
	@RequestMapping(value = "/getAdditionaFeeCategories", method = RequestMethod.POST)
	public ResponseEntity<List<ClassFeeCategoryJsonModel>> getAdditionaFeeCategories() {
		return services.getAdditionaFeeCategories();
	}
	
	@RequestMapping(value = "/rd", method = RequestMethod.GET)
	public ModelAndView redirectPage() {
		return services.redirectPage();
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectClassSectionModal", method = RequestMethod.POST)
	public ModelAndView selectClassSectionModal(HttpServletRequest request){
		return services.selectClassSectionModal(request);
	}
}
