/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.antilia.common.util.StringUtils;

/**
 * A neutral representation of a query. The API is similar to Hibernate 
 * Criteria or OJB Criteria, but with no references to any of them. 
 * That way a query could be used to filter tabular data 
 * coming from different sources  (e.g. iBatis, or a simple list) without 
 * needing to include references to those projects.
 * 
 * Additionally, advantage is taken from Java 5 main additions (e.g. generics 
 * and enumerations).
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Query<B extends Serializable> implements IQuery<B> {

	private static final long serialVersionUID = 1L;

	private List<IRestriction> restrictions = new ArrayList<IRestriction>();
	
	private List<IOrder<B>> orders = new ArrayList<IOrder<B>>();
	
	private IProjection projection;
	
	private Class<B> beanClass;
		
	private int maxResults = ALL_RESULTS;
	
	private int firstResult = ALL_RESULTS;
	
	@SuppressWarnings("unchecked")
	public Query(B bean) {
		this((Class<B>)bean.getClass());
	}
	
	public Query(Class<B> beanClass) {
		if(beanClass == null)
			throw new IllegalArgumentException("Bean class cannot be null");
		this.beanClass = beanClass;
	}
	
	/**
	 * Factory method for a bean.
	 * 
	 * @param <T>
	 * @param bean
	 * @return
	 */
	public static <T extends Serializable> Query<T> ofBean(T bean)
	{
		return new Query<T>(bean);
	}
	
	/**
	 * Factory method for a bean class.
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T extends Serializable> Query<T> ofClass(Class<T> beanClass)
	{
		return new Query<T>(beanClass);
	}
		
	public IQuery<B> addRestriction(IRestriction filter) {
		restrictions.add(filter);
		return this;
	}

	public void clearRestrictions() {
		restrictions.clear();
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
	
	public Iterable<IRestriction> getRestrictions() {
		return restrictions;
	}
	
	public IRestriction findRestriction(String propertyName) {
		if(StringUtils.isEmpty(propertyName)) {
			return null;
		}
		for(IRestriction filter: restrictions) {
			if(filter.getPropertyName() != null && filter.getPropertyName().equals(propertyName)){
				return filter;
			}
		}
		return null;
	}
	
	public IQuery<B> removeRestriction(String propertyName) {
		if(StringUtils.isEmpty(propertyName)) {
			return this;
		}
		List<IRestriction> nfilters = new ArrayList<IRestriction>();
		for(IRestriction filter: restrictions) {
			if(filter.getPropertyName() != null && filter.getPropertyName().equals(propertyName)){				
				continue;
			}
			nfilters.add(filter);
		}
		this.restrictions = nfilters;
		return this;
	}
	
	public Iterable<IOrder<B>> getOrders() {
		return orders;
	}
	
	public IOrder<B> getOrder(String propertyPath) {
		for(IOrder<B> order: orders) {
			if(order.getPropertyPath().equals(propertyPath)) 
				return order;
		}
		return null;
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
