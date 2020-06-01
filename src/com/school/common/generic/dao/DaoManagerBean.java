/**
 * @(#)GenericEAO.java			Jul 30, 2009	6:45:40 PM	IST
 *
 *
 * Kott Software Pvt. Ltd.
 *
 * Copyright (c) 2002 OCR Services Inc., All rights reserved.
 *
 *
 *
 * @author		Rajiv Radhakrishnan
 * @version		1.0
 */
package com.school.common.generic.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.model.EntityModel;


/**
 * DaoManagerBean class implements the DaoManagerLocal methods<br>
 * The actual persistence mechanism is defined in this class.
 *
 * @author Monu Kumar
 * @version		1.0
 */
@SuppressWarnings({"rawtypes"})
@Service("daoManagerBean")
public class DaoManagerBean implements DaoManagerLocal {

	@Autowired
	private GenericDaoCommonLocal genericDaoCommonLocal;
	
	
	//private static Logger LOG = LoggerFactory.getLogger(DaoManagerBean.class);
	

	
	
	/* (non-Javadoc)
	 * @see com.share.common.generic.dao.DaoManagerLocal#load(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public Object load(Class classType, Serializable pk) throws Exception {
		try {
			return genericDaoCommonLocal.load(classType, pk);
		} catch (Exception e) {
			//LOG.error("ERROR: load() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	@Override
	public Serializable save(EntityModel entity) throws Exception {
		Serializable serializable = null;
		try {
			serializable = genericDaoCommonLocal.save(entity);
		} catch (HibernateException e) {
			//LOG.error("ERROR: save() in DaoManagerBean ", e);
			throw e;
		}
		return serializable;
	}


	/**
	 * Create a new record for the enity.
	 *
	 * @param entity The enity which has to be created
	 */
	@Override
	public void persist(EntityModel entity) throws Exception {
		try {
			genericDaoCommonLocal.persist(entity);
		} catch (HibernateException e) {
			//LOG.error("ERROR: persist() in DaoManagerBean ", e);
			throw e;
		}
	}

	/**
	 * Get the entity.
	 *
	 * @param pk The Serializable primary key or the Primary Key class (for composite primary keys)
	 * @param type the type
	 *
	 * @return the Entity object
	 */
	@Override
	public Object get(Class type, Serializable pk) throws Exception {
		try {
			return genericDaoCommonLocal.get(type, pk);
		} catch (Exception e) {
			//LOG.error("ERROR: get() in DaoManagerBean ", e);
			throw e;
		}
	}

	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void update(EntityModel entity) throws Exception {
		try {
			genericDaoCommonLocal.update(entity);
		} catch (HibernateException e) {
			//LOG.error("ERROR: update() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	
	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void merge(EntityModel entity) throws Exception {
		try {
			genericDaoCommonLocal.merge(entity);
		} catch (HibernateException e) {
			//LOG.error("ERROR: merge() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	/**
	 * Save And Updates an existing record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void saveOrUpdate(EntityModel entity) throws Exception {
		try {
			genericDaoCommonLocal.saveOrUpdate(entity);
		} catch (HibernateException e) {
			//LOG.error("ERROR: saveOrUpdate() in DaoManagerBean ", e);
			throw e;
		}
	}

	/**
	 * Deletes the record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void delete(EntityModel entity) throws Exception {
		try {
			genericDaoCommonLocal.delete(entity);
		} catch (Exception e) {
			//LOG.error("ERROR: delete() in DaoManagerBean ", e);
			throw e;
		}
	}

	
	/* (non-Javadoc)
	 * @see com.share.common.generic.dao.DaoManagerLocal#deleteCascade(com.share.common.model.EntityModel, java.io.Serializable)
	 */
	@Override
	public void deleteCascade(EntityModel entity, Serializable pk) throws Exception {
		try {
			entity = (EntityModel) load(entity.getClass(), pk);
			delete(entity);
		} catch (Exception e) {
			//LOG.error("ERROR: deleteCascade() in DaoManagerBean ", e);
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T> List<T> findDynamicQuery(String queryString, Object [] parameters) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicQuery(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicQuery() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	@Override
	public <T extends EntityModel> List<T> findDynamicQuery(String queryString) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicQuery(queryString);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicQuery() in DaoManagerBean ", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicScalar(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicScalar(String queryString, Object [] parameters) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicScalar(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicScalar() in DaoManagerBean ", e);
			throw e;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see com.ocr.les.manager.GenericEaoLesLocal#findDynamicQueryWithFields(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicQueryWithFields(String queryString, Object [] parameters, Class classType) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicQueryWithFields(queryString, parameters, classType);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicQueryWithFields() in DaoManagerBean ", e);
			throw e;
		}
	}

	/**
	 * find dynamic query using a native SQL Statement with UPDATE or DELETE
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the int value
	 */
	@Override
	public int findDynamicNativeQuery(String queryString, Object[] parameters) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicNativeQuery(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}

	/**
	 * find dynamic query using a native SQL Statement with SELECT of given fields
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	@Override
	public List<?> findDynamicNativeQueryScalar(String queryString, Object[] parameters) throws Exception {
		try {
			return  genericDaoCommonLocal.findDynamicNativeQueryScalar(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicNativeQueryScalar() in DaoManagerBean ", e);
			throw e;
		}
	}
	/**
	 * find dynamic query using a native SQL Statement that retrieves a single entity type
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicNativeQuery(String queryString, Object[] parameters, Class classType) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicNativeQuery(queryString, parameters, classType);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}

	
	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicQueryPagination(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T> List<T> findDynamicQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicQueryPagination(queryString, parameters, start, chunkSize);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicQueryPagination(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T extends EntityModel> List<T> findNativeQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) throws Exception {
		try {
			return genericDaoCommonLocal.findNativeQueryPagination(queryString, parameters, start, chunkSize);
		} catch (Exception e) {
			//LOG.error("ERROR: findNativeQueryPagination() in DaoManagerBean ", e);
			throw e;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#findDynamicQueryWithFieldsPagination(java.lang.String, java.lang.Object[], java.lang.Class, int, int)
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicQueryWithFieldsPagination(String queryString, Object [] parameters, Class classType, int start, int chunkSize) throws Exception {
		try {
			return genericDaoCommonLocal.findDynamicQueryWithFieldsPagination(queryString, parameters, classType, start, chunkSize);
		} catch (Exception e) {
			//LOG.error("ERROR: findDynamicQueryWithFieldsPagination() in DaoManagerBean ", e);
			throw e;
		}
	}



	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findNativeQuery(java.lang.String)
	 */
	@Override
	public <T extends EntityModel> List<T> findNativeQuery(String queryString) throws Exception {
		try {
			return genericDaoCommonLocal.findNativeQuery(queryString);
		} catch (Exception e) {
			//LOG.error("ERROR: findNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#executeUpdateJPQLQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int executeUpdateHQLQuery(String queryString, Object[] parameters) throws Exception {
		try {
			return genericDaoCommonLocal.executeUpdateHQLQuery(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: findNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#executeUpdateJPQLQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Object uniqueResultHQLQuery(String queryString, Object[] parameters) throws Exception {
		try {
			return genericDaoCommonLocal.uniqueResultHQLQuery(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: findNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#executeUpdateNativeQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int executeUpdateNativeQuery(String queryString, Object[] parameters) throws Exception {
		try {
			return genericDaoCommonLocal.executeUpdateNativeQuery(queryString, parameters);
		} catch (Exception e) {
			//LOG.error("ERROR: executeUpdateNativeQuery() in DaoManagerBean ", e);
			throw e;
		}
	}
	
}
