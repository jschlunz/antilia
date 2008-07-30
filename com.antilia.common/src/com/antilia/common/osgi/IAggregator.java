/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IAggregator<A> {
	
	IAggregator<A> add(A a);
	
	IAggregator<A> delete(A a);
	
	IAggregator<A> deleteAll();
	
	Iterable<A> elements();
	
	int size();
	
}
