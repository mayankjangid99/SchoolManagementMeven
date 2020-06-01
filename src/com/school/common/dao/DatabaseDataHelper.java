package com.school.common.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.AdmissionNumberModel;

@Repository
public class DatabaseDataHelper 
{
	private static Logger LOG = LoggerFactory.getLogger(DatabaseDataHelper.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private Session getCurrentSession()	{
		return sessionFactory.getCurrentSession();
	}
	
	

	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetails(Collection<String> admissionNos, String schoolCode, String sessionCode) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			List<Long> admissionNumberIds = getAdmissionNumberIdsByAdmissionNoAndSchoolCode(schoolCode, admissionNos);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.in("admissionNumber.admissionNumberId", admissionNumberIds));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetails() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetails(String schoolCode, String sessionCode, String classCode, String sectionCode) {		
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetails() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsIDs(String schoolCode, String sessionCode, String classCode, String sectionCode) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.setProjection(Projections.property("id"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsIDs() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsByStatus(String schoolCode, String sessionCode, String classCode, String sectionCode, String status) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = new ArrayList<AdmissionDetailsModel>();
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsByStatus() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}

	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsIDsByStatus(String schoolCode, String sessionCode, String classCode, String sectionCode, String status) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = new ArrayList<AdmissionDetailsModel>();
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.setProjection(Projections.property("id"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsIDsByStatus() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsByStatusAndAdmissionNo(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, String status){
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {		
			Long admissionNumberId = getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));		
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsByStatusAndAdmissionNo() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsIDsByStatusAndAdmissionNo(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, String status){
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Long admissionNumberId = getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.setProjection(Projections.property("id"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));		
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsIDsByStatusAndAdmissionNo() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsByStatusAndAdmissionNoAndRollNo(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, int rollNo, String status) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Long admissionNumberId = getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));
			criteria.add(Restrictions.eq("rollNo", rollNo));
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsByStatusAndAdmissionNoAndRollNo() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}

	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, int rollNo, String status) {		
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Long admissionNumberId = getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.setProjection(Projections.property("id"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));
			criteria.add(Restrictions.eq("rollNo", rollNo));
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsIDsByStatusAndAdmissionNoAndRollNo() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsByStatusAndRollNo(String schoolCode, String sessionCode, String classCode, String sectionCode, int rollNo, String status) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("rollNo", rollNo));
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsByStatusAndRollNo() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	

	
	
	@SuppressWarnings("unchecked")
	public Collection<AdmissionDetailsModel> getAdmissionDetailsIDsByStatusAndRollNo(String schoolCode, String sessionCode, String classCode, String sectionCode, int rollNo, String status) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			criteria.setProjection(Projections.property("id"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("rollNo", rollNo));
			criteria.add(Restrictions.eq("status", status));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionDetailsIDsByStatusAndRollNo() " , e);
			e.printStackTrace();
		}
		return admissionDetailsIdList;
	}
	
	
	public Long getAdmissionNumberIdByAdmissionNoAndSchoolCode(String schoolCode, String admissionNo) {
		Long AdmissionNumberId = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionNumberModel.class);
			criteria.setProjection(Projections.property("admissionNumberId"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("admissionNo", admissionNo));
			
			AdmissionNumberId = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionNumberIdByAdmissionNoAndSchoolCode() " , e);
			e.printStackTrace();
		}
		return AdmissionNumberId;
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAdmissionNumberIdsByAdmissionNoAndSchoolCode(String schoolCode, Collection<String> admissionNos) {
		List<Long> admissionNumberIds = new ArrayList<Long>();
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionNumberModel.class);
			criteria.setProjection(Projections.property("admissionNumberId"));
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.in("admissionNo", admissionNos));
			
			admissionNumberIds = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in getAdmissionNumberIdsByAdmissionNoAndSchoolCode() " , e);
			e.printStackTrace();
		}
		return admissionNumberIds;
	}
	

	@SuppressWarnings("unchecked")
	public boolean isAdmisionNoExist(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			Long admissionNumberId = getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in isAdmisionNoExist() " , e);
			e.printStackTrace();
		}
		if(admissionDetailsIdList != null && !admissionDetailsIdList.isEmpty())
			return true;
		else
			return false;
	}
	
	

	@SuppressWarnings("unchecked")
	public boolean isAdmisionNoExist(String schoolCode, String sessionCode, String admissionNo) {
		Collection<AdmissionDetailsModel> admissionDetailsIdList = null;
		try {
			Criteria criteria = getCurrentSession().createCriteria(AdmissionDetailsModel.class);
			Long admissionNumberId = getAdmissionNumberIdByAdmissionNoAndSchoolCode(schoolCode, admissionNo);
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			criteria.add(Restrictions.eq("admissionNumber.admissionNumberId", admissionNumberId));
			
			admissionDetailsIdList = criteria.list();
		} catch (HibernateException e) {
			LOG.error("Error in isAdmisionNoExist() " , e);
			e.printStackTrace();
		}
		if(admissionDetailsIdList != null && !admissionDetailsIdList.isEmpty())
			return true;
		else
			return false;
	}
	

}
