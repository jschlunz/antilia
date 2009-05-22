/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;

import com.antilia.hibernate.dao.IDaoLocator;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.dao.IQuerableUpdatableDao;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public class DefaultDaoLocator implements IDaoLocator {

	public static DefaultDaoLocator instance;
	
	/**
	 * 
	 */
	protected DefaultDaoLocator() {
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.dao.IDaoLocator#locateQuerableDao(java.lang.Class, java.lang.String)
	 */
	public <T extends Serializable> IQuerableDao<T> locateQuerableDao(
			Class<? extends T> beanClass, String extraId) {
		return new HibernateQuerableDao<T>();
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.dao.IDaoLocator#locateQuerableUpdatableDao(java.lang.Class, java.lang.String)
	 */
	public <T extends Serializable> IQuerableUpdatableDao<T> locateQuerableUpdatableDao(
			Class<? extends T> beanClass, String extraId) {
		return new HibernateQuerableUpdatableDao<T>();
	}

	/**
	 * @return the instance
	 */
	public static DefaultDaoLocator getInstance() {
		if(instance == null) {
			instance = new DefaultDaoLocator();
		}
		return instance;
	}

}
