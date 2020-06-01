package com.school.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.ApplicationSettingsModel;

@Repository
public class ApplicationSettingsDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger LOG = LoggerFactory.getLogger(ApplicationSettingsDao.class);
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	public ApplicationSettingsModel getApplicationSetting(ApplicationSettingsModel settingsModel){
		return getCurrentSession().get(ApplicationSettingsModel.class, settingsModel.getSettingCode());
	}
	
	public boolean saveAndUpdateApplicationSetting(ApplicationSettingsModel applicationSettingsModel){
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(applicationSettingsModel);
			transaction.commit();
			session.flush();
			return true;
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: ApplicationSettingsDao in saveAndUpdateApplicationSetting()" + e);
			return false;
		}
		finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ApplicationSettingsModel> getApplicationSettingsForSchool(String schoolCode){
		Criteria criteria = getCurrentSession().createCriteria(ApplicationSettingsModel.class);
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		return criteria.list();
	}
}
