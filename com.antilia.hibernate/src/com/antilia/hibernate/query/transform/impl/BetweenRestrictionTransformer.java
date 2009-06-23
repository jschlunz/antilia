/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.BetweenRestriction;
import com.antilia.common.query.IRestriction;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class BetweenRestrictionTransformer extends FilterToCriterionTransformer  {

	private static final long serialVersionUID = 1L;
	
	
	protected BetweenRestrictionTransformer() {
	}
	
	public Criterion transform(IRestriction source) {
		if(source instanceof BetweenRestriction) {
			BetweenRestriction betweenRestriction = (BetweenRestriction)source;
			return Restrictions.between(betweenRestriction.getPropertyName(), betweenRestriction.getLo(), betweenRestriction.getHi());
		}
		return null;
	}
}
