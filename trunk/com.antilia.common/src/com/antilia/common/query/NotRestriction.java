/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NotRestriction implements IRestriction {

	private static final long serialVersionUID = 1L;

	private final IRestriction filter;
	/**
	 * 
	 */
	public NotRestriction(IRestriction filter) {
		this.filter = filter;
	}
	/**
	 * @return the filter
	 */
	public IRestriction getFilter() {
		return filter;
	}
	
	public String getPropertyName() {
		if(this.filter != null) {
			return this.filter.getPropertyName();
		}
		return null;
	}
}
