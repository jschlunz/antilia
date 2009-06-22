/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class IlikeRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;
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
