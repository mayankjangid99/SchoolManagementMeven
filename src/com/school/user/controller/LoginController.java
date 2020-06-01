package com.school.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.UserModel;
import com.school.common.services.LoginServices;

@Controller
public class LoginController 
{
	@Autowired
	private LoginServices services;
	
	
	/*@RequestMapping(value = {"/", "/indexPage"})
	public String index(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("index");
		return "redirect:/loginDefault";
	}*/
	
	
	/*
	 * Don't Remove /login otherwise if session expire then redirect on it.
	 */	
	@RequestMapping(value = { "/login", "/loginDefault" })
	public ModelAndView loginDefault(HttpServletRequest request, @CookieValue("SMUser") String cookieVal)
	{
		System.out.println("loginDefault");
		return services.loginDefault(request, cookieVal);
	}
	
	
	/*@RequestMapping(value="/login")
	public String gotoLogin2()
	{
		return "redirect:/loginDefault";
	}*/
	
	
	/*@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String loginWelcome(Principal principal) 
	{
//		System.out.println(principal.getName());
		return "redirect:/login";
	}*/
	
	@RequestMapping(value="/alreadyLogin")
	public ModelAndView alreadyLogin(HttpServletRequest request)
	{
		ModelAndView result = services.alreadyLogin(request);
		return result;
	}
	
	
	@RequestMapping(value="/welcome")
	public ModelAndView loginAdmin(HttpServletRequest request)
	{
		ModelAndView result = services.loginUser(request);
		return result;
	}

	@RequestMapping(value="*/welcome")
	public String gotoHomePage()
	{
		return "user/welcome";
	}

	@RequestMapping(value="/failedLogin", method = RequestMethod.GET)
	public ModelAndView loginerror(HttpServletRequest request, @CookieValue("SMUser") String cookieVal) {
		ModelAndView model = services.loginDefault(request, cookieVal);
		model.addObject("error", "true");
		model.setViewName("login");
		return model;
 
	}
	
	
	
	@RequestMapping(value={"/logout", "*/logout"})
	public String logout(HttpServletRequest request)
	{
		System.out.println("Logout");
		services.logout(request.getSession());
		return "redirect:/loginDefault";
	}
	
	
	
	@RequestMapping(value="/encrypt")
	public String encrypt(HttpServletRequest request)
	{
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(cryptPasswordEncoder.encode(request.getParameter("pwd")));
		return "redirect:/index";
	}
	
	
	@ResponseBody
	@RequestMapping(value= "/forgotPassword", method = RequestMethod.GET)
	public JSONObject forgotPassword(HttpServletRequest request)
	{
		return services.forgotPassword(request);
	}
	
	
	@RequestMapping(value = "/changeForgotPassword", method = RequestMethod.GET)
	public ModelAndView changeForgotPassword(HttpServletRequest request)
	{
		return services.changeForgotPassword(request);
	}
	
	
	@RequestMapping(value = "*/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(HttpServletRequest request)
	{
		return services.changePassword(request);
	}
		
	
	@RequestMapping(value = {"/updatePassword","*/updatePassword" }, method = RequestMethod.POST)
	public ModelAndView updatePassword(HttpServletRequest request, @ModelAttribute UserModel user)
	{
		return services.updatePassword(request, user);
	}
	
	
	@RequestMapping(value = "/updatedPassword", method = RequestMethod.GET)
	public ModelAndView updatedPassword(HttpServletRequest request)
	{
		return services.updatedPassword(request);
	}
	
	
	@RequestMapping(value = "*/updatedPassword", method = RequestMethod.GET)
	public ModelAndView updatedPasswordByAnyUser(HttpServletRequest request)
	{
		return services.updatedPasswordByAnyUser(request);
	}
	
	
}
