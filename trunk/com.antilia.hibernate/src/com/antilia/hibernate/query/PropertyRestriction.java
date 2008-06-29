/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.PropertyExpression;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PropertyRestriction extends FilterToCriterionTransfomer  implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;
	private final String otherPropertyName;
	private final Operator op;
	
	private static class PropertyExpressionWrapper extends PropertyExpression {
		
		private static final long serialVersionUID = 1L;

		protected PropertyExpressionWrapper(String propertyName, String otherPropertyName, String op) {
			super(propertyName, otherPropertyName, op);
		}
	}
	
	/**
	 * 
	 */
	public PropertyRestriction(String propertyName, String otherPropertyName, Operator op) {
		this.propertyName = propertyName;
		this.otherPropertyName = otherPropertyName;
		this.op = op;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the otherPropertyName
	 */
	public String getOtherPropertyName() {
		return otherPropertyName;
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
		return new PropertyExpressionWrapper(propertyName, otherPropertyName, op.getValue());
	}
}
