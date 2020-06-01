package com.school.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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

import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SessionDetailsModel;

@Repository
public class SchoolSessionDao 
{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger log = LoggerFactory.getLogger(ClassSectionDao.class);
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	
	
//	Get All School Session List
	@SuppressWarnings("unchecked")
	public List<SchoolSessionModel> getAllSchoolSession()
	{
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		return criteria.list();
	}
	
//	Get All Session Details List
	@SuppressWarnings("unchecked")
	public List<SessionDetailsModel> getAllSessionDetails() {
		Criteria criteria = getCurrentSession().createCriteria(SessionDetailsModel.class);
		criteria.addOrder(Order.asc("sessionName"));
		return criteria.list();
	}
	
	
//	fetch School Session List
	@SuppressWarnings("unchecked")
	public List<SchoolSessionModel> fetchAllocatedSchoolSession(SchoolSessionModel schoolSession) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		if(schoolSession.getSessionDetails() != null && !"".equals(schoolSession.getSessionDetails().getSessionCode()) && schoolSession.getSessionDetails().getSessionCode() != null)
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", schoolSession.getSessionDetails().getSessionCode()));
		
		if(schoolSession.getSchoolProfile() != null && !"".equals(schoolSession.getSchoolProfile().getSchoolCode()) && schoolSession.getSchoolProfile().getSchoolCode() != null)
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolSession.getSchoolProfile().getSchoolCode()));
		
		if(!"".equals(schoolSession.getStatus()) && schoolSession.getStatus() != null)
			criteria.add(Restrictions.eq("status", schoolSession.getStatus()));	
		return criteria.list();
	}
	
	
//	Get Session Details by Session Code
	public SessionDetailsModel getSessionDetailsBySessionCode(SessionDetailsModel sessionDetails) {
		Criteria criteria = getCurrentSession().createCriteria(SessionDetailsModel.class);
		criteria.addOrder(Order.desc("sessionName"));
		criteria.add(Restrictions.eq("sessionCode", sessionDetails.getSessionCode()));
		SessionDetailsModel sessionDetailsModel = (SessionDetailsModel)criteria.uniqueResult();
		return sessionDetailsModel;
	}
	
	
	public boolean updateSessionDetails(SessionDetailsModel sessionDetails) {
		int i = getCurrentSession().createQuery("UPDATE SessionDetailsModel SET sessionName=:sn WHERE sessionCode=:sc")
				.setParameter("sn", sessionDetails.getSessionName()).setParameter("sc", sessionDetails.getSessionCode()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
//	Get School Session by Session Code 
	public SchoolSessionModel getSchoolSessionBySessionCode(SchoolSessionModel schoolSession) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", schoolSession.getSessionDetails().getSessionCode()));
		SchoolSessionModel schoolSessionModel = (SchoolSessionModel)criteria.uniqueResult();
		return schoolSessionModel;
	}
	
	
	
//	Get School Session by Session Code 
	public SchoolSessionModel getSchoolSessionBySessionCodeAndSchoolCode(SchoolSessionModel schoolSession) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", schoolSession.getSessionDetails().getSessionCode()));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolSession.getSchoolProfile().getSchoolCode()));
		SchoolSessionModel schoolSessionModel = (SchoolSessionModel)criteria.uniqueResult();
		return schoolSessionModel;
	}
	
	
//	Get School Session by Session Code 
	public SchoolSessionModel getSchoolSessionBySessionCode(SessionDetailsModel sessionDetails) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionDetails.getSessionCode()));
		SchoolSessionModel schoolSessionModel = (SchoolSessionModel)criteria.uniqueResult();
		return schoolSessionModel;
	}
	
	
//	Get School Session by School Code 
	@SuppressWarnings("unchecked")
	public List<SchoolSessionModel> getSchoolSessionBySchoolCode(SchoolProfileModel schoolProfile) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
		List<SchoolSessionModel> schoolSessionList = criteria.list();
		return schoolSessionList;
	}
	
	
