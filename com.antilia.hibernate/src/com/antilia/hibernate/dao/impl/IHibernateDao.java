/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;

import com.antilia.common.dao.IQuerableDao;

/**
 * Marker interface to tell apart Hibernate based DAOs from other DAOs. Hibernate based DAO also
 * know how to retrieve a primary key out of an entity.
 * 
 * @see #getId(Serializable)
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public interface IHibernateDao<E extends Serializable> extends IQuerableDao<E> {
	
	/**
	 * Given an entity returns the {@link Serializable} representation the primary key of the entity.
	 * 
	 * @param entity
	 * @return
	 */
	Serializable getId(E entity);
	
}
