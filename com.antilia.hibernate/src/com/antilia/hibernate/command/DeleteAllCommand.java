/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Session;

import com.antilia.hibernate.util.CommandUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DeleteAllCommand<E extends Serializable> extends  AbstractPersistentCommand<E, Collection<E>> {

	private Collection<E> entities;
	
	@SuppressWarnings("unchecked")
	public DeleteAllCommand(Collection<E> entities) {
		super((entities != null && entities.size() > 0)?(Class<E>)entities.iterator().next().getClass():null);
		this.entities = entities;
	}
		
	@Override
	protected Collection<E> doExecute() throws Throwable {
		Session session = getSession();
		for(E entity: getEntities()) {
			session.delete(CommandUtils.attachObject(entity));	
			// without this this HSQLDB does not works.
			session.flush();
		}
		return getEntities();
	}

	/**
	 * @return the entities
	 */
	public Collection<E> getEntities() {
		return entities;
	}
}
