/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class FindByExampleCommand<E extends Serializable> extends AbstractPersistentCommand<E, E>{

	private E sample;
	
	private String entityClassName;
	
	public FindByExampleCommand(Class<E> persistentClass, E sample) {
		super(persistentClass);
		this.sample = sample;
	}
	
	public FindByExampleCommand(String entityClassName, E sample) {
		this((Class<E>)null, sample);
		this.entityClassName = entityClassName;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected E doExecute() throws Throwable {
		if(getPersistentClass() != null) {			
			return load(getPersistentClass(), getSample());
		}
		else  {
			Class<E> entityClass = (Class<E>)Thread.currentThread().getContextClassLoader().loadClass(getEntityClassName());
			return load(entityClass, getSample());
		}
	}

	@SuppressWarnings("unchecked")
	private E load(Class<E> entityClass, E sample) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Example.create(sample));
		return (E)criteria.uniqueResult();
	}
	

	/**
	 * @return the entityClassName
	 */
	public String getEntityClassName() {
		return entityClassName;
	}

	/**
	 * @param entityClassName the entityClassName to set
	 */
	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	public E getSample() {
		return sample;
	}

	public void setSample(E sample) {
		this.sample = sample;
	}

}
