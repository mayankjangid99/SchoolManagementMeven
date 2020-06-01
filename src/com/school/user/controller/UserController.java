package com.school.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.model.UserSettingModel;
import com.school.common.services.UserServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff", "/user" })
public class UserController 
{
	@Autowired
	private UserServices services;
	
	@ResponseBody
	@RequestMapping(value = "/validateUsernameForNewUser", method = RequestMethod.GET)
	public boolean validateUsernameForNewUser(HttpServletRequest request)
	{
		return services.validateUsernameForNewUser(request);
	}
	
	
	/*@RequestMapping(value = "/saveSupAdminDetails", method = RequestMethod.POST)
	public String saveSupAdminDetails(@RequestParam CommonsMultipartFile pimage, @RequestParam String pactive, @ModelAttribute UserModel users, @ModelAttribute UserRoleModel usersRole)
	{
		try {
			users.setUserRole(usersRole);
			services.saveUser(pimage, pactive, users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:savedSupAdminDetails";
	}
	
	@RequestMapping(value = "/savedSupAdminDetails", method = RequestMethod.GET)
	public ModelAndView savedSupAdminDetails()
	{
		ModelAndView model = new ModelAndView("supadmin/addSupAdminDetails");
		model.addObject("SuccessMessage", "Super Admin Information Successfully saved... !!!");
		return model;
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/changeUserTheme", method = RequestMethod.GET)
	public String changeUserTheme(@ModelAttribute UserSettingModel userSetting){
		return services.changeUserTheme(userSetting);	
	}
	
}
