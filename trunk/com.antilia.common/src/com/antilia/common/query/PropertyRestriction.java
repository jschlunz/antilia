/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PropertyRestriction  implements IRestriction {

	private static final long serialVersionUID = 1L;

	private final String propertyName;
	private final String otherPropertyName;
	private final Operator op;

	/**
	 * 
	 */
	public PropertyRestriction(String propertyName, String otherPropertyName, Operator op) {
		this.propertyName = propertyName;
		this.otherPropertyName = otherPropertyName;
		this.op = op;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the otherPropertyName
	 */
	public String getOtherPropertyName() {
		return otherPropertyName;
	}

	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}
	
}
