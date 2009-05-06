/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class LogicalRestriction extends FilterToCriterionTransfomer implements IRestrictionFilter {

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
	
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	public Criterion transform(IFilter source) {
		if(getOp().equals(LogicalOperator.OR)) {
			return Restrictions.or(lhs.getTransformer().transform(lhs), rhs.getTransformer().transform(rhs));
		} else if(getOp().equals(LogicalOperator.AND)) {
			return Restrictions.and(lhs.getTransformer().transform(lhs), rhs.getTransformer().transform(rhs));
		}
		return null;		
	}

	public String getPropertyName() {
		return null;
	}
}
