package com.school.supadmin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.ServerLogServices;
import com.school.json.model.ServerLogJsonModel;

@Controller
@RequestMapping(value = "/supadmin")
public class SupAdminServerLogController 
{
	@Autowired
	private ServerLogServices services;
	
	
	@RequestMapping(value = "/getServerLog", method = RequestMethod.GET)
	public ModelAndView getServerLog()
	{
		return services.getServerLog();
	}
	
	
	@RequestMapping(value = "/downloadServerLog", method = RequestMethod.POST)
	public void downloadLog(HttpServletRequest request)
	{
		services.downloadLog(request);
	}
	
	
	@RequestMapping(value = "/resultServerHistoryLogs", method = RequestMethod.GET)
	public String resultServerHistoryLogs()
	{
		return "supadmin/resultServerHistoryLogs";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchServerHistoryLogs", method = RequestMethod.GET)
	public List<ServerLogJsonModel> fetchServerHistoryLogs()
	{
		return services.fetchServerHistoryLogs();
	}
	
}