//	Get School Session by School Code and Session Code
	@SuppressWarnings("unchecked")
	public List<SchoolSessionModel> getSchoolSessionBySchoolCodeAndSessionCode(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionDetails.getSessionCode()));
		List<SchoolSessionModel> schoolSessionList = criteria.list();
		return schoolSessionList;
	}
	
	
	
//	Get School Session by school session Id
	public SchoolSessionModel getSchoolSessionBySchoolSessionId(SchoolSessionModel schoolSession) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.addOrder(Order.desc("sequence"));
		criteria.add(Restrictions.eq("schoolSessionId", schoolSession.getSchoolSessionId()));
		SchoolSessionModel schoolSessionModel = (SchoolSessionModel) criteria.uniqueResult();
		return schoolSessionModel;
	}
	
	
	public void updateSchoolSessionStatusInPrivious(String schoolCode) {
		getCurrentSession().createQuery("UPDATE SchoolSessionModel SET status=:s WHERE schoolProfile.schoolCode=:sc")
		.setParameter("s", "P").setParameter("sc", schoolCode).executeUpdate();
	}
	
	
	public Integer getSchoolSessionMaxSequence(SchoolProfileModel schoolProfile) {
		Criteria criteria = getCurrentSession().createCriteria(SchoolSessionModel.class);
		criteria.setProjection(Projections.max("sequence"));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
		Integer maxSequence = (Integer) criteria.uniqueResult();
		return maxSequence;
	}
	
	
	public void saveSessionDetails(SessionDetailsModel sessionDetails) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(sessionDetails);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveSessionDetails() in SchoolSessionDao ", e);
		} finally {
			session.close();
		}
	}
	
	
	
	public void saveSchoolSession(SchoolSessionModel schoolSession) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(schoolSession);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveSchoolSession() in SchoolSessionDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public boolean updateSchoolSessionBySessionCodeAndSchoolCode(SchoolProfileModel schoolProfile, SchoolSessionModel schoolSession) {
		int i = getCurrentSession().createQuery("UPDATE SchoolSessionModel SET status=:st WHERE sessionDetails.sessionCode=:sc AND schoolProfile.schoolCode=:spc")
				.setParameter("st", schoolSession.getStatus()).setParameter("sc", schoolSession.getSessionDetails().getSessionCode())
				.setParameter("spc", schoolProfile.getSchoolCode()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public void deleteSessionDetails(SessionDetailsModel sessionDetails) {
		Session session = openSession();
		try {
			Serializable sessionCode = new String(sessionDetails.getSessionCode());
			Object persistentInstance = session.load(SessionDetailsModel.class, sessionCode);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			}
			Transaction transaction = session.beginTransaction();
			//getCurrentSession().createQuery("DELETE ClassInformationModel WHERE classCode=:cc").setParameter("cc", classInformation.getClassCode()).executeUpdate();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: deleteSessionDetails() in SchoolSessionDao ", e);
		} finally {
			session.close();
		}
	}
	
	
	public boolean deleteSchoolSessionByschoolSessionId(SchoolSessionModel schoolSession) {
		int i = getCurrentSession().createQuery("DELETE SchoolSessionModel WHERE schoolSessionId=:sid")
				.setParameter("sid", schoolSession.getSchoolSessionId()).executeUpdate();
				if(i > 0)
					return true;
				else
					return false;
	}
	
	
	public boolean deleteSchoolSessionBySchoolCodeAndSessionCode(SchoolProfileModel schoolProfile, SchoolSessionModel schoolSession) {
		int i = getCurrentSession().createQuery("DELETE SchoolSessionModel WHERE schoolProfile.schoolCode=:spc AND sessionDetails.sessionCode=:sc")
		.setParameter("spc", schoolProfile.getSchoolCode()).setParameter("sc", schoolSession.getSessionDetails().getSessionCode()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
}
