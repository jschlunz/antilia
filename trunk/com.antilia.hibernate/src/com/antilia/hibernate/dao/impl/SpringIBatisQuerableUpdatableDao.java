/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.antilia.hibernate.dao.IQuerableUpdatableDao;

/**
 * A QuerableUpdatable DAO that uses Spring Hibernate support.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SpringIBatisQuerableUpdatableDao<E extends Serializable> extends SpringIBatisQuerableDao<E> implements IQuerableUpdatableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public SpringIBatisQuerableUpdatableDao() {
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public E add(E element) {
		getSqlMapClientTemplate().insert(null, null);
		return element;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addAll(Collection<E> elements) {
		getSqlMapClientTemplate().insert(null, null);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public E remove(E element) {
		getSqlMapClientTemplate().delete(null, null);
		return element;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeAll(Collection<E> elements) {
		getSqlMapClientTemplate().delete(null, null);
	}
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(E element) {
		getSqlMapClientTemplate().update(null, null);
	}
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAll(Collection<E> elements) {
		for(E element: elements) {
			getSqlMapClientTemplate().update(null, element);
		}
	}

}
