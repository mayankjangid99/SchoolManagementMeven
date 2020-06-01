package com.school.common.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.StudentAdditionalFeeDetailsModel;

@Repository
public class StudentAdditionalFeeDetailsDao extends DaoManagerBean {

private static Logger LOG = LoggerFactory.getLogger(StudentAdditionalFeeDetailsDao.class);
	
	
	public int deleteStudentAdditionalFeeDetails(StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails) {
		try {
			return executeUpdateHQLQuery("DELETE StudentAdditionalFeeDetailsModel WHERE schoolCode=:p1 AND sessionCode=:p2 AND classCode=:p3 AND sectionCode=:p4 AND admissionNo=:p5 AND rollNo=:p6 AND feeCategoryCode=:p7", 
					new Object[]{studentAdditionalFeeDetails.getSchoolCode(), studentAdditionalFeeDetails.getSessionCode(), studentAdditionalFeeDetails.getClassCode(), studentAdditionalFeeDetails.getSectionCode(), 
							studentAdditionalFeeDetails.getAdmissionNo(), studentAdditionalFeeDetails.getRollNo(), studentAdditionalFeeDetails.getFeeCategoryCode()});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: deleteStudentAdditionalFeeDetails() in StudentAdditionalDao", e);
		}
		return 0;
	}
	
	public List<StudentAdditionalFeeDetailsModel> getStudentAdditionalFeeDetails(StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails) {
		try {
			return findDynamicQuery("FROM StudentAdditionalFeeDetailsModel WHERE schoolCode=:p1 AND sessionCode=:p2 AND classCode=:p3 AND sectionCode=:p4 AND admissionNo=:p5 AND rollNo=:p6", 
					new Object[]{studentAdditionalFeeDetails.getSchoolCode(), studentAdditionalFeeDetails.getSessionCode(), studentAdditionalFeeDetails.getClassCode(), studentAdditionalFeeDetails.getSectionCode(), 
							studentAdditionalFeeDetails.getAdmissionNo(), studentAdditionalFeeDetails.getRollNo()});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: getStudentAdditionalFeeDetails() in StudentAdditionalDao", e);
		}
		return null;
	}
	
	
	public List<StudentAdditionalFeeDetailsModel> getClassSectionAdditionalFeeDetails(StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails) {
		try {
			return findDynamicQuery("FROM StudentAdditionalFeeDetailsModel WHERE schoolCode=:p1 AND sessionCode=:p2 AND classCode=:p3 AND sectionCode=:p4", 
					new Object[]{studentAdditionalFeeDetails.getSchoolCode(), studentAdditionalFeeDetails.getSessionCode(), studentAdditionalFeeDetails.getClassCode(), studentAdditionalFeeDetails.getSectionCode()});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: getClassSectionAdditionalFeeDetails() in StudentAdditionalDao", e);
		}
		return null;
	}
	
	public List<StudentAdditionalFeeDetailsModel> getClassSectionAdditionalFeeDetailsByType(StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails) {
		try {
			return findDynamicQuery("FROM StudentAdditionalFeeDetailsModel WHERE schoolCode=:p1 AND sessionCode=:p2 AND classCode=:p3 AND sectionCode=:p4 AND type=:p5", 
					new Object[]{studentAdditionalFeeDetails.getSchoolCode(), studentAdditionalFeeDetails.getSessionCode(), studentAdditionalFeeDetails.getClassCode(), studentAdditionalFeeDetails.getSectionCode(), studentAdditionalFeeDetails.getType()});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: getClassSectionAdditionalFeeDetailsByType() in StudentAdditionalDao", e);
		}
		return null;
	}
}
