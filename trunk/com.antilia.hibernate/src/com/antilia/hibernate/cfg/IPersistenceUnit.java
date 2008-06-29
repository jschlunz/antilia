/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.cfg;

import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Represents a persistence unit...
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IPersistenceUnit extends IPersistenceSet {

	/**
	 * Creates the Hibernate configuration for this persistence set.
	 * 
	 * @return
	 */
	public AnnotationConfiguration createConfiguration();
	
	
	public IPersistenceUnit addPersistenceSet(IPersistenceSet entitiesSet);
	
	
	/**
	 * The (unique) name that identifies a persistence unit...
	 * @return
	 */
	public String getName();

}
