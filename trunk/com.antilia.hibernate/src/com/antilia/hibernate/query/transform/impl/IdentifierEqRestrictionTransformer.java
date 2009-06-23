/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.IdentifierEqRestriction;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class IdentifierEqRestrictionTransformer extends FilterToCriterionTransformer {
	
	
	public Criterion transform(IRestriction source) {
		if(source instanceof IdentifierEqRestriction )
			return Restrictions.idEq(((IdentifierEqRestriction)source).getValue());
		return null;
	}
	
}
