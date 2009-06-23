/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class EmptyRestrictionTransformer extends FilterToCriterionTransformer {
	
	public Criterion transform(IRestriction source) {
		return Restrictions.isEmpty(source.getPropertyName());
		
	}
}
