/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class InRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;
	private final Object[] values;

	protected InRestriction(String propertyName, Object[] values) {
		this.propertyName = propertyName;
		this.values = values;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the values
	 */
	public Object[] getValues() {
		return values;
	}

}
