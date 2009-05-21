/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.provider.IQuerableUpdatebleDataProvider;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HibernateQuerableUpdatebleDataProvider<E extends Serializable> implements IQuerableUpdatebleDataProvider<E> {

	private static final long serialVersionUID = 1L;
	
	private IQuery<E> query;
	
	/**
	 * 
	 */
	public HibernateQuerableUpdatebleDataProvider(IQuery<E> query) {
		this.query = query;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int, int)
	 */
	public Iterator<E> iterator(int first, int count) {
		query.setFirstResult(first);
		query.setMaxResults(count);
		return DefaultCommander.loadList(query).iterator();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
	 */
	public IModel<E> model(E object) {
		// TODO: make it detachable.
		return new Model<E>(object);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
	 */
	public int size() {
		return  ((Long)DefaultCommander.count((Query<E>)query)).intValue();
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

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#add(java.io.Serializable)
	 */
	public void add(E element) {
		DefaultCommander.persist(element);		
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#addAll(java.util.Collection)
	 */
	public void addAll(Collection<E> element) {
		DefaultCommander.persistAll(element);		
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#remove(java.io.Serializable)
	 */
	public void remove(E element) {
		DefaultCommander.delete(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#removeAll(java.util.Collection)
	 */
	public void removeAll(Collection<E> element) {
		DefaultCommander.deleteAll(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#update(java.io.Serializable)
	 */
	public void update(E element) {
		DefaultCommander.update(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#updateAll(java.util.Collection)
	 */
	public void updateAll(Collection<E> element) {
		DefaultCommander.updateAll(element);
	}

}
