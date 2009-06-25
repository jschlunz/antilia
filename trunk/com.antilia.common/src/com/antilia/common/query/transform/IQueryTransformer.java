/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query.transform;

import java.io.Serializable;

import com.antilia.common.query.IQuery;

/**
 * Defines a transformer from an IQuery<E> (source) into a criteria (R) which 
 * is back end specific (e.g. in case of Hibernate this could be Hibernate criteria and 
 * in case of iBatis an utility clas to build dynamic queries).
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IQueryTransformer<E extends Serializable, R> {
	
	/**
	 * Transforms an IQuery<E> into a result criteria (R).
	 * 
	 * @param source
	 * @param includeOrder
	 * @return
	 */
	R transform(IQuery<E> source, boolean includeOrder);

	/**
	 * Transforms an IQuery<E> into a result criteria (R). It receives an initial criteria as parameter.
	 * 
	 * @param criteria
	 * @param source
	 * @param includeOrder
	 * @return
	 */
	R transform(R criteria,IQuery<E> source, boolean includeOrder);
}
