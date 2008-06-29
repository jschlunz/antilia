/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import java.io.Serializable;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IQuery<B extends Serializable> extends Serializable  {
	
	public static final int ALL_RESULTS = -1;
	
	Class<B> getEntityClass();

	IQuery<B> addFilter(IFilter filter);
	
	Iterable<IFilter> getFilters(); 
	
	public void clearFilters();
	
	IQuery<B> addOrder(IOrder order);
	
	Iterable<IOrder> getOrders();
	
	IQuery<B> setProjection(IProjection projection);
	
	public IProjection getProjection();
	
	public IQuery<B> setMaxResults(int maxResults);
	
	public int getMaxResults( );
	
	public IQuery<B> setFirstResult(int firstResult);
	
	public int getFirstResult( );
	
	
}
