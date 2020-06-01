package com.school.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.common.model.ClassInformationModel;
import com.school.common.services.ClassSectionServices;
import com.school.json.response.ClassSectionJsonResponse;

@Controller
@RequestMapping(value = "{*}")
public class ClassSectionController 
{
	@Autowired
	private ClassSectionServices service;
	
	@ResponseBody
	@RequestMapping(value="/getAllClasses")
	public ClassSectionJsonResponse getClassList(HttpServletRequest request)
	{
		ClassSectionJsonResponse jsonResponse = service.getClassList(request);
		return jsonResponse;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getSections")
	public ClassSectionJsonResponse getSectionList(@ModelAttribute ClassInformationModel classInformation)
	{
		ClassSectionJsonResponse jsonResponse = service.getSectionList(classInformation);
		return jsonResponse;
	}
	
	
	
	
	
}
