/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * Represents a conjunction of filters.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ConjunctionRestriction extends JunctionRestriction {

	private static final long serialVersionUID = 1L;

	/**
	 * @param op
	 */
	public ConjunctionRestriction() {
		super(LogicalOperator.AND);
	}
}
