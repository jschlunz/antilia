/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query.transform;


/**
 * Interface marking a class capable of transforming a source of type 
 * <em>S</em> into a result of type <em>R</em>.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface ITransformer<S,R> {

	/**
	 * Transforms an instance of S (source) to an instance of R (result)
	 * @param source
	 * @return
	 */
	R transform(S source);	
}
