package com.school.common.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.StudentAttendanceDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.facade.HiberSessionFactory;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.StudentAttendanceDetailsModel;
import com.school.common.model.StudentAttendanceEmailDetailsModel;
import com.school.common.model.StudentAttendanceSmsDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.services.business.StudentAttendanceBusiness;
import com.school.json.model.AttendanceCalendarJsonModel;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentAttendanceServices 
{
	@Autowired
	private StudentAttendanceDao studentAttendanceDao;
	
	@Autowired
	private StudentAttendanceBusiness attendanceBusiness;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	@Autowired
	private HiberSessionFactory hiberSessionFactory;
	
	
//	Get All Student of Class and Section Where Status = Y
	public ModelAndView resultAllStudentDetailsForAttendance(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("admin/addMarkAttendance");
		
		String classCode = null;
		String sectionCode = null;
		String schoolSession = null;
		String status = request.getParameter("status");
		String date = request.getParameter("date");
		
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}
		if(status == null || "".equals(status))
			status = "Y";
		model.addObject("Status", status);
		
		String ddStr = null;
		String mmStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}
		schoolSession = SessionManagerDataHelper.getSchoolCurrentSession();
		
		UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
		
		if(userProfileUtils.getClassCode() != null && userProfileUtils.getSectionCode() != null)
		{
			classCode = userProfileUtils.getClassCode();
			sectionCode = userProfileUtils.getSectionCode();
		}
		else
		{
			classCode = request.getParameter("classCode");
			sectionCode = request.getParameter("sectionCode");
		}
		
//		call Dao class mothod
		List<StudentDetailsModel> list = facadeDaoManager.getStudentDetailsDao().getAllStudentsDetailsByClassSectionSessionAndStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), schoolSession, classCode, sectionCode, status);

		String[] admissionNos = new String[list.size()];
		String[] rollNos =  new String[list.size()];
		int index = 0;
		for(StudentDetailsModel studentDetailsModel : list){
			admissionNos[index] = studentDetailsModel.getAdmissionNo();
			AdmissionDetailsModel admissionDetailsModel = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(studentDetailsModel);
			rollNos[index] = admissionDetailsModel.getRollNo().toString();
		}
		String flag = studentAttendanceDao.checkMarkAttendance(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, mm, dd, admissionNos, rollNos, status);
		if(flag != null)
		{
			model.setViewName("admin/searchMarkAttendance");
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.AttendencesAlreadysubmitted", null, null));
			return model;
		}
		model.addObject("StudentsDetails", list);
		model.addObject("ClassCode", classCode);
		model.addObject("SectionCode", sectionCode);
		return model;
	}
	
	
//	@After aspect use on this method
	public ModelAndView saveMarkAttendance(HttpServletRequest request, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("redirect:savedMarkAttendance");
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		String[] admissionNos = request.getParameterValues("admissionNo");
		String[] rollNos = request.getParameterValues("rollNo");
		String[] attendances = request.getParameterValues("attendanceStatus");
		String[] smss = request.getParameterValues("smsStatus");
		String[] emails = request.getParameterValues("emailStatus");
		String[] fatherEmail = request.getParameterValues("fatherEmail");
		String[] fatherMobile = request.getParameterValues("fatherMobile");
		
		if(status == null)
			status = "Y";
		String ddStr = null;
		String mmStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}
		String classCode = classInformation.getClassCode();
		String sectionCode = sectionInformation.getSectionCode();
		String flag = studentAttendanceDao.checkMarkAttendance(SessionManagerDataHelper.getSchoolCode(), currentSession, classCode, sectionCode, mm, dd, admissionNos, rollNos, status);
		if(flag != null)
		{
			model.setViewName("admin/searchMarkAttendance");
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.AttendencesAlreadysubmitted", null, null));
			return model;
		}
		
		boolean saveStatus = studentAttendanceDao.saveMarkAttendance(currentSession, classCode, sectionCode, mm, dd, admissionNos, rollNos, attendances, smss, emails, status);
		
		String emailStudentAttendance = request.getParameter("emailStudentAttendance");
		String smsStudentAttendance = request.getParameter("smsStudentAttendance");
		if(saveStatus
			&& ((emailStudentAttendance != null && !"".equals(emailStudentAttendance) && "Y".equals(emailStudentAttendance))
			|| (smsStudentAttendance != null && !"".equals(smsStudentAttendance) && "Y".equals(smsStudentAttendance))))
			attendanceBusiness.saveOrUpdateMarkAttendance(admissionNos, attendances, smss, emails, fatherEmail, fatherMobile);
		return model;
	}
	
	
