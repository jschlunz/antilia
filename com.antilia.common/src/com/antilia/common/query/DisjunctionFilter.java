/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DisjunctionFilter extends JunctionFilter {

	private static final long serialVersionUID = 1L;

	/**
	 * @param op
	 */
	public DisjunctionFilter() {
		super(LogicalOperator.OR);
	}
	
	public String getPropertyName() {
		return null;
	}
}
