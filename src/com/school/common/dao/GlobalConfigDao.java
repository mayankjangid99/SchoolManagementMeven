package com.school.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.GlobalConfigModel;

@Repository
public class GlobalConfigDao 
{

	private static Logger LOG = LoggerFactory.getLogger(GlobalConfigDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session getSession()
	{
		return sessionFactory.openSession();
	}
	
	
//	get One Global Config
	public GlobalConfigModel getGlobalConfigByCode(String schoolCode, GlobalConfigModel globalConfigModel) {
		Criteria criteria = getCurrentSession().createCriteria(GlobalConfigModel.class);
		if(globalConfigModel.getConfigCode() != null && !"".equals(globalConfigModel.getConfigCode())) {
			criteria.add(Restrictions.eq("configCode", globalConfigModel.getConfigCode()));
		}
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		GlobalConfigModel globalConfig =(GlobalConfigModel) criteria.uniqueResult();
		return globalConfig;
	}
	

	
	
//	Save GlobalConfig Information
	public void saveGlobalConfig(GlobalConfigModel globalConfig) {
		Session session = getSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(globalConfig);
			transaction.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			LOG .error("Error: saveGlobalConfig() in GlobalConfigDao", e);
		} finally {
			session.close();
		}
	}
	
	

//	Get All Global Config
	@SuppressWarnings("unchecked")
	public List<GlobalConfigModel> getAllGlobalConfigBySchoolCode(String schoolCode) {
		List<GlobalConfigModel> list = getCurrentSession().createQuery("FROM GlobalConfigModel WHERE schoolProfile.schoolCode=:sc").setParameter("sc", schoolCode).list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GlobalConfigModel> fetchGlobalConfig(String schoolCode, GlobalConfigModel globalConfig) {
		Criteria criteria = getCurrentSession().createCriteria(GlobalConfigModel.class);
		if(globalConfig.getConfigCode() != null && !"".equals(globalConfig.getConfigCode())) {
			criteria.add(Restrictions.like("configCode", "%" + globalConfig.getConfigCode() + "%"));
		}
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		List<GlobalConfigModel> globalConfigList = criteria.list();
		return globalConfigList;
	}
	
	
//	Get One Global Config
	public GlobalConfigModel editGlobalConfig(String schoolCode, GlobalConfigModel globalConfigModel) {
		Criteria criteria = getCurrentSession().createCriteria(GlobalConfigModel.class);
		criteria.add(Restrictions.eq("configCode", globalConfigModel.getConfigCode()));
		criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
		GlobalConfigModel globalConfig =(GlobalConfigModel) criteria.uniqueResult();
		return globalConfig;
	}
	
//	Update Global Config
	public void updateGlobalConfig(GlobalConfigModel globalConfigModel) {
		Session session = getSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.update(globalConfigModel);
			transaction.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			LOG .error("Error: updateGlobalConfig() in GlobalConfigDao", e);
		} finally {
			session.close();
		}
	}
}
