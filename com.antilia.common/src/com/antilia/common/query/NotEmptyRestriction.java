/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NotEmptyRestriction  implements IRestriction {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	
	/**
	 * 
	 */
	public NotEmptyRestriction(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
}
