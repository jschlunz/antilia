/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;

import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IFilter extends Serializable {
	
	String getPropertyName();
	
	//TODO: clean up any references to Hibernate... So that queries are independent from Hibernate..
	IFilterTransformer<Criterion> getTransformer();

}
