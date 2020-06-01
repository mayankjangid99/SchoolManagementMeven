package com.school.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSectionModel;
import com.school.common.model.PaymentCategoryModel;
import com.school.common.model.SchoolPaymentCategoryModel;

@Repository
public class PaymentCategoryDao 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger LOG = LoggerFactory.getLogger(PaymentCategoryDao.class);
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	
	public void savePaymentCategory(PaymentCategoryModel paymentCategory){
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(paymentCategory);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: savePaymentCategory() in PaymentCategoryDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public void updatePaymentCategory(PaymentCategoryModel paymentCategory)
	{
		getCurrentSession().createQuery("UPDATE PaymentCategoryModel SET paymentCategoryName=:cn WHERE paymentCategoryCode=:cd")
		.setParameter("cn", paymentCategory.getPaymentCategoryName()).setParameter("cd", paymentCategory.getPaymentCategoryCode()).executeUpdate();

	}
	
	
	
	public void deletePaymentCategory(PaymentCategoryModel paymentCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Serializable paymentCategoryCode = new String(paymentCategory.getPaymentCategoryCode());
			Object persistentInstance = session.load(PaymentCategoryModel.class, paymentCategoryCode);
			if (persistentInstance != null) {
			    session.delete(persistentInstance);
			}
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: deletePaymentCategory() in PaymentCategoryDao ", e);
		}
		finally {
			session.flush();
			session.close();
		}
	}
	
	
	public boolean deleteAllocatedPaymentCategory(SchoolPaymentCategoryModel schoolPaymentCategory)
	{
		int i = getCurrentSession().createQuery("DELETE SchoolPaymentCategoryModel WHERE schoolPaymentCategoryId=:sc")
		.setParameter("sc", schoolPaymentCategory.getSchoolPaymentCategoryId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public void saveAllocatedPaymentCategoryToSchool(SchoolPaymentCategoryModel schoolPaymentCategory)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(schoolPaymentCategory);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveAllocatedPaymentCategoryToSchool() in PaymentCategoryDao " + e);
		}
		finally {
			session.close();
		}
	}
	
	
	public List<ClassInformationModel> getAllClassList() {
		Criteria criteria = getCurrentSession().createCriteria(ClassInformationModel.class);
		criteria.addOrder(Order.asc("className"));
		criteria.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<ClassInformationModel> list = criteria.list();
		return list;
	}
	
	public PaymentCategoryModel getPaymentCategoryByCode(PaymentCategoryModel paymentCategory) {
		PaymentCategoryModel paymentCategoryModel = getCurrentSession().get(PaymentCategoryModel.class, paymentCategory.getPaymentCategoryCode());
		return paymentCategoryModel;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ClassInformationModel> getClassListBySchoolCode(String schoolCode) {
		List<ClassSectionModel> list = getCurrentSession().createQuery("FROM ClassSectionModel WHERE schoolProfile.schoolCode=:ssc GROUP BY classInformation.classCode")
		.setParameter("ssc", schoolCode).setCacheable(true).list();
		List<ClassInformationModel> list2 = new ArrayList<ClassInformationModel>();
		for(ClassSectionModel classSection : list)
		{
			list2.add(classSection.getClassInformation());
		}
		return list2;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ClassInformationModel> getPaymentCategoryBySchoolCodeAndSessionCode(String schoolCode, String session) {
		List<ClassSectionModel> list = getCurrentSession().createSQLQuery("FROM ClassSectionModel WHERE schoolProfile.schoolCode=:ssc GROUP BY classInformation.classCode")
		.setParameter("ssc", schoolCode).list();
		List<ClassInformationModel> list2 = new ArrayList<ClassInformationModel>();
		for(ClassSectionModel classSection : list)
		{
			list2.add(classSection.getClassInformation());
		}
		return list2;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentCategoryModel> getNotAllocatedPaymentCategory(String schoolCode, String sessionCode){
		List<Object[]> list = getCurrentSession().createSQLQuery("SELECT a.payment_category_code, a.payment_category_name FROM payment_category a WHERE a.payment_category_code NOT IN (SELECT b.payment_category_code FROM school_payment_category b WHERE school_code='"+schoolCode+"' and session_code='"+sessionCode+"')").list();
		List<PaymentCategoryModel> resultList = new ArrayList<PaymentCategoryModel>();
		PaymentCategoryModel paymentCategory = null;
		for(Object[] obj : list){
			paymentCategory = new PaymentCategoryModel();
			paymentCategory.setPaymentCategoryCode((String)obj[0]);
			paymentCategory.setPaymentCategoryName((String)obj[1]);
			resultList.add(paymentCategory);
		}
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentCategoryModel> getAllocatedPaymentCategory(String schoolCode, String sessionCode){
		List<Object[]> list = getCurrentSession().createSQLQuery("SELECT a.payment_category_code, a.payment_category_name FROM payment_category a INNER JOIN school_payment_category b WHERE a.payment_category_code = b.payment_category_code AND school_code='"+schoolCode+"' and session_code='"+sessionCode+"'").list();
		List<PaymentCategoryModel> resultList = new ArrayList<PaymentCategoryModel>();
		PaymentCategoryModel paymentCategory = null;
		for(Object[] obj : list){
			paymentCategory = new PaymentCategoryModel();
			paymentCategory.setPaymentCategoryCode((String)obj[0]);
			paymentCategory.setPaymentCategoryName((String)obj[1]);
			resultList.add(paymentCategory);
		}
		return resultList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentCategoryModel> getAllPaymentCategory(){
		List<PaymentCategoryModel> list = getCurrentSession().createQuery("FROM PaymentCategoryModel").list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SchoolPaymentCategoryModel> getAllocatedSchoolPaymentCategory(String schoolCode, String sessionCode, PaymentCategoryModel paymentCategory){
		if(paymentCategory != null && !"".equals(paymentCategory.getPaymentCategoryCode())){
			List<SchoolPaymentCategoryModel> list = getCurrentSession().createQuery("FROM SchoolPaymentCategoryModel WHERE schoolProfile.schoolCode=:sc AND sessionDetails.sessionCode=:ss AND paymentCategory.paymentCategoryCode=:pc")
					.setParameter("sc", schoolCode).setParameter("ss", sessionCode).setParameter("pc", paymentCategory.getPaymentCategoryCode()).list();
			return list;
		} else {
			List<SchoolPaymentCategoryModel> list = getCurrentSession().createQuery("FROM SchoolPaymentCategoryModel WHERE schoolProfile.schoolCode=:sc AND sessionDetails.sessionCode=:ss")
					.setParameter("sc", schoolCode).setParameter("ss", sessionCode).list();
			return list;
		}
	}
}
