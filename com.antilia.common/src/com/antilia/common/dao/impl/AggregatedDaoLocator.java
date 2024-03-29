/**
 * 
 */
package com.antilia.common.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.antilia.common.dao.IDaoLocator;
import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.dao.IQuerableUpdatableDao;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AggregatedDaoLocator implements IDaoLocator {

	private boolean initialized = false;
	
	private Map<Class<? extends Serializable>, IQuerableUpdatableDao<? extends Serializable>> locators = new HashMap<Class<? extends Serializable>, IQuerableUpdatableDao<? extends Serializable>>();
	
	protected AggregatedDaoLocator() {		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.dao.IDaoLocator#locateQuerableDao(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> IQuerableDao<T> locateQuerableDao(Class<? extends T> beanClass, String extraId) {		
		if(!initialized) {
			initialize();
			initialized = true;
		}
		for(Class<? extends Serializable> c: locators.keySet()) {
			if(beanClass.isAssignableFrom(c)) {
				return (IQuerableDao<T>)locators.get(c);
			}
		}
		return getDefaultDaoLocator().locateQuerableDao(beanClass, extraId);
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.dao.IDaoLocator#locateQuerableUpdatableDao(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> IQuerableUpdatableDao<T> locateQuerableUpdatableDao(Class<? extends T> beanClass, String extraId) {
		if(!initialized) {
			initialize();
			initialized = true;
		}
		for(Class<? extends Serializable> c: locators.keySet()) {
			if(beanClass.isAssignableFrom(c)) {
				return (IQuerableUpdatableDao<T>)locators.get(c);
			}
		}
		return getDefaultDaoLocator().locateQuerableUpdatableDao(beanClass, extraId);
	}

	public <T extends Serializable> AggregatedDaoLocator registerLocator(Class<? extends T> beanClass, IQuerableUpdatableDao<T> dao) {
		if(dao != null)
			locators.put(beanClass, dao);
		return this;
	}
	
	protected abstract IDaoLocator getDefaultDaoLocator();
	
	protected abstract void initialize();
	
}
