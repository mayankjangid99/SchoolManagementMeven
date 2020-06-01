package com.school.supadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.ParentDetailsModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.model.UserSettingModel;
import com.school.common.services.UserServices;

@Controller
public class SupAdminSuperAdminController 
{
	@Autowired
	private UserServices services;
	
	@RequestMapping(value = "/IAmMayankJangid")
	public String addSuperAdminUser()
	{
		return "supadmin/addSupAdminDetails";
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateUsernameForNewUser", method = RequestMethod.GET)
	public boolean validateUsernameForNewUser(HttpServletRequest request)
	{
		return services.validateUsernameForNewUser(request);
	}
	
	@RequestMapping(value = "/saveSupAdminDetails", method = RequestMethod.POST)
	public String saveSupAdminDetails(HttpServletRequest request, @RequestParam CommonsMultipartFile pimage, @RequestParam String pactive, @ModelAttribute UserRoleModel usersRole, @ModelAttribute UserModel users,  
			@ModelAttribute UserDetailsModel userDetails, @ModelAttribute UserSettingModel userSetting) {
		services.saveUser(request, pimage, pactive, usersRole, users, userDetails, userSetting, new ParentDetailsModel());
		return "redirect:savedSupAdminDetails";
	}
	
	@RequestMapping(value = "/savedSupAdminDetails", method = RequestMethod.GET)
	public ModelAndView savedSupAdminDetails()
	{
		ModelAndView model = new ModelAndView("supadmin/addSupAdminDetails");
		model.addObject("SuccessMessage", "Super Admin Information Successfully saved... !!!");
		return model;
	}
}
