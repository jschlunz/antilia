/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.antilia.common.dao.IQuerableUpdatableDao;

/**
 * A QuerableUpdatable DAO that uses Spring Hibernate support.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SpringHibernateQuerableUpdatableDao<E extends Serializable> extends SpringHibernateQuerableDao<E> implements IQuerableUpdatableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public SpringHibernateQuerableUpdatableDao() {
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public E add(E element) {
		getHibernateTemplate().saveOrUpdate(element);		
		return element;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addAll(Collection<E> elements) {
		getHibernateTemplate().saveOrUpdateAll(elements);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public E remove(E element) {
		getHibernateTemplate().delete(element);
		return element;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeAll(Collection<E> elements) {
		getHibernateTemplate().deleteAll(elements);
	}
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(E element) {
		getHibernateTemplate().update(element);
	}
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAll(Collection<E> elements) {
		for(E element: elements) {
			getHibernateTemplate().update(element);
		}
	}

}
