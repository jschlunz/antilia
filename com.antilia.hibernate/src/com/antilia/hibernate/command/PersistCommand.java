/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersistCommand<E extends Serializable> extends AbstractPersistentCommand<E, E> {
	
	private E transientInstance;
	
	
	@SuppressWarnings("unchecked")
	public PersistCommand(E transientInstance) {
		super((Class<E>)transientInstance.getClass());
		this.transientInstance = transientInstance;
	}
	
	@Override
	protected E doExecute() throws Throwable {
		getSession().persist(getTransientInstance());
		return getTransientInstance();
	}

	/**
	 * @return the transientInstance
	 */
	public E getTransientInstance() {
		return transientInstance;
	}

	/**
	 * @param transientInstance the transientInstance to set
	 */
	public void setTransientInstance(E transientInstance) {
		this.transientInstance = transientInstance;
	}

}
