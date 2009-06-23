/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.query.IQuery;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.context.RequestContext;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class HibernateQuerableDao<E extends Serializable> implements IQuerableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public HibernateQuerableDao() {
	}
	
	public List<E> findAll(IQuery<E> query) {		
		IPersistenceUnit old = setPersistenceUnit();
		try {
			return DefaultCommander.loadList(query);
		} finally {
			restorePersistenceUnit(old);
		}
	}
	
	public List<E> findAll(Class<E> beanClass) {		
		IPersistenceUnit old = setPersistenceUnit();
		try {
			return DefaultCommander.loadList(beanClass);
		} finally {
			restorePersistenceUnit(old);
		}
	}
	
	public Long count(IQuery<E> query) {
		IPersistenceUnit old = setPersistenceUnit();
		try {
			return DefaultCommander.count(query);
		} finally {
			restorePersistenceUnit(old);
		}
	}
	
	public E findById(Class<E> beanClass, Serializable key) {
		IPersistenceUnit old = setPersistenceUnit();
		try {
			E bean =  DefaultCommander.findById(beanClass, key);
			return bean;
		} finally {
			restorePersistenceUnit(old);
		}		
	}
	
	//TODO: do this with GUICE aspects
	protected IPersistenceUnit setPersistenceUnit() {
		RequestContext requestContext = RequestContext.get();
		IPersistenceUnit old = requestContext.getPersistenceUnit();
		IPersistenceUnit persistenceUnit = getPersistenceUnit();		
		if(persistenceUnit != null) {
			requestContext.setPersistenceUnit(persistenceUnit);
		}
		return old;
	}
	
	protected void restorePersistenceUnit(IPersistenceUnit old) {
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(old);
	}
	
	/**
	 * Give implementing DAOS the opportunity to specify the persistence unit.
	 * 
	 * @return
	 */
	protected IPersistenceUnit getPersistenceUnit() {
		return null;		
	}	
}