//	@After aspect use on this method
	public ModelAndView updateMarkAttendance(HttpServletRequest request, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("redirect:updatedMarkAttendance");
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		String[] admissionNos = request.getParameterValues("admissionNo");
		String[] rollNos = request.getParameterValues("rollNo");
		String[] attendances = request.getParameterValues("attendanceStatus");
		String[] smss = request.getParameterValues("smsStatus");
		String[] emails = request.getParameterValues("emailStatus");
		String[] fatherEmail = request.getParameterValues("fatherEmail");
		String[] fatherMobile = request.getParameterValues("fatherMobile");
		
		if(status == null)
			status = "Y";
		String ddStr = null;
		String mmStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}
		String classCode = classInformation.getClassCode();
		String sectionCode = sectionInformation.getSectionCode();
		/*String att = dao.checkMarkAttendance(currentSession, classCode, sectionCode, mm, dd, admissionNos, rollNos, status);
		if(att != null)
		{
			model.setViewName("admin/searchMarkAttendance");
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.AttendencesAlreadysubmitted", null, null));
			return model;
		}*/
		boolean saveStatus = studentAttendanceDao.saveMarkAttendance(currentSession, classCode, sectionCode, mm, dd, admissionNos, rollNos, attendances, smss, emails, status);

		String emailStudentAttendance = request.getParameter("emailStudentAttendance");
		String smsStudentAttendance = request.getParameter("smsStudentAttendance");
		if(saveStatus
			&& ((emailStudentAttendance != null && !"".equals(emailStudentAttendance) && "Y".equals(emailStudentAttendance))
			|| (smsStudentAttendance != null && !"".equals(smsStudentAttendance) && "Y".equals(smsStudentAttendance))))
			attendanceBusiness.saveOrUpdateMarkAttendance(admissionNos, attendances, smss, emails, fatherEmail, fatherMobile);
		return model;
		
	}
	
//	get Attendance Details from All Student
	public ModelAndView resultAttendanceDetails(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("admin/resultAttendanceDetails"); 
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}
		if(status == null || "".equals(status))
			status = "Y";
		model.addObject("Status", status);
		model.addObject("ClassCode", classCode);
		model.addObject("SectionCode", sectionCode);
		String ddStr = null;
		String mmStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}
		else if(date != null && date.equalsIgnoreCase(""))
		{
			String errorMsg = "<li>" + messageSource.getMessage("ui.text.valid.msg.datefield", null, null) + "</li>";
			model.addObject("ErrorMessage", errorMsg);
			model.setViewName("admin/searchAttendanceDetails");
			return model;
		}
		

