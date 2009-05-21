/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Collection;

import com.antilia.hibernate.dao.IQuerableUpdatableDao;
import com.antilia.hibernate.query.IQuery;
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
	
	public HibernateQuerableUpdatebleDataProvider(IQuerableUpdatableDao<E> querableDao) {
		super(querableDao);
	}
	
	public HibernateQuerableUpdatebleDataProvider(IQuery<E> query, IQuerableUpdatableDao<E> querableDao) {
		super(query, querableDao);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#add(java.io.Serializable)
	 */
	public void add(E element) {
		getQuerableUpdatableDao().add(element);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#addAll(java.util.Collection)
	 */
	public void addAll(Collection<E> elements) {		
		getQuerableUpdatableDao().addAll(elements);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#remove(java.io.Serializable)
	 */
	public void remove(E element) {
		getQuerableUpdatableDao().remove(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#removeAll(java.util.Collection)
	 */
	public void removeAll(Collection<E> elements) {
		getQuerableUpdatableDao().removeAll(elements);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#update(java.io.Serializable)
	 */
	public void update(E element) {
		getQuerableUpdatableDao().update(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#updateAll(java.util.Collection)
	 */
	public void updateAll(Collection<E> elements) {
		getQuerableUpdatableDao().updateAll(elements);
	}
	
	protected IQuerableUpdatableDao<E> getQuerableUpdatableDao() {
		return (IQuerableUpdatableDao<E>)getQuerableDao();
	}

}
