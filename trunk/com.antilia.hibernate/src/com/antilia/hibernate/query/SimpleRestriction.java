/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SimpleRestriction extends FilterToCriterionTransfomer  implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	private final Object value;
	private boolean ignoreCase;
	private final Operator op;

	private static class SimpleExpressionWrapper extends org.hibernate.criterion.SimpleExpression {
		
		private static final long serialVersionUID = 1L;

		protected SimpleExpressionWrapper(String propertyName, Object value, String op) {
			super(propertyName, value, op);
		}
	}
	
	protected SimpleRestriction(String propertyName, Object value, Operator op) {
		this.propertyName = propertyName;
		this.value = value;
		this.op = op;
	}

	protected SimpleRestriction(String propertyName, Object value, Operator op, boolean ignoreCase) {
		this.propertyName = propertyName;
		this.value = value;
		this.ignoreCase = ignoreCase;
		this.op = op;
	}

	public SimpleRestriction ignoreCase() {
		ignoreCase = true;
		return this;
	}
	
	public String toString() {
		return propertyName + getOp() + value;
	}

	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	public Criterion transform(IFilter source) {
		return new SimpleExpressionWrapper(propertyName, value, op.getValue());
	}
}
