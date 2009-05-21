/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Collection;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.provider.IQuerableDao;
import com.antilia.web.beantable.provider.IQuerableUpdatebleDataProvider;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HibernateQuerableUpdatebleDataProvider<E extends Serializable> extends HibernateQuerableDataProvider<E> implements IQuerableUpdatebleDataProvider<E> {

	private static final long serialVersionUID = 1L;
	
	public HibernateQuerableUpdatebleDataProvider() {
		super();
	}
	
	/**
	 * 
	 */
	public HibernateQuerableUpdatebleDataProvider(IQuery<E> query) {
		super(query);
	}
	
	public HibernateQuerableUpdatebleDataProvider(IQuerableDao<E> querableDao) {
		super(querableDao);
	}
	
	public HibernateQuerableUpdatebleDataProvider(IQuery<E> query, IQuerableDao<E> querableDao) {
		super(query, querableDao);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#add(java.io.Serializable)
	 */
	public void add(E element) {
		//DefaultCommander.persist(element);		
		getQuerableDao().add(element);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#addAll(java.util.Collection)
	 */
	public void addAll(Collection<E> elements) {
		//DefaultCommander.persistAll(element);		
		getQuerableDao().addAll(elements);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#remove(java.io.Serializable)
	 */
	public void remove(E element) {
		//DefaultCommander.delete(element);
		getQuerableDao().remove(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#removeAll(java.util.Collection)
	 */
	public void removeAll(Collection<E> elements) {
		//DefaultCommander.deleteAll(element);
		getQuerableDao().removeAll(elements);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#update(java.io.Serializable)
	 */
	public void update(E element) {
		//DefaultCommander.update(element);
		getQuerableDao().update(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#updateAll(java.util.Collection)
	 */
	public void updateAll(Collection<E> elements) {
		//DefaultCommander.updateAll(element);
		getQuerableDao().updateAll(elements);
	}

}
