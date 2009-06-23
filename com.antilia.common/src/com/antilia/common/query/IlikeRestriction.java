/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;



/**
 * Represents a like restriction.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class IlikeRestriction implements IRestriction {

	private static final long serialVersionUID = 1L;

	/**
	 * The property to which the restriction applies.
	 */
	private final String propertyName;
	
	/**
	 * The value of the restriction.
	 */	
	private final Object value;	
	private MatchMode matchMode;

	protected IlikeRestriction(String propertyName, Object value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	protected IlikeRestriction(String propertyName, String value, MatchMode matchMode) {
		this.propertyName = propertyName;
		this.value = value;
		this.matchMode = matchMode;
	}
	
	
	public String toString() {
		return propertyName + " ilike " + value;
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

	/**
	 * @return the matchMode
	 */
	public MatchMode getMatchMode() {
		return matchMode;
	}
}
