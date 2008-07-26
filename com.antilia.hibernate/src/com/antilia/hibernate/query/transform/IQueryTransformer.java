/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import java.io.Serializable;

import com.antilia.hibernate.query.IQuery;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IQueryTransformer<E extends Serializable, R> {
	
	R transform(IQuery<E> source, boolean includeOrder);
	
}
