/**
 * 
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.antilia.hibernate.query.IQuery;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableDao<E extends Serializable> extends Serializable {
	
	/**
	 * Returns a list of all elements;
	 * @param first
	 * @param count
	 * @return
	 */
	List<E> findAll(IQuery<E> query);
	
	
	/**
	 * 
	 * @param beanClass
	 * @param id
	 * @return
	 */
	public E findById(Class<E> beanClass, Serializable key);	
	 
	public Long count(IQuery<E> query);
	 
	public E add(E element);

		
	public void addAll(Collection<E> element);

		
	
	public E remove(E element);

	 
	public void removeAll(Collection<E> element);

	 
	public void update(E element);

	public void updateAll(Collection<E> element);
}
