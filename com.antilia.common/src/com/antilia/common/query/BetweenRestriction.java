/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class BetweenRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	private final Object lo;
	private final Object hi;
	
	protected BetweenRestriction(String propertyName, Object lo, Object hi) {
		this.propertyName = propertyName;
		this.lo = lo;
		this.hi = hi;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the lo
	 */
	public Object getLo() {
		return lo;
	}

	/**
	 * @return the hi
	 */
	public Object getHi() {
		return hi;
	}
}
