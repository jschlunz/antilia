/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.provider.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.common.query.IQuery;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.dao.impl.ListQuerableDao;
import com.antilia.web.provider.IQuerableDataProvider;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ListQuerableDataProvider<E extends Serializable> implements IQuerableDataProvider<E> {
	
	private IQuery<E> query;
	
	private static final long serialVersionUID = 1L;
	
	private List<E> cached;
	
	private IQuerableDao<E> dao;
	
	public ListQuerableDataProvider(List<E> list) {
		this(new ListQuerableDao<E>(list));
	}

	public ListQuerableDataProvider(IQuerableDao<E> dao) {
		this.dao = dao;
	}
	
	public void detach() {
		cached = null;
	}
	
	public Iterator<? extends E> iterator(int first, int count) {			
		if(cached == null) {
			cached = dao.findAll(query);
		}
		int to = first+count;
		if(to >= cached.size())
			to = cached.size();
		if(to < 0) {
			return new ArrayList<E>().iterator();
		}
		return cached.subList(first, to).iterator();
	}
	
	public int size() {
		if(cached == null) {
			cached = dao.findAll(query);
		}
		return cached.size();
	}
	
	public IModel<E> model(E object) {
		return new Model<E>(object);
	}
	
	/**
	 * @return the query
	 */
	public IQuery<E> getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(IQuery<E> query) {
		this.query = query;
	}

	/**
	 * @return the dao
	 */
	public IQuerableDao<E> getDao() {
		return dao;
	}

}
