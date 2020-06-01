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

import com.school.common.model.ReportCardConfigModel;
import com.school.common.services.ReportCardConfigServices;
import com.school.json.model.ReportCardConfigJsonModel;

@Controller
@RequestMapping(value ="/supadmin")
public class SupAdminReportCardConfigController {
	
	@Autowired
	private ReportCardConfigServices services;
	
	
	@RequestMapping(value = "/searchReportCardConfig", method = RequestMethod.GET)
	public ModelAndView searchReportCardConfig() {
		return services.searchReportCardConfig();
	}
	

	@RequestMapping(value = "/addReportCardConfig", method = RequestMethod.GET)
	public ModelAndView addReportCardConfig() {
		return services.addReportCardConfig();
	}
	

	
	@RequestMapping(value = "/saveReportCardConfig", method = RequestMethod.POST)
	public ModelAndView saveReportCardConfig(@ModelAttribute ReportCardConfigModel reportCardConfig, HttpServletRequest request) {
		return services.saveReportCardConfig(reportCardConfig, request);
	}
	

	@RequestMapping(value = "/editReportCardConfig", method = RequestMethod.POST)
	public ModelAndView editReportCardConfig(@ModelAttribute ReportCardConfigModel reportCardConfig, HttpServletRequest request) {
		return services.editReportCardConfig(reportCardConfig, request);
	}
	
	
	@RequestMapping(value = "/resultReportCardConfig", method = RequestMethod.POST)
	public ModelAndView resultReportCardConfig() {
		return services.resultReportCardConfig();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "fetchAllReportCardConfig", method = RequestMethod.GET)
	public List<ReportCardConfigJsonModel> fetchAllReportCardConfig(HttpServletRequest request) {
		return services.fetchAllReportCardConfig(request);
	}
}
