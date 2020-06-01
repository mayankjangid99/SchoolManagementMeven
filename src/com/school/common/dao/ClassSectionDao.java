package com.school.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import com.school.common.model.ClassSectionModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;


@Repository
public class ClassSectionDao 
{
	@Autowired
	private SessionFactory sessionFactory;

	
	private static Logger LOG = LoggerFactory.getLogger(ClassSectionDao.class);
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ClassInformationModel> getAllClassList() {
		Criteria criteria = getCurrentSession().createCriteria(ClassInformationModel.class);
		criteria.addOrder(Order.asc("className"));
		criteria.setCacheable(true);
		List<ClassInformationModel> list = criteria.list();
		return list;
	}


	/*@SuppressWarnings("unchecked")
	public List<ClassInformationModel> getClassListBySchoolCode(String schoolCode) {
		Criteria criteria = getCurrentSession().createCriteria(ClassSectionModel.class, "classSection");
		criteria.createAlias("classSection.classInformation", "classInfo");
		criteria.addOrder(Order.asc("classInfo.className"));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("classInfo.classCode")));
		criteria.setResultTransformer(Transformers.aliasToBean(ClassSectionModel.class));
		criteria.setCacheable(true);
		List<ClassSectionModel> list = criteria.list();
		
		List<ClassInformationModel> list2 = new ArrayList<ClassInformationModel>();
		for(ClassSectionModel classSection : list)
		{
			list2.add(classSection.getClassInformation());
		}
		return list2;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<ClassInformationModel> getClassListBySchoolCodeSessionCode(String schoolCode, String sessionCode) {
		List<ClassInformationModel> classList = new ArrayList<ClassInformationModel>();
		if(schoolCode != null && !schoolCode.isEmpty() && sessionCode != null && !sessionCode.isEmpty()) {
			List<ClassSectionModel> list = getCurrentSession().createQuery("FROM ClassSectionModel WHERE schoolProfile.schoolCode=:ssc AND sessionDetails.sessionCode=:sc GROUP BY classInformation.classCode")
					.setParameter("ssc", schoolCode).setParameter("sc", sessionCode).setCacheable(true).list();
			for(ClassSectionModel classSection : list) {
				classList.add(classSection.getClassInformation());
			}
		} else {
			List<ClassInformationModel> list = getCurrentSession().createQuery("FROM ClassInformationModel ORDER BY classOrder")
					.setCacheable(true).list();
			classList.addAll(list);
		}
		return classList;
	}
	
	
	public List<SectionInformationModel> getSectionList() {
		Criteria criteria = getCurrentSession().createCriteria(SectionInformationModel.class);
		criteria.addOrder(Order.asc("sectionName"));
		@SuppressWarnings("unchecked")
		List<SectionInformationModel> list = criteria.list();
		return list;
	}
	
	
	public ClassInformationModel getClassInformationByClassCode(ClassInformationModel classInformation) {
		Criteria criteria = getCurrentSession().createCriteria(ClassInformationModel.class);
		criteria.addOrder(Order.asc("className"));
		criteria.add(Restrictions.eq("classCode", classInformation.getClassCode()));
		ClassInformationModel classInformationModel = (ClassInformationModel)criteria.uniqueResult();		
		return classInformationModel;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SectionInformationModel> getSectionListByClassCodeAndSchoolCodeSessionCode(String schoolCode, String classCode, String sessionCode) {
		Criteria criteria = getCurrentSession().createCriteria(ClassSectionModel.class, "classSection");
		criteria.createAlias("classSection.classInformation", "classInfo");
		criteria.createAlias("classSection.sectionInformation", "sectionInfo");
		criteria.createAlias("classSection.sessionDetails", "sessionDe");
		criteria.addOrder(Order.asc("sectionInfo.sectionName"));
		criteria.add(Restrictions.eq("classInfo.classCode", classCode));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		criteria.add(Restrictions.eq("sessionDe.sessionCode", sessionCode));
		List<ClassSectionModel> list = criteria.list();		
		
		List<SectionInformationModel> list2 = new ArrayList<SectionInformationModel>();
		for(ClassSectionModel classSection : list)
		{
			list2.add(classSection.getSectionInformation());
		}
		return list2;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ClassSectionModel> getClassListByClassCodeSessionCodeAndSchoolCode(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails, ClassInformationModel classInformation) {
		Criteria criteria = getCurrentSession().createCriteria(ClassSectionModel.class);
		criteria.addOrder(Order.asc("schoolProfile.schoolCode"));
		
		if(schoolProfile.getSchoolCode() != null && !"".equals(schoolProfile.getSchoolCode()))
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
		
		if(sessionDetails.getSessionCode() != null && !"".equals(sessionDetails.getSessionCode()))
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionDetails.getSessionCode()));
		
		if(classInformation.getClassCode() != null && !"".equals(classInformation.getClassCode()))
			criteria.add(Restrictions.eq("classInformation.classCode", classInformation.getClassCode()));
		
		List<ClassSectionModel> classSectionList = criteria.list();
		return classSectionList;
	}
	
	
	public SectionInformationModel getSectionInformationBySectionCode(SectionInformationModel sectionInformation) {
		Criteria criteria = getCurrentSession().createCriteria(SectionInformationModel.class);
		criteria.addOrder(Order.asc("sectionName"));
		criteria.add(Restrictions.eq("sectionCode", sectionInformation.getSectionCode()));
		SectionInformationModel sectionInformationModel = (SectionInformationModel)criteria.uniqueResult();		
		return sectionInformationModel;
	}
	
	
	public List<ClassInformationModel> getClassListByClassCodes(Collection<String> classCodes) {
		Criteria criteria = getCurrentSession().createCriteria(ClassInformationModel.class);
		criteria.addOrder(Order.asc("className"));
		criteria.add(Restrictions.in("classCode", classCodes));
		@SuppressWarnings("unchecked")
		List<ClassInformationModel> list = criteria.list();
		return list;
	}

	
	public List<SectionInformationModel> getSectionListBySectionCodesList(Collection<String> sectionCodes) {
		Criteria criteria = getCurrentSession().createCriteria(SectionInformationModel.class);
		criteria.addOrder(Order.asc("sectionName"));
		criteria.add(Restrictions.in("sectionCode", sectionCodes));
		@SuppressWarnings("unchecked")
		List<SectionInformationModel> list = criteria.list();
		return list;
	}
	
	
	public void saveClass(ClassInformationModel classInformation)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(classInformation);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveClass() in ClassSectionDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public void updateClass(ClassInformationModel classInformation)
	{
		getCurrentSession().createQuery("UPDATE ClassInformationModel SET className=:cn WHERE classCode=:cd")
		.setParameter("cn", classInformation.getClassName()).setParameter("cd", classInformation.getClassCode()).executeUpdate();

	}
	
	
	
	/*public void deleteClass(ClassInformationModel classInformation)
	{
		
		getCurrentSession().createQuery("DELETE ClassInformationModel WHERE classCode=:cc")
		.setParameter("cc", classInformation.getClassCode()).executeUpdate();
	}*/
	
	
	public void deleteClass(ClassInformationModel classInformation)
	{
		Session session = openSession();
		try {
			Serializable classCode = new String(classInformation.getClassCode());
			Object persistentInstance = session.load(ClassInformationModel.class, classCode);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			}
			Transaction transaction = session.beginTransaction();
			//getCurrentSession().createQuery("DELETE ClassInformationModel WHERE classCode=:cc").setParameter("cc", classInformation.getClassCode()).executeUpdate();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: deleteClass() in ClassSectionDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public boolean deleteAllocatedSection(String schoolCode, String classCode, String sectionCode)
	{
		int i = getCurrentSession().createQuery("DELETE ClassSectionModel WHERE schoolProfile.schoolCode=:sc AND classInformation.classCode=:cc AND sectionInformation.sectionCode=:sd")
		.setParameter("sc", schoolCode).setParameter("cc", classCode).setParameter("sd", sectionCode).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	/*public void deleteSection(SectionInformationModel sectionInformation)
	{
		getCurrentSession().createQuery("DELETE SectionInformationModel WHERE sectionCode=:sc")
		.setParameter("sc", sectionInformation.getSectionCode()).executeUpdate();
	}*/
	
	
	
	public void deleteSection(SectionInformationModel sectionInformation)
	{
		Session session = openSession();
		try {
			Serializable sectionCode = new String(sectionInformation.getSectionCode());
			Object persistentInstance = session.load(SectionInformationModel.class, sectionCode);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			}
			Transaction transaction = session.beginTransaction();
			//getCurrentSession().createQuery("DELETE SectionInformationModel WHERE sectionCode=:sc").setParameter("sc", sectionInformation.getSectionCode()).executeUpdate();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: deleteSection() in ClassSectionDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public void saveSection(SectionInformationModel sectionInformation)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(sectionInformation);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveSection() in ClassSectionDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public void updateSection(SectionInformationModel sectionInformation)
	{
		getCurrentSession().createQuery("UPDATE SectionInformationModel SET sectionName=:sn WHERE sectionCode=:sd").setParameter("sn", sectionInformation.getSectionName())
		.setParameter("sd", sectionInformation.getSectionCode()).executeUpdate();
	}
	
	
	
	public void deleteAllocatedSectionToClassBySchoolCodeSessionCodeAndClassCode(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails, ClassInformationModel classInformation)
	{
		getCurrentSession().createQuery("DELETE ClassSectionModel WHERE schoolProfile.schoolCode=:sc AND sessionDetails.sessionCode=:ssc AND classInformation.classCode=:cc")
		.setParameter("sc", schoolProfile.getSchoolCode()).setParameter("ssc", sessionDetails.getSessionCode()).setParameter("cc", classInformation.getClassCode()).executeUpdate();
	}
	
	
	/*public void saveAllocatedSectionToClass(ClassInformationModel classInformation)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(classInformation);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveAllocatedSectionToClass() in ClassSectionDao " + e);
		}
		finally {
			session.close();
		}
	}*/
	
	
	public void saveAllocatedSectionToClass(ClassSectionModel classSection)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(classSection);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveAllocatedSectionToClass() in ClassSectionDao " + e);
		}
		finally {
			session.close();
		}
	}
	
	
	
	
//	fetch Class  Section 
	/*public List<ClassInformationModel> fetchClassSection(ClassInformationModel classInformation) 
	{
		if(classInformation.getClassCode() != null && !"".equals(classInformation.getClassCode()))
		{
			Criteria criteria = getCurrentSession().createCriteria(ClassInformationModel.class);
			criteria.addOrder(Order.asc("className"));
			criteria.add(Restrictions.eq("classCode", classInformation.getClassCode()));
			@SuppressWarnings("unchecked")
			List<ClassInformationModel> list = criteria.list();		
			return list;
		}
		else
		{
			@SuppressWarnings("unchecked")
			List<ClassInformationModel> list = getCurrentSession().createQuery("FROM ClassInformationModel").list();
			return list;
		}
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<ClassSectionModel> fetchClassSection(String schoolCode, String classCode, String sectionCode) 
	{
		Criteria criteria = getCurrentSession().createCriteria(ClassSectionModel.class);
		criteria.addOrder(Order.asc("schoolProfile.schoolCode"));
		
		if(schoolCode != null && !"".equals(schoolCode))
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		
		if(classCode != null && !"".equals(classCode))
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
		
		if(sectionCode != null && !"".equals(sectionCode))
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
		
		List<ClassSectionModel> list = criteria.list();
		return list;
	}
	
	
	
}
