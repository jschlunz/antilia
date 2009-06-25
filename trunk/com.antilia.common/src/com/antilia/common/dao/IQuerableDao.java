/**
 * 
 */
package com.antilia.common.dao;

import java.io.Serializable;
import java.util.List;

import com.antilia.common.query.IQuery;

/**
 * Interface modeling a read only DAO.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableDao<E extends Serializable> extends Serializable {
	
	/**
	 * Returns a list of all elements matching the query.
	 * 
	 * @param query The query.
	 * @return Returns a list of all elements matching the query.
	 */
	List<E> findAll(IQuery<E> query);
	
	
	/**
	 * Returns a list of all elements of a certain class.
	 * 
	 * @param beanClass
	 * @return
	 */
	List<E> findAll(Class<E> beanClass);
	
	/**
	 * Finds all beans matching <code>bean</code>
	 * 
	 * @param bean
	 * @return
	 */
	List<E> findByExample(E bean);
	
	/**
	 * Find an entity given its ID.
	 * 
	 * @param beanClass
	 * @param id
	 * @return
	 */
	public E findById(Class<E> beanClass, Serializable key);	
	 
	
	/**
	 * Counts the elements matching the query.
	 * 
	 * @param query The query.
	 * @return
	 */
	public Long count(IQuery<E> query);
	 
}
