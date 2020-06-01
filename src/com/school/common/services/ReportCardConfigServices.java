package com.school.common.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.ReportCardConfigDao;
import com.school.common.generic.ServicesStaticValue;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticData;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ReportCardConfigModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.services.business.ReportCardConfigBusiness;
import com.school.json.model.ReportCardConfigJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportCardConfigServices extends ServicesStaticValue {
	
	private static Logger LOG = LoggerFactory.getLogger(ReportCardConfigServices.class);
	
	@Autowired
	private MessageSource messageSource;	
	
	@Autowired
	private ReportCardConfigDao reportCardConfigDao;
	
	@Autowired
	private ReportCardConfigBusiness reportCardConfigBusiness;
	

	public ModelAndView searchReportCardConfig() {
		ModelAndView model = new ModelAndView("supadmin/searchReportCardConfig");
		model.addObject("reportCardTermData", StaticData.getReportCardTermData());
		model.addObject("reportCardConfig", new ReportCardConfigModel());
		return model;
	}
	
	public ModelAndView addReportCardConfig() {
		ModelAndView model = new ModelAndView("supadmin/addReportCardConfig");
		model.addObject("reportCardTermData", StaticData.getReportCardTermData());
		model.addObject("reportCardConfig", new ReportCardConfigModel());
		return model;
	}
	
	public ModelAndView saveReportCardConfig(ReportCardConfigModel reportCardConfig, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("supadmin/addReportCardConfig");
		
		ReportCardConfigModel reportCardConfigTemp = null;
		String opt = request.getParameter(OPT);
		model.addObject(OPT, opt);
		model.addObject("reportCardTermData", StaticData.getReportCardTermData());
		model.addObject("reportCardConfig", reportCardConfig);
		
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		try {
			
			SchoolProfileModel schoolProfile = new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode());
			reportCardConfig.setSchoolProfile(schoolProfile);
			
			SessionDetailsModel sessionDetails = new SessionDetailsModel();
			sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
			reportCardConfig.setSessionDetails(sessionDetails);
			if(classCode != null && !classCode.isEmpty()) {
				ClassInformationModel classInformationModel = new ClassInformationModel();
				classInformationModel.setClassCode(classCode);
				reportCardConfig.setClassInformation(classInformationModel);
			}
			if(sectionCode != null && !sectionCode.isEmpty()) {
				SectionInformationModel sectionInformationModel = new SectionInformationModel();
				sectionInformationModel.setSectionCode(sectionCode);
				reportCardConfig.setSectionInformation(sectionInformationModel);
			}
			if(E.equals(opt)) {
				reportCardConfigDao.update(reportCardConfig);
				model.addObject("SuccessMessage", "Report Card Config successfully saved...!!!");
			} else {
				reportCardConfigTemp = getReportCardConfig(reportCardConfig.getConfigCode(), classCode, sectionCode);
				if(reportCardConfigTemp != null) {
					model.addObject("ErrorMessage", "Report Card Config already configured...!!!");
					return model;
				}
				reportCardConfigDao.persist(reportCardConfig);
				model.addObject("SuccessMessage", "Report Card Config successfully updated...!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: saveReportCardConfig() in ReportCardConfigServices ", e);
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		reportCardConfigTemp = getReportCardConfig(reportCardConfig.getConfigCode(), classCode, sectionCode);
		model.addObject("reportCardConfig", reportCardConfigTemp);
		return model;
	}
	

	public ModelAndView editReportCardConfig(ReportCardConfigModel reportCardConfig, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("supadmin/addReportCardConfig");
		model.addObject("reportCardTermData", StaticData.getReportCardTermData());
		try {			
			String classCode = request.getParameter("classCode");
			String sectionCode = request.getParameter("sectionCode");
			
			SchoolProfileModel schoolProfile = new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode());
			reportCardConfig.setSchoolProfile(schoolProfile);
			reportCardConfig = getReportCardConfig(reportCardConfig.getConfigCode(), classCode, sectionCode);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: editReportCardConfig() in ReportCardConfigServices ", e);
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		model.addObject("reportCardConfig", reportCardConfig);
		model.addObject(OPT, E);
		return model;
	}
	
	public ModelAndView resultReportCardConfig() {
		ModelAndView model = new ModelAndView("supadmin/resultReportCardConfig");
		model.addObject("reportCardTermData", StaticData.getReportCardTermData());
		return model;
	}
	
	
	public List<ReportCardConfigJsonModel> fetchAllReportCardConfig(HttpServletRequest request) {
		String configCode = request.getParameter("configCode");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		List<ReportCardConfigModel> list = reportCardConfigDao.getAllReportCardConfig(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), configCode, classCode, sectionCode);
		List<ReportCardConfigJsonModel> reportCardConfigList = reportCardConfigBusiness.fetchAllReportCardConfig(list);
		return reportCardConfigList;
	}
	
	public ReportCardConfigModel getReportCardConfig(String configCode, String classCode, String sectionCode) {
		return (ReportCardConfigModel) reportCardConfigDao.getReportCardConfig(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), configCode, classCode, sectionCode);
	}
}
