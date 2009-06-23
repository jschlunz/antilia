/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class LogicalRestriction implements IRestriction {

	private static final long serialVersionUID = 1L;

	private final IRestriction lhs;
	private final IRestriction rhs;
	private final LogicalOperator op;
	
	/**
	 * 
	 */
	public LogicalRestriction(final IRestriction lhs,
	final IRestriction rhs,
	final LogicalOperator op) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}

	/**
	 * @return the lhs
	 */
	public IRestriction getLhs() {
		return lhs;
	}

	/**
	 * @return the rhs
	 */
	public IRestriction getRhs() {
		return rhs;
	}

	/**
	 * @return the op
	 */
	public LogicalOperator getOp() {
		return op;
	}
	

	public String getPropertyName() {
		return null;
	}
}
