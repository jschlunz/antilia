/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import com.antilia.common.util.ExceptionUtils;
import com.antilia.hibernate.util.CommandUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UpdateCommand<E extends Serializable> extends AbstractPersistentCommand<E, E> {
	
	private E transientInstance;
	
	
	@SuppressWarnings("unchecked")
	public UpdateCommand(E transientInstance) {
		super((Class<E>)transientInstance.getClass());
		this.transientInstance = transientInstance;
	}
	
	@Override
	protected E doExecute() throws Throwable {
		try {
			
			
			//validate(entity);			
			// invoke beforeUpdate
			//invokeAnnotatedMethod(entity,BeforeUpdate.class);	
			
			//entity = em.merge(centity));
			
			
			// invoke afterUpdate
			//invokeAnnotatedMethod(entity,AfterUpdate.class);
			
			getSession().update(CommandUtils.attachObject(getTransientInstance()));
			
			return getTransientInstance();
		} catch (Throwable e) {
			throw ExceptionUtils.getRootCause(e);
		}
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
