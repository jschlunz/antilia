/**
 * 
 */
package com.antilia.hibernate.dao;

import java.io.Serializable;

/**
 * This interface defines a kind of service for locating DAOs.
 * This service is needed because most of the CRUD machinery will
 * auto-generate components, so it will not be practical to directly 
 * inject DAOs on those components. Instead DAOs could be injected on a 
 * locator and the locator will be injected on the the required 
 * components. 
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDaoLocator {
		
	/**
	 * 
	 * @param <T>
	 * @param beanClass
	 * @param extraId
	 * @return
	 */
	<T extends Serializable> IQuerableUpdatableDao<T>  locateQuerableUpdatableDao(Class<? extends T> beanClass, String extraId);	

	/**
	 * 
	 * @param <T>
	 * @param beanClass
	 * @param extraId
	 * @return
	 */
	<T extends Serializable> IQuerableDao<T>  locateQuerableDao(Class<? extends T> beanClass, String extraId);
}
