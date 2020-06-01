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

import com.school.common.model.MicroServiceConfigModel;

@Repository
public class MicroServiceConfigDao 
{

	private static Logger LOG = LoggerFactory.getLogger(MicroServiceConfigDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	
//	get One Micro Service Config
	public MicroServiceConfigModel getMicroServiceConfigByCode(MicroServiceConfigModel microServiceConfigModel) {
		Criteria criteria = getCurrentSession().createCriteria(MicroServiceConfigModel.class);
		if(microServiceConfigModel.getConfigCode() != null && !"".equals(microServiceConfigModel.getConfigCode())) {
			criteria.add(Restrictions.eq("configCode", microServiceConfigModel.getConfigCode()));
		}
		MicroServiceConfigModel microServiceConfig =(MicroServiceConfigModel) criteria.uniqueResult();
		return microServiceConfig;
	}
	

	
	
//	Save MicroServiceConfig Information
	public void saveMicroServiceConfig(MicroServiceConfigModel microServiceConfig)
	{
		Session session = getSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(microServiceConfig);
			transaction.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			LOG .error("Error: saveMicroServiceConfig() in MicroServiceConfigDao", e);
		}
		finally {
			session.close();
		}
	}
	
	

//	Get All Micro Service Config
	public List<MicroServiceConfigModel> getAllMicroServiceConfigs()
	{
		@SuppressWarnings("unchecked")
		List<MicroServiceConfigModel> list = getCurrentSession().createQuery("FROM MicroServiceConfigModel").list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MicroServiceConfigModel> fetchMicroServiceConfig(String schoolCode, MicroServiceConfigModel microServiceConfig) {
		Criteria criteria = getCurrentSession().createCriteria(MicroServiceConfigModel.class);
		if(microServiceConfig.getConfigCode() != null && !microServiceConfig.getConfigCode().isEmpty()) {
			criteria.add(Restrictions.eq("configCode", microServiceConfig.getConfigCode()));
		}
		List<MicroServiceConfigModel> microServiceConfigList = criteria.list();
		return microServiceConfigList;
	}
	
	
//	Get One MicroService Config
	public MicroServiceConfigModel editMicroServiceConfig(String schoolCode, MicroServiceConfigModel microServiceConfigModel)
	{
		Criteria criteria = getCurrentSession().createCriteria(MicroServiceConfigModel.class);
		criteria.add(Restrictions.eq("configCode", microServiceConfigModel.getConfigCode()));
		MicroServiceConfigModel microServiceConfig =(MicroServiceConfigModel) criteria.uniqueResult();
		return microServiceConfig;
	}
	
//	Update MicroService Config
	public void updateMicroServiceConfig(MicroServiceConfigModel microServiceConfigModel)
	{
		Session session = getSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.update(microServiceConfigModel);
			transaction.commit();
		} catch (Exception e) {
			//e.printStackTrace();
			LOG .error("Error: updateMicroServiceConfig() in MicroServiceConfigDao", e);
		}
		finally {
			session.close();
		}
	}
}
