/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.test;

import com.antilia.demo.manager.entities.City;
import com.antilia.hibernate.cfg.PersistenceSet;

/**
 * The persistence set of all entities in RDS.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ManagerPersistenceSet extends PersistenceSet {

	private static final ManagerPersistenceSet  instance = new ManagerPersistenceSet();
	
	private  ManagerPersistenceSet() {
		addEntitiesInSamePackageAs(City.class);
	}

	/**
	 * @return the instance
	 */
	public static ManagerPersistenceSet getInstance() {
		return instance;
	}
}
