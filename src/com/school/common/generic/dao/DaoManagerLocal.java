/**
 * @(#)BaseEAO.java			Jul 30, 2009	6:44:12 PM	IST
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

import com.school.common.model.EntityModel;


/**
 * The GenericDaoCommonLocal interface defines the four CRUD methods and other persistent methods. <br>
 * The Entity Access Object (EAO) pattern
 * decouples the entity access logic from the business logic and improves code maintainability. It
 * allows to easily change the underlying entity access code without affecting the business logic
 *
 * @author Monu Kumar
 * @version		1.0
 */

@SuppressWarnings("rawtypes")
public interface DaoManagerLocal {

	/**
	 * Load the record.
	 *
	 * @param entity the entity
	 */
	public Object load(Class classType, Serializable pk) throws Exception;
	
	/**
	 * Save a new record for the enity.
	 *
	 * @param entity the entity
	 */
	Serializable save(EntityModel entity) throws Exception;
	
	/**
	 * persist a new record for the enity.
	 *
	 * @param entity the entity
	 */
	public void persist(EntityModel entity) throws Exception;

	/**
	 * Get the entity.
	 *
	 * @param pk The Serializable primary key or the Primary Key class (for composite primary keys)
	 * @param type the type
	 *
	 * @return the Entity object
	 */
	Object get(Class type, Serializable pk) throws Exception;

	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	void update(EntityModel entity) throws Exception;
	
	
	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	void merge(EntityModel entity) throws Exception;
	
	/**
	 * Updates an existing record.
	 *
	 * @param entity the entity
	 */
	void saveOrUpdate(EntityModel entity) throws Exception;

	/**
	 * Deletes the record.
	 *
	 * @param entity the entity
	 */
	void delete(EntityModel entity) throws Exception;
	
	/**
	 * DeletesCascade the record.
	 *
	 * @param entity the entity
	 */
	public void deleteCascade(EntityModel entity, Serializable pk) throws Exception;

	/**
	 * Find dynamic query.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T> List<T> findDynamicQuery(String queryString, Object [] parameters) throws Exception;
	
	
	/**
	 * Find dynamic query.
	 *
	 * @param queryString the query string
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T extends EntityModel> List<T> findDynamicQuery(String queryString) throws Exception;

	/**
	 * Find dynamic scalar.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T extends EntityModel> List<T> findDynamicScalar(String queryString, Object [] parameters) throws Exception;

	/**
	 * Find dynamic with table fields.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 * @param classType the Class
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T extends EntityModel> List<T> findDynamicQueryWithFields(String queryString, Object [] parameters, Class classType) throws Exception;

	/**
	 * find dynamic query using a native SQL Statement with UPDATE or DELETE
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the int value
	 */
	public int findDynamicNativeQuery(String queryString, Object[] parameters) throws Exception;

	/**
	 * find dynamic query using a native SQL Statement with SELECT of given fields
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	public List<?> findDynamicNativeQueryScalar(String queryString, Object[] parameters) throws Exception;

	/**
	 * find dynamic query using a native SQL Statement that retrieves a single entity type
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return the <T extends EntityModel> List<T>
	 */
	//public <T extends EntityModel> List<T> findDynamicNativeQuery(String queryString, Object[] parameters, Class classType) throws Exception;

	/**
	 * Find dynamic with table fields with limited resultset.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 * @param classType the Class
	 * @param start		the index
	 * @param chunkSize  the resultsize
	 * @return the <T extends EntityModel> List<T>
	 * @author Naveen John, 24th November 2010
	 */
	public <T> List<T> findDynamicQueryPagination(String queryString, Object [] parameters, int start, int chunkSize) throws Exception;

	/**
	 * Find dynamic with table fields with limited resultset.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 * @param classType the Class
	 * @param start		the index
	 * @param chunkSize  the resultsize
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T extends EntityModel> List<T> findDynamicQueryWithFieldsPagination(String queryString, Object [] parameters, Class classType, int start, int chunkSize) throws Exception;

	/**
	 * Native Query with Pagination.
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 * @param start		the index
	 * @param chunkSize  the resultsize
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T extends EntityModel> List<T> findNativeQueryPagination(String queryString,Object [] parameters, int start, int chunkSize) throws Exception;

	/**
	 * Find Native Query.
	 *
	 * @param queryString the query string
	 * @return the <T extends EntityModel> List<T>
	 */
	public <T extends EntityModel> List<T> findNativeQuery(String queryString) throws Exception;

	/**
	 *
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return The number of entities updated or deleted.
	 */
	public int executeUpdateNativeQuery(String queryString, Object [] parameters) throws Exception;

	/**
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return The number of entities updated or deleted.
	 */
	public int executeUpdateHQLQuery(String queryString, Object [] parameters) throws Exception;
	
	/**
	 * @param queryString the query string
	 * @param parameters the parameters
	 *
	 * @return Unique Result.
	 */
	public Object uniqueResultHQLQuery(String queryString, Object [] parameters) throws Exception;

	public <T extends EntityModel> List<T> findDynamicNativeQuery(String queryString, Object[] parameters, Class classType) throws Exception;
	
}
