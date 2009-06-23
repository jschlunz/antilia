/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.io.Serializable;


/**
 * Abstarct representation of a query.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IQuery<B extends Serializable> extends Serializable  {
	
	public static final int ALL_RESULTS = -1;
	
	/**
	 * The bean class associated with the query.
	 * 
	 * @return
	 */
	Class<B> getEntityClass();

	/**
	 * Adds a restriction.
	 * 
	 * @param filter
	 * @return
	 */
	IQuery<B> addRestriction(IRestriction filter);
	
	/**
	 * Removes a restriction.
	 * 
	 * @param propertyName
	 * @return
	 */
	IQuery<B> removeRestriction(String propertyName);
		
	/**
	 * @return Returns an {@link Iterable} over the filters.
	 */
	Iterable<IRestriction> getRestrictions(); 
	
	/**
	 * Finds the {@link IRestriction}s for a given property name.
	 * @param propertyName
	 * @return
	 */
	IRestriction findRestriction(String propertyName);
	
	/**
	 * Clear all the restrictions.
	 */
	public void clearRestrictions();
	
	/**
	 * Adds a order.
	 * @param order
	 * @return
	 */
	IQuery<B> addOrder(IOrder<B> order);
	
	/**
	 * @return Returns an {@link Iterable} over the orders.
	 */
	Iterable<IOrder<B>> getOrders();
	
	/**
	 * Returns the order for the property <code>propertyPath</code> or null if no order is found for that property.
	 * @param propertyPath
	 * @return
	 */
	IOrder<B> getOrder(String propertyPath);
	
	/**
	 * Clears any orders present on the query.
	 */
	public void clearOrders();
	
	/**
	 * Adds a projection to the query.
	 * 
	 * @param projection
	 * @return
	 */
	IQuery<B> setProjection(IProjection projection);
	
	/**
	 * 
	 * @return
	 */
	public IProjection getProjection();
	
	public IQuery<B> setMaxResults(int maxResults);
	
	public int getMaxResults( );
	
	public IQuery<B> setFirstResult(int firstResult);
	
	public int getFirstResult( );
	
	
}
