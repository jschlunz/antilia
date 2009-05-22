/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.hibernate.dao.HibernateQuerableDao;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.provider.IQuerableDataProvider;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HibernateQuerableDataProvider<E extends Serializable> implements IQuerableDataProvider<E> {

	private static final long serialVersionUID = 1L;
	
	private IQuery<E> query;
	
	private IQuerableDao<E> querableDao;
	
	public HibernateQuerableDataProvider(IQuerableDao<E> querableDao) {
		this(null, querableDao);
	}
	
	public HibernateQuerableDataProvider() {
		this(null, new HibernateQuerableDao<E>());
	}
	
	public HibernateQuerableDataProvider(IQuery<E> query) {
		this(query, new HibernateQuerableDao<E>());
	}
	/**
	 * 
	 */
	public HibernateQuerableDataProvider(IQuery<E> query, IQuerableDao<E> querableDao) {
		this.query = query;
		this.querableDao = querableDao;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int, int)
	 */
	public Iterator<E> iterator(int first, int count) {
		query.setFirstResult(first);
		query.setMaxResults(count);
		return querableDao.findAll(query).iterator();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
	 */
	public IModel<E> model(E object) {
		return new Model<E>(object);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
	 */
	public int size() {
		return  querableDao.count(query).intValue();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	public void detach() {
		
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IQuerable#getQuery()
	 */
	public IQuery<E> getQuery() {
		return query;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IQuerable#setQuery(com.antilia.hibernate.query.IQuery)
	 */
	public void setQuery(IQuery<E> query) {
		this.query = query;
	}

	/**
	 * @return the querableDao
	 */
	public IQuerableDao<E> getQuerableDao() {
		return querableDao;
	}
}
