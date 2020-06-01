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

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.EntityModel;


/**
 * GenericDaoCommonBean class implements the GenericDaoCommonLocal methods<br>
 * The actual persistence mechanism is defined in this class.
 *
 * @author Monu Kumar
 * @version		1.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Repository("genericDaoCommonBean")
public class GenericDaoCommonBean implements GenericDaoCommonLocal {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	private static Logger LOG = LoggerFactory.getLogger(GenericDaoCommonBean.class);

	
	@Override
	public Serializable save(EntityModel entity) {
		Session session = openSession();
		Serializable serializable = null;
		try {
			Transaction transaction = session.beginTransaction();
			serializable = session.save(entity);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//LOG.error("ERROR: save() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
		}
		return serializable;
	}
	
	
	/**
	 * Create a new record for the enity.
	 *
	 * @param entity The enity which has to be created
	 */
	@Override
	public void persist(EntityModel entity) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(entity);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//LOG.error("ERROR: persist() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
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
	public Object get(Class classType, Serializable pk) {
		Session session = openSession();
		Object obj = null;
		try {
			Transaction transaction = session.beginTransaction();
			obj = getCurrentSession().get(classType, pk);
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			//LOG.error("ERROR: get() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
		}
		return obj;
	}

	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void update(EntityModel entity) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//LOG.error("ERROR: update() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void merge(EntityModel entity) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.merge(entity);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//LOG.error("ERROR: update() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Save And Updates an existing record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void saveOrUpdate(EntityModel entity) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//LOG.error("ERROR: saveOrUpdate() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
		}
	}

	
	/**
	 * Load the record.
	 *
	 * @param entity the entity
	 */
	@Override
	public Object load(Class classType, Serializable pk) {
		return getCurrentSession().load(classType, pk);
	}
	
	
	/**
	 * Deletes the record.
	 *
	 * @param entity the entity
	 */
	@Override
	public void delete(EntityModel entity) {
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.delete(session.merge(entity));
			/*entity = session.load(EntityModel.class, pk);
			session.delete(entity);*/
			transaction.commit();
			session.flush();
		} catch (Exception e) {
			//LOG.error("ERROR: delete() in GenericDaoCommonBean ", e);
			throw e;
		}
		finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T> List<T> findDynamicQuery(String queryString, Object [] parameters) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			return query.list();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public <T extends EntityModel> List<T> findDynamicQuery(String queryString) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			return query.list();
		} catch (Exception e) {
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicScalar(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicScalar(String queryString, Object [] parameters) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			return query.list();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Sets the query parameters. Ignores null and empty String
	 *
	 * @param query the query
	 * @param parameters the parameters
	 *
	 * @return the query
	 */
	private Query setQueryParameters(Query query, Object [] parameters) {
		for (int i=0; i< parameters.length; i++) {
			if (parameters [i] != null) {
				// Check for empty String
				if (parameters [i] instanceof String) {
					String newName = (String) parameters [i];
					// If empty String, then ignore
					if (!newName.equals(""))
						query.setParameter("p"+(i+1), parameters [i]);
				} else
					query.setParameter("p"+(i+1), parameters [i]);
			}
		}
		return query;
	}
	
	
	private SQLQuery setQueryParameters(SQLQuery query, Object [] parameters) {
		for (int i=0; i< parameters.length; i++) {
			if (parameters [i] != null) {
				// Check for empty String
				if (parameters [i] instanceof String) {
					String newName = (String) parameters [i];
					// If empty String, then ignore
					if (!newName.equals(""))
						query.setParameter("p"+(i+1), parameters [i]);
				} else
					query.setParameter("p"+(i+1), parameters [i]);
			}
		}
		return query;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ocr.les.manager.GenericEaoLesLocal#findDynamicQueryWithFields(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicQueryWithFields(String queryString, Object [] parameters, Class classType) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			List<T> coll = query.list();
			return populateEntity(coll,queryString,classType);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Populates the Entity with the columns data from the table
	 * Modified the query splitting logic, to support the field names in the select query that contains string "FROM",
	 * @param coll
	 * @param queryString
	 * @param classType
	 * @return <T extends EntityModel> List<T>
	 */
	private <T extends EntityModel> List<T> populateEntity(List<T> coll, String queryString, Class classType ){
		String strForTokenizing = queryString.substring(0,queryString.toUpperCase().indexOf(" FROM "));
		strForTokenizing = strForTokenizing.substring(strForTokenizing.toUpperCase().indexOf("SELECT")+7);
		StringTokenizer fields = new StringTokenizer(strForTokenizing,",");
		ArrayList fieldArray = new ArrayList();
		while(fields.hasMoreTokens()){
			String fieldName = fields.nextToken().toString();
			fieldName = fieldName.substring(fieldName.indexOf(".") +1).trim();
			fieldArray.add(fieldName);
		}
		Iterator it  = coll.iterator();
		Object obj;
		ArrayList list = new ArrayList();
		try {
			while(it.hasNext()){
				obj = classType.newInstance();
				Object[] objectArray = (Object[])it.next();

				for(int i=0;i<objectArray.length;i++){
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldArray.get(i).toString(),classType);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(obj, objectArray[i]);
				}
				list.add(obj);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: populateEntity() in GenericDaoCommonBean ", e);
		}
		return list;
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
	public int findDynamicNativeQuery(String queryString, Object[] parameters){
		try {
			Query query = getCurrentSession().createSQLQuery(queryString);
			query = setQueryParameters(query, parameters);
			return query.executeUpdate();
		} catch (Exception e) {
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
	public List<?> findDynamicNativeQueryScalar(String queryString, Object[] parameters){
		try {
			SQLQuery query = getCurrentSession().createSQLQuery(queryString);
			query = setQueryParameters(query, parameters);
			return  query.list();
		} catch (Exception e) {
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
	public <T extends EntityModel> List<T> findDynamicNativeQuery(String queryString, Object[] parameters, Class classType){
		try {
			SQLQuery query = getCurrentSession().createSQLQuery(queryString);
			query.addEntity(classType);
			query = setQueryParameters(query, parameters);
			//query.setHint("org.hibernate.fetchSize", 2);
			return query.list();
		} catch (Exception e) {
			throw e;
		}
	}

	
	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicQueryPagination(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T> List<T> findDynamicQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			query.setFirstResult(start);
			query.setMaxResults(chunkSize);
			return query.list();
		} catch (Exception e) {
			throw e;
		}
	}
	//	Added by Sreejith K  on 22th November 2011
	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findDynamicQueryPagination(java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T extends EntityModel> List<T> findNativeQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) {
		try {
			Query query = getCurrentSession().createSQLQuery(queryString);
			query = setQueryParameters(query, parameters);
			query.setFirstResult(start);
			query.setMaxResults(chunkSize);
			return query.list();
		} catch (Exception e) {
			throw e;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#findDynamicQueryWithFieldsPagination(java.lang.String, java.lang.Object[], java.lang.Class, int, int)
	 */
	@Override
	public <T extends EntityModel> List<T> findDynamicQueryWithFieldsPagination(String queryString, Object [] parameters, Class classType, int start, int chunkSize) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			query.setFirstResult(start);
			query.setMaxResults(chunkSize);
			List<T> coll = query.list();
			return populateEntity(coll,queryString,classType);
		} catch (Exception e) {
			throw e;
		}
	}



	/* (non-Javadoc)
	 * @see com.ocr.eao.manager.BaseEAO#findNativeQuery(java.lang.String)
	 */
	@Override
	public <T extends EntityModel> List<T> findNativeQuery(String queryString) {
		try {
			Query query = getCurrentSession().createSQLQuery(queryString);
			return query.list();
		} catch (Exception e) {
			throw e;
		}

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#executeUpdateJPQLQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int executeUpdateHQLQuery(String queryString, Object[] parameters) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			return query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#executeUpdateJPQLQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Object uniqueResultHQLQuery(String queryString, Object[] parameters) {
		try {
			Query query = getCurrentSession().createQuery(queryString);
			query = setQueryParameters(query, parameters);
			return query.uniqueResult();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.share.generic.dao.GenericEaoCommonLocal#executeUpdateNativeQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int executeUpdateNativeQuery(String queryString, Object[] parameters) {
		try {
			Query query = getCurrentSession().createSQLQuery(queryString);
			query = setQueryParameters(query, parameters);
			return query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
}
