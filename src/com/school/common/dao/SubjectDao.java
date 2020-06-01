package com.school.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSubjectModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.SubjectDetailsModel;

@Repository
public class SubjectDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private static Logger log = LoggerFactory.getLogger(SubjectDao.class);
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	
	public void saveSubject(SubjectDetailsModel subjectDetails)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(subjectDetails);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ERROR: saveSubject() in SubjectDao " , e);
		}
		finally {
			session.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SubjectDetailsModel> getAllSubjectDetails()
	{
		List<SubjectDetailsModel> list = getCurrentSession().createQuery("FROM SubjectDetailsModel").list();
		return list;
	}
	
	
	public SubjectDetailsModel getSubjectDetailsBySubjectCode(SubjectDetailsModel subjectDetails)
	{
		Criteria criteria = getCurrentSession().createCriteria(SubjectDetailsModel.class);
		criteria.add(Restrictions.eq("subjectCode", subjectDetails.getSubjectCode()));
		return (SubjectDetailsModel) criteria.uniqueResult();
	}
	
	
	public boolean updateSubjectDetails(SubjectDetailsModel subjectDetails)
	{
		int i = getCurrentSession().createQuery("UPDATE SubjectDetailsModel SET subjectName=:sn WHERE subjectCode=:sc")
				.setParameter("sn", subjectDetails.getSubjectName()).setParameter("sc", subjectDetails.getSubjectCode()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public void deleteSubjectDetails(SubjectDetailsModel subjectDetails)
	{
		Session session = openSession();
		try {
			Serializable subjectCode = new String(subjectDetails.getSubjectCode());
			Object persistentInstance = session.load(SubjectDetailsModel.class, subjectCode);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			}
			Transaction transaction = session.beginTransaction();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: deleteSubjectDetails() in SubjectDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ClassSubjectModel> fetchClassSubjectBySchoolCodeClassCodeSectionCodeAndType(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails, ClassInformationModel classInformation, 
			SectionInformationModel sectionInformation, SubjectDetailsModel subjectDetails, ClassSubjectModel classSubject)
	{
		Criteria criteria = getCurrentSession().createCriteria(ClassSubjectModel.class);
		
		if(schoolProfile.getSchoolCode() != null && !"".equals(schoolProfile.getSchoolCode()))
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
		
		if(sessionDetails.getSessionCode() != null && !"".equals(sessionDetails.getSessionCode()))
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionDetails.getSessionCode()));
		
		if(classInformation.getClassCode() != null && !"".equals(classInformation.getClassCode()))
			criteria.add(Restrictions.eq("classInformation.classCode", classInformation.getClassCode()));
		
		if(sectionInformation.getSectionCode() != null && !"".equals(sectionInformation.getSectionCode()))
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionInformation.getSectionCode()));
		
		if(subjectDetails.getSubjectCode() != null && !"".equals(subjectDetails.getSubjectCode()))
			criteria.add(Restrictions.eq("subjectDetails.subjectCode", subjectDetails.getSubjectCode()));
		
		if(classSubject.getType() != null && !"".equals(classSubject.getType()))
			criteria.add(Restrictions.eq("type", classSubject.getType()));
		
		List<ClassSubjectModel> list = criteria.list();
		return list;
	}
	
	
	
	public boolean deleteAllocatedSubjectDetailsToClassBySchoolCodeClassCodeSectionCodeAndType(ClassSubjectModel classSubject)
	{
		int i = getCurrentSession().createQuery("DELETE ClassSubjectModel WHERE schoolProfile.schoolCode=:spc AND sessionDetails.sessionCode=:sdc AND classInformation.classCode=:cic AND  sectionInformation.sectionCode=:sic AND subjectDetails.subjectCode=:sdc AND type=:typ")
		.setParameter("spc", classSubject.getSchoolProfile().getSchoolCode()).setParameter("sdc", classSubject.getSessionDetails().getSessionCode())
		.setParameter("cic", classSubject.getClassInformation().getClassCode()).setParameter("sic", classSubject.getSectionInformation().getSectionCode())
		.setParameter("sdc", classSubject.getSubjectDetails().getSubjectCode()).setParameter("typ", classSubject.getType()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ClassSubjectModel> getClassSubjectBySchoolCodeClassCodeSectionCodeAndType(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails, ClassInformationModel classInformation, 
			SectionInformationModel sectionInformation, ClassSubjectModel classSubject)
	{
		Criteria criteria = getCurrentSession().createCriteria(ClassSubjectModel.class);
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
		criteria.add(Restrictions.eq("classInformation.classCode", classInformation.getClassCode()));
		criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionInformation.getSectionCode()));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionDetails.getSessionCode()));
		if(classSubject != null && classSubject.getType() != null && !classSubject.getType().isEmpty()) {
			criteria.add(Restrictions.eq("type", classSubject.getType()));
		}
		criteria.addOrder(Order.asc("type"));
		List<ClassSubjectModel> list = criteria.list();
		return list;
	}
	
	
	public void deleteClassSubjectBySchoolCodeClassCodeSectionCodeAndType(ClassSubjectModel classSubject)
	{
		getCurrentSession().createQuery("DELETE ClassSubjectModel WHERE schoolProfile.schoolCode=:spc AND sessionDetails.sessionCode=:sdc AND classInformation.classCode=:cic AND sectionInformation.sectionCode=:sic AND type=:typ")
		.setParameter("spc", classSubject.getSchoolProfile().getSchoolCode()).setParameter("sdc", classSubject.getSessionDetails().getSessionCode()).setParameter("cic", classSubject.getClassInformation().getClassCode())
		.setParameter("sic", classSubject.getSectionInformation().getSectionCode()).setParameter("typ", classSubject.getType()).executeUpdate();
	}
	
	
	public void saveAllocatedSubjectToClass(ClassSubjectModel classSubject)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(classSubject);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ERROR: saveAllocatedSubjectToClass() in SubjectDao ", e);
		}
		finally
		{
			session.close();
		}
	}
	
	
	
}
