/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * Represents a disjunction of filters.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DisjunctionRestriction extends JunctionRestriction {

	private static final long serialVersionUID = 1L;

	/**
	 * @param op
	 */
	public DisjunctionRestriction() {
		super(LogicalOperator.OR);
	}
}
