package com.school.common.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.StudentDetailsDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.facade.FacadeServicesManager;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.StudentAdditionalFeeDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.model.UserSettingModel;
import com.school.common.services.business.StudentDetailsBusiness;
import com.school.json.response.StudentDetailsJsonResponse;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentDetailsServices {

	@Autowired
	private StudentDetailsDao studentDetailsDao;
	
	@Autowired
	private StudentDetailsBusiness studentDetailsBusiness;
	
	@Autowired
	private FacadeServicesManager facadeServicesManager;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
		

	private static Logger LOG = LoggerFactory.getLogger(StudentDetailsBusiness.class);
	
	public ModelAndView searchStudentDetailsPage()
	{
		ModelAndView model = new  ModelAndView("staff/searchStudentDetails");
		return model;
	}
	
	public ModelAndView addStudentDetails()
	{
		ModelAndView model = new  ModelAndView("subadmin/addStudentDetails");
		model.addObject("Transports", facadeServicesManager.getTransportServices().getAllActiveTransports());
		return model;
	}
	
//	Get Admission No and Roll No for New Admission
	public String getRollAndAdmissionNoForNewAdmission(HttpServletRequest request)
	{
//		get Current Admission No 
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String[] currentSchoolSession = currentSession.split("-");
		String currentYear = currentSchoolSession[0];
		String yearDigit = currentYear.substring(currentYear.length()-2, currentYear.length());
					
		AdmissionNumberModel admissionNumber = studentDetailsDao.getMaxAdmissionNoBySessionCode(SessionManagerDataHelper.getSchoolCode(), yearDigit);
		String currentAdmissionNo = studentDetailsBusiness.getAdmissionNoBySession(admissionNumber, yearDigit);
		
//		Get Current Roll No
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		
		List<Integer> list = studentDetailsDao.getRollNewAdmission(SessionManagerDataHelper.getSchoolCode(), currentSession, classCode, sectionCode);
		String rollNo = studentDetailsBusiness.getRollAndAdmissionNoForNewAdmission(list);
				
		return currentAdmissionNo+"~"+rollNo;
	}
	
	

//	@After aspect use on this method
	public ModelAndView saveStudentDetails(HttpServletRequest request, CommonsMultipartFile psimage, StudentDetailsModel studentDetails, ParentDetailsModel parentDetails, AdmissionNumberModel admissionNumber,
			AdmissionDetailsModel admissionDetails, UserModel user, UserDetailsModel userDetails) {
		ModelAndView model = new ModelAndView("redirect:savedStudentDetails");
		String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		Long maxSeq = studentDetailsDao.getMaxSequenceInAdmissionNumberBySchoolCode(SessionManagerDataHelper.getSchoolCode());
		studentDetails = studentDetailsBusiness.saveStudentDetails(request, psimage, studentDetails, parentDetails, admissionNumber, admissionDetails, maxSeq);
		
		studentDetailsDao.saveStudentDetails(studentDetails, currentYear);
		
		String provideUserCredential = request.getParameter("provideUserCredential");
		if(provideUserCredential != null && "Y".equals(provideUserCredential)){
			userDetails.setEmail(parentDetails.getFatherEmail());
			userDetails.setFirstname(parentDetails.getFatherFirstName());
			userDetails.setMiddlename(parentDetails.getFatherMiddleName());
			userDetails.setLastname(parentDetails.getFatherLastName());
			userDetails.setParentDetails(parentDetails);
			model = facadeServicesManager.getUserServices().saveUser(request, null, "A", new UserRoleModel("ROLE_USER"), user, userDetails, new UserSettingModel(), new ParentDetailsModel(parentDetails.getParentDetailsId()));
		}
		
		deleteOrInsertStudentAdditionalFeeDetails(request);
		model.setViewName("redirect:savedStudentDetails");
		return model;
	}
	
	public ModelAndView savedStudentDetails()
	{
		ModelAndView model = new ModelAndView("subadmin/addStudentDetails");
		model.addObject("Transports", facadeServicesManager.getTransportServices().getAllActiveTransports());
		model.addObject("SuccessMessage", "Student Information Successfully Saved ...!!!");
		return model;
	}
	
	public ModelAndView resultStudentDetails() {
		ModelAndView model = new ModelAndView("staff/resultStudentDetails");
		return model;
	}
	

//	Fetch all Student Details in Grid
	public StudentDetailsJsonResponse fetchAllStudentsList(HttpServletRequest request) {
 		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String status = request.getParameter("status");
		if(status == null || "".equals(status))
			status = "Y";
		String schoolSession = SessionManagerDataHelper.getSchoolCurrentSession();
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}
//		call Dao class mothod
		List<StudentDetailsModel> list = studentDetailsDao.getAllStudentsDetailsByClassSectionSessionAndStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), schoolSession, classCode, sectionCode, status);
		StudentDetailsJsonResponse studentDetailsJsonResponse = studentDetailsBusiness.fetchAllStudentsList(list);
		return studentDetailsJsonResponse;
	}
	

