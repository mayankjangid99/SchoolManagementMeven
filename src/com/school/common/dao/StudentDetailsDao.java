package com.school.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.StudentAttendanceDetailsModel;
import com.school.common.model.StudentAttendanceEmailDetailsModel;
import com.school.common.model.StudentAttendanceSmsDetailsModel;
import com.school.common.model.StudentDetailsModel;

@Repository
public class StudentDetailsDao extends DaoManagerBean
{
	private static Logger LOG = LoggerFactory.getLogger(StudentDetailsDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DatabaseDataHelper databaseDataHelper;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	
	private Session getOpenSession()
	{
		return sessionFactory.openSession();
	}
	

//	Get Admission No By School Session
	@SuppressWarnings("unchecked")
	public List<AdmissionNumberModel> getAllAdmissionNumberBySchoolCode(String schoolCode)
	{
		Criteria criteria = getCurrentSession().createCriteria(AdmissionNumberModel.class);
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		return criteria.list();
	}
	
	
//	Get Admission No By School Session
	public AdmissionNumberModel getMaxAdmissionNoBySessionCode(String schoolCode, String yearDigit)
	{
		Criteria criteria = getCurrentSession().createCriteria(AdmissionNumberModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.setMaxResults(1);
		criteria.add(Restrictions.like("admissionNo", "%" + yearDigit));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		AdmissionNumberModel admissionNumber = (AdmissionNumberModel) criteria.uniqueResult();
		return admissionNumber;
	}

	
	public Long getMaxSequenceInAdmissionNumberBySchoolCode(String schoolCode)
	{
		Criteria criteria = getCurrentSession().createCriteria(AdmissionNumberModel.class);
		criteria.setProjection(Projections.max("sequence"));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		
		Long maxSeq = (Long) criteria.uniqueResult();
		return maxSeq;
	}
	
	
//	Get Roll For New Admission
	public List<Integer> getRollNewAdmission(String schoolCode, String schoolSession, String classCode, String sectionCode)
	{
		Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
		criteria.setProjection(Projections.max("rollNo"));
		criteria.add(Restrictions.eq("classInformation.classCode", classCode));
		criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", schoolSession));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		@SuppressWarnings("unchecked")
		List<Integer> list=criteria.list();
		return list;
	}
		
	
//	Save New Admission
	public void saveStudentDetails(StudentDetailsModel studentDetails, String currentYear) {
		Session session = getOpenSession();
		try
		{
			Transaction transaction = session.beginTransaction();	
			AdmissionDetailsModel admissionDetails = studentDetails.getAdmissionDetailses().iterator().next();
			session.save(admissionDetails.getAdmissionNumber());
			session.save(studentDetails.getParentDetails());
			session.save(studentDetails);
			session.save(admissionDetails);
			transaction.commit();
			
			for(int i=1; i<=12; i++) {
				transaction = session.beginTransaction();
				StudentAttendanceDetailsModel attendanceDetails = new StudentAttendanceDetailsModel();
				attendanceDetails.setAdmissionDetails(admissionDetails);
				attendanceDetails.setMonths(i);
				attendanceDetails.setYear(currentYear);
				session.save(attendanceDetails);
				
				StudentAttendanceSmsDetailsModel attendanceSmsDetails = new StudentAttendanceSmsDetailsModel();
				attendanceSmsDetails.setAdmissionDetails(admissionDetails);
				attendanceSmsDetails.setMonths(i);
				attendanceSmsDetails.setYear(currentYear);
				session.save(attendanceSmsDetails);
				
				StudentAttendanceEmailDetailsModel attendanceEmailDetails = new StudentAttendanceEmailDetailsModel();
				attendanceEmailDetails.setAdmissionDetails(admissionDetails);
				attendanceEmailDetails.setMonths(i);
				attendanceEmailDetails.setYear(currentYear);
				session.save(attendanceEmailDetails);
				
				transaction.commit();
				session.flush();
			}
		}
		catch(Exception ex)
		{
			LOG.error("Error: saveStudentDetails in StudentDetailsDao - " + ex);
		}
		finally {
			session.close();
		}
	}
		
	
	
//	Get all Student Details of Class and Section where status = Y
	public List<StudentDetailsModel> getAllStudentsDetailsByClassSectionSessionAndStatus(String schoolCode, String schoolSession, String classCode, String sectionCode, String status)
	{
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		if("A".equalsIgnoreCase(status))
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetails(schoolCode, schoolSession, classCode, sectionCode);
		else
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsByStatus(schoolCode, schoolSession, classCode, sectionCode, status);
		
		if(admissionDetailsIdList == null || admissionDetailsIdList.size() == 0)
			return new ArrayList<StudentDetailsModel>();
		
		/*Query query = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
		query.setParameterList("adid", admissionDetailsIdList);*/
		List<StudentDetailsModel> list = new ArrayList<StudentDetailsModel>();
		admissionDetailsIdList.forEach(e -> {
			list.add(e.getStudentDetails());
		});
		
		return list;
	}
	
	
//	Get Student Details By Collection
	public List<StudentDetailsModel> getAllStudentDetailsByCollection(Collection<String> admissionNos, String schoolCode, String sessionCode) {
		if(admissionNos != null && !admissionNos.isEmpty() && schoolCode != null && !schoolCode.isEmpty() && sessionCode != null && !sessionCode.isEmpty()) {
			/*List<StudentDetailsModel> list = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionNo IN (:addNos) AND admissionDetailses.schoolProfile.schoolCode=:sc AND admissionDetailses.sessionDetails.sessionCode=:ssc")
					.setParameter("sc", schoolCode).setParameter("ssc", sessionCode).setParameterList("addNos", admissionNos).list();*/
			List<StudentDetailsModel> list = new ArrayList<StudentDetailsModel>();
			List<AdmissionDetailsModel> dbList = (List<AdmissionDetailsModel>) databaseDataHelper.getAdmissionDetails(admissionNos, schoolCode, sessionCode);
			if(dbList != null && !dbList.isEmpty()) {
				dbList.forEach(e -> list.add(e.getStudentDetails()));
			}
			return list;
		}
		else {
			return new ArrayList<StudentDetailsModel>();
		}
	}
	
	
//	Get Student Details of Class and Section where status = Y
	public StudentDetailsModel getStudentDetailsByAddmissioNoOrRollNo(String schoolCode, String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo, String status)
	{
		Collection<AdmissionDetailsModel> admissionDetailsIdList = new ArrayList<AdmissionDetailsModel>();
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			if("A".equalsIgnoreCase(status))
				admissionDetailsIdList = databaseDataHelper.getAdmissionDetails(schoolCode, currentSession, classCode, sectionCode);
			else
				admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsByStatusAndAdmissionNo(schoolCode, currentSession, classCode, sectionCode, admissionNo, status);
			
			if(admissionDetailsIdList.size() == 0)
				return null;
			
			/*Query query = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			StudentDetailsModel StudentDetails = (StudentDetailsModel)query.setParameterList("adid", admissionDetailsIdList).uniqueResult();*/
			return admissionDetailsIdList.iterator().next().getStudentDetails();
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			if("A".equalsIgnoreCase(status))
				admissionDetailsIdList = databaseDataHelper.getAdmissionDetails(schoolCode, currentSession, classCode, sectionCode);
			else
				admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsByStatusAndRollNo(schoolCode, currentSession, classCode, sectionCode, rollNo, status);
			
			if(admissionDetailsIdList.size() == 0)
				return null;
			
			/*Query query = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			StudentDetailsModel StudentDetails = (StudentDetailsModel)query.setParameterList("adid", admissionDetailsIdList).uniqueResult();*/
			return admissionDetailsIdList.iterator().next().getStudentDetails();
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			if("A".equalsIgnoreCase(status))
				admissionDetailsIdList = databaseDataHelper.getAdmissionDetails(schoolCode, currentSession, classCode, sectionCode);
			else
				admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsByStatusAndAdmissionNoAndRollNo(schoolCode, currentSession, classCode, sectionCode, admissionNo, rollNo, status);
			
			if(admissionDetailsIdList.size() == 0)
				return null;
			
			/*Query query = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			StudentDetailsModel StudentDetails = (StudentDetailsModel)query.setParameterList("adid", admissionDetailsIdList).uniqueResult();*/
			return admissionDetailsIdList.iterator().next().getStudentDetails();
		}
		return null;
	}
	
	
	
	

	
//	change Student Status from Grid
	public void changeStudentStatus(String status, String admissionNo, String schoolSession, String schoolCode)
	{
		Long admissionNumberId = databaseDataHelper.getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
		getCurrentSession().createQuery("UPDATE AdmissionDetailsModel SET status=:s WHERE admissionNumber.admissionNumberId=:an AND sessionDetails.sessionCode=:ss")
		.setParameter("s", status).setParameter("an", admissionNumberId).setParameter("ss", schoolSession).executeUpdate();
	}
	
	
//	Edit Student Details
	public AdmissionDetailsModel editStudentDetails(String admissionNo, String schoolSession, String schoolCode)
	{
		Long admissionNumberId = databaseDataHelper.getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
		AdmissionDetailsModel AdmissionDetails = (AdmissionDetailsModel) getCurrentSession().createQuery("FROM AdmissionDetailsModel WHERE admissionNumber.admissionNumberId=:an AND sessionDetails.sessionCode=:ss")
				.setParameter("an", admissionNumberId).setParameter("ss", schoolSession).uniqueResult();
		return AdmissionDetails;
	}
	
	
//	Update Student Details
	public void updateStudentDetails(StudentDetailsModel studentDetails, String schoolCode)
	{
		AdmissionDetailsModel admissionDetails = studentDetails.getAdmissionDetailses().iterator().next();
		ParentDetailsModel parentDetails = studentDetails.getParentDetails();

		Long admissionNumberId = databaseDataHelper.getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionDetails.getAdmissionNumber().getAdmissionNo());
//		update Admission Details
		getCurrentSession().createQuery("UPDATE AdmissionDetailsModel SET classInformation.classCode=:cc, sectionInformation.sectionCode=:sc, rollNo=:rl, status=:st, modifiedBy=:mb, modifiedOn=:mo WHERE admissionNumber.admissionNumberId=:an AND sessionDetails.sessionCode=:ss")
		.setParameter("cc", admissionDetails.getClassInformation().getClassCode()).setParameter("sc", admissionDetails.getSectionInformation().getSectionCode())
		.setParameter("rl", admissionDetails.getRollNo()).setParameter("st", admissionDetails.getStatus()).setParameter("mb", admissionDetails.getModifiedBy())
		.setParameter("mo", admissionDetails.getModifiedOn()).setParameter("an", admissionNumberId)
		.setParameter("ss", admissionDetails.getSessionDetails().getSessionCode()).executeUpdate();
		
//		update Parents Details
		getCurrentSession().createQuery("UPDATE ParentDetailsModel SET fatherFirstName=:ffn, fatherMiddleName=:fmn, fatherLastName=:fln, motherName=:mn, fatherOccupation=:fo, motherOccupation=:mo, fatherMobile=:fm, motherMobile=:mm, fatherEmail=:fe, motherEmail=:me, parentAddress=:pa WHERE parentDetailsId=:pid")
		.setParameter("ffn", parentDetails.getFatherFirstName()).setParameter("fmn", parentDetails.getFatherMiddleName()).setParameter("fln", parentDetails.getFatherLastName())
		.setParameter("mn", parentDetails.getMotherName()).setParameter("fo", parentDetails.getFatherOccupation())
		.setParameter("mo", parentDetails.getMotherOccupation()).setParameter("fm", parentDetails.getFatherMobile()).setParameter("mm", parentDetails.getMotherMobile())
		.setParameter("fe", parentDetails.getFatherEmail()).setParameter("me", parentDetails.getMotherEmail()).setParameter("pa", parentDetails.getParentAddress())
		.setParameter("pid", parentDetails.getParentDetailsId()).executeUpdate();

//		update Student Details
		getCurrentSession().createQuery("UPDATE StudentDetailsModel SET firstName=:fn, middleName=:mn, lastName=:ln, email=:em, previousSchoolName=:ps, image=:im, age=:ag, studentMobile=:mb, dateOfBirth=:do, gender=:gn, category=:ca, address=:ad, modifiedBy=:mb, modifiedOn=:mo WHERE admissionNo=:an")
		.setParameter("fn", studentDetails.getFirstName()).setParameter("mn", studentDetails.getMiddleName()).setParameter("ln", studentDetails.getLastName())
		.setParameter("em", studentDetails.getEmail()).setParameter("ps", studentDetails.getPreviousSchoolName()).setParameter("im", studentDetails.getImage())
		.setParameter("ag", studentDetails.getAge()).setParameter("mb", studentDetails.getStudentMobile()).setParameter("do", studentDetails.getDateOfBirth()).setParameter("gn", studentDetails.getGender())
		.setParameter("ca", studentDetails.getCategory()).setParameter("ad", studentDetails.getAddress()).setParameter("mb", studentDetails.getModifiedBy())
		.setParameter("mo", studentDetails.getModifiedOn()).setParameter("an", studentDetails.getAdmissionNo()).executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	public List<StudentDetailsModel> getStudentDetailsByClassCodeSectionCodeStatusAndNameWithLike(String schoolCode, String schoolSession, String classCode, String sectionCode, String status, String nameLike){
		Collection<AdmissionDetailsModel> admissionDetailsIdList = new ArrayList<AdmissionDetailsModel>();
		if("A".equalsIgnoreCase(status))
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetails(schoolCode, schoolSession, classCode, sectionCode);
		else
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsByStatus(schoolCode, schoolSession, classCode, sectionCode, status);
		
		if(admissionDetailsIdList.size() == 0)
			return new ArrayList<StudentDetailsModel>();
		/*Query query = null;
		if(nameLike != null && !"".equals(nameLike))
			query = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid) AND (firstName like '%"+nameLike+"%' OR middleName like '%"+nameLike+"%' OR lastName like '%"+nameLike+"%')");
		else
			query = getCurrentSession().createQuery("FROM StudentDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
		query.setParameterList("adid", admissionDetailsIdList);
		@SuppressWarnings("unchecked")
		List<StudentDetailsModel> list = query.list();*/
		List<StudentDetailsModel> list = new ArrayList<StudentDetailsModel>();
		
		admissionDetailsIdList.forEach(e -> list.add(e.getStudentDetails()));
		
		if(nameLike != null && !"".equals(nameLike)) {
			List<StudentDetailsModel> filteredList = (List<StudentDetailsModel>) list.stream().filter(e -> {
				if(e.getFirstName().contains(nameLike))
					return true;
				else if(e.getLastName().contains(nameLike))
					return true;
				else if(e.getMiddleName() != null && e.getMiddleName().contains(nameLike))
					return true;
				else
					return false;
			});
			return filteredList;
		}
		return list;
	}
	
	

	public boolean updatePromoteStudentDetails(AdmissionDetailsModel admissionDetails, String currentYear) {
		Session session = getOpenSession();
		boolean flag = false;
		try {
			Transaction transaction = session.beginTransaction();		
			session.persist(admissionDetails);
			//transaction.commit();
			for(int i = 1; i <= 12; i++) {
				//transaction = session.beginTransaction();
				StudentAttendanceDetailsModel attendanceDetails = new StudentAttendanceDetailsModel();
				attendanceDetails.setAdmissionDetails(admissionDetails);
				attendanceDetails.setMonths(i);
				attendanceDetails.setYear(currentYear);
				session.persist(attendanceDetails);
				
				StudentAttendanceSmsDetailsModel attendanceSmsDetails = new StudentAttendanceSmsDetailsModel();
				attendanceSmsDetails.setAdmissionDetails(admissionDetails);
				attendanceSmsDetails.setMonths(i);
				attendanceSmsDetails.setYear(currentYear);
				session.persist(attendanceSmsDetails);
				
				StudentAttendanceEmailDetailsModel attendanceEmailDetails = new StudentAttendanceEmailDetailsModel();
				attendanceEmailDetails.setAdmissionDetails(admissionDetails);
				attendanceEmailDetails.setMonths(i);
				attendanceEmailDetails.setYear(currentYear);
				session.persist(attendanceEmailDetails);
				
				//transaction.commit();
			}
			transaction.commit();
			session.flush();
			flag = true;
		}
		catch(Exception ex) {
			LOG.error("Error: updatePromoteStudentDetails in StudentDetailsDao - " + ex);
		} finally {
			session.close();
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @param schoolCode
	 * @param admissionNo
	 * @return
	 */
	public Long getAdmissionNumberIdByAdmissionNoAndSchoolCode(String schoolCode, String admissionNo) {
		return databaseDataHelper.getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
	}
	
	/**
	 * 
	 * @param schoolCode
	 * @param sessionCode
	 * @param classCode
	 * @param sectionCode
	 * @param admissionNo
	 * @return
	 */
	public boolean isAdmisionNoExist(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo) {
		return databaseDataHelper.isAdmisionNoExist(schoolCode, sessionCode, classCode, sectionCode, admissionNo);
	}
	

	public boolean isAdmisionNoExist(String schoolCode, String sessionCode, String admissionNo) {
		return databaseDataHelper.isAdmisionNoExist(schoolCode, sessionCode, admissionNo);
	}
}
