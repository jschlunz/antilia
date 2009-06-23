/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

/**
 *  A simple restriction on a property.
 *  
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SimpleRestriction implements IRestriction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The property to which the restriction will apply.
	 */
	private final String propertyName;
	
	/**
	 * The value applied to the restriction.
	 */
	private final Object value;
	/**
	 * If case should matter for the restriction.
	 */
	private boolean ignoreCase;
	/**
	 * The operator to apply to the restriction.
	 */
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