//	Get All Student of Class and Section Where Status = Y
	public ModelAndView getStudentDetails(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView();
	
		String classCode = null;
		String sectionCode = null;
		String schoolSession = null;
		
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
		List<StudentDetailsModel> list = studentDetailsDao.getAllStudentsDetailsByClassSectionSessionAndStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), schoolSession, classCode, sectionCode, "Y");
		model.addObject("StudentsDetails", list);
		return model;
	}
	
	
//	change Student Status from Grid	
	public String changeStudentStatus(HttpServletRequest request)
	{
		String status = request.getParameter("status");
		String admissionNo = request.getParameter("admissionNo");
		
		if(status.equalsIgnoreCase("Y"))
			status = "N";
		else 
			status = "Y";
		
		String schoolSession = SessionManagerDataHelper.getSchoolCurrentSession();
		studentDetailsDao.changeStudentStatus(status, admissionNo, schoolSession, SessionManagerDataHelper.getSchoolCode());
		return "changed";
	}
	
	
//	Edit Student Details
	public ModelAndView editStudentDetails(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("subadmin/editStudentDetails");
		String schoolSession = SessionManagerDataHelper.getSchoolCurrentSession();
		String admissionNo = request.getParameter("admissionNo");
		model.addObject("Transports", facadeServicesManager.getTransportServices().getAllActiveTransports());
		
		AdmissionDetailsModel admissionDetails = studentDetailsDao.editStudentDetails(admissionNo, schoolSession, SessionManagerDataHelper.getSchoolCode());
		StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails = new StudentAdditionalFeeDetailsModel(SessionManagerDataHelper.getSchoolCode(), 
				SessionManagerDataHelper.getSchoolCurrentSession(), admissionDetails.getClassInformation().getClassCode(), admissionDetails.getSectionInformation().getSectionCode(), admissionNo, admissionDetails.getRollNo());
		List<StudentAdditionalFeeDetailsModel> additionalFeeList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getStudentAdditionalFeeDetails(studentAdditionalFeeDetails);
		model.addObject("AdmissionDetails", admissionDetails);
		model.addObject("StudentAdditionalFeeDetailsList", additionalFeeList);
		for(StudentAdditionalFeeDetailsModel additionalModel : additionalFeeList) {
			if(additionalModel.getFeeCategoryCode().equals(StaticValue.FEE_CATEGORY_TRANSPORT)) {
				model.addObject("StudentTransportFee", additionalModel);
			}
		}
		return model;
	}
	
	

//	@After aspect use on this method
//	Update Student Details
	public ModelAndView updateStudentDetails(HttpServletRequest request, CommonsMultipartFile psimage, StudentDetailsModel studentDetails, ParentDetailsModel parentDetails, AdmissionNumberModel admissionNumber,
			AdmissionDetailsModel admissionDetails, UserModel user) throws Exception
	{
		ModelAndView model=new ModelAndView("redirect:updatedStudentDetails");
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(12);
		ParentDetailsModel parentDetailsModel = (ParentDetailsModel) studentDetailsDao.get(ParentDetailsModel.class, parentDetails.getParentDetailsId());
		UserDetailsModel userDetailsModel = parentDetailsModel.getUserDetails();
		UserModel userModel = userDetailsModel.getUser();
		if(userDetailsModel != null) {
			boolean pwdMatcherFlag = cryptPasswordEncoder.matches(user.getPassword(), userDetailsModel.getPassword());
			if(pwdMatcherFlag) {
				model = editStudentDetails(request);
				model.addObject("ErrorMessage", "Please enter different password from last password");
				return model;
			} else if(user.getPassword().length() <= 15){
				String encPwd = cryptPasswordEncoder.encode(user.getPassword());
				userDetailsModel.setPassword(encPwd);
				userModel.setPassword(encPwd);
			}
		}
		studentDetails = studentDetailsBusiness.updateStudentDetails(request, psimage, studentDetails, parentDetails, admissionNumber, admissionDetails);
		
		boolean flagUser = facadeDaoManager.getUserDao().updatePassword(userModel);
		boolean flagUserDetails = facadeDaoManager.getUserDao().updatePasswordInUserDetails(userModel);
		if(!flagUser || !flagUserDetails) {
			model = editStudentDetails(request);
			model.addObject("ErrorMessage", "Password is not updated due to server internal errors.");
			return model;
		}
		
		studentDetailsDao.updateStudentDetails(studentDetails, SessionManagerDataHelper.getSchoolCode());
		deleteOrInsertStudentAdditionalFeeDetails(request);
		return model;
	}
	
	public ModelAndView updatedStudentDetails()
	{
		ModelAndView model = new ModelAndView("staff/searchStudentDetails");
		String msg = "Student Information Successfully Updated ...!!!";
		model.addObject("Transports", facadeServicesManager.getTransportServices().getAllActiveTransports());
		model.addObject("SuccessMessage", msg);
		return model;
	}
	
	
