/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import com.antilia.hibernate.command.CommandExecuter;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.provider.IQuerable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HibernateQueryDataProvider<E extends Serializable> implements IDataProvider, IQuerable<E> {

	private static final long serialVersionUID = 1L;
	
	private IQuery<E> query;
	
	/**
	 * 
	 */
	public HibernateQueryDataProvider(IQuery<E> query) {
		this.query = query;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int, int)
	 */
	@Override
	public Iterator<E> iterator(int first, int count) {
		query.setFirstResult(first);
		query.setMaxResults(count);
		return CommandExecuter.loadList(query).iterator();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
	 */
	@Override
	public IModel model(Object object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
	 */
	@Override
	public int size() {
		return  ((Long)CommandExecuter.count((Query<E>)query)).intValue();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	@Override
	public void detach() {
		
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IQuerable#getQuery()
	 */
	@Override
	public IQuery<E> getQuery() {
		return query;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IQuerable#setQuery(com.antilia.hibernate.query.IQuery)
	 */
	@Override
	public void setQuery(IQuery<E> query) {
		this.query = query;
	}

}
