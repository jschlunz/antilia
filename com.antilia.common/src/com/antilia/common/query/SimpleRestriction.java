/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SimpleRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	private final Object value;
	private boolean ignoreCase;
	private final Operator op;
	
	protected SimpleRestriction(String propertyName, Object value, Operator op) {
		this.propertyName = propertyName;
		this.value = value;
		this.op = op;
	}

	protected SimpleRestriction(String propertyName, Object value, Operator op, boolean ignoreCase) {
		this.propertyName = propertyName;
		this.value = value;
		this.ignoreCase = ignoreCase;
		this.op = op;
	}

	public SimpleRestriction ignoreCase() {
		ignoreCase = true;
		return this;
	}
	
	public String toString() {
		return propertyName + getOp() + value;
	}

	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
}
