package com.school.common.services.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.CommonConstaint;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.UploadFilesUtils;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.json.model.StudentDetailsJsonModel;
import com.school.json.response.StudentDetailsJsonResponse;

@Service
public class StudentDetailsBusiness 
{
	
	private static Logger LOG = LoggerFactory.getLogger(StudentDetailsBusiness.class);
	
//	Get Admission No By Session
	public String getAdmissionNoBySession(AdmissionNumberModel admissionNumber, String yearDigit)
	{			
		if(admissionNumber != null && admissionNumber.getAdmissionNo().length() == 5)
		{
			String admissionNoString = admissionNumber.getAdmissionNo();
			
//			get incremental from admission No.
			String admissionNoIncremental = admissionNoString.substring(0,admissionNoString.length()-2);	
			int admissionNoInt = (Integer.parseInt(admissionNoIncremental)) + 1;
//			System.out.println("inc- "+admissionNoInt);
//			format incremental in 3 digit
			String currentAdmissionNo = String.format("%03d", admissionNoInt);
			currentAdmissionNo = currentAdmissionNo + yearDigit;
//			System.out.println("ddddddd>"+currentAdmissionNo);
			return currentAdmissionNo;
		}
		else
		{
			return "001"+yearDigit;
		}
	}
	
	
	
//	Get Roll No 	
	public String getRollAndAdmissionNoForNewAdmission(List<Integer> list)
	{
		Integer rollNo = 1;
		if(list.size()>0 && list != null && list.get(0) != null)
		{
			rollNo = (Integer)list.get(0);
			rollNo++;
		}		
		return rollNo.toString();
	}
	
	
//	save New Admission
	public StudentDetailsModel saveStudentDetails(HttpServletRequest request, CommonsMultipartFile psimage, StudentDetailsModel studentDetails, ParentDetailsModel parentDetails, AdmissionNumberModel admissionNumber,
			AdmissionDetailsModel admissionDetails, Long maxSeq)
	{		
		if(maxSeq == null)
			maxSeq = 1L;
		else
			maxSeq++;
		
		admissionNumber.setSequence(maxSeq);
		
		String username = SessionManagerDataHelper.getUsername();
		Date date = new Date();
		admissionNumber.setCreatedBy(username);
		admissionDetails.setCreatedBy(username);
		admissionDetails.setAdmissionDate(date);
		studentDetails.setCreatedBy(username);
		SchoolProfileModel schoolProfileModel = new SchoolProfileModel();
		schoolProfileModel.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		admissionDetails.setSchoolProfile(schoolProfileModel);
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		admissionDetails.setSessionDetails(sessionDetails);

		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		admissionNumber.setSchoolProfile(new SchoolProfileModel(schoolCode));
		admissionDetails.setAdmissionNumber(admissionNumber);
		admissionDetails.setStudentDetails(studentDetails);
		Set<AdmissionDetailsModel> admissionDetailss = new HashSet<AdmissionDetailsModel>();
		admissionDetailss.add(admissionDetails);
		studentDetails.setAdmissionDetailses(admissionDetailss);
		
//		parentDetails.setAdmissionNo(admissionNumber.getAdmissionNo());
		studentDetails.setParentDetails(parentDetails);
		String simage = UploadFilesUtils.uploadFileOnServer(schoolCode, psimage, CommonConstaint.STUDENT_IMAGE_PATH, admissionNumber.getAdmissionNo());
		studentDetails.setImage(simage);
		studentDetails.setAdmissionNo(admissionNumber.getAdmissionNo());
		LOG.info("STUDENT IMAGE NAME: " + simage);
		
		//admissionDetails.setAdmissionNumber(admissionNumber);
		return studentDetails;
	}
	
	
//	Fetch all Student Details in Grid
	public StudentDetailsJsonResponse fetchAllStudentsList(List<StudentDetailsModel> list)
	{
		StudentDetailsJsonResponse studentDetailsJsonResponse = new StudentDetailsJsonResponse();
		List<StudentDetailsJsonModel> studentDetailsJsonModelsList = new ArrayList<StudentDetailsJsonModel>();
		StudentDetailsJsonModel studentDetailsJsonModel = null;
		Iterator<StudentDetailsModel> iterator = list.iterator();
		while (iterator.hasNext()) {
			StudentDetailsModel studentDetails = (StudentDetailsModel) iterator.next();
			studentDetailsJsonModel = new StudentDetailsJsonModel();
			studentDetailsJsonModel.setAdmissionNo(studentDetails.getAdmissionNo());
			studentDetailsJsonModel.setsFirstName(studentDetails.getFirstName());
			studentDetailsJsonModel.setsName(studentDetails.getFullname());
			studentDetailsJsonModel.setGender(studentDetails.getGender());
			studentDetailsJsonModel.setsImage(studentDetails.getImage());
			AdmissionDetailsModel admissionDetailsModel = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(studentDetails, SessionManagerDataHelper.getSchoolCurrentSession());
			studentDetailsJsonModel.setRollNo(admissionDetailsModel.getRollNo().toString());
			studentDetailsJsonModel.setStatus(admissionDetailsModel.getStatus());
			studentDetailsJsonModel.setClassName(admissionDetailsModel.getClassInformation().getClassName());
			studentDetailsJsonModel.setSectionName(admissionDetailsModel.getSectionInformation().getSectionName());
			
			studentDetailsJsonModel.setfName(studentDetails.getParentDetails().getFatherName());
			studentDetailsJsonModel.setmName(studentDetails.getParentDetails().getMotherName());
			studentDetailsJsonModelsList.add(studentDetailsJsonModel);
		}

		studentDetailsJsonResponse.setStudentDetailsJsonModels(studentDetailsJsonModelsList);
		return studentDetailsJsonResponse;
	}
	
	
//	Update Student Details
	public StudentDetailsModel updateStudentDetails(HttpServletRequest request, CommonsMultipartFile psimage, StudentDetailsModel studentDetails, ParentDetailsModel parentDetails, AdmissionNumberModel admissionNumber,
			AdmissionDetailsModel admissionDetails) throws Exception
	{		
		Date date = new Date();
		admissionDetails.setAdmissionNumber(admissionNumber);
		admissionDetails.setModifiedOn(date);
		admissionDetails.setModifiedBy(SessionManagerDataHelper.getUsername());
		SessionDetailsModel sessionDetailsModel = new SessionDetailsModel();
		sessionDetailsModel.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		admissionDetails.setSessionDetails(sessionDetailsModel);

		Set<AdmissionDetailsModel> admissionDetailss = new HashSet<AdmissionDetailsModel>();
		admissionDetailss.add(admissionDetails);
		studentDetails.setAdmissionDetailses(admissionDetailss);
//		parentDetails.setAdmissionNo(admissionNumber.getAdmissionNo());
		studentDetails.setModifiedOn(date);
		studentDetails.setModifiedBy(SessionManagerDataHelper.getUsername());
		studentDetails.setParentDetails(parentDetails);
		if(!psimage.getOriginalFilename().isEmpty() && !studentDetails.getImage().equalsIgnoreCase(psimage.getOriginalFilename())) {
			//System.out.println(saveDirectory);
			String schoolCode = SessionManagerDataHelper.getSchoolCode();
			String simage = UploadFilesUtils.uploadFileOnServer(schoolCode, psimage, CommonConstaint.STUDENT_IMAGE_PATH, admissionNumber.getAdmissionNo());
			studentDetails.setImage(simage);
		}
		studentDetails.setAdmissionNo(admissionNumber.getAdmissionNo());
		
		return studentDetails;
	}
	
	
	public AdmissionDetailsModel preparedAdmissionDetailsForPromoteStudent(List<Integer> list, StudentDetailsModel student, Long admisionNumberId, String sessionCode, String classCode, String sectionCode) {

		Date date = new Date();
		AdmissionDetailsModel admissionDetails = new AdmissionDetailsModel();
		AdmissionDetailsModel admissionDetailsTemp = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(student, SessionManagerDataHelper.getSchoolCurrentSession());
		
		admissionDetails.setAdmissionNumber(new AdmissionNumberModel(admisionNumberId));
		admissionDetails.setSessionDetails(new SessionDetailsModel(sessionCode));
		admissionDetails.setClassInformation(new ClassInformationModel(classCode));
		admissionDetails.setSectionInformation(new SectionInformationModel(sectionCode));
		admissionDetails.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
		admissionDetails.setAdmissionDate(date);
		admissionDetails.setStatus(admissionDetailsTemp.getStatus());
		admissionDetails.setCreatedOn(date);
		admissionDetails.setCreatedBy(SessionManagerDataHelper.getUsername());
		
		String rollNo = getRollAndAdmissionNoForNewAdmission(list);
		admissionDetails.setRollNo(new Integer(rollNo));
		admissionDetails.setStudentDetails(student);
		return admissionDetails;
	}
	
}
