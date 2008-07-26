/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.cfg;

/**
 * Represents a set of Entities...
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IPersistenceSet {
	/**
	 * @return Returns an Iterable over all the entities (classes) on the set.
	 */
	Iterable<Class<?>> getEntityClasses();
	
	/**
	 * Allows to add an Entity class to the set.
	 * 
	 * @param entityClass
	 * @return
	 */
	public IPersistenceSet addEntityClass(Class<?> entityClass);
	
	/**
	 * 
	 * @param entityClass
	 * @return
	 */
	public IPersistenceSet removeEntity(Class<?> entityClass);
	
	
	public IPersistenceSet addEntitiesInSamePackageAs(Class<?> entityClass);
}
