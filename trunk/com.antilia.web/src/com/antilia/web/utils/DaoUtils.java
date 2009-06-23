package com.antilia.web.utils;

import java.io.Serializable;

import com.antilia.common.dao.IDaoLocator;
import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.dao.IQuerableUpdatableDao;
import com.antilia.hibernate.dao.impl.DefaultDaoLocator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class DaoUtils {

	/**
	 * 
	 * @param <T>
	 * @param daoLocator
	 * @param beanClass
	 * @param extraId
	 * @return
	 */
	public static <T extends Serializable> IQuerableDao<T> findQuerableDao(IDaoLocator daoLocator, Class<T> beanClass, String extraId) {
		try {
			return daoLocator.locateQuerableDao(beanClass, extraId);
		}catch (Exception e) {
			// do nothing
		}		
		daoLocator = DefaultDaoLocator.getInstance();
		return daoLocator.locateQuerableDao(beanClass, extraId);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param daoLocator
	 * @param beanClass
	 * @param extraId
	 * @return
	 */
	public static <T extends Serializable> IQuerableUpdatableDao<T> findQuerableUpdatableDao(IDaoLocator daoLocator, Class<T> beanClass, String extraId) {
		try {
			return daoLocator.locateQuerableUpdatableDao(beanClass, extraId);
		}catch (Exception e) {
			// do nothing
		}		
		daoLocator = DefaultDaoLocator.getInstance();
		return daoLocator.locateQuerableUpdatableDao(beanClass, extraId);
	}
}
