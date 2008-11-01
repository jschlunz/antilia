/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.util.AnnotationUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class FindByIdCommand<E extends Serializable, K extends Serializable> extends AbstractPersistentCommand<E, E>{

	private K key;
	
	private String entityClassName;
	
	public FindByIdCommand(Class<E> persistentClass, K key) {
		super(persistentClass);
		this.key = key;
	}
	
	public FindByIdCommand(String entityClassName, K key) {
		this((Class<E>)null, key);
		this.entityClassName = entityClassName;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected E doExecute() throws Throwable {
		if(getPersistentClass() != null) {			
			return load(getPersistentClass(), getKey());
		}
		else  {
			Class<E> entityClass = (Class<E>)Thread.currentThread().getContextClassLoader().loadClass(getEntityClassName());
			return load(entityClass, getKey());
		}
	}

	@SuppressWarnings("unchecked")
	private E load(Class<E> entityClass, K key) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Field[] field = AnnotationUtils.findAnnotatedFields(entityClass, Id.class);
		if(field == null || field.length == 0)
			field = AnnotationUtils.findAnnotatedFields(entityClass, EmbeddedId.class);
		criteria.add(Restrictions.eq(field[0].getName(), key));
		return (E)criteria.uniqueResult();
	}
	
	public static <T extends Serializable> T find(Class<T> persistentClass, Serializable key) {
		try {
			return new FindByIdCommand<T, Serializable>(persistentClass, key).execute();
		} catch (Throwable e) {
			throw new CommandExecutionException(CommandExecutionException.COMMAND_EXECUTION_EXCEPTION, e);
		}
	}
	
	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
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

}
