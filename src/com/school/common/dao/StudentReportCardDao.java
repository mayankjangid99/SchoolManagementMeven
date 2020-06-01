package com.school.common.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.StudentReportCardModel;

@Service
public class StudentReportCardDao extends DaoManagerBean {
	
	private static Logger LOG = LoggerFactory.getLogger(StudentReportCardDao.class);
	
	
	public List<StudentReportCardModel> getStudentReportCardData(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo) {
		try {
			return findDynamicQuery("FROM StudentReportCardModel c WHERE c.schoolCode=:p1 AND c.sessionCode=:p2 AND c.classCode=:p3 AND c.sectionCode=:p4 AND c.admissionNo=:p5", new Object[] {schoolCode, sessionCode, classCode, sectionCode, admissionNo});
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: getStudentReportCardData() in StudentReportCardDao ", e);
		}
		return null;
	}
}
