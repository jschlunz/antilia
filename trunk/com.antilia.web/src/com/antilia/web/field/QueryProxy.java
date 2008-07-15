/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Operator;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class QueryProxy <B extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private IQuery<B> query;
	
	private String propertyName;
	
	private Operator operator;
	
	/**
	 * 
	 */
	public QueryProxy(IQuery<B> query, String propertyName) {
		this(query,propertyName, Operator.EQUAL);
	}
	
	public QueryProxy(IQuery<B> query, String propertyName, Operator operator) {
		this.query = query;
		this.propertyName = propertyName;
		this.operator = operator;
	}
	
	

}