//		call Dao class mothod
		String schoolSession = SessionManagerDataHelper.getSchoolCurrentSession();
		List<StudentDetailsModel> list = facadeDaoManager.getStudentDetailsDao().getAllStudentsDetailsByClassSectionSessionAndStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), schoolSession, classCode, sectionCode, status);

		model.addObject("StudentsDetails", list);
		
		List<StudentAttendanceDetailsModel> attendanceList = studentAttendanceDao.resultAttendanceDetails(currentSession, classCode, sectionCode, mm, dd, status);
		List<StudentAttendanceSmsDetailsModel> smsList = studentAttendanceDao.resultAttendanceSMSDetails(currentSession, classCode, sectionCode, mm, dd, status);
		List<StudentAttendanceEmailDetailsModel> emailList = studentAttendanceDao.resultAttendanceEmailDetails(currentSession, classCode, sectionCode, mm, dd, status);
		List<Map<String, String>> studentMap = attendanceBusiness.getAttendanceDetails(attendanceList, smsList, emailList, dd);
		model.addObject("AttendanceDetails", attendanceList);
		model.addObject("StudentMap", studentMap);
		return model;
	}
	
	
	
	public void downloadBulkMarkAttendance(HttpServletRequest request, HttpServletResponse response, ClassInformationModel classInformation, 
			SectionInformationModel sectionInformation)
	{
		ModelAndView model = new ModelAndView("admin/resultAttendanceDetails");
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}
		if(classInformation.getClassCode() == null || "".equals(classInformation.getClassCode()) 
				|| sectionInformation.getSectionCode() == null || "".equals(sectionInformation.getSectionCode())){
			classInformation.setClassCode(classCode);
			sectionInformation.setSectionCode(sectionCode);
			classInformation = facadeDaoManager.getClassSectionDao().getClassInformationByClassCode(classInformation);
			sectionInformation = facadeDaoManager.getClassSectionDao().getSectionInformationBySectionCode(sectionInformation);
		}
		if(status == null || "".equals(status))
			status = "Y";
		
		String ddStr = null;
		String mmStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}
		else if(date != null && date.equalsIgnoreCase(""))
		{
			String errorMsg = "<li>" + messageSource.getMessage("ui.text.valid.msg.datefield", null, null) + "</li>";
			model.addObject("ErrorMessage", errorMsg);
			model.setViewName("admin/searchAttendanceDetails");
		}
		List<StudentAttendanceDetailsModel> attendanceList = studentAttendanceDao.resultAttendanceDetails(currentSession, classCode, sectionCode, mm, dd, status);
		attendanceBusiness.downloadBulkMarkAttendance(request, response, attendanceList, classInformation, sectionInformation, date, status);
	}
	
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public ModelAndView uploadBulkMarkAttendance(HttpServletRequest request, HttpServletResponse response, CommonsMultipartFile bulkFile)
	{		
		ModelAndView model = new ModelAndView("redirect:uploadedBulkMarkAttendance");
		Map<String, Object> preparedData = attendanceBusiness.getPrepareQueryForBulkAttendance(bulkFile);
		
		List<String> queryList = (List<String>) preparedData.get("queryList");

		Collection<String> admissionNoListSmsColAbs = (Collection<String>) preparedData.get("admissionNoListSmsColAbs");
		Collection<String> admissionNoListEmailColAbs = (Collection<String>) preparedData.get("admissionNoListEmailColAbs");
		
		Collection<String> admissionNoListSmsColPrsnt = (Collection<String>) preparedData.get("admissionNoListSmsColPrsnt");
		Collection<String> admissionNoListEmailColPrsnt = (Collection<String>) preparedData.get("admissionNoListEmailColPrsnt");
		
		studentAttendanceDao.saveMarkAttendanceByBulkExcel(queryList);
		
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		List<StudentDetailsModel> studentDetailsListSmsAbs = facadeDaoManager.getStudentDetailsDao().getAllStudentDetailsByCollection(admissionNoListSmsColAbs, schoolCode, SessionManagerDataHelper.getSchoolCurrentSession());
		List<StudentDetailsModel> studentDetailsListEmailAbs = facadeDaoManager.getStudentDetailsDao().getAllStudentDetailsByCollection(admissionNoListEmailColAbs, schoolCode, SessionManagerDataHelper.getSchoolCurrentSession());

		List<StudentDetailsModel> studentDetailsListSmsPrsnt = facadeDaoManager.getStudentDetailsDao().getAllStudentDetailsByCollection(admissionNoListSmsColPrsnt, schoolCode, SessionManagerDataHelper.getSchoolCurrentSession());
		List<StudentDetailsModel> studentDetailsListEmailPrsnt = facadeDaoManager.getStudentDetailsDao().getAllStudentDetailsByCollection(admissionNoListEmailColPrsnt, schoolCode, SessionManagerDataHelper.getSchoolCurrentSession());
		
		attendanceBusiness.sendEmailForBulkAttendance(studentDetailsListEmailAbs);
		attendanceBusiness.sendEmailForBulkAttendanceForPresent(studentDetailsListEmailPrsnt);
		return model;
	}
	
	

	
	
	
	
//	################################################################## USER ##########################################################
	
//	Get Student Details For Attendance
	public ModelAndView resultStudentAttendanceDetails(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("user/resultStudentAttendanceDetails"); 
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String date = request.getParameter("date");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String admissionNo = request.getParameter("admissionNo");
		String rollNoStr = request.getParameter("rollNo");
		String status = request.getParameter("status");
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}
		if("ROLE_USER".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getStudentProfileUtils().getClassCode();
			sectionCode = profileUtils.getStudentProfileUtils().getSectionCode();
			admissionNo = profileUtils.getStudentProfileUtils().getAdmissionNo();
			rollNoStr = profileUtils.getStudentProfileUtils().getRollNo();
		}
		if(status == null || "".equals(status))
			status = "Y";
		int rollNo = 0;

//		Validate Parameters
		StringBuffer errorMsg = new StringBuffer(""); 
		if("".equals(rollNoStr) && "".equals(admissionNo))
			errorMsg.append("<li>" + messageSource.getMessage("ui.text.valid.msg.RollOrAdmission", null, null) + "</li>");
		if("".equals(date) || date == null)
			errorMsg.append("<li>" + messageSource.getMessage("ui.text.valid.msg.datefield", null, null) + "</li>");
		if(errorMsg.length() > 0)
		{
			model.setViewName("user/searchStudentAttendanceDetails");
			model.addObject("ErrorMessage", errorMsg.toString());
			return model;
		}
		
		String ddStr = null;
		String mmStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}
		
		if(!"".equals(rollNoStr) && rollNoStr != null)
		{
			rollNo = Integer.parseInt(rollNoStr);
		}
		StudentDetailsModel studentDetailsModel = facadeDaoManager.getStudentDetailsDao().getStudentDetailsByAddmissioNoOrRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, status);
		if(studentDetailsModel == null)
		{
			model.setViewName("user/searchStudentAttendanceDetails");
			model.addObject("ErrorMessage", "No Records Found, Please fill correct informations");
			return model;
		}
		model.addObject("StudentDetails", studentDetailsModel);
		return model;
	}
	
	
