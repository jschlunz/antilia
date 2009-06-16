/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.ibatis.IBatisQuery;
import com.antilia.ibatis.QueryToBatisQueryTransformer;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class IBatisQueryCallback<B extends Serializable> implements SqlMapClientCallback {

	private IQuery<B> query;
	
	private boolean includeOrdering = true;
	
	private Class<B> beanClass;
	
	public IBatisQueryCallback(Class<B> beanClass, boolean includeOrdering) {
		this(new Query<B>(beanClass), includeOrdering);
	}
	
	public IBatisQueryCallback(Class<B> beanClass) {
		this(new Query<B>(beanClass), true);
	}
	
	/**
	 * 
	 */
	public IBatisQueryCallback(IQuery<B> query) {
		this(query, true);
	}
	
	/**
	 * 
	 */
	public IBatisQueryCallback(IQuery<B> query, boolean includeOrdering) {
		if(query == null)
			throw new IllegalArgumentException("Query cannot be null");					
		this.query = query;
		this.includeOrdering = includeOrdering;
	}

	
	public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		IBatisQuery<B> iBatisQuery = new QueryToBatisQueryTransformer<B>().transform(getQuery(), isIncludeOrdering());
		return doInIBatis(executor, iBatisQuery);
	}

	public abstract Object doInIBatis(SqlMapExecutor executor, IBatisQuery<B> iBatisQuery) throws SQLException;

	
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
