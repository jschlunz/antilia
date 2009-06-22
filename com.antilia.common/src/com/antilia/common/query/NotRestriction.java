/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NotRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final IRestrictionFilter filter;
	/**
	 * 
	 */
	public NotRestriction(IRestrictionFilter filter) {
		this.filter = filter;
	}
	/**
	 * @return the filter
	 */
	public IRestrictionFilter getFilter() {
		return filter;
	}
	
	public String getPropertyName() {
		if(this.filter != null) {
			return this.filter.getPropertyName();
		}
		return null;
	}
}
