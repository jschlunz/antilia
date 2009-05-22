/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.repeater.data.ListDataProvider;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.provider.IQuerable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class QuerableListDataProvider<E extends Serializable> extends ListDataProvider<E> implements IQuerable<E> {
	
	private IQuery<E> query;
	
	private static final long serialVersionUID = 1L;

	public QuerableListDataProvider(List<E> list) {
		super(list);
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

}
