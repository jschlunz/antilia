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
	
	IQuery<B> removeFilter(String propertyName);
		
	Iterable<IFilter> getFilters(); 
	
	IFilter findFilter(String propertyName);
	
	public void clearFilters();
	
	IQuery<B> addOrder(IOrder<B> order);
	
	Iterable<IOrder<B>> getOrders();
	
	/**
	 * Returns the order for the property <code>propertyPath</code> or null if no order is found for that property.
	 * @param propertyPath
	 * @return
	 */
	IOrder<B> getOrder(String propertyPath);
	
	public void clearOrders();
	
	IQuery<B> setProjection(IProjection projection);
	
	public IProjection getProjection();
	
	public IQuery<B> setMaxResults(int maxResults);
	
	public int getMaxResults( );
	
	public IQuery<B> setFirstResult(int firstResult);
	
	public int getFirstResult( );
	
	
}
