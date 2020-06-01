package com.school.admin.controller;

import java.util.List;

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
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.model.UserSettingModel;
import com.school.common.services.UserServices;
import com.school.json.model.UserJsonModel;

@Controller
@RequestMapping(value = {"/supadmin", "/admin"})
public class AdminUserController 
{
	@Autowired
	private UserServices services;
	
	
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public ModelAndView searchUser(HttpServletRequest request)
	{
		return services.searchUser(request);
	}
	
	@RequestMapping(value = "/resultUser", method = RequestMethod.POST)
	public ModelAndView resultUser(HttpServletRequest request)
	{
		return services.resultUser(request);
	}
	

	
	@ResponseBody
	@RequestMapping(value = "fetchUser", method = RequestMethod.GET)
	public List<UserJsonModel> fetchUser(HttpServletRequest request, @ModelAttribute UserModel user, @ModelAttribute UserRoleModel userRole, 
			@ModelAttribute UserDetailsModel userDetails, @ModelAttribute SchoolProfileModel schoolProfile, @ModelAttribute ParentDetailsModel parentDetails)
	{
		return services.fetchUser(request, user, userRole, userDetails, schoolProfile, parentDetails);
	}
	
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser()
	{
		return services.addUser();
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute UserModel user)
	{
		return services.editUser(user);
	}
	
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(HttpServletRequest request, @RequestParam CommonsMultipartFile pimage, @RequestParam String pactive, @ModelAttribute UserRoleModel usersRole, @ModelAttribute UserModel users,  
			@ModelAttribute UserDetailsModel userDetails, @ModelAttribute UserSettingModel userSetting, @ModelAttribute ParentDetailsModel parentDetails)
	{
		return services.saveUser(request, pimage, pactive, usersRole, users, userDetails, userSetting, parentDetails);
	}
	
	@RequestMapping(value = "/savedUser", method = RequestMethod.GET)
	public ModelAndView savedUser()
	{
		return services.savedUser();
	}
	
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, @RequestParam CommonsMultipartFile pimage, @RequestParam String pactive, @ModelAttribute UserRoleModel usersRole, @ModelAttribute UserModel users,  
			@ModelAttribute UserDetailsModel userDetails, @ModelAttribute UserSettingModel userSetting, @ModelAttribute ParentDetailsModel parentDetails)
	{
		return services.updateUser(request, pimage, pactive, usersRole, users, userDetails, userSetting, parentDetails);
	}
	
	
	
	@RequestMapping(value = "/updatedUser", method = RequestMethod.GET)
	public ModelAndView updatedUser()
	{
		return services.updatedUser();
	}
	

	@ResponseBody
	@RequestMapping(value = "/changeUserActive", method = RequestMethod.GET)
	public String changeUserActive(HttpServletRequest request, @ModelAttribute UserModel user)
	{
		return services.changeUserActive(request, user);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getParentUserDetailsId", method = RequestMethod.POST)
	public String getParentUserDetailsId(HttpServletRequest request)
	{
		return services.getParentUserDetailsId(request);
	}
	
}
