/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Aggregator<B> implements IAggregator<B> {

	List<B> elements = new ArrayList<B>();
	
	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IAggregator#add(java.lang.Object)
	 */
	public IAggregator<B> add(B a) {
		elements.add(a);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IAggregator#elements()
	 */
	public Iterable<B> elements() {
		return elements;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IAggregator#remove(java.lang.Object)
	 */
	public IAggregator<B> delete(B a) {
		elements.remove(a);
		return this;
	}
	
	public int size() {
		return elements.size();
	}
	
	public IAggregator<B> deleteAll() {
		elements.clear();
		return this;
	}

}
