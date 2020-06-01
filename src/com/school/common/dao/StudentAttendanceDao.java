package com.school.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.StudentAttendanceDetailsModel;
import com.school.common.model.StudentAttendanceEmailDetailsModel;
import com.school.common.model.StudentAttendanceSmsDetailsModel;

@Repository
public class StudentAttendanceDao 
{
	private static Logger LOG = LoggerFactory.getLogger(StudentAttendanceDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DatabaseDataHelper databaseDataHelper;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	

	
//	Check Mark Attendance
	public String checkMarkAttendance(String schoolCode, String currentSession, String classCode, String sectionCode, int mm, int dd, String[] admissionNos, String[] rollNos, String status)
	{

		Long admissionNumberId = databaseDataHelper.getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNos[0]);
		Criteria criteria = getCurrentSession().createCriteria(StudentAttendanceDetailsModel.class, "attendanceDetails");
		criteria.createAlias("attendanceDetails.admissionDetails", "admissionDetails");
		criteria.createAlias("admissionDetails.admissionNumber", "admissionNumber");
		criteria.createAlias("admissionDetails.classInformation", "classInformation");
		criteria.createAlias("admissionDetails.sectionInformation", "sectionInformation");
		criteria.setProjection(Projections.property("d"+dd));
		criteria.add(Restrictions.eq("months", mm));
		criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));
		criteria.add(Restrictions.eq("admissionDetails.rollNo", Integer.parseInt(rollNos[0])) );
		criteria.add(Restrictions.eq("classInformation.classCode", classCode));
		criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
		String att = null;
		try {
			att = (String) criteria.uniqueResult();
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: StudentAttendanceDao in checkMarkAttendance()", e);
		}
		return att;
	}
	
//	Save Attendance for All Students
	public boolean saveMarkAttendance(String currentSession, String classCode, String sectionCode, int mm, int dd, String[] admissionNos, String[] rollNos, String[] attendances, String[] smss,  String[] emails, String status)
	{
		try {
			for(int i = 0; i < admissionNos.length; i++)
			{
				@SuppressWarnings("rawtypes")
				Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNos[i], status);
				if(admissionDetailsIds.size() > 0)
				{
					Query query = getCurrentSession().createQuery("UPDATE StudentAttendanceDetailsModel SET d"+dd+"=:att WHERE months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
					query.setParameter("att", attendances[i]).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds);
					query.executeUpdate();
					
					Query query2 = getCurrentSession().createQuery("UPDATE StudentAttendanceSmsDetailsModel SET d"+dd+"=:att WHERE months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");					
					query2.setParameter("att", smss[i]).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds);
					query2.executeUpdate();
					
					Query query3 = getCurrentSession().createQuery("UPDATE StudentAttendanceEmailDetailsModel SET d"+dd+"=:att WHERE months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");					
					query3.setParameter("att", emails[i]).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds);
					query3.executeUpdate();
				}
			}
		return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			LOG.error("ERROR: saveAttendance() in AttendanceDao - " + e);
			return false;
		}
	}
	
	
	
	
//	get Attendance Details from All Student
	public List<StudentAttendanceDetailsModel> resultAttendanceDetails(String currentSession, String classCode, String sectionCode, int mm, int dd, String status)
	{
		@SuppressWarnings("rawtypes")
		Collection admissionDetailsIdList = null;
		if("A".equalsIgnoreCase(status))
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsIDs(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode);
		else
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsIDsByStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, status);

		if(admissionDetailsIdList.size() == 0)
			return new ArrayList<StudentAttendanceDetailsModel>();
		
		Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
		query.setParameter("mo", mm).setParameterList("adid", admissionDetailsIdList);
		
		@SuppressWarnings("unchecked")
		List<StudentAttendanceDetailsModel> list = query.list();
	
		return list;
	}
	
	
//	get Attendance Email Details from All Student
	public List<StudentAttendanceEmailDetailsModel> resultAttendanceEmailDetails(String currentSession, String classCode, String sectionCode, int mm, int dd, String status)
	{
		@SuppressWarnings("rawtypes")
		Collection admissionDetailsIdList = null;
		if("A".equalsIgnoreCase(status))
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsIDs(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode);
		else
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsIDsByStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, status);

		if(admissionDetailsIdList.size() == 0)
			return new ArrayList<StudentAttendanceEmailDetailsModel>();
		
		Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
		query.setParameter("mo", mm).setParameterList("adid", admissionDetailsIdList);
		
		@SuppressWarnings("unchecked")
		List<StudentAttendanceEmailDetailsModel> list = query.list();
	
		return list;
	}
	
	
