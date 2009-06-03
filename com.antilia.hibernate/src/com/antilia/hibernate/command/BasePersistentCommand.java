/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2007-2008.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import org.hibernate.Session;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;

/**
 * Base class for all persistence related commands...
 *
 *@param <E> The type of the entity associated with the command. 
 *@param <R> The type of the returned result.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class BasePersistentCommand<E extends Serializable,R extends Object> implements ICommand<R> {
		
	/**
	 * The persistence unit associated with the persistent command.
	 */
	private IPersistenceUnit persistenceUnit;
	
	private Class<E> persistentClass;
	
	public BasePersistentCommand(Class<E> persistentClass) {		
		this.persistentClass = persistentClass;
	}
		
	
	/**
	 * @return Returns the hibernate sesion to be used on the command...
	 */
	protected Session getSession() {
		return HibernateUtil.getSessionFactory(getPersistenceUnit()).getCurrentSession();
	}


	/**
	 * @return the persistenceUnit
	 */
	public IPersistenceUnit getPersistenceUnit() {
		if(persistenceUnit == null)
			persistenceUnit = RequestContext.get().getPersistenceUnit();;
		return persistenceUnit;
	}



	/**
	 * @param persistenceUnit the persistenceUnit to set
	 */
	public void setPersistenceUnit(IPersistenceUnit persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}


	/**
	 * @return the persistentClass
	 */
	public Class<E> getPersistentClass() {
		return persistentClass;
	}


	/**
	 * @param persistentClass the persistentClass to set
	 */
	public void setPersistentClass(Class<E> persistentClass) {
		this.persistentClass = persistentClass;
	}

}
