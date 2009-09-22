/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import com.antilia.hibernate.util.CommandUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DeleteCommand<E extends Serializable> extends  AbstractPersistentCommand<E, E> {

	public E entity;
	
	@SuppressWarnings("unchecked")
	public DeleteCommand(E entity) {
		super((Class<E>)entity.getClass());
		this.entity = entity;
	}
		
	@Override
	protected E doExecute() throws Throwable {
		getSession().delete(CommandUtils.attachObject(entity));
		return entity;
	}
}
