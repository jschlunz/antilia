/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class IdentifierEqRestriction implements IRestrictionFilter{

	private static final long serialVersionUID = 1L;

	private final Object value;

	protected IdentifierEqRestriction(Object value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
		
	public String getPropertyName() {
		return null;
	}
}