//	get Attendance SMS Details from All Student
	public List<StudentAttendanceSmsDetailsModel> resultAttendanceSMSDetails(String currentSession, String classCode, String sectionCode, int mm, int dd, String status)
	{
		@SuppressWarnings("rawtypes")
		Collection admissionDetailsIdList = null;
		if("A".equalsIgnoreCase(status))
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsIDs(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode);
		else
			admissionDetailsIdList = databaseDataHelper.getAdmissionDetailsIDsByStatus(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, status);

		if(admissionDetailsIdList.size() == 0)
			return new ArrayList<StudentAttendanceSmsDetailsModel>();
		
		Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
		query.setParameter("mo", mm).setParameterList("adid", admissionDetailsIdList);
		
		@SuppressWarnings("unchecked")
		List<StudentAttendanceSmsDetailsModel> list = query.list();
	
		return list;
	}
	

	
//	fetch Student Attendance Details 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StudentAttendanceDetailsModel> fetchStudentAttendanceDetails(String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo, String yyStr, int mm, int dd)
	{
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		return new ArrayList<StudentAttendanceDetailsModel>();
	}
	
	
	
//	fetch Student Attendance Email Details 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<StudentAttendanceEmailDetailsModel> fetchStudentAttendanceEmailDetails(String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo, String yyStr, int mm, int dd)
	{
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceEmailDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceEmailDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceEmailDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceEmailDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceEmailDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceEmailDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		return new ArrayList<StudentAttendanceEmailDetailsModel>();
	}
	
	
	
//	fetch Student Attendance SMS Details 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<StudentAttendanceSmsDetailsModel> fetchStudentAttendanceSmsDetails(String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo, String yyStr, int mm, int dd)
	{
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceSmsDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceSmsDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceSmsDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceSmsDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceSmsDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE year=:yy AND months=:mo AND admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceSmsDetailsModel> list = query.setParameter("yy", yyStr).setParameter("mo", mm).setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		return new ArrayList<StudentAttendanceSmsDetailsModel>();
	}
	
	
	
	
	
	
//	fetch Student Attendance Details Yearly
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<StudentAttendanceDetailsModel> fetchStudentAttendanceDetailsYearly(String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo)
	{
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, "Y");
			
			if(admissionDetailsIds.size() == 0)
				return new ArrayList<StudentAttendanceDetailsModel>();
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		return new ArrayList<StudentAttendanceDetailsModel>();
	}
	
	
	
//	fetch Student Attendance Email Details Yearly
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<StudentAttendanceEmailDetailsModel> fetchStudentAttendanceEmailDetailsYearly(String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo)
	{
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, "Y");
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceEmailDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, rollNo, "Y");
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceEmailDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, "Y");
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceEmailDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceEmailDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		return new ArrayList<StudentAttendanceEmailDetailsModel>();
	}
	
	
	
//	fetch Student Attendance SMS Details Yearly
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<StudentAttendanceSmsDetailsModel> fetchStudentAttendanceSmsDetailsYearly(String currentSession, String classCode, String sectionCode, String admissionNo, int rollNo)
	{
		if(admissionNo != null && !admissionNo.equals("") && rollNo == 0 )
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, "Y");
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceSmsDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && (admissionNo == null || admissionNo.equals("")) ) 
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, rollNo, "Y");
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceSmsDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		else if (rollNo != 0 && admissionNo != null && !admissionNo.equals(""))
		{
			Collection admissionDetailsIds = databaseDataHelper.getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(SessionManagerDataHelper.getUserActiveSchoolCode(), currentSession, classCode, sectionCode, admissionNo, rollNo, "Y");
			
			Query query = getCurrentSession().createQuery("FROM StudentAttendanceSmsDetailsModel WHERE admissionDetails.admissionDetailsId IN (:adid)");
			
			List<StudentAttendanceSmsDetailsModel> list = query.setParameterList("adid", admissionDetailsIds).list();
			return list;
		}
		return new ArrayList<StudentAttendanceSmsDetailsModel>();
	}
	
	
	public void saveMarkAttendanceByBulkExcel(List<String> queryList)
	{
		for(String query : queryList)
		{
			try {
				getCurrentSession().createQuery(query).executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
