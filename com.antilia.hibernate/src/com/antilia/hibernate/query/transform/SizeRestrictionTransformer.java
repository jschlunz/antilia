/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.Operator;
import com.antilia.common.query.SizeRestriction;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SizeRestrictionTransformer extends FilterToCriterionTransformer {
	
	public Criterion transform(IRestriction source) {
		if(source instanceof SizeRestriction) {
			SizeRestriction sizeRestriction = (SizeRestriction)source;
			if(sizeRestriction.getOp().equals(Operator.EQUAL)) {
				return Restrictions.sizeEq(sizeRestriction.getPropertyName(), sizeRestriction.getSize());
			} else if(sizeRestriction.getOp().equals(Operator.NOTEQUAL)) {
				return Restrictions.sizeNe(sizeRestriction.getPropertyName(), sizeRestriction.getSize());
			} else if(sizeRestriction.getOp().equals(Operator.LESS_THAN)) {
				return Restrictions.sizeLt(sizeRestriction.getPropertyName(), sizeRestriction.getSize());
			} else if(sizeRestriction.getOp().equals(Operator.LESS_EQUAL_THAN)) {
				return Restrictions.sizeLe(sizeRestriction.getPropertyName(), sizeRestriction.getSize());
			} else if(sizeRestriction.getOp().equals(Operator.GREATER_THAN)) {
				return Restrictions.sizeGt(sizeRestriction.getPropertyName(), sizeRestriction.getSize());
			} else if(sizeRestriction.getOp().equals(Operator.GREATER_EQUAL_THAN)) {
				return Restrictions.sizeGe(sizeRestriction.getPropertyName(), sizeRestriction.getSize());
			}
		}
		return null;
	}
}