//	View Student Details
	public ModelAndView viewStudentDetails(HttpServletRequest request)
	{
		ModelAndView model = editStudentDetails(request);
		String operation = "View";
		model.addObject("Operation", operation);
		return model;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONArray getStudentsDetailsInAutocomplete(HttpServletRequest request, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		JSONArray jsonResponse = new JSONArray();
		String nameLike = request.getParameter("term");
		List<StudentDetailsModel> studentList = studentDetailsDao.getStudentDetailsByClassCodeSectionCodeStatusAndNameWithLike(SessionManagerDataHelper.getUserActiveSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classInformation.getClassCode(), sectionInformation.getSectionCode(), "Y", nameLike);
		
		JSONObject jsonObject = null;
		for(StudentDetailsModel studentDetails : studentList){
			jsonObject = new JSONObject();
			jsonObject.put("image", studentDetails.getImage());
			jsonObject.put("name", studentDetails.getFullname());
			jsonObject.put("fatherName", studentDetails.getParentDetails().getFatherName());
			jsonObject.put("motherName", studentDetails.getParentDetails().getMotherName());
			jsonObject.put("rollNo", BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(studentDetails, SessionManagerDataHelper.getSchoolCurrentSession()).getRollNo().toString());
			jsonObject.put("admissionNo", studentDetails.getAdmissionNo());
			jsonResponse.add(jsonObject);
		}
		return jsonResponse;
	}
	
	
	private void deleteOrInsertStudentAdditionalFeeDetails(HttpServletRequest request) {
		int i = 0;
		StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails = null;
		String classCode = request.getParameter("classInformation.classCode");
		String sectionCode = request.getParameter("sectionInformation.sectionCode");
		String rollNo = request.getParameter("rollNo");
		String admissionNo = request.getParameter("admissionNo");
		String[] selectedAdditionalFees = request.getParameterValues("selectedAdditionalFee");
		String[] amounts = request.getParameterValues("amount");
		String[] types = request.getParameterValues("type");
		String[] feeCategoryCodes = request.getParameterValues("feeCategoryCode");
		String createdBy = SessionManagerDataHelper.getUsername();
		Date date = new Date();
		for(String feeCategoryCode : feeCategoryCodes) {
			studentAdditionalFeeDetails = new StudentAdditionalFeeDetailsModel(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, admissionNo, new Integer(rollNo));
			studentAdditionalFeeDetails.setFeeCategoryCode(feeCategoryCode);
			try {
				if(selectedAdditionalFees != null && i < selectedAdditionalFees.length && selectedAdditionalFees[i] != null && !selectedAdditionalFees[i].isEmpty()) {
					studentAdditionalFeeDetails.setCreatedBy(createdBy);
					studentAdditionalFeeDetails.setCreatedOn(date);
					studentAdditionalFeeDetails.setAmount(new BigDecimal(amounts[i]));
					studentAdditionalFeeDetails.setType(types[i]);
					facadeDaoManager.getStudentAdditionalFeeDetailsDao().deleteStudentAdditionalFeeDetails(studentAdditionalFeeDetails);
					if(StaticValue.FEE_CATEGORY_TRANSPORT.equals(feeCategoryCode)) {
						String transportId = request.getParameter("transportId");
						studentAdditionalFeeDetails.setAttribute1(transportId);
					}
					studentDetailsDao.persist(studentAdditionalFeeDetails);
				} else {
					studentAdditionalFeeDetails.setType(types[i]);
					facadeDaoManager.getStudentAdditionalFeeDetailsDao().deleteStudentAdditionalFeeDetails(studentAdditionalFeeDetails);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("ERROR: deleteStudentAdditionalFeeDetails() in StudentDetailsServices", e);
			}
			i++;
		}
	}

	
	

	
	public ModelAndView resultPromoteStudentDetails(HttpServletRequest request) {
		String admissionNosStr = request.getParameter("admissionNos");
		ModelAndView model = new ModelAndView("staff/resultPromoteStudentDetails");
		model.addObject("admissionNos", admissionNosStr);
		List<SchoolSessionModel> sessionList = facadeDaoManager.getSchoolSessionDao().getAllSchoolSession();
		model.addObject("SessionList", sessionList);
		return model;
	}
	
//	Fetch all Student Details in Grid
	public StudentDetailsJsonResponse fetchPromoteStudentsDetails(HttpServletRequest request) {
		String admissionNosStr = request.getParameter("admissionNos");
		Collection<String> admissionNos = null;
		if(admissionNosStr != null && !admissionNosStr.isEmpty()) {
			String [] admissionNosStrArr = admissionNosStr.split("~");
			admissionNos = new ArrayList<String>();
			admissionNos = Arrays.asList(admissionNosStrArr);
		}

		List<StudentDetailsModel> list = studentDetailsDao.getAllStudentDetailsByCollection(admissionNos, SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession());
		StudentDetailsJsonResponse studentDetailsJsonResponse = studentDetailsBusiness.fetchAllStudentsList(list);
		return studentDetailsJsonResponse;
	}
	
	public ModelAndView updatePromoteStudentDetails(HttpServletRequest request) {
		ModelAndView model = new  ModelAndView("staff/searchStudentDetails");
 		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String sessionCode = request.getParameter("sessionCode");
		String admissionNosStr = request.getParameter("admissionNos");
		Collection<String> admissionNos = null;
		if(admissionNosStr != null && !admissionNosStr.isEmpty()) {
			String [] admissionNosStrArr = admissionNosStr.split("~");
			admissionNos = new ArrayList<String>();
			admissionNos = Arrays.asList(admissionNosStrArr);
		}
		

		List<StudentDetailsModel> studentList = studentDetailsDao.getAllStudentDetailsByCollection(admissionNos, SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession());
		String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		
		ArrayList<String> errorAdmNo = new ArrayList<String>();
		ArrayList<String> successAdmNo = new ArrayList<String>();
		ArrayList<String> errorAlreadyPromoted = new ArrayList<String>();
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		if(studentList != null && !studentList.isEmpty()) {
			for(StudentDetailsModel student : studentList) {
				String admissionNo = student.getAdmissionNo();
				boolean existFlag = studentDetailsDao.isAdmisionNoExist(schoolCode, sessionCode, admissionNo);
				if(!existFlag) {
					Long admisionNumberId = studentDetailsDao.getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
					List<Integer> list = studentDetailsDao.getRollNewAdmission(schoolCode, sessionCode, classCode, sectionCode);
					AdmissionDetailsModel admissionDetails = studentDetailsBusiness.preparedAdmissionDetailsForPromoteStudent(list, student, admisionNumberId, sessionCode, classCode, sectionCode);
					boolean flag = studentDetailsDao.updatePromoteStudentDetails(admissionDetails, currentYear);
					if(flag)
						successAdmNo.add(student.getAdmissionNo());
					else
						errorAdmNo.add(student.getAdmissionNo());
				} else {
					errorAlreadyPromoted.add(admissionNo);
				}
			}
			
		}
		if(!successAdmNo.isEmpty())
			model.addObject("SuccessMessage", "Student promoted next Session = " + sessionCode + ", Class Code = " + classCode + " AND Section Code = " + sectionCode + " Success Admission No(s). - " + String.join(", ", successAdmNo) + " ...!!!");
		
		String errorMsg = "";
		if(!errorAdmNo.isEmpty())
		 errorMsg = "Student is not promoted next Session = " + sessionCode + ", Class Code = " + classCode + " AND Section Code = " + sectionCode + "<br/> Error Admission No(s). - " + String.join(", ", errorAdmNo) + " ...!!!<br/>";
		if(!errorAlreadyPromoted.isEmpty())
			errorMsg += "Admission No(s). - " + String.join(", ", errorAlreadyPromoted) + " already exist for Section Code = " + sectionCode + " ...!!!";
		model.addObject("ErrorMessage", errorMsg);
		
		return model;
	}
}
