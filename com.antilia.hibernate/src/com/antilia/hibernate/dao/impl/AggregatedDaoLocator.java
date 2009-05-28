/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.antilia.hibernate.dao.IDaoLocator;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.dao.IQuerableUpdatableDao;

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
		if(!initialized)
			initialize();
		for(Class<? extends Serializable> c: locators.keySet()) {
			if(beanClass.isAssignableFrom(c)) {
				return (IQuerableDao<T>)locators.get(c);
			}
		}
		return DefaultDaoLocator.getInstance().locateQuerableDao(beanClass, extraId);
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.dao.IDaoLocator#locateQuerableUpdatableDao(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> IQuerableUpdatableDao<T> locateQuerableUpdatableDao(Class<? extends T> beanClass, String extraId) {
		if(!initialized)
			initialize();
		for(Class<? extends Serializable> c: locators.keySet()) {
			if(beanClass.isAssignableFrom(c)) {
				return (IQuerableUpdatableDao<T>)locators.get(c);
			}
		}
		return DefaultDaoLocator.getInstance().locateQuerableUpdatableDao(beanClass, extraId);
	}

	public AggregatedDaoLocator registerLocator(Class<? extends Serializable> beanClass, IQuerableUpdatableDao<? extends Serializable> dao) {
		if(dao != null)
			locators.put(beanClass, dao);
		return this;
	}
	
	protected abstract void initialize();
	
}
