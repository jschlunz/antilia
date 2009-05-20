/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import org.apache.wicket.model.IModel;

import com.antilia.hibernate.command.CommandExecuter;
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
		return CommandExecuter.loadList(query).iterator();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
	 */
	public IModel<E> model(E object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
	 */
	public int size() {
		return  ((Long)CommandExecuter.count((Query<E>)query)).intValue();
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
		CommandExecuter.persist(element);		
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#addAll(java.util.Collection)
	 */
	public void addAll(Collection<E> element) {
		CommandExecuter.persistAll(element);		
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#remove(java.io.Serializable)
	 */
	public void remove(E element) {
		CommandExecuter.delete(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#removeAll(java.util.Collection)
	 */
	public void removeAll(Collection<E> element) {
		CommandExecuter.deleteAll(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#update(java.io.Serializable)
	 */
	public void update(E element) {
		CommandExecuter.update(element);
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IUpdatable#updateAll(java.util.Collection)
	 */
	public void updateAll(Collection<E> element) {
		CommandExecuter.updateAll(element);
	}

}
