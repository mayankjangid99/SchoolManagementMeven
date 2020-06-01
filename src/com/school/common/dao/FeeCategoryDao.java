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

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.FeeCategoryModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;

@Repository
public class FeeCategoryDao extends DaoManagerBean {
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
	
	
	@SuppressWarnings("unchecked")
	public List<FeeCategoryModel> getFeeCategoryList() {
		List<FeeCategoryModel> list = new ArrayList<FeeCategoryModel>();
		try {
			Criteria criteria = getCurrentSession().createCriteria(FeeCategoryModel.class);
			criteria.addOrder(Order.asc("feeCategoryName"));
			list = criteria.list();
		} catch (HibernateException e) {
			log.error("ERROR: saveFeeCategory() in FeeCategoryDao ", e);
			//e.printStackTrace();
		}
		return list;
	}
	
	
	
	/*@SuppressWarnings("unchecked")
	public List<ClassFeeCategoryModel> getFeeCategoryListByClassCodeSchoolCodeAndType(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails, ClassInformationModel classInformation, SectionInformationModel sectionInformation, ClassFeeCategoryModel classFeeCategory) {
			Criteria criteria = getCurrentSession().createCriteria(ClassFeeCategoryModel.class);
			criteria.addOrder(Order.asc("schoolProfile.schoolCode"));
			
			if(schoolProfile.getSchoolCode() != null && !"".equals(schoolProfile.getSchoolCode()))
				criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolProfile.getSchoolCode()));
			
			if(sessionDetails.getSessionCode() != null && !"".equals(sessionDetails.getSessionCode()))
				criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionDetails.getSessionCode()));
			
			if(classInformation.getClassCode() != null && !"".equals(classInformation.getClassCode()))
				criteria.add(Restrictions.eq("classInformation.classCode", classInformation.getClassCode()));
			
			if(sectionInformation.getSectionCode() != null && !"".equals(sectionInformation.getSectionCode()))
				criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionInformation.getSectionCode()));
			
			if(classFeeCategory.getType() != null && !"".equals(classFeeCategory.getType()))
				criteria.add(Restrictions.eq("type", classFeeCategory.getType()));
			
			List<ClassFeeCategoryModel> classSectionList = criteria.list();
			return classSectionList;
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<ClassFeeCategoryModel> getFeeCategoryListByClassCodeSchoolCodeAndType(String schoolCode, String sessionCode, String classCode, String sectionCode, String type, String feeType) {
		Criteria criteria = getCurrentSession().createCriteria(ClassFeeCategoryModel.class);
		criteria.addOrder(Order.asc("schoolProfile.schoolCode"));
		
		if(schoolCode != null && !"".equals(schoolCode))
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		
		if(sessionCode != null && !"".equals(sessionCode))
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
		
		if(classCode != null && !"".equals(classCode))
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));

		if(sectionCode != null && !"".equals(sectionCode))
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
		
		if(type != null && !"".equals(type))
			criteria.add(Restrictions.eq("type", type));
		
		if(feeType != null && !"".equals(feeType))
			criteria.add(Restrictions.eq("feeType", feeType));
		
		List<ClassFeeCategoryModel> classSectionList = criteria.list();
		return classSectionList;
}
	
	
	public void deleteAllocatedFeeCategoryToClassBySchoolCodeAndClassCodeAndSecCode(SchoolProfileModel schoolProfile, SessionDetailsModel sessionDetails, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		getCurrentSession().createQuery("DELETE ClassFeeCategoryModel WHERE schoolProfile.schoolCode=:sc AND sessionDetails.sessionCode=:sd AND classInformation.classCode=:cc AND sectionInformation.sectionCode=:si")
		.setParameter("sc", schoolProfile.getSchoolCode()).setParameter("sd", sessionDetails.getSessionCode()).setParameter("cc", classInformation.getClassCode()).setParameter("si", sectionInformation.getSectionCode()).executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FeeCategoryModel> getFeeCategoryListByCategoryCode(FeeCategoryModel feeCategory) {
		List<FeeCategoryModel> list = new ArrayList<FeeCategoryModel>();
		try {
			Criteria criteria = getCurrentSession().createCriteria(FeeCategoryModel.class);
			criteria.addOrder(Order.asc("feeCategoryName"));
			criteria.add(Restrictions.eq("feeCategoryCode", feeCategory.getFeeCategoryCode()));
			list = criteria.list();
			return list;
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: getFeeCategoryListByCategoryCode() in FeeCategoryDao ", e);
		}		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FeeCategoryModel> getFeeCategoryListByCategoryCodes(Collection<String> feeCategoryCodes) {
		try {
			Criteria criteria = getCurrentSession().createCriteria(FeeCategoryModel.class);
			criteria.addOrder(Order.asc("feeCategoryName"));
			criteria.add(Restrictions.in("feeCategoryCode", feeCategoryCodes));
			List<FeeCategoryModel> list = criteria.list();
			return list;
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: getFeeCategoryListByCategoryCodes() in FeeCategoryDao ", e);
		}
		return null;
	}
	
	
	public void saveFeeCategory(FeeCategoryModel feeCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(feeCategory);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveFeeCategory() in FeeCategoryDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public boolean updateFeeCategory(FeeCategoryModel feeCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			int status = session.createQuery("UPDATE FeeCategoryModel SET feeCategoryName=:fn WHERE feeCategoryCode=:fd").setParameter("fn", feeCategory.getFeeCategoryName())
			.setParameter("fd", feeCategory.getFeeCategoryCode()).executeUpdate();
//			session.update(classInformation);
			transaction.commit();
			session.flush();
			if(status > 0)
				return true; 
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: updateFeeCategory() in FeeCategoryDao ", e);
		}
		finally {
			session.close();
		}
		return false;
	}
	
	
	
	public boolean deleteAllocatedFeeCategory(String schoolCode, String sessionCode, String classCode, String sectionCode, String feeCategoryCode, String type)
	{
		int i = getCurrentSession().createQuery("DELETE ClassFeeCategoryModel WHERE schoolProfile.schoolCode=:sc AND sessionDetails.sessionCode=:sd AND classInformation.classCode=:cc AND sectionInformation.sectionCode=:si AND type=:ty")
		.setParameter("sc", schoolCode).setParameter("sd", sessionCode).setParameter("cc", classCode).setParameter("si", sectionCode).setParameter("ty", type).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	
	/*public void deleteFeeCategory(FeeCategoryModel feeCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.createQuery("DELETE FeeCategoryModel WHERE feeCategoryCode=:fd").setParameter("fd", feeCategory.getFeeCategoryCode()).executeUpdate();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: updateFeeCategory() in FeeCategoryDao ", e);
		}
		finally {
			session.close();
		}
	}*/
	
	
	
