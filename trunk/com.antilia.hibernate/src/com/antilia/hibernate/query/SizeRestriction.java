/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SizeExpression;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SizeRestriction extends FilterToCriterionTransfomer  implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;  
	private final int size;
	private final Operator op;

	private static class SizeExpressionWrapper extends SizeExpression{
	
		private static final long serialVersionUID = 1L;

		protected SizeExpressionWrapper (String propertyName, int size, String op) {
			super(propertyName, size, op);
		}
	}
	
	/**
	 * 
	 */
	public SizeRestriction(final String propertyName, int size, Operator op) {
		this.propertyName = propertyName;
		this.size = size;
		this.op = op;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}

	@Override
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	@Override
	public Criterion transform(IFilter source) {
		return new SizeExpressionWrapper(propertyName, getSize(), getOp().getValue());
	}
}
