/**
 * 
 */
package com.antilia.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.antilia.hibernate.query.IQuery;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableDao<E extends Serializable> extends Serializable {
	
	/**
	 * @param query The query.
	 * @return Returns a list of all elements matching the query.
	 */
	List<E> findAll(IQuery<E> query);
	
	
	/**
	 * 
	 * @param beanClass
	 * @return
	 */
	List<E> findAll(Class<E> beanClass);
	
	/**
	 * 
	 * @param beanClass
	 * @param id
	 * @return
	 */
	public E findById(Class<E> beanClass, Serializable key);	
	 
	
	/**
	 * Counts the elements matching the query.
	 * 
	 * @param query
	 * @return
	 */
	public Long count(IQuery<E> query);
	 
}