//	fetch Student Attendance Details 
	public AttendanceCalendarJsonResponse fetchStudentAttendanceDetails(HttpServletRequest request)
	{
		System.out.println("HiberSessionFactory== "+hiberSessionFactory.getSessionFactory());
		AttendanceCalendarJsonResponse attendanceCalendarJsonResponse = new AttendanceCalendarJsonResponse();
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String rollNoStr = request.getParameter("rollNo");
		int rollNo = 0;
		if(!rollNoStr.equals("") && rollNoStr != null)
		{
			rollNo = Integer.parseInt(request.getParameter("rollNo"));
		}
		String admissionNo = request.getParameter("admissionNo");

		String date = request.getParameter("date");
		String ddStr = null;
		String mmStr = null;
		String yyStr = null;
		String yyDigit = null;
		int dd = 0;
		int mm = 0;
		int yy = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			yyStr = date.split("-")[2];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
			yy = Integer.parseInt(yyStr);
		}
		
		String nextMonth = request.getParameter("nextMonth");
		if(nextMonth != null && !nextMonth.equals(""))
		{
			if(nextMonth.equalsIgnoreCase("Y"))
			{
				mm++;
				mmStr = mm+"";
				if(mm < 10)
				{
					mmStr = "0" + mm;
				} else if(mm > 12){
					mm = 1;
					yy++;
					mmStr = "0" + mm;
					yyStr = yy+"";
				}
			}
			else
			{
				mm--;
				mmStr = mm+"";
				if(mm < 1){
					mm = 12;
					yy--;
					mmStr = mm+"";
					yyStr = yy+"";
				} else if(mm < 10) {
					mmStr = "0" + mm;
				}
			}
		}
		yyStr = yyStr.trim();
		attendanceCalendarJsonResponse.setDate(ddStr);
		attendanceCalendarJsonResponse.setMonth(mmStr);
		attendanceCalendarJsonResponse.setYear(yyStr);
		attendanceCalendarJsonResponse.setAttendanceCalendarJsonModels(new ArrayList<AttendanceCalendarJsonModel>());
		
		yyDigit = yyStr.substring(yyStr.length()-2, yyStr.length());
		if(!currentSession.contains(yyDigit)){
			attendanceCalendarJsonResponse.setHasError("true");
			attendanceCalendarJsonResponse.setErrorMsg("Sorry, You can't see data before or after school session.");
			return attendanceCalendarJsonResponse;
		}
		
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		
		List<StudentAttendanceDetailsModel> attendanceDetailsList = studentAttendanceDao.fetchStudentAttendanceDetails(currentSession, classCode, sectionCode, admissionNo, rollNo, yyStr, mm, dd);
		List<StudentAttendanceEmailDetailsModel> attendanceEmailDetailsList = studentAttendanceDao.fetchStudentAttendanceEmailDetails(currentSession, classCode, sectionCode, admissionNo, rollNo, yyStr, mm, dd);
		List<StudentAttendanceSmsDetailsModel> attendanceSmsDetailsList = studentAttendanceDao.fetchStudentAttendanceSmsDetails(currentSession, classCode, sectionCode, admissionNo, rollNo, yyStr, mm, dd);
		attendanceCalendarJsonResponse = attendanceBusiness.fetchStudentAttendanceAllDetails(request, attendanceCalendarJsonResponse, attendanceDetailsList, attendanceSmsDetailsList, attendanceEmailDetailsList);
		return attendanceCalendarJsonResponse;
	}
	
	
	
//	fetch Student Attendance Details Yearly
	public AttendanceBarChartJsonResponse fetchStudentAttendanceDetailsYearly(HttpServletRequest request)
	{
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String rollNoStr = request.getParameter("rollNo");
		int rollNo = 0;
		if(!rollNoStr.equals("") && rollNoStr != null)
		{
			rollNo = Integer.parseInt(request.getParameter("rollNo"));
		}
		String admissionNo = request.getParameter("admissionNo");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		List<StudentAttendanceDetailsModel> list = studentAttendanceDao.fetchStudentAttendanceDetailsYearly(currentSession, classCode, sectionCode, admissionNo, rollNo);
		AttendanceBarChartJsonResponse AttendanceBarChartJsonResponse= attendanceBusiness.fetchStudentAttendanceDetailsYearly(request, list);
		return AttendanceBarChartJsonResponse;
	}
}
