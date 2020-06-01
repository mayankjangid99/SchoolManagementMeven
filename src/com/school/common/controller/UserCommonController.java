package com.school.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.services.UserServices;

@Controller
@RequestMapping(value = "{*}")
public class UserCommonController {

	@Autowired
	private UserServices services;
	
	@ResponseBody
	@RequestMapping(value="/getUsersDetailsInAutocomplete", method = RequestMethod.GET)
	public JSONArray getUsersDetailsInAutocomplete(HttpServletRequest request) {
		JSONArray jsonResponse = services.getUsersDetailsInAutocomplete(request);
		return jsonResponse;
	}
}
