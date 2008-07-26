/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Query<B extends Serializable> implements IQuery<B> {

	private static final long serialVersionUID = 1L;

	private List<IFilter> filters = new ArrayList<IFilter>();
	
	private List<IOrder<B>> orders = new ArrayList<IOrder<B>>();
	
	private IProjection projection;
	
	private Class<B> beanClass;
		
	private int maxResults = ALL_RESULTS;
	
	private int firstResult = ALL_RESULTS;
	
	public Query(Class<B> beanClass) {
		this.beanClass = beanClass;
	}
	/* (non-Javadoc)
	 * @see com.antilia.persistence.filter.IQuery#addFilter(com.antilia.persistence.filter.IFilter)
	 */
	public IQuery<B> addFilter(IFilter filter) {
		filters.add(filter);
		return this;
	}

	public void clearFilters() {
		filters.clear();
	}
	
	public void clearOrders() {
		orders.clear();
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.persistence.filter.IQuery#addOrder(com.antilia.persistence.filter.IOrder)
	 */
	public IQuery<B> addOrder(IOrder<B> order) {
		orders.add(order);
		return this;
	}
	
	public IQuery<B> setProjection(IProjection projection) {
		this.projection = projection;
		return this;
	}

	public IProjection getProjection() {
		return projection;
	}

	public Iterable<IFilter> getFilters() {
		return filters;
	}

	public Iterable<IOrder<B>> getOrders() {
		return orders;
	}
	
	public Class<B> getEntityClass() {
		return beanClass;
	}
	
	public int getMaxResults() {
		return maxResults;
	}
	
	public IQuery<B> setMaxResults(int maxResults) {
		this.maxResults = maxResults;
		return this;
	}
	
	public int getFirstResult() {
		return firstResult;
	}
	
	public IQuery<B> setFirstResult(int firstResult) {
		this.firstResult = firstResult;
		return this;
	}
}
