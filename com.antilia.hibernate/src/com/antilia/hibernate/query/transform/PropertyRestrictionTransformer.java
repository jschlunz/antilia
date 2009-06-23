/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.PropertyExpression;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.PropertyRestriction;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PropertyRestrictionTransformer extends FilterToCriterionTransformer {

	
	private static class PropertyExpressionWrapper extends PropertyExpression {
		
		private static final long serialVersionUID = 1L;

		protected PropertyExpressionWrapper(String propertyName, String otherPropertyName, String op) {
			super(propertyName, otherPropertyName, op);
		}
	}


	public Criterion transform(IRestriction source) {
		if(source instanceof PropertyRestriction) {
			PropertyRestriction propertyRestriction = (PropertyRestriction)source;
			return new PropertyExpressionWrapper(propertyRestriction.getPropertyName(), propertyRestriction.getOtherPropertyName(), propertyRestriction.getOp().getValue());
		}
		return null;
	}
}
