package com.school.common.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.StudentReportCardDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.facade.FacadeServicesManager;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ReportCardConfigModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.StudentReportCardModel;
import com.school.common.services.business.StudentReportCardBusiness;
import com.school.json.model.ClassSubjectJsonModel;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentReportCardServices {

	private static Logger LOG = LoggerFactory.getLogger(StudentReportCardServices.class);
	
	@Autowired
	private StudentReportCardDao studentReportCardDao;
	
	@Autowired
	private FacadeServicesManager facadeServicesManager;
	
	@Autowired
	private StudentReportCardBusiness studentReportCardBusiness;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	
	
	
	
	public ModelAndView searchStudentReportCard(){
		ModelAndView model = new ModelAndView("staff/searchStudentReportCard");
		return model;
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public ModelAndView addStudentReportCard(HttpServletRequest request){
		ModelAndView model = new ModelAndView("staff/addStudentReportCard");
		String classCode = request.getParameter("classCode");
		String className = request.getParameter("className");
		String sectionCode = request.getParameter("sectionCode");
		String sectionName = request.getParameter("sectionName");
		String fullName = request.getParameter("fullName");
		String admissionNo = request.getParameter("admissionNo");
		String rollNo = request.getParameter("rollNo");
		model.addObject("classCode", classCode);
		model.addObject("className", className);
		model.addObject("sectionCode", sectionCode);
		model.addObject("sectionName", sectionName);
		model.addObject("fullName", fullName);
		model.addObject("admissionNo", admissionNo);
		model.addObject("rollNo", rollNo);
		
		Map<String, List<ClassSubjectJsonModel>> mapData = facadeServicesManager.getSubjectServices().getSubjectDetailsForAllocateToClassSection(new ClassInformationModel(classCode), new SectionInformationModel(sectionCode), null);
		model.addObject("AllocatedSubjects", mapData.get("allocated"));
		ReportCardConfigModel unitTest = facadeServicesManager.getReportCardConfigServices().getReportCardConfig(StaticValue.REPORT_CARD_UNIT_TEST, classCode, sectionCode);
		ReportCardConfigModel term = facadeServicesManager.getReportCardConfigServices().getReportCardConfig(StaticValue.REPORT_CARD_TERM, classCode, sectionCode);
		Map<String, Map<String, Object>> fieldsData = studentReportCardBusiness.preparedReportCardFields(mapData.get("allocated"), term, unitTest, request);
		if(admissionNo != null && !admissionNo.isEmpty()) {
			List<StudentReportCardModel> reportCardData = studentReportCardDao.getStudentReportCardData(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, admissionNo);
			studentReportCardBusiness.preparedReportCardFieldsWithReportCardData(fieldsData, reportCardData);
		}
		model.addObject("fieldsData", fieldsData);
		model.addObject("unitTestObject", unitTest);
		model.addObject("termObject", term);
		return model;
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public ModelAndView saveStudentReportCard(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("staff/addStudentReportCard");

		String classCode = request.getParameter("classCode");
		String className = request.getParameter("className");
		String sectionCode = request.getParameter("sectionCode");
		String sectionName = request.getParameter("sectionName");
		String fullName = request.getParameter("fullName");
		String admissionNo = request.getParameter("admissionNo");
		String rollNo = request.getParameter("rollNo");
		model.addObject("classCode", classCode);
		model.addObject("className", className);
		model.addObject("sectionCode", sectionCode);
		model.addObject("sectionName", sectionName);
		model.addObject("fullName", fullName);
		model.addObject("admissionNo", admissionNo);
		model.addObject("rollNo", rollNo);

		Map<String, List<ClassSubjectJsonModel>> mapData = facadeServicesManager.getSubjectServices().getSubjectDetailsForAllocateToClassSection(new ClassInformationModel(classCode), new SectionInformationModel(sectionCode), null);
		model.addObject("AllocatedSubjects", mapData.get("allocated"));
		ReportCardConfigModel unitTest = facadeServicesManager.getReportCardConfigServices().getReportCardConfig(StaticValue.REPORT_CARD_UNIT_TEST, classCode, sectionCode);
		ReportCardConfigModel term = facadeServicesManager.getReportCardConfigServices().getReportCardConfig(StaticValue.REPORT_CARD_TERM, classCode, sectionCode);
		Map<String, Map<String, Object>> fieldsData = studentReportCardBusiness.preparedReportCardFields(mapData.get("allocated"), term, unitTest, request);
		model.addObject("fieldsData", fieldsData);
		List<StudentReportCardModel> objectList = studentReportCardBusiness.preparedReportCardObjects(fieldsData, classCode, sectionCode, admissionNo, rollNo);
		try {
			studentReportCardDao.executeUpdateHQLQuery("DELETE StudentReportCardModel c WHERE c.admissionNo=:p1", new Object[] {admissionNo});
			if(objectList != null && !objectList.isEmpty()) {
				for(StudentReportCardModel reportCard : objectList) {
					studentReportCardDao.saveOrUpdate(reportCard);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: saveStudentReportCard() in StudentReportCardServices ", e);
		}
		model.addObject("unitTestObject", unitTest);
		model.addObject("termObject", term);
		return model;
	}
	
	
	public void generateStudentReportCard(HttpServletRequest request) {

		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String admissionNo = request.getParameter("admissionNo");
		
		Map<String, List<ClassSubjectJsonModel>> mapData = facadeServicesManager.getSubjectServices().getSubjectDetailsForAllocateToClassSection(new ClassInformationModel(classCode), new SectionInformationModel(sectionCode), null);
		ReportCardConfigModel unitTest = facadeServicesManager.getReportCardConfigServices().getReportCardConfig(StaticValue.REPORT_CARD_UNIT_TEST, classCode, sectionCode);
		ReportCardConfigModel term = facadeServicesManager.getReportCardConfigServices().getReportCardConfig(StaticValue.REPORT_CARD_TERM, classCode, sectionCode);
		Map<String, Map<String, Object>> fieldsData = studentReportCardBusiness.preparedReportCardFields(mapData.get("allocated"), term, unitTest, request);
		if(admissionNo != null && !admissionNo.isEmpty()) {
			List<StudentReportCardModel> reportCards = studentReportCardDao.getStudentReportCardData(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, admissionNo);
			studentReportCardBusiness.preparedReportCardFieldsWithReportCardData(fieldsData, reportCards);
			Map<String, Object> reportCardData = studentReportCardBusiness.preparedReportCardHeaderAndColumn(fieldsData, term, unitTest);
			StudentDetailsModel studentDetails = facadeDaoManager.getStudentDetailsDao().getStudentDetailsByAddmissioNoOrRollNo(schoolCode, currentSession, classCode, sectionCode, admissionNo, 0, "A");
			studentReportCardBusiness.generateStudentReportCard("D", studentDetails, reportCardData);
		}
		
	}
}
