/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SizeRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;  
	private final int size;
	private final Operator op;
	
	/**
	 * 
	 */
	public SizeRestriction(final String propertyName, int size, Operator op) {
		this.propertyName = propertyName;
		this.size = size;
		this.op = op;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}

	
}
