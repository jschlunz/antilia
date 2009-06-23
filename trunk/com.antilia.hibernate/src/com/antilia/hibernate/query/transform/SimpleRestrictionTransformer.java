/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.SimpleRestriction;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SimpleRestrictionTransformer extends FilterToCriterionTransformer  {


	private static class SimpleExpressionWrapper extends org.hibernate.criterion.SimpleExpression {
		
		private static final long serialVersionUID = 1L;

		protected SimpleExpressionWrapper(String propertyName, Object value, String op) {
			super(propertyName, value, op);
		}
	}
	
	
	public Criterion transform(IRestriction source) {
		if(source instanceof SimpleRestriction) {
			SimpleRestriction simpleRestriction = (SimpleRestriction)source;
			return new SimpleExpressionWrapper(simpleRestriction.getPropertyName(), simpleRestriction.getValue(), simpleRestriction.getOp().getValue());
		}
		//
		return null;
	}
}
