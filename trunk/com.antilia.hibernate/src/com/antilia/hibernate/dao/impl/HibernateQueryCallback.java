/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.antilia.common.query.IQuery;
import com.antilia.common.query.Query;
import com.antilia.hibernate.query.transform.QueryToCriteriaTransformer;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class HibernateQueryCallback<B extends Serializable> implements HibernateCallback {

	private IQuery<B> query;
	
	private boolean includeOrdering = true;
	
	private Class<B> beanClass;
	
	private B sample;
	
	
	public HibernateQueryCallback(Class<B> beanClass, boolean includeOrdering) {
		this(new Query<B>(beanClass), includeOrdering);
	}
	
	public HibernateQueryCallback(Class<B> beanClass) {
		this(new Query<B>(beanClass), true);
	}
	
	/**
	 * 
	 */
	public HibernateQueryCallback(IQuery<B> query) {
		this(query, true);
	}
	
	public HibernateQueryCallback(B bean) {
		if(bean == null)
			throw new IllegalArgumentException("Bean cannot be null");					
		this.query = null;
		this.sample = bean;
		this.includeOrdering = false;
	}
	
	/**
	 * 
	 */
	public HibernateQueryCallback(IQuery<B> query, boolean includeOrdering) {
		if(query == null)
			throw new IllegalArgumentException("Query cannot be null");					
		this.query = query;
		this.includeOrdering = includeOrdering;
	}

	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
	 */
	public final Object doInHibernate(Session session) throws HibernateException, SQLException {
		Criteria criteria = session.createCriteria(getQuery().getEntityClass());
		if(sample != null)
			criteria.add(Example.create(sample));
		else
			criteria = new QueryToCriteriaTransformer<B>().transform(criteria,getQuery(), isIncludeOrdering());
		return doInHibernate(session, criteria);
	}

	public abstract Object doInHibernate(Session session, Criteria criteria) throws HibernateException, SQLException;

	/**
	 * @return the includeOrdering
	 */
	public boolean isIncludeOrdering() {
		return includeOrdering;
	}

	/**
	 * @param includeOrdering the includeOrdering to set
	 */
	public void setIncludeOrdering(boolean includeOrdering) {
		this.includeOrdering = includeOrdering;
	}

	/**
	 * @return the beanClass
	 */
	public Class<B> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<B> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @return the query
	 */
	public IQuery<B> getQuery() {
		return query;
	}
}