	public void deleteFeeCategory(FeeCategoryModel feeCategory)
	{
		Session session = openSession();
		try {
			Serializable feeCategoryCode = new String(feeCategory.getFeeCategoryCode());
			Object persistentInstance = session.load(FeeCategoryModel.class, feeCategoryCode);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			}
			Transaction transaction = session.beginTransaction();
			//session.createQuery("DELETE FeeCategoryModel WHERE feeCategoryCode=:fd").setParameter("fd", feeCategory.getFeeCategoryCode()).executeUpdate();
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: updateFeeCategory() in FeeCategoryDao ", e);
		}
		finally {
			session.close();
		}
	}
	

	public void saveAllocatedFeeCategoryToClass(ClassInformationModel feeCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(feeCategory);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	
	public void saveAllocatedFeeCategoryToClassSection(ClassFeeCategoryModel classFeeCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(classFeeCategory);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveAllocatedFeeCategoryToClass() in FeeCategoryDao " + e);
		}
		finally {
			session.close();
		}
	}
	
//	fetch Fee Category 
	public List<FeeCategoryModel> fetchFeeCategoryWithClass(FeeCategoryModel feeCategory) 
	{
		try {
			if(feeCategory.getFeeCategoryCode() != null && !"".equals(feeCategory.getFeeCategoryCode()))
			{
				@SuppressWarnings("unchecked")
				List<FeeCategoryModel> list = getCurrentSession().createQuery("FROM FeeCategoryModel WHERE feeCategoryCode=:i")
				.setParameter("i", feeCategory.getFeeCategoryCode()).list();
				return list;
			}
			else
			{
				@SuppressWarnings("unchecked")
				List<FeeCategoryModel> list = getCurrentSession().createQuery("FROM FeeCategoryModel").list();
				return list;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ERROR: fetchFeeCategoryWithClass() in FeeCategoryDao ", e);
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ClassFeeCategoryModel> getClassFeeCategoryByClassCodeSecCodeFeeCodeAndType(String schoolCode, String sessionCode, String classCode, String sectionCode, String feeCategoryCode, String type, String feeType) 
	{
		Criteria criteria = getCurrentSession().createCriteria(ClassFeeCategoryModel.class);
		criteria.addOrder(Order.asc("schoolProfile.schoolCode"));
		
		if(schoolCode != null && !"".equals(schoolCode))
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		
		if(sessionCode != null && !"".equals(sessionCode))
			criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
		
		if(classCode != null && !"".equals(classCode))
			criteria.add(Restrictions.eq("classInformation.classCode", classCode));
		
		if(sectionCode != null && !"".equals(sectionCode))
			criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
		
		if(feeCategoryCode != null && !"".equals(feeCategoryCode))
			criteria.add(Restrictions.eq("feeCategory.feeCategoryCode", feeCategoryCode));
		
		if(type != null && !"".equals(type))
			criteria.add(Restrictions.eq("type", type));
		
		if(feeType != null && !"".equals(feeType))
			criteria.add(Restrictions.eq("feeType", feeType));
		
		List<ClassFeeCategoryModel> list = criteria.list();
		return list;
	}
	
	
	public void changeAllocatedFeeCategoryStatus(String schoolCode, String sessionCode, String classCode, String sectionCode, String feeCategoryCode, String status)
	{
		getCurrentSession().createQuery("UPDATE ClassFeeCategoryModel SET status=:st WHERE schoolProfile.schoolCode=:sc AND sessionDetails.sessionCode=:sd AND classInformation.classCode=:cc AND sectionInformation.sectionCode=:si AND feeCategory.feeCategoryCode=:fc")
		.setParameter("st", status).setParameter("sc", schoolCode).setParameter("sd", sessionCode).setParameter("cc", classCode).setParameter("si", sectionCode).setParameter("fc", feeCategoryCode).executeUpdate();
	}
	
	public List<ClassFeeCategoryModel> getFeeCategories(String schoolCode, String sessionCode, String classCode, String sectionCode, String feeType, String status){
		try {
			String query = "";
			if(feeType != null && !feeType.isEmpty())
				query = query + " AND feeType='"+feeType+"'";
			if(status != null && !status.isEmpty())
				query = query + " AND status='"+status+"'";
			
			return findDynamicQuery("FROM ClassFeeCategoryModel WHERE schoolProfile.schoolCode=:p1 AND sessionDetails.sessionCode=:p2 AND classInformation.classCode=:p3 AND sectionInformation.sectionCode=:p4" + query, new Object[]{schoolCode, sessionCode, classCode, sectionCode});
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ERROR: getAdditionaFeeCategories() in FeeCategoryDao ", e);
		}
		return null;
	}
	
	
}
