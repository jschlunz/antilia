/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.cfg;

import java.util.ArrayList;
import java.util.List;

import com.antilia.hibernate.util.EntityUtils;

/**
 *	Default implementation of IPersistenceSet: a collection of entities.. 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersistenceSet implements IPersistenceSet {

	List<Class<?>> entities =  new ArrayList<Class<?>>();
		
	public PersistenceSet() {		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.cfg.IEntitiesSet#addEntityClass(java.lang.Class)
	 */
	@Override
	public IPersistenceSet addEntityClass(Class<?> entityClass) {
		if(EntityUtils.isEntity(entityClass)) {
			entities.add(entityClass);
		}
		return this;
	}
	
	public IPersistenceSet addEntitiesInSamePackageAs(Class<?> entityClass) {
		EntityUtils.addEntitiesInSamePackageAs(entityClass, this);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.cfg.IEntitiesSet#getEntityClasses()
	 */
	@Override
	public Iterable<Class<?>> getEntityClasses() {
		return entities;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.cfg.IEntitiesSet#removeEntity(java.lang.Class)
	 */
	@Override
	public IPersistenceSet removeEntity(Class<?> entityClass) {
		entities.remove(entityClass);
		return  this;
	}

}
