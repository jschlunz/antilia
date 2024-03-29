/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.provider.impl;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.query.IQuery;
import com.antilia.hibernate.dao.impl.HibernateQuerableDao;
import com.antilia.hibernate.dao.impl.IHibernateDao;
import com.antilia.web.provider.HibernateEntityModel;
import com.antilia.web.provider.IQuerableDataProvider;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DaoQuerableDataProvider<E extends Serializable> implements IQuerableDataProvider<E> {

	private static final long serialVersionUID = 1L;
	
	private IQuery<E> query;
	
	private IQuerableDao<E> querableDao;
	
	public DaoQuerableDataProvider(IQuerableDao<E> querableDao) {
		this(null, querableDao);
	}
	
	public DaoQuerableDataProvider() {
		this(null, new HibernateQuerableDao<E>());
	}
	
	public DaoQuerableDataProvider(IQuery<E> query) {
		this(query, new HibernateQuerableDao<E>());
	}
	
	/**
	 * 
	 */
	public DaoQuerableDataProvider(IQuery<E> query, IQuerableDao<E> querableDao) {
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
		if(querableDao instanceof IHibernateDao<?>) {
			return new HibernateEntityModel<E>(object,(IHibernateDao<E>)this.querableDao);
		}
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
