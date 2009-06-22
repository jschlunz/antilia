/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class LogicalRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final IRestrictionFilter lhs;
	private final IRestrictionFilter rhs;
	private final LogicalOperator op;
	
	/**
	 * 
	 */
	public LogicalRestriction(final IRestrictionFilter lhs,
	final IRestrictionFilter rhs,
	final LogicalOperator op) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.op = op;
	}

	/**
	 * @return the lhs
	 */
	public IRestrictionFilter getLhs() {
		return lhs;
	}

	/**
	 * @return the rhs
	 */
	public IRestrictionFilter getRhs() {
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
